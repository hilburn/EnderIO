package crazypants.enderio.machine.crusher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import crazypants.enderio.Log;
import crazypants.enderio.ModObject;
import crazypants.enderio.config.Config;
import crazypants.enderio.machine.MachineRecipeInput;
import crazypants.enderio.machine.MachineRecipeRegistry;
import crazypants.enderio.machine.recipe.IRecipe;
import crazypants.enderio.machine.recipe.Recipe;
import crazypants.enderio.machine.recipe.RecipeConfig;
import crazypants.enderio.machine.recipe.RecipeConfigParser;
import crazypants.enderio.machine.recipe.RecipeInput;
import crazypants.enderio.machine.recipe.RecipeOutput;
import crazypants.enderio.machine.recipe.RecipeBonusType;
import crazypants.util.Util;

public class CrusherRecipeManager {

  public static final int ORE_ENERGY_COST = 400;

  public static final int INGOT_ENERGY_COST = 240;

  private static final String CORE_FILE_NAME = "SAGMillRecipes_Core.xml";
  private static final String CUSTOM_FILE_NAME = "SAGMillRecipes_User.xml";

  static final CrusherRecipeManager instance = new CrusherRecipeManager();

  public static CrusherRecipeManager getInstance() {
    return instance;
  }

  private final List<Recipe> recipes = new ArrayList<Recipe>();
  
  private final List<RecipeInput> ballExcludes = new ArrayList<RecipeInput>();

  private final List<GrindingBall> balls = new ArrayList<GrindingBall>();
  
  private Set<ItemStack> excludedStacks = new HashSet<ItemStack>();

  public CrusherRecipeManager() {   
  }

  public boolean isValidSagBall(ItemStack stack) {
    return getGrindballFromStack(stack) != null;
  }
  
  public boolean isExcludedFromBallBonus(MachineRecipeInput[] inputs) {
    if(inputs == null || inputs.length < 1) {
      return true;
    }
    for(MachineRecipeInput input : inputs) {
      if(input.item != null) {
        if(isExcludedStack(input.item)){
          return true;
        }
        int id = OreDictionary.getOreID(input.item);
        if(id >= 0) {
          String name = OreDictionary.getOreName(id);
          if(name.startsWith("ingot")) {            
            addExcludedStack(input.item);
            return true;
          }
        }
      }
    }
    for(RecipeInput input : ballExcludes) {
      if(input != null && input.isInput(inputs[0].item)) {
        addExcludedStack(inputs[0].item);
        return true;
      }
    }    
    
    return false;
  }

  private void addExcludedStack(ItemStack item) {
    item = item.copy();
    item.stackSize = 1;
    excludedStacks.add(item);    
  }

  private boolean isExcludedStack(ItemStack item) {
    item = item.copy();
    item.stackSize = 1;
    return excludedStacks.contains(item);
  }

  public IGrindingMultiplier getGrindballFromStack(ItemStack stack) {
    if(stack == null) {
      return null;
    }
    for(GrindingBall ball : balls) {
      if(ball.isInput(stack)) {
        return ball;
      }
    }
    return null;
  }

  public boolean isValidInput(MachineRecipeInput input) {
    if(input.slotNumber == 1) {
      return isValidSagBall(input.item);
    }
    return getRecipeForInput(input.item) != null;
  }

  public void loadRecipesFromConfig() {
    GrindingBallTagHandler th = new GrindingBallTagHandler();
    RecipeConfig config = RecipeConfig.loadRecipeConfig(CORE_FILE_NAME, CUSTOM_FILE_NAME, th);
    balls.addAll(th.balls.values());
    ballExcludes.addAll(th.excludes);
    Log.info("Loaded " + balls.size() + " grinding balls from SAG Mill config.");
    Log.info("Excluding " + ballExcludes.size() + " recipes from grinding balls bonus.");
    if(config != null) {
      processConfig(config);
    } else {
      Log.error("Could not load recipes for SAG Mill.");
    }
    MachineRecipeRegistry.instance.registerRecipe(ModObject.blockSagMill.unlocalisedName, new CrusherMachineRecipe());
  }

  public void addCustomRecipes(String xmlDef) {
    GrindingBallTagHandler th = new GrindingBallTagHandler();
    RecipeConfig config;
    try {
      config = RecipeConfigParser.parse(xmlDef, th);
    } catch (Exception e) {
      Log.error("Error parsing custom xml");
      return;
    }

    balls.addAll(th.balls.values());
    ballExcludes.addAll(th.excludes);
    if(config == null) {
      Log.error("Could not process custom XML");
      return;
    }
    processConfig(config);
  }

  public IRecipe getRecipeForInput(ItemStack input) {
    if(input == null) {
      return null;
    }
    for (Recipe recipe : recipes) {
      if(recipe.isInputForRecipe(new MachineRecipeInput(0, input))) {
        return recipe;
      }
    }
    return null;
  }

  private void processConfig(RecipeConfig config) {
    if(config.isDumpItemRegistery()) {
      Util.dumpModObjects(new File(Config.configDirectory, "modObjectsRegistery.txt"));
    }
    if(config.isDumpOreDictionary()) {
      Util.dumpOreNames(new File(Config.configDirectory, "oreDictionaryRegistery.txt"));
    }

    List<Recipe> newRecipes = config.getRecipes(true);
    Log.info("Found " + newRecipes.size() + " valid SAG Mill recipes in config.");
    for (Recipe rec : newRecipes) {
      addRecipe(rec);
    }
    Log.info("Finished processing SAG Mill recipes. " + recipes.size() + " recipes avaliable.");
  }

  public void addRecipe(ItemStack input, int energyCost, ItemStack output) {
    addRecipe(input, energyCost, new RecipeOutput(output, 1));
  }

  public void addRecipe(ItemStack input, int energyCost, RecipeOutput... output) {
    if(input == null || output == null) {
      return;
    }
    addRecipe(new Recipe(new RecipeInput(input, false), energyCost, RecipeBonusType.MULTIPLY_OUTPUT, output));
  }

  public void addRecipe(Recipe recipe) {
    if(recipe == null || !recipe.isValid()) {
      Log.debug("Could not add invalid recipe: " + recipe);
      return;
    }
    IRecipe rec = getRecipeForInput(getInput(recipe));
    if(rec != null) {
      Log.warn("Not adding supplied recipe as a recipe already exists for the input: " + getInput(recipe));
      return;
    }
    recipes.add(recipe);
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public static ItemStack getInput(IRecipe recipe) {
    if(recipe == null || recipe.getInputs() == null || recipe.getInputs().length == 0) {
      return null;
    }
    return recipe.getInputs()[0].getInput();
  }

  public List<GrindingBall> getBalls() {
    return balls;
  }

}

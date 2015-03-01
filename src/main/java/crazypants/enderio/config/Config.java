package crazypants.enderio.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import tterrag.core.common.event.ConfigFileChangedEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.EnderIO;
import crazypants.enderio.Log;
import crazypants.enderio.machine.weather.TileWeatherObelisk.WeatherTask;
import crazypants.vecmath.VecmathUtil;

public final class Config {

  public static class Section {
    public final String name;
    public final String lang;

    public Section(String name, String lang) {
      this.name = name;
      this.lang = lang;
      register();
    }

    private void register() {
      sections.add(this);
    }

    public String lc() {
      return name.toLowerCase();
    }
  }

  public static final List<Section> sections;

  static {
    sections = new ArrayList<Section>();
  }

  public static Configuration config;

  public static final Section sectionPower = new Section("Power Settings", "power");
  public static final Section sectionRecipe = new Section("Recipe Settings", "recipe");
  public static final Section sectionItems = new Section("Item Enabling", "item");
  public static final Section sectionEfficiency = new Section("Efficiency Settings", "efficiency");
  public static final Section sectionPersonal = new Section("Personal Settings", "personal");
  public static final Section sectionAnchor = new Section("Anchor Settings", "anchor");
  public static final Section sectionStaff = new Section("Staff Settings", "staff");
  public static final Section sectionDarkSteel = new Section("Dark Steel", "darksteel");
  public static final Section sectionFarm = new Section("Farm Settings", "farm");
  public static final Section sectionAesthetic = new Section("Aesthetic Settings", "aesthetic");
  public static final Section sectionAdvanced = new Section("Advanced Settings", "advanced");
  public static final Section sectionMagnet = new Section("Magnet Settings", "magnet");
  public static final Section sectionFluid = new Section("Fluid Settings", "fluid");
  public static final Section sectionSpawner = new Section("PoweredSpawner Settings", "spawner");
  public static final Section sectionKiller = new Section("Killer Joe Settings", "killerjoe");
  public static final Section sectionSoulBinder = new Section("Soul Binder Settings", "soulBinder");
  public static final Section sectionAttractor = new Section("Mob Attractor Settings", "attractor");
  public static final Section sectionLootConfig = new Section("Loot Config", "lootconfig");
  public static final Section sectionMobConfig = new Section("Mob Config", "mobconfig");
  public static final Section sectionRailConfig = new Section("Rail", "railconfig");
  public static final Section sectionEnchantments = new Section("Enchantments", "enchantments");
  public static final Section sectionWeather = new Section("Weather", "weather");
  public static final Section sectionMisc = new Section("Misc", "misc");

  public static final double DEFAULT_CONDUIT_SCALE = 0.6;

  public static boolean reinforcedObsidianEnabled = true;
  public static boolean reinforcedObsidianUseDarkSteelBlocks = false;

  public static boolean useAlternateBinderRecipe = false;

  public static boolean useAlternateTesseractModel = false;

  public static boolean photovoltaicCellEnabled = true;
  
  public static boolean reservoirEnabled = true;

  public static double conduitScale = DEFAULT_CONDUIT_SCALE;

  public static int numConduitsPerRecipe = 8;

  public static boolean transceiverEnabled = true;
  public static double transceiverEnergyLoss = 0.1;
  public static int transceiverUpkeepCostRF = 10;
  public static int transceiverBucketTransmissionCostRF = 100;
  public static int transceiverMaxIoRF = 20480;
  public static boolean transceiverUseEasyRecipe = false;

  public static File configDirectory;

  public static boolean useHardRecipes = false;

  public static boolean useSteelInChassi = false;

  public static boolean detailedPowerTrackingEnabled = false;

  public static boolean useSneakMouseWheelYetaWrench = true;
  public static boolean useSneakRightClickYetaWrench = false;

  public static boolean itemConduitUsePhyscialDistance = false;

  public static int enderFluidConduitExtractRate = 200;
  public static int enderFluidConduitMaxIoRate = 800;
  public static int advancedFluidConduitExtractRate = 100;
  public static int advancedFluidConduitMaxIoRate = 400;
  public static int fluidConduitExtractRate = 50;
  public static int fluidConduitMaxIoRate = 200;

  public static int gasConduitExtractRate = 200;
  public static int gasConduitMaxIoRate = 800;

  public static boolean updateLightingWhenHidingFacades = false;

  public static boolean travelAnchorEnabled = true;
  public static int travelAnchorMaxDistance = 48;

  public static int travelStaffMaxDistance = 128;
  public static float travelStaffPowerPerBlockRF = 250;

  public static int travelStaffMaxBlinkDistance = 16;
  public static int travelStaffBlinkPauseTicks = 10;

  public static boolean travelStaffEnabled = true;
  public static boolean travelStaffBlinkEnabled = true;
  public static boolean travelStaffBlinkThroughSolidBlocksEnabled = true;
  public static boolean travelStaffBlinkThroughClearBlocksEnabled = true;
  public static boolean travelStaffBlinkThroughUnbreakableBlocksEnabled = false;
  public static String[] travelStaffBlinkBlackList = new String[] {
      "minecraft:bedrock",
      "Thaumcraft:blockWarded"
  };
  public static float travelAnchorZoomScale = 0.2f;

  public static int enderIoRange = 8;
  public static boolean enderIoMeAccessEnabled = true;

  public static double[] darkSteelPowerDamgeAbsorptionRatios = {0.5, 0.6, 0.75, 0.95};
  public static int darkSteelPowerStorageBase = 100000;
  public static int darkSteelPowerStorageLevelOne = 150000;
  public static int darkSteelPowerStorageLevelTwo = 250000;
  public static int darkSteelPowerStorageLevelThree = 1000000;

  public static float darkSteelSpeedOneWalkModifier = 0.1f;
  public static float darkSteelSpeedTwoWalkMultiplier = 0.2f;
  public static float darkSteelSpeedThreeWalkMultiplier = 0.3f;

  public static float darkSteelSpeedOneSprintModifier = 0.1f;
  public static float darkSteelSpeedTwoSprintMultiplier = 0.3f;
  public static float darkSteelSpeedThreeSprintMultiplier = 0.5f;
  
  public static int darkSteelSpeedOneCost = 10;
  public static int darkSteelSpeedTwoCost = 15;
  public static int darkSteelSpeedThreeCost = 20;

  
  public static double darkSteelBootsJumpModifier = 1.5;  
  public static int darkSteelJumpOneCost = 10;
  public static int darkSteelJumpTwoCost = 15;
  public static int darkSteelJumpThreeCost = 20;  

  public static boolean slotZeroPlacesEight = true;

  public static int darkSteelWalkPowerCost = darkSteelPowerStorageLevelTwo / 3000;
  public static int darkSteelSprintPowerCost = darkSteelWalkPowerCost * 4;
  public static boolean darkSteelDrainPowerFromInventory = false;
  public static int darkSteelBootsJumpPowerCost = 150;
  public static int darkSteelFallDistanceCost = 75;

  public static float darkSteelSwordWitherSkullChance = 0.05f;
  public static float darkSteelSwordWitherSkullLootingModifier = 0.05f;
  public static float darkSteelSwordSkullChance = 0.1f;
  public static float darkSteelSwordSkullLootingModifier = 0.075f;
  public static float vanillaSwordSkullLootingModifier = 0.05f;
  public static float vanillaSwordSkullChance = 0.05f;
  public static float ticCleaverSkullDropChance = 0.1f;
  public static float ticBeheadingSkullModifier = 0.075f;
  public static float fakePlayerSkullChance = 0.5f;

  public static int darkSteelSwordPowerUsePerHit = 750;
  public static double darkSteelSwordEnderPearlDropChance = 1;
  public static double darkSteelSwordEnderPearlDropChancePerLooting = 0.5;

  public static int darkSteelPickEffeciencyObsidian = 50;
  public static int darkSteelPickPowerUseObsidian = 10000;
  public static float darkSteelPickApplyObsidianEffeciencyAtHardess = 40;
  public static int darkSteelPickPowerUsePerDamagePoint = 750;
  public static float darkSteelPickEffeciencyBoostWhenPowered = 2;
  public static boolean darkSteelPickMinesTiCArdite = true;

  public static int darkSteelAxePowerUsePerDamagePoint = 750;
  public static int darkSteelAxePowerUsePerDamagePointMultiHarvest = 1500;
  public static float darkSteelAxeEffeciencyBoostWhenPowered = 2;
  public static float darkSteelAxeSpeedPenaltyMultiHarvest = 4;

  public static int darkSteelUpgradeVibrantCost = 10;
  public static int darkSteelUpgradePowerOneCost = 10;
  public static int darkSteelUpgradePowerTwoCost = 15;
  public static int darkSteelUpgradePowerThreeCost = 20;

  public static int darkSteelGliderCost = 10;
  public static double darkSteelGliderHorizontalSpeed = 0.03;
  public static double darkSteelGliderVerticalSpeed = -0.05;
  public static double darkSteelGliderVerticalSpeedSprinting = -0.15;
  
  public static int darkSteelGogglesOfRevealingCost = 10;

  public static int darkSteelApiaristArmorCost = 10;

  public static int darkSteelSwimCost = 10;

  public static int darkSteelNightVisionCost = 10;

  public static int darkSteelSoundLocatorCost = 10;
  public static int darkSteelSoundLocatorRange = 40;
  public static int darkSteelSoundLocatorLifespan = 40;
  
  public static int darkSteelTravelCost = 30;  
  public static int darkSteelSpoonCost = 10;
  
  public static int darkSteelSolarOneGen = 10;
  public static int darkSteelSolarOneCost = 15;
  public static int darkSteelSolarTwoGen = 40;
  public static int darkSteelSolarTwoCost = 30;
  public static boolean darkSteelSolarChargeOthers = true;

  public static float darkSteelAnvilDamageChance = 0.024f;
  
  public static float darkSteelLadderSpeedBoost = 0.06f;
  
  public static int hootchPowerPerCycleRF = 60;
  public static int hootchPowerTotalBurnTime = 6000;
  public static int rocketFuelPowerPerCycleRF = 160;
  public static int rocketFuelPowerTotalBurnTime = 7000;
  public static int fireWaterPowerPerCycleRF = 80;
  public static int fireWaterPowerTotalBurnTime = 15000;
  public static int vatPowerUserPerTickRF = 20;

  public static int maxPhotovoltaicOutputRF = 10;
  public static int maxPhotovoltaicAdvancedOutputRF = 40;

  public static int zombieGeneratorRfPerTick = 80;
  public static int zombieGeneratorTicksPerBucketFuel = 10000;

  public static int stirlingGeneratorBaseRfPerTick = 20;

  public static boolean combustionGeneratorUseOpaqueModel = true;

  public static boolean addFuelTooltipsToAllFluidContainers = true;
  public static boolean addFurnaceFuelTootip = true;
  public static boolean addDurabilityTootip = true;
  public static boolean addOreDictionaryTooltips = false;
  public static boolean addRegisterdNameTooltip = false;

  public static int farmContinuousEnergyUseRF = 40;
  public static int farmActionEnergyUseRF = 500;
  public static int farmAxeActionEnergyUseRF = 1000;

  public static int farmDefaultSize = 3;
  public static boolean farmAxeDamageOnLeafBreak = false;
  public static float farmToolTakeDamageChance = 1;
  public static boolean disableFarmNotification = false;
  public static boolean farmEssenceBerriesEnabled = true;
  public static boolean farmManaBeansEnabled = false;
  public static boolean farmHarvestJungleWhenCocoa = false;
  public static String[] hoeStrings = new String[] {
      "minecraft:wooden_hoe", "minecraft:stone_hoe", "minecraft:iron_hoe", "minecraft:diamond_hoe", "minecraft:golden_hoe",
      "MekanismTools:ObsidianHoe", "MekanismTools:LapisLazuliHoe", "MekanismTools:OsmiumHoe", "MekanismTools:BronzeHoe", "MekanismTools:GlowstoneHoe",
      "MekanismTools:SteelHoe",
      "Steamcraft:hoeBrass", "Steamcraft:hoeGildedGold",
      "Railcraft:tool.steel.hoe",
      "TConstruct:mattock",
      "appliedenergistics2:item.ToolCertusQuartzHoe", "appliedenergistics2:item.ToolNetherQuartzHoe",
      "ProjRed|Exploration:projectred.exploration.hoeruby", "ProjRed|Exploration:projectred.exploration.hoesapphire",
      "ProjRed|Exploration:projectred.exploration.hoeperidot",
      "magicalcrops:magicalcrops_AccioHoe", "magicalcrops:magicalcrops_CrucioHoe", "magicalcrops:magicalcrops_ImperioHoe",
      // disabled as it is currently not unbreaking as advertised "magicalcrops:magicalcrops_ZivicioHoe",
      "BiomesOPlenty:hoeAmethyst", "BiomesOPlenty:hoeMud",
      "Eln:Eln.Copper Hoe",
      "Thaumcraft:ItemHoeThaumium", "Thaumcraft:ItemHoeElemental", "Thaumcraft:ItemHoeVoid",
      "ThermalExpansion:tool.hoeInvar"
  };
  public static List<ItemStack> farmHoes = new ArrayList<ItemStack>();

  public static int magnetPowerUsePerSecondRF = 1;
  public static int magnetPowerCapacityRF = 100000;
  public static int magnetRange = 5;

  public static boolean useCombustionGenModel = false;

  public static int crafterRfPerCraft = 2500;

  public static int capacitorBankMaxIoRF = 5000;
  public static int capacitorBankMaxStorageRF = 5000000;
  
  public static int capacitorBankTierOneMaxIoRF = 1000;
  public static int capacitorBankTierOneMaxStorageRF = 1000000;

  public static int capacitorBankTierTwoMaxIoRF = 5000;
  public static int capacitorBankTierTwoMaxStorageRF = 5000000;

  public static int capacitorBankTierThreeMaxIoRF = 25000;
  public static int capacitorBankTierThreeMaxStorageRF = 25000000;

  public static int poweredSpawnerMinDelayTicks = 200;
  public static int poweredSpawnerMaxDelayTicks = 800;
  public static int poweredSpawnerLevelOnePowerPerTickRF = 160;
  public static int poweredSpawnerLevelTwoPowerPerTickRF = 500;
  public static int poweredSpawnerLevelThreePowerPerTickRF = 1500;
  public static int poweredSpawnerMaxPlayerDistance = 0;
  public static int poweredSpawnerDespawnTimeSeconds = 120;
  public static boolean poweredSpawnerUseVanillaSpawChecks = false;
  public static double brokenSpawnerDropChance = 1;
  public static String[] brokenSpawnerToolBlacklist = new String[] {
    "RotaryCraft:rotarycraft_item_bedpick"
  };
  public static int powerSpawnerAddSpawnerCost = 30;

  public static int painterEnergyPerTaskRF = 2000;

  public static int vacuumChestRange = 6;

  public static boolean useModMetals = true;

  public static int wirelessChargerRange = 24;

  public static long nutrientFoodBoostDelay = 400;

  public static int enchanterBaseLevelCost = 4;

  public static boolean machineSoundsEnabled = true;

  public static float machineSoundVolume = 1.0f;

  public static int killerJoeNutrientUsePerAttackMb = 5;
  public static double killerJoeAttackHeight = 2;
  public static double killerJoeAttackWidth = 2;
  public static double killerJoeAttackLength = 4;  
  public static double killerJoeHooverXpWidth = 5;
  public static double killerJoeHooverXpLength = 10;
  public static int killerJoeMaxXpLevel = Integer.MAX_VALUE;
  public static boolean killerJoeMustSee = false;

  public static boolean allowTileEntitiesAsPaintSource = true;

  public static String isGasConduitEnabled = "auto";
  public static boolean enableMEConduits = true;

  public static String[] soulVesselBlackList = new String[0];
  public static boolean soulVesselCapturesBosses = false;

  public static int soulBinderLevelOnePowerPerTickRF = 500;
  public static int soulBinderLevelTwoPowerPerTickRF = 1000;
  public static int soulBinderLevelThreePowerPerTickRF = 2000;
  public static int soulBinderBrokenSpawnerRF = 2500000;
  public static int soulBinderBrokenSpawnerLevels = 15;
  public static int soulBinderReanimationRF = 100000;
  public static int soulBinderReanimationLevels = 10;
  public static int soulBinderEnderCystalRF = 100000;
  public static int soulBinderEnderCystalLevels = 10;
  public static int soulBinderAttractorCystalRF = 100000;
  public static int soulBinderAttractorCystalLevels = 10;
  public static int soulBinderEnderRailRF = 100000;
  public static int soulBinderEnderRailLevels = 10;
  public static int soulBinderMaxXpLevel = 40;

  public static boolean powerConduitCanDifferentTiersConnect = false;
  public static int powerConduitTierOneRF = 640;
  public static int powerConduitTierTwoRF = 5120;
  public static int powerConduitTierThreeRF = 20480;
  public static boolean powerConduitOutputMJ = true;

  public static int sliceAndSpliceLevelOnePowerPerTickRF = 80;
  public static int sliceAndSpliceLevelTwoPowerPerTickRF = 160;
  public static int sliceAndSpliceLevelThreePowerPerTickRF = 320;

  public static boolean soulBinderRequiresEndermanSkull = true;

  public static int attractorRangeLevelOne = 16;
  public static int attractorPowerPerTickLevelOne = 20;
  public static int attractorRangeLevelTwo = 32;
  public static int attractorPowerPerTickLevelTwo = 40;
  public static int attractorRangeLevelThree = 64;
  public static int attractorPowerPerTickLevelThree = 80;
   
  public static int spawnGuardRangeLevelOne = 64;
  public static int spawnGuardPowerPerTickLevelOne = 80;
  public static int spawnGuardRangeLevelTwo = 96;
  public static int spawnGuardPowerPerTickLevelTwo = 300;
  public static int spawnGuardRangeLevelThree = 160;
  public static int spawnGuardPowerPerTickLevelThree = 800;
  public static boolean spawnGuardStopAllSlimesDebug = false;
  public static boolean spawnGuardStopAllSquidSpawning = false;
  
  public static String weatherObeliskClearItem = "minecraft:cake";
  public static String weatherObeliskRainItem = "minecraft:water_bucket";
  public static String weatherObeliskThunderItem = "minecraft:lava_bucket";
  public static int weatherObeliskClearPower = 1000000;
  public static int weatherObeliskRainPower = 250000;
  public static int weatherObeliskThunderPower = 500000;

  //Loot Defaults
  public static boolean lootDarkSteel = true;
  public static boolean lootItemConduitProbe = true;
  public static boolean lootQuartz = true;
  public static boolean lootNetherWart = true;
  public static boolean lootEnderPearl = true;
  public static boolean lootElectricSteel = true;
  public static boolean lootRedstoneAlloy = true;
  public static boolean lootPhasedIron = true;
  public static boolean lootPhasedGold = true;
  public static boolean lootTravelStaff = true;
  public static boolean lootTheEnder = true;
  public static boolean lootDarkSteelBoots = true;
  
  public static boolean dumpMobNames = false;

  public static boolean enderRailEnabled = true;
  public static int enderRailPowerRequireCrossDimensions = 10000; 
  public static int enderRailPowerRequiredPerBlock = 10;
  public static boolean enderRailCapSameDimensionPowerAtCrossDimensionCost = true;
  public static int enderRailTicksBeforeForceSpawningLinkedCarts = 60;
  public static boolean enderRailTeleportPlayers = false;

  public static int xpObeliskMaxXpLevel = Integer.MAX_VALUE;
  public static String xpJuiceName = "xpjuice";
  
  public static boolean clearGlassSameTexture = false;
  public static boolean clearGlassConnectToFusedQuartz = false;

  public static int enchantmentSoulBoundId = -1;
  public static int enchantmentSoulBoundWeight = 1;
  public static boolean enchantmentSoulBoundEnabled = true;

  public static boolean replaceWitherSkeletons = true;


  public static void load(FMLPreInitializationEvent event) {

    FMLCommonHandler.instance().bus().register(new Config());
    configDirectory = new File(event.getModConfigurationDirectory(), EnderIO.MODID.toLowerCase());
    if(!configDirectory.exists()) {
      configDirectory.mkdir();
    }

    File configFile = new File(configDirectory, "EnderIO.cfg");
    config = new Configuration(configFile);
    syncConfig(false);
  }

  public static void syncConfig(boolean load) {
    try {
      if (load) {
        config.load();
      }
      Config.processConfig(config);
    } catch (Exception e) {
      Log.error("EnderIO has a problem loading it's configuration");
      e.printStackTrace();
    } finally {
      if(config.hasChanged()) {
        config.save();
      }
    }
  }

  @SubscribeEvent
  public void onConfigChanged(OnConfigChangedEvent event) {
    if(event.modID.equals(EnderIO.MODID)) {
      Log.info("Updating config...");
      syncConfig(false);
      postInit();
    }
  }
  
  @SubscribeEvent
  @Method(modid = "ttCore")
  public void onConfigFileChanged(ConfigFileChangedEvent event) {
    if (event.modID.equals(EnderIO.MODID)) {
      Log.info("Updating config...");
      syncConfig(true);
      event.setSuccessful();
      postInit();
    }
  }

  public static void processConfig(Configuration config) {

    capacitorBankMaxIoRF = config.get(sectionPower.name, "capacitorBankMaxIoRF", capacitorBankMaxIoRF, "The maximum IO for a single capacitor in RF/t")
        .getInt(capacitorBankMaxIoRF);
    capacitorBankMaxStorageRF = config.get(sectionPower.name, "capacitorBankMaxStorageRF", capacitorBankMaxStorageRF,
        "The maximum storage for a single capacitor in RF")
        .getInt(capacitorBankMaxStorageRF);

    capacitorBankTierOneMaxIoRF = config.get(sectionPower.name, "capacitorBankTierOneMaxIoRF", capacitorBankTierOneMaxIoRF,
        "The maximum IO for a single tier one capacitor in RF/t")
        .getInt(capacitorBankTierOneMaxIoRF);
    capacitorBankTierOneMaxStorageRF = config.get(sectionPower.name, "capacitorBankTierOneMaxStorageRF", capacitorBankTierOneMaxStorageRF,
        "The maximum storage for a single tier one capacitor in RF")
        .getInt(capacitorBankTierOneMaxStorageRF);

    capacitorBankTierTwoMaxIoRF = config.get(sectionPower.name, "capacitorBankTierTwoMaxIoRF", capacitorBankTierTwoMaxIoRF,
        "The maximum IO for a single tier two capacitor in RF/t")
        .getInt(capacitorBankTierTwoMaxIoRF);
    capacitorBankTierTwoMaxStorageRF = config.get(sectionPower.name, "capacitorBankTierTwoMaxStorageRF", capacitorBankTierTwoMaxStorageRF,
        "The maximum storage for a single tier two capacitor in RF")
        .getInt(capacitorBankTierTwoMaxStorageRF);

    capacitorBankTierThreeMaxIoRF = config.get(sectionPower.name, "capacitorBankTierThreeMaxIoRF", capacitorBankTierThreeMaxIoRF,
        "The maximum IO for a single tier three capacitor in RF/t")
        .getInt(capacitorBankTierThreeMaxIoRF);
    capacitorBankTierThreeMaxStorageRF = config.get(sectionPower.name, "capacitorBankTierThreeMaxStorageRF", capacitorBankTierThreeMaxStorageRF,
        "The maximum storage for a single tier three capacitor in RF")
        .getInt(capacitorBankTierThreeMaxStorageRF);

    powerConduitTierOneRF = config.get(sectionPower.name, "powerConduitTierOneRF", powerConduitTierOneRF, "The maximum IO for the tier 1 power conduit")
        .getInt(powerConduitTierOneRF);
    powerConduitTierTwoRF = config.get(sectionPower.name, "powerConduitTierTwoRF", powerConduitTierTwoRF, "The maximum IO for the tier 2 power conduit")
        .getInt(powerConduitTierTwoRF);
    powerConduitTierThreeRF = config.get(sectionPower.name, "powerConduitTierThreeRF", powerConduitTierThreeRF, "The maximum IO for the tier 3 power conduit")
        .getInt(powerConduitTierThreeRF);
    powerConduitCanDifferentTiersConnect = config
        .getBoolean("powerConduitCanDifferentTiersConnect", sectionPower.name, powerConduitCanDifferentTiersConnect,
            "If set to false power conduits of different tiers cannot be connected. in this case a block such as a cap. bank is needed to bridge different tiered networks");
    powerConduitOutputMJ = config.getBoolean("powerConduitOutputMJ", sectionPower.name, powerConduitOutputMJ, "When set to true power conduits will output MJ if RF is not supported");
    
    
    painterEnergyPerTaskRF = config.get(sectionPower.name, "painterEnergyPerTaskRF", painterEnergyPerTaskRF,
        "The total amount of RF required to paint one block")
        .getInt(painterEnergyPerTaskRF);

    useHardRecipes = config.get(sectionRecipe.name, "useHardRecipes", useHardRecipes, "When enabled machines cost significantly more.")
        .getBoolean(useHardRecipes);
    soulBinderRequiresEndermanSkull = config.getBoolean("soulBinderRequiresEndermanSkull", sectionRecipe.name, soulBinderRequiresEndermanSkull, 
        "When true the Soul Binder requires an Enderman Skull to craft");
    allowTileEntitiesAsPaintSource = config.get(sectionRecipe.name, "allowTileEntitiesAsPaintSource", allowTileEntitiesAsPaintSource,
        "When enabled blocks with tile entities (e.g. machines) can be used as paint targets.")
        .getBoolean(allowTileEntitiesAsPaintSource);
    useSteelInChassi = config.get(sectionRecipe.name, "useSteelInChassi", useSteelInChassi, "When enabled machine chassis will require steel instead of iron.")
        .getBoolean(useSteelInChassi);
    numConduitsPerRecipe = config.get(sectionRecipe.name, "numConduitsPerRecipe", numConduitsPerRecipe,
        "The number of conduits crafted per recipe.").getInt(numConduitsPerRecipe);
    transceiverUseEasyRecipe= config.get(sectionRecipe.name, "transceiverUseEasyRecipe", transceiverUseEasyRecipe, "When enabled the dim trans. will use a cheaper recipe")
        .getBoolean(useHardRecipes);

    enchanterBaseLevelCost = config.get(sectionRecipe.name, "enchanterBaseLevelCost", enchanterBaseLevelCost,
        "Base level cost added to all recipes in the enchanter.").getInt(enchanterBaseLevelCost);

    photovoltaicCellEnabled = config.get(sectionItems.name, "photovoltaicCellEnabled", photovoltaicCellEnabled,
        "If set to false: Photovoltaic Cells will not be craftable.")
        .getBoolean(photovoltaicCellEnabled);

    reservoirEnabled= config.get(sectionItems.name, "reservoirEnabled", reservoirEnabled,
        "If set to false reservoirs will not be craftable.")
        .getBoolean(reservoirEnabled);
    
    transceiverEnabled = config.get(sectionItems.name, "transceiverEnabled", transceiverEnabled,
        "If set to false: Dimensional Transceivers will not be craftable.")
        .getBoolean(transceiverEnabled);

    maxPhotovoltaicOutputRF = config.get(sectionPower.name, "maxPhotovoltaicOutputRF", maxPhotovoltaicOutputRF,
        "Maximum output in RF/t of the Photovoltaic Panels.").getInt(maxPhotovoltaicOutputRF);
    maxPhotovoltaicAdvancedOutputRF = config.get(sectionPower.name, "maxPhotovoltaicAdvancedOutputRF", maxPhotovoltaicAdvancedOutputRF,
        "Maximum output in RF/t of the Advanced Photovoltaic Panels.").getInt(maxPhotovoltaicAdvancedOutputRF);

    useAlternateBinderRecipe = config.get(sectionRecipe.name, "useAlternateBinderRecipe", false, "Create conduit binder in crafting table instead of furnace")
        .getBoolean(useAlternateBinderRecipe);

    conduitScale = config.get(sectionAesthetic.name, "conduitScale", DEFAULT_CONDUIT_SCALE,
        "Valid values are between 0-1, smallest conduits at 0, largest at 1.\n" +
            "In SMP, all clients must be using the same value as the server.").getDouble(DEFAULT_CONDUIT_SCALE);
    conduitScale = VecmathUtil.clamp(conduitScale, 0, 1);

    wirelessChargerRange = config.get(sectionEfficiency.name, "wirelessChargerRange", wirelessChargerRange,
        "The range of the wireless charger").getInt(wirelessChargerRange);

    fluidConduitExtractRate = config.get(sectionEfficiency.name, "fluidConduitExtractRate", fluidConduitExtractRate,
        "Number of millibuckets per tick extracted by a fluid conduits auto extracting").getInt(fluidConduitExtractRate);

    fluidConduitMaxIoRate = config.get(sectionEfficiency.name, "fluidConduitMaxIoRate", fluidConduitMaxIoRate,
        "Number of millibuckets per tick that can pass through a single connection to a fluid conduit.").getInt(fluidConduitMaxIoRate);

    advancedFluidConduitExtractRate = config.get(sectionEfficiency.name, "advancedFluidConduitExtractRate", advancedFluidConduitExtractRate,
        "Number of millibuckets per tick extracted by pressurised fluid conduits auto extracting").getInt(advancedFluidConduitExtractRate);

    advancedFluidConduitMaxIoRate = config.get(sectionEfficiency.name, "advancedFluidConduitMaxIoRate", advancedFluidConduitMaxIoRate,
        "Number of millibuckets per tick that can pass through a single connection to an pressurised fluid conduit.").getInt(advancedFluidConduitMaxIoRate);

    enderFluidConduitExtractRate = config.get(sectionEfficiency.name, "enderFluidConduitExtractRate", enderFluidConduitExtractRate,
        "Number of millibuckets per tick extracted by ender fluid conduits auto extracting").getInt(enderFluidConduitExtractRate);

    enderFluidConduitMaxIoRate = config.get(sectionEfficiency.name, "enderFluidConduitMaxIoRate", enderFluidConduitMaxIoRate,
        "Number of millibuckets per tick that can pass through a single connection to an ender fluid conduit.").getInt(enderFluidConduitMaxIoRate);

    gasConduitExtractRate = config.get(sectionEfficiency.name, "gasConduitExtractRate", gasConduitExtractRate,
        "Amount of gas per tick extracted by gas conduits auto extracting").getInt(gasConduitExtractRate);

    gasConduitMaxIoRate = config.get(sectionEfficiency.name, "gasConduitMaxIoRate", gasConduitMaxIoRate,
        "Amount of gas per tick that can pass through a single connection to a gas conduit.").getInt(gasConduitMaxIoRate);

    useAlternateTesseractModel = config.get(sectionAesthetic.name, "useAlternateTransceiverModel", useAlternateTesseractModel,
        "Use TheKazador's alternative model for the Dimensional Transceiver")
        .getBoolean(false);
    transceiverEnergyLoss = config.get(sectionPower.name, "transceiverEnergyLoss", transceiverEnergyLoss,
        "Amount of energy lost when transfered by Dimensional Transceiver; 0 is no loss, 1 is 100% loss").getDouble(transceiverEnergyLoss);
    transceiverUpkeepCostRF = config.get(sectionPower.name, "transceiverUpkeepCostRF", transceiverUpkeepCostRF,
        "Number of RF/t required to keep a Dimensional Transceiver connection open").getInt(transceiverUpkeepCostRF);
    transceiverMaxIoRF = config.get(sectionPower.name, "transceiverMaxIoRF", transceiverMaxIoRF,
        "Maximum RF/t sent and received by a Dimensional Transceiver per tick. Input and output limits are not cumulative").getInt(transceiverMaxIoRF);
    transceiverBucketTransmissionCostRF = config.get(sectionEfficiency.name, "transceiverBucketTransmissionCostRF", transceiverBucketTransmissionCostRF,
        "The cost in RF of transporting a bucket of fluid via a Dimensional Transceiver.").getInt(transceiverBucketTransmissionCostRF);

    vatPowerUserPerTickRF = config.get(sectionPower.name, "vatPowerUserPerTickRF", vatPowerUserPerTickRF,
        "Power use (RF/t) used by the vat.").getInt(vatPowerUserPerTickRF);

    detailedPowerTrackingEnabled = config
        .get(
            sectionAdvanced.name,
            "perInterfacePowerTrackingEnabled",
            detailedPowerTrackingEnabled,
            "Enable per tick sampling on individual power inputs and outputs. This allows slightly more detailed messages from the RF Reader but has a negative impact on server performance.")
        .getBoolean(detailedPowerTrackingEnabled);

    useSneakMouseWheelYetaWrench = config.get(sectionPersonal.name, "useSneakMouseWheelYetaWrench", useSneakMouseWheelYetaWrench,
        "If true, shift-mouse wheel will change the conduit display mode when the YetaWrench is equipped.")
        .getBoolean(useSneakMouseWheelYetaWrench);

    useSneakRightClickYetaWrench = config.get(sectionPersonal.name, "useSneakRightClickYetaWrench", useSneakRightClickYetaWrench,
        "If true, shift-clicking the YetaWrench on a null or non wrenchable object will change the conduit display mode.")
        .getBoolean(useSneakRightClickYetaWrench);

    machineSoundsEnabled = config.get(sectionPersonal.name, "useMachineSounds", machineSoundsEnabled,
        "If true, machines will make sounds.")
        .getBoolean(machineSoundsEnabled);

    machineSoundVolume = (float) config.get(sectionPersonal.name, "machineSoundVolume", machineSoundVolume, "Volume of machine sounds.").getDouble(
        machineSoundVolume);

    itemConduitUsePhyscialDistance = config.get(sectionEfficiency.name, "itemConduitUsePhyscialDistance", itemConduitUsePhyscialDistance, "If true, " +
        "'line of sight' distance rather than conduit path distance is used to calculate priorities.")
        .getBoolean(itemConduitUsePhyscialDistance);

    vacuumChestRange = config.get(sectionEfficiency.name, "vacumChestRange", vacuumChestRange, "The range of the vacuum chest").getInt(vacuumChestRange);

    if(!useSneakMouseWheelYetaWrench && !useSneakRightClickYetaWrench) {
      Log.warn("Both useSneakMouseWheelYetaWrench and useSneakRightClickYetaWrench are set to false. Enabling mouse wheel.");
      useSneakMouseWheelYetaWrench = true;
    }

    reinforcedObsidianEnabled = config.get(sectionItems.name, "reinforcedObsidianEnabled", reinforcedObsidianEnabled,
        "When set to false reinforced obsidian is not craftable.").getBoolean(reinforcedObsidianEnabled);
    reinforcedObsidianUseDarkSteelBlocks = config.get(sectionRecipe.name, "reinforcedObsidianUseDarkSteelBlocks", reinforcedObsidianUseDarkSteelBlocks,
        "When set to true four dark steel blocks are required instead of ingots when making reinforced obsidian.").getBoolean(reinforcedObsidianUseDarkSteelBlocks);

    travelAnchorEnabled = config.get(sectionItems.name, "travelAnchorEnabled", travelAnchorEnabled,
        "When set to false: the travel anchor will not be craftable.").getBoolean(travelAnchorEnabled);

    travelAnchorMaxDistance = config.get(sectionAnchor.name, "travelAnchorMaxDistance", travelAnchorMaxDistance,
        "Maximum number of blocks that can be traveled from one travel anchor to another.").getInt(travelAnchorMaxDistance);

    travelStaffMaxDistance = config.get(sectionStaff.name, "travelStaffMaxDistance", travelStaffMaxDistance,
        "Maximum number of blocks that can be traveled using the Staff of the Traveling.").getInt(travelStaffMaxDistance);
    travelStaffPowerPerBlockRF = (float) config.get(sectionStaff.name, "travelStaffPowerPerBlockRF", travelStaffPowerPerBlockRF,
        "Number of RF required per block travelled using the Staff of the Traveling.").getDouble(travelStaffPowerPerBlockRF);

    travelStaffMaxBlinkDistance = config.get(sectionStaff.name, "travelStaffMaxBlinkDistance", travelStaffMaxBlinkDistance,
        "Max number of blocks teleported when shift clicking the staff.").getInt(travelStaffMaxBlinkDistance);

    travelStaffBlinkPauseTicks = config.get(sectionStaff.name, "travelStaffBlinkPauseTicks", travelStaffBlinkPauseTicks,
        "Minimum number of ticks between 'blinks'. Values of 10 or less allow a limited sort of flight.").getInt(travelStaffBlinkPauseTicks);

    travelStaffEnabled = config.get(sectionStaff.name, "travelStaffEnabled", travelAnchorEnabled,
        "If set to false: the travel staff will not be craftable.").getBoolean(travelStaffEnabled);
    travelStaffBlinkEnabled = config.get(sectionStaff.name, "travelStaffBlinkEnabled", travelStaffBlinkEnabled,
        "If set to false: the travel staff can not be used to shift-right click teleport, or blink.").getBoolean(travelStaffBlinkEnabled);
    travelStaffBlinkThroughSolidBlocksEnabled = config.get(sectionStaff.name, "travelStaffBlinkThroughSolidBlocksEnabled",
        travelStaffBlinkThroughSolidBlocksEnabled,
        "If set to false: the travel staff can be used to blink through any block.").getBoolean(travelStaffBlinkThroughSolidBlocksEnabled);
    travelStaffBlinkThroughClearBlocksEnabled = config
        .get(sectionItems.name, "travelStaffBlinkThroughClearBlocksEnabled", travelStaffBlinkThroughClearBlocksEnabled,
            "If travelStaffBlinkThroughSolidBlocksEnabled is set to false and this is true: the travel " +
                "staff can only be used to blink through transparent or partial blocks (e.g. torches). " +
                "If both are false: only air blocks may be teleported through.")
        .getBoolean(travelStaffBlinkThroughClearBlocksEnabled);
    travelStaffBlinkThroughUnbreakableBlocksEnabled = config.get(sectionItems.name, "travelStaffBlinkThroughUnbreakableBlocksEnabled",
        travelStaffBlinkThroughUnbreakableBlocksEnabled, "Allows the travel staff to blink through unbreakable blocks such as warded blocks and bedrock.")
        .getBoolean();
    travelStaffBlinkBlackList = config.getStringList("travelStaffBlinkBlackList", sectionStaff.name, travelStaffBlinkBlackList,
        "Lists the blocks that cannot be teleported through in the form 'modID:blockName'");
    travelAnchorZoomScale = config.getFloat("travelAnchorZoomScale", sectionStaff.name, travelAnchorZoomScale, 0, 1, 
        "Set the max zoomed size of a travel anchor as an aprox. percentage of screen height");
    

    enderIoRange = config.get(sectionEfficiency.name, "enderIoRange", enderIoRange,
        "Range accessible (in blocks) when using the Ender IO.").getInt(enderIoRange);

    enderIoMeAccessEnabled = config.get(sectionPersonal.name, "enderIoMeAccessEnabled", enderIoMeAccessEnabled,
        "If false: you will not be able to access a ME access or crafting terminal using the Ender IO.").getBoolean(enderIoMeAccessEnabled);

    updateLightingWhenHidingFacades = config.get(sectionEfficiency.name, "updateLightingWhenHidingFacades", updateLightingWhenHidingFacades,
        "When true: correct lighting is recalculated (client side) for conduit bundles when transitioning to"
            + " from being hidden behind a facade. This produces "
            + "better quality rendering but can result in frame stutters when switching to/from a wrench.")
        .getBoolean(updateLightingWhenHidingFacades);

    darkSteelPowerDamgeAbsorptionRatios = config
        .get(sectionDarkSteel.name, "darkSteelPowerDamgeAbsorptionRatios", darkSteelPowerDamgeAbsorptionRatios,
            "A list of the amount of durability damage absorbed when items are powered. In order of upgrade level. 1=100% so items take no durability damage when powered.")
        .getDoubleList();
    darkSteelPowerStorageBase = config.get(sectionDarkSteel.name, "darkSteelPowerStorageBase", darkSteelPowerStorageBase,
        "Base amount of power stored by dark steel items.").getInt(darkSteelPowerStorageBase);
    darkSteelPowerStorageLevelOne = config.get(sectionDarkSteel.name, "darkSteelPowerStorageLevelOne", darkSteelPowerStorageLevelOne,
        "Amount of power stored by dark steel items with a level 1 upgrade.").getInt(darkSteelPowerStorageLevelOne);
    darkSteelPowerStorageLevelTwo = config.get(sectionDarkSteel.name, "darkSteelPowerStorageLevelTwo", darkSteelPowerStorageLevelTwo,
        "Amount of power stored by dark steel items with a level 2 upgrade.").getInt(darkSteelPowerStorageLevelTwo);
    darkSteelPowerStorageLevelThree = config.get(sectionDarkSteel.name, "darkSteelPowerStorageLevelThree", darkSteelPowerStorageLevelThree,
        "Amount of power stored by dark steel items with a level 3 upgrade.").getInt(darkSteelPowerStorageLevelThree);

    darkSteelUpgradeVibrantCost = config.get(sectionDarkSteel.name, "darkSteelUpgradeVibrantCost", darkSteelUpgradeVibrantCost,
        "Number of levels required for the 'Empowered.").getInt(darkSteelUpgradeVibrantCost);
    darkSteelUpgradePowerOneCost = config.get(sectionDarkSteel.name, "darkSteelUpgradePowerOneCost", darkSteelUpgradePowerOneCost,
        "Number of levels required for the 'Power 1.").getInt(darkSteelUpgradePowerOneCost);
    darkSteelUpgradePowerTwoCost = config.get(sectionDarkSteel.name, "darkSteelUpgradePowerTwoCost", darkSteelUpgradePowerTwoCost,
        "Number of levels required for the 'Power 2.").getInt(darkSteelUpgradePowerTwoCost);
    darkSteelUpgradePowerThreeCost = config.get(sectionDarkSteel.name, "darkSteelUpgradePowerThreeCost", darkSteelUpgradePowerThreeCost,
        "Number of levels required for the 'Power 3' upgrade.").getInt(darkSteelUpgradePowerThreeCost);

    darkSteelJumpOneCost = config.get(sectionDarkSteel.name, "darkSteelJumpOneCost", darkSteelJumpOneCost,
        "Number of levels required for the 'Jump 1' upgrade.").getInt(darkSteelJumpOneCost);
    darkSteelJumpTwoCost = config.get(sectionDarkSteel.name, "darkSteelJumpTwoCost", darkSteelJumpTwoCost,
        "Number of levels required for the 'Jump 2' upgrade.").getInt(darkSteelJumpTwoCost);
    darkSteelJumpThreeCost = config.get(sectionDarkSteel.name, "darkSteelJumpThreeCost", darkSteelJumpThreeCost,
        "Number of levels required for the 'Jump 3' upgrade.").getInt(darkSteelJumpThreeCost);
    
    darkSteelSpeedOneCost = config.get(sectionDarkSteel.name, "darkSteelSpeedOneCost", darkSteelSpeedOneCost,
        "Number of levels required for the 'Speed 1' upgrade.").getInt(darkSteelSpeedOneCost);
    darkSteelSpeedTwoCost = config.get(sectionDarkSteel.name, "darkSteelSpeedTwoCost", darkSteelSpeedTwoCost,
        "Number of levels required for the 'Speed 2' upgrade.").getInt(darkSteelSpeedTwoCost);
    darkSteelSpeedThreeCost = config.get(sectionDarkSteel.name, "darkSteelSpeedThreeCost", darkSteelSpeedThreeCost,
        "Number of levels required for the 'Speed 3' upgrade.").getInt(darkSteelSpeedThreeCost);
    
    slotZeroPlacesEight = config.get(sectionDarkSteel.name, "shouldSlotZeroWrap", slotZeroPlacesEight, "Should the dark steel placement, when in the first (0th) slot, place the item in the last slot. If false, will place what's in the second slot.").getBoolean();
    
    darkSteelSpeedOneWalkModifier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedOneWalkModifier", darkSteelSpeedOneWalkModifier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedOneWalkModifier);
    darkSteelSpeedTwoWalkMultiplier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedTwoWalkMultiplier", darkSteelSpeedTwoWalkMultiplier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedTwoWalkMultiplier);
    darkSteelSpeedThreeWalkMultiplier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedThreeWalkMultiplier", darkSteelSpeedThreeWalkMultiplier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedThreeWalkMultiplier);

    darkSteelSpeedOneSprintModifier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedOneSprintModifier", darkSteelSpeedOneSprintModifier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedOneSprintModifier);
    darkSteelSpeedTwoSprintMultiplier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedTwoSprintMultiplier", darkSteelSpeedTwoSprintMultiplier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedTwoSprintMultiplier);
    darkSteelSpeedThreeSprintMultiplier = (float) config.get(sectionDarkSteel.name, "darkSteelSpeedThreeSprintMultiplier", darkSteelSpeedThreeSprintMultiplier,
        "Speed modifier applied when walking in the Dark Steel Boots with Speed I.").getDouble(darkSteelSpeedThreeSprintMultiplier);

    darkSteelBootsJumpModifier = config.get(sectionDarkSteel.name, "darkSteelBootsJumpModifier", darkSteelBootsJumpModifier,
        "Jump height modifier applied when jumping with Dark Steel Boots equipped").getDouble(darkSteelBootsJumpModifier);

    darkSteelPowerStorageBase = config.get(sectionDarkSteel.name, "darkSteelPowerStorage", darkSteelPowerStorageBase,
        "Amount of power stored (RF) per crystal in the armor items recipe.").getInt(darkSteelPowerStorageBase);
    darkSteelWalkPowerCost = config.get(sectionDarkSteel.name, "darkSteelWalkPowerCost", darkSteelWalkPowerCost,
        "Amount of power stored (RF) per block walked when wearing the dark steel boots.").getInt(darkSteelWalkPowerCost);
    darkSteelSprintPowerCost = config.get(sectionDarkSteel.name, "darkSteelSprintPowerCost", darkSteelWalkPowerCost,
        "Amount of power stored (RF) per block walked when wearing the dark steel boots.").getInt(darkSteelSprintPowerCost);
    darkSteelDrainPowerFromInventory = config.get(sectionDarkSteel.name, "darkSteelDrainPowerFromInventory", darkSteelDrainPowerFromInventory,
        "If true, dark steel armor will drain power stored (RF) in power containers in the players inventory.").getBoolean(darkSteelDrainPowerFromInventory);

    darkSteelBootsJumpPowerCost = config.get(sectionDarkSteel.name, "darkSteelBootsJumpPowerCost", darkSteelBootsJumpPowerCost,
        "Base amount of power used per jump (RF) dark steel boots. The second jump in a 'double jump' uses 2x this etc").getInt(darkSteelBootsJumpPowerCost);

    darkSteelFallDistanceCost = config.get(sectionDarkSteel.name, "darkSteelFallDistanceCost", darkSteelFallDistanceCost,
        "Amount of power used (RF) per block height of fall distance damage negated.").getInt(darkSteelFallDistanceCost);

    darkSteelSwimCost = config.get(sectionDarkSteel.name, "darkSteelSwimCost", darkSteelSwimCost,
        "Number of levels required for the 'Swim' upgrade.").getInt(darkSteelSwimCost);

    darkSteelNightVisionCost = config.get(sectionDarkSteel.name, "darkSteelNightVisionCost", darkSteelNightVisionCost,
        "Number of levels required for the 'Night Vision' upgrade.").getInt(darkSteelNightVisionCost);

    darkSteelGliderCost = config.get(sectionDarkSteel.name, "darkSteelGliderCost", darkSteelGliderCost,
        "Number of levels required for the 'Glider' upgrade.").getInt(darkSteelGliderCost);
    darkSteelGliderHorizontalSpeed = config.get(sectionDarkSteel.name, "darkSteelGliderHorizontalSpeed", darkSteelGliderHorizontalSpeed,
        "Horizontal movement speed modifier when gliding.").getDouble(darkSteelGliderHorizontalSpeed);
    darkSteelGliderVerticalSpeed = config.get(sectionDarkSteel.name, "darkSteelGliderVerticalSpeed", darkSteelGliderVerticalSpeed,
        "Rate of altitude loss when gliding.").getDouble(darkSteelGliderVerticalSpeed);
    darkSteelGliderVerticalSpeedSprinting = config.get(sectionDarkSteel.name, "darkSteelGliderVerticalSpeedSprinting", darkSteelGliderVerticalSpeedSprinting,
        "Rate of altitude loss when sprinting and gliding.").getDouble(darkSteelGliderVerticalSpeedSprinting);

    darkSteelSoundLocatorCost = config.get(sectionDarkSteel.name, "darkSteelSoundLocatorCost", darkSteelSoundLocatorCost,
        "Number of levels required for the 'Sound Locator' upgrade.").getInt(darkSteelSoundLocatorCost);
    darkSteelSoundLocatorRange = config.get(sectionDarkSteel.name, "darkSteelSoundLocatorRange", darkSteelSoundLocatorRange,
        "Range of the 'Sound Locator' upgrade.").getInt(darkSteelSoundLocatorRange);
    darkSteelSoundLocatorLifespan = config.get(sectionDarkSteel.name, "darkSteelSoundLocatorLifespan", darkSteelSoundLocatorLifespan,
        "Number of ticks the 'Sound Locator' icons are displayed for.").getInt(darkSteelSoundLocatorLifespan);
    
    darkSteelGogglesOfRevealingCost= config.get(sectionDarkSteel.name, "darkSteelGogglesOfRevealingCost", darkSteelGogglesOfRevealingCost,
        "Number of levels required for the Goggles of Revealing upgrade.").getInt(darkSteelGogglesOfRevealingCost);
    
    darkSteelApiaristArmorCost= config.get(sectionDarkSteel.name, "darkSteelApiaristArmorCost", darkSteelApiaristArmorCost,
        "Number of levels required for the Apiarist Armor upgrade.").getInt(darkSteelApiaristArmorCost);

    darkSteelTravelCost = config.get(sectionDarkSteel.name, "darkSteelTravelCost", darkSteelTravelCost,
        "Number of levels required for the 'Travel' upgrade.").getInt(darkSteelTravelCost);
    
    darkSteelSpoonCost = config.get(sectionDarkSteel.name, "darkSteelSpoonCost", darkSteelSpoonCost,
        "Number of levels required for the 'Spoon' upgrade.").getInt(darkSteelSpoonCost);

    darkSteelSolarOneCost = config.get(sectionDarkSteel.name, "darkSteelSolarOneCost", darkSteelSolarOneCost,
        "Cost in XP levels of the Solar I upgrade.").getInt();
    darkSteelSolarOneGen = config.get(sectionDarkSteel.name, "darkSteelSolarOneGen", darkSteelSolarOneGen,
        "RF per SECOND generated by the Solar I upgrade. Split between all equipped DS armors.").getInt();
  
    darkSteelSolarTwoCost = config.get(sectionDarkSteel.name, "darkSteelSolarTwoCost", darkSteelSolarTwoCost,
        "Cost in XP levels of the Solar II upgrade.").getInt();
    darkSteelSolarTwoGen = config.get(sectionDarkSteel.name, "darkSteelSolarTwoGen", darkSteelSolarTwoGen,
        "RF per SECOND generated by the Solar II upgrade. Split between all equipped DS armors.").getInt();
    darkSteelSolarChargeOthers = config.get(sectionDarkSteel.name, "darkSteelSolarChargeOthers", darkSteelSolarChargeOthers,
        "If enabled allows the solar upgrade to charge non-darksteel armors that the player is wearing.").getBoolean();

    darkSteelSwordSkullChance = (float) config.get(sectionDarkSteel.name, "darkSteelSwordSkullChance", darkSteelSwordSkullChance,
        "The base chance that a skull will be dropped when using a powered dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        darkSteelSwordSkullChance);
    darkSteelSwordSkullLootingModifier = (float) config.get(sectionDarkSteel.name, "darkSteelSwordSkullLootingModifier", darkSteelSwordSkullLootingModifier,
        "The chance per looting level that a skull will be dropped when using a powered dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        darkSteelSwordSkullLootingModifier);

    darkSteelSwordWitherSkullChance = (float) config.get(sectionDarkSteel.name, "darkSteelSwordWitherSkullChance", darkSteelSwordWitherSkullChance,
        "The base chance that a wither skull will be dropped when using a powered dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        darkSteelSwordWitherSkullChance);
    darkSteelSwordWitherSkullLootingModifier = (float) config.get(sectionDarkSteel.name, "darkSteelSwordWitherSkullLootingModifie",
        darkSteelSwordWitherSkullLootingModifier,
        "The chance per looting level that a wither skull will be dropped when using a powered dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        darkSteelSwordWitherSkullLootingModifier);

    vanillaSwordSkullChance = (float) config.get(sectionDarkSteel.name, "vanillaSwordSkullChance", vanillaSwordSkullChance,
        "The base chance that a skull will be dropped when using a non dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        vanillaSwordSkullChance);
    vanillaSwordSkullLootingModifier = (float) config.get(sectionPersonal.name, "vanillaSwordSkullLootingModifier", vanillaSwordSkullLootingModifier,
        "The chance per looting level that a skull will be dropped when using a non-dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        vanillaSwordSkullLootingModifier);

    ticCleaverSkullDropChance = (float) config.get(sectionDarkSteel.name, "ticCleaverSkullDropChance", ticCleaverSkullDropChance,
        "The base chance that an Enderman Skull will be dropped when using TiC Cleaver").getDouble(
        ticCleaverSkullDropChance);
    ticBeheadingSkullModifier = (float) config.get(sectionPersonal.name, "ticBeheadingSkullModifier", ticBeheadingSkullModifier,
        "The chance per level of Beheading that a skull will be dropped when using a TiC weapon").getDouble(
        ticBeheadingSkullModifier);

    fakePlayerSkullChance = (float) config
        .get(
            sectionDarkSteel.name,
            "fakePlayerSkullChance",
            fakePlayerSkullChance,
            "The ratio of skull drops when a mob is killed by a 'FakePlayer', such as Killer Joe. When set to 0 no skulls will drop, at 1 the rate of skull drops is not modified")
        .getDouble(
            fakePlayerSkullChance);

    darkSteelSwordPowerUsePerHit = config.get(sectionDarkSteel.name, "darkSteelSwordPowerUsePerHit", darkSteelSwordPowerUsePerHit,
        "The amount of power (RF) used per hit.").getInt(darkSteelSwordPowerUsePerHit);
    darkSteelSwordEnderPearlDropChance = config.get(sectionDarkSteel.name, "darkSteelSwordEnderPearlDropChance", darkSteelSwordEnderPearlDropChance,
        "The chance that an ender pearl will be dropped when using a dark steel sword (0 = no chance, 1 = 100% chance)").getDouble(
        darkSteelSwordEnderPearlDropChance);
    darkSteelSwordEnderPearlDropChancePerLooting = config.get(sectionDarkSteel.name, "darkSteelSwordEnderPearlDropChancePerLooting",
        darkSteelSwordEnderPearlDropChancePerLooting,
        "The chance for each looting level that an additional ender pearl will be dropped when using a dark steel sword (0 = no chance, 1 = 100% chance)")
        .getDouble(
            darkSteelSwordEnderPearlDropChancePerLooting);

    darkSteelPickPowerUseObsidian = config.get(sectionDarkSteel.name, "darkSteelPickPowerUseObsidian", darkSteelPickPowerUseObsidian,
        "The amount of power (RF) used to break an obsidian block.").getInt(darkSteelPickPowerUseObsidian);
    darkSteelPickEffeciencyObsidian = config.get(sectionDarkSteel.name, "darkSteelPickEffeciencyObsidian", darkSteelPickEffeciencyObsidian,
        "The efficiency when breaking obsidian with a powered Dark Pickaxe.").getInt(darkSteelPickEffeciencyObsidian);
    darkSteelPickApplyObsidianEffeciencyAtHardess = (float) config.get(sectionDarkSteel.name, "darkSteelPickApplyObsidianEffeciencyAtHardess",
        darkSteelPickApplyObsidianEffeciencyAtHardess,
        "If set to a value > 0, the obsidian speed and power use will be used for all blocks with hardness >= to this value.").getDouble(
        darkSteelPickApplyObsidianEffeciencyAtHardess);
    darkSteelPickPowerUsePerDamagePoint = config.get(sectionDarkSteel.name, "darkSteelPickPowerUsePerDamagePoint", darkSteelPickPowerUsePerDamagePoint,
        "Power use (RF) per damage/durability point avoided.").getInt(darkSteelPickPowerUsePerDamagePoint);
    darkSteelPickEffeciencyBoostWhenPowered = (float) config.get(sectionDarkSteel.name, "darkSteelPickEffeciencyBoostWhenPowered",
        darkSteelPickEffeciencyBoostWhenPowered, "The increase in efficiency when powered.").getDouble(darkSteelPickEffeciencyBoostWhenPowered);
    darkSteelPickMinesTiCArdite = config.getBoolean("darkSteelPickMinesTiCArdite", sectionDarkSteel.name, darkSteelPickMinesTiCArdite,
        "When true the dark steel pick will be able to mine TiC Ardite and Cobalt");

    darkSteelAxePowerUsePerDamagePoint = config.get(sectionDarkSteel.name, "darkSteelAxePowerUsePerDamagePoint", darkSteelAxePowerUsePerDamagePoint,
        "Power use (RF) per damage/durability point avoided.").getInt(darkSteelAxePowerUsePerDamagePoint);
    darkSteelAxePowerUsePerDamagePointMultiHarvest = config.get(sectionDarkSteel.name, "darkSteelPickAxeUsePerDamagePointMultiHarvest",
        darkSteelAxePowerUsePerDamagePointMultiHarvest,
        "Power use (RF) per damage/durability point avoided when shift-harvesting multiple logs").getInt(darkSteelAxePowerUsePerDamagePointMultiHarvest);
    darkSteelAxeSpeedPenaltyMultiHarvest = (float) config.get(sectionDarkSteel.name, "darkSteelAxeSpeedPenaltyMultiHarvest",
        darkSteelAxeSpeedPenaltyMultiHarvest,
        "How much slower shift-harvesting logs is.").getDouble(darkSteelAxeSpeedPenaltyMultiHarvest);
    darkSteelAxeEffeciencyBoostWhenPowered = (float) config.get(sectionDarkSteel.name, "darkSteelAxeEffeciencyBoostWhenPowered",
        darkSteelAxeEffeciencyBoostWhenPowered, "The increase in efficiency when powered.").getDouble(darkSteelAxeEffeciencyBoostWhenPowered);

    darkSteelAnvilDamageChance = (float) config.get(sectionDarkSteel.name, "darkSteelAnvilDamageChance", darkSteelAnvilDamageChance, "Chance that the dark steel anvil will take damage after repairing something.").getDouble();

    darkSteelLadderSpeedBoost = (float) config.get(sectionDarkSteel.name, "darkSteelLadderSpeedBoost", darkSteelLadderSpeedBoost, "Speed boost, in blocks per tick, that the DS ladder gives over the vanilla ladder.").getDouble();
    
    hootchPowerPerCycleRF = config.get(sectionPower.name, "hootchPowerPerCycleRF", hootchPowerPerCycleRF,
        "The amount of power generated per BC engine cycle. Examples: BC Oil = 30, BC Fuel = 60").getInt(hootchPowerPerCycleRF);
    hootchPowerTotalBurnTime = config.get(sectionPower.name, "hootchPowerTotalBurnTime", hootchPowerTotalBurnTime,
        "The total burn time. Examples: BC Oil = 5000, BC Fuel = 25000").getInt(hootchPowerTotalBurnTime);

    rocketFuelPowerPerCycleRF = config.get(sectionPower.name, "rocketFuelPowerPerCycleRF", rocketFuelPowerPerCycleRF,
        "The amount of power generated per BC engine cycle. Examples: BC Oil = 3, BC Fuel = 6").getInt(rocketFuelPowerPerCycleRF);
    rocketFuelPowerTotalBurnTime = config.get(sectionPower.name, "rocketFuelPowerTotalBurnTime", rocketFuelPowerTotalBurnTime,
        "The total burn time. Examples: BC Oil = 5000, BC Fuel = 25000").getInt(rocketFuelPowerTotalBurnTime);

    fireWaterPowerPerCycleRF = config.get(sectionPower.name, "fireWaterPowerPerCycleRF", fireWaterPowerPerCycleRF,
        "The amount of power generated per BC engine cycle. Examples: BC Oil = 30, BC Fuel = 60").getInt(fireWaterPowerPerCycleRF);
    fireWaterPowerTotalBurnTime = config.get(sectionPower.name, "fireWaterPowerTotalBurnTime", fireWaterPowerTotalBurnTime,
        "The total burn time. Examples: BC Oil = 5000, BC Fuel = 25000").getInt(fireWaterPowerTotalBurnTime);

    zombieGeneratorRfPerTick = config.get(sectionPower.name, "zombieGeneratorRfPerTick", zombieGeneratorRfPerTick,
        "The amount of power generated per tick.").getInt(zombieGeneratorRfPerTick);
    zombieGeneratorTicksPerBucketFuel = config.get(sectionPower.name, "zombieGeneratorTicksPerMbFuel", zombieGeneratorTicksPerBucketFuel,
        "The number of ticks one bucket of fuel lasts.").getInt(zombieGeneratorTicksPerBucketFuel);

    stirlingGeneratorBaseRfPerTick = config.get(sectionPower.name, "stirlingGeneratorBaseRfPerTick", stirlingGeneratorBaseRfPerTick,
        "The amount of power generated per tick.").getInt(stirlingGeneratorBaseRfPerTick);

    addFuelTooltipsToAllFluidContainers = config.get(sectionPersonal.name, "addFuelTooltipsToAllFluidContainers", addFuelTooltipsToAllFluidContainers,
        "If true, the RF/t and burn time of the fuel will be displayed in all tooltips for fluid containers with fuel.").getBoolean(
        addFuelTooltipsToAllFluidContainers);
    addDurabilityTootip = config.get(sectionPersonal.name, "addDurabilityTootip", addFuelTooltipsToAllFluidContainers,
        "If true, adds durability tooltips to tools and armor").getBoolean(
        addDurabilityTootip);
    addFurnaceFuelTootip = config.get(sectionPersonal.name, "addFurnaceFuelTootip", addFuelTooltipsToAllFluidContainers,
        "If true, adds burn duration tooltips to furnace fuels").getBoolean(addFurnaceFuelTootip);
    addOreDictionaryTooltips = config.get(sectionPersonal.name, "addOreDictionaryTooltips", addOreDictionaryTooltips,
        "If true, adds ore dictionary registrations to tooltips").getBoolean(addOreDictionaryTooltips);
    addRegisterdNameTooltip = config.get(sectionPersonal.name, "addRegisterdNameTooltip", addRegisterdNameTooltip,
        "If true, adds the registered name for the item").getBoolean(addRegisterdNameTooltip);

    farmContinuousEnergyUseRF = config.get(sectionFarm.name, "farmContinuousEnergyUseRF", farmContinuousEnergyUseRF,
        "The amount of power used by a farm per tick ").getInt(farmContinuousEnergyUseRF);
    farmActionEnergyUseRF = config.get(sectionFarm.name, "farmActionEnergyUseRF", farmActionEnergyUseRF,
        "The amount of power used by a farm per action (eg plant, till, harvest) ").getInt(farmActionEnergyUseRF);
    farmAxeActionEnergyUseRF = config.get(sectionFarm.name, "farmAxeActionEnergyUseRF", farmAxeActionEnergyUseRF,
        "The amount of power used by a farm per wood block 'chopped'").getInt(farmAxeActionEnergyUseRF);

    farmDefaultSize = config.get(sectionFarm.name, "farmDefaultSize", farmDefaultSize,
        "The number of blocks a farm will extend from its center").getInt(farmDefaultSize);

    farmAxeDamageOnLeafBreak = config.get(sectionFarm.name, "farmAxeDamageOnLeafBreak", farmAxeDamageOnLeafBreak,
        "Should axes in a farm take damage when breaking leaves?").getBoolean(farmAxeDamageOnLeafBreak);
    farmToolTakeDamageChance = (float) config.get(sectionFarm.name, "farmToolTakeDamageChance", farmToolTakeDamageChance,
        "The chance that a tool in the farm will take damage.").getDouble(farmToolTakeDamageChance);

    disableFarmNotification = config.get(sectionFarm.name, "disableFarmNotifications", disableFarmNotification,
        "Disable the notification text above the farm block.").getBoolean();
    
    farmEssenceBerriesEnabled = config.get(sectionFarm.name, "farmEssenceBerriesEnabled", farmEssenceBerriesEnabled,
        "This setting controls whether essence berry bushes from TiC can be harvested by the farm.").getBoolean();

    farmManaBeansEnabled = config.get(sectionFarm.name, "farmManaBeansEnabled", farmManaBeansEnabled,
            "This setting controls whether mana beans from Thaumcraft can be harvested by the farm.").getBoolean();
    
    farmHarvestJungleWhenCocoa = config.get(sectionFarm.name, "farmHarvestJungleWhenCocoa", farmHarvestJungleWhenCocoa,
        "If this is enabled the farm will harvest jungle wood even if it has cocoa beans in its inventory.").getBoolean();    

    hoeStrings = config.get(sectionFarm.name, "farmHoes", hoeStrings,
        "Use this to specify items that can be hoes in the farming station. Use the registry name (eg. modid:name).").getStringList();

    combustionGeneratorUseOpaqueModel = config.get(sectionAesthetic.name, "combustionGeneratorUseOpaqueModel", combustionGeneratorUseOpaqueModel,
        "If set to true: fluid will not be shown in combustion generator tanks. Improves FPS. ").getBoolean(combustionGeneratorUseOpaqueModel);

    magnetPowerUsePerSecondRF = config.get(sectionMagnet.name, "magnetPowerUsePerTickRF", magnetPowerUsePerSecondRF,
        "The amount of RF power used per tick when the magnet is active").getInt(magnetPowerUsePerSecondRF);
    magnetPowerCapacityRF = config.get(sectionMagnet.name, "magnetPowerCapacityRF", magnetPowerCapacityRF,
        "Amount of RF power stored in a fully charged magnet").getInt(magnetPowerCapacityRF);
    magnetRange = config.get(sectionMagnet.name, "magnetRange", magnetRange,
        "Range of the magnet in blocks.").getInt(magnetRange);

    useCombustionGenModel = config.get(sectionAesthetic.name, "useCombustionGenModel", useCombustionGenModel,
        "If set to true: WIP Combustion Generator model will be used").getBoolean(useCombustionGenModel);

    crafterRfPerCraft = config.get("AutoCrafter Settings", "crafterRfPerCraft", crafterRfPerCraft,
        "RF used per autocrafted recipe").getInt(crafterRfPerCraft);

    poweredSpawnerMinDelayTicks = config.get(sectionSpawner.name, "poweredSpawnerMinDelayTicks", poweredSpawnerMinDelayTicks,
        "Min tick delay between spawns for a non-upgraded spawner").getInt(poweredSpawnerMinDelayTicks);
    poweredSpawnerMaxDelayTicks = config.get(sectionSpawner.name, "poweredSpawnerMaxDelayTicks", poweredSpawnerMaxDelayTicks,
        "Min tick delay between spawns for a non-upgraded spawner").getInt(poweredSpawnerMaxDelayTicks);
    poweredSpawnerLevelOnePowerPerTickRF = config.get(sectionSpawner.name, "poweredSpawnerLevelOnePowerPerTickRF", poweredSpawnerLevelOnePowerPerTickRF,
        "RF per tick for a level 1 (non-upgraded) spawner. See PoweredSpanerConfig_Core.json for mob type multipliers").getInt(poweredSpawnerLevelOnePowerPerTickRF);
    poweredSpawnerLevelTwoPowerPerTickRF = config.get(sectionSpawner.name, "poweredSpawnerLevelTwoPowerPerTickRF", poweredSpawnerLevelTwoPowerPerTickRF,
        "RF per tick for a level 2 spawner").getInt(poweredSpawnerLevelTwoPowerPerTickRF);
    poweredSpawnerLevelThreePowerPerTickRF = config.get(sectionSpawner.name, "poweredSpawnerLevelThreePowerPerTickRF",
        poweredSpawnerLevelThreePowerPerTickRF,
        "RF per tick for a level 3 spawner").getInt(poweredSpawnerLevelThreePowerPerTickRF);
    poweredSpawnerMaxPlayerDistance = config.get(sectionSpawner.name, "poweredSpawnerMaxPlayerDistance", poweredSpawnerMaxPlayerDistance,
        "Max distance of the closest player for the spawner to be active. A zero value will remove the player check").getInt(poweredSpawnerMaxPlayerDistance);
    poweredSpawnerDespawnTimeSeconds = config.get(sectionSpawner.name, "poweredSpawnerDespawnTimeSeconds" , poweredSpawnerDespawnTimeSeconds,
        "Number of seconds in which spawned entities are protected from despawning").getInt(poweredSpawnerDespawnTimeSeconds);
    poweredSpawnerUseVanillaSpawChecks = config.get(sectionSpawner.name, "poweredSpawnerUseVanillaSpawChecks", poweredSpawnerUseVanillaSpawChecks,
        "If true, regular spawn checks such as lighting level and dimension will be made before spawning mobs").getBoolean(poweredSpawnerUseVanillaSpawChecks);
    brokenSpawnerDropChance = (float) config.get(sectionSpawner.name, "brokenSpawnerDropChance", brokenSpawnerDropChance,
        "The chance a broken spawner will be dropped when a spawner is broken. 1 = 100% chance, 0 = 0% chance").getDouble(brokenSpawnerDropChance);    
    brokenSpawnerToolBlacklist = config.getStringList("brokenSpawnerToolBlacklist", sectionSpawner.name, brokenSpawnerToolBlacklist, 
        "When a spawner is broken with these tools they will not drop a broken spawner");
    
    powerSpawnerAddSpawnerCost = config.get(sectionSpawner.name, "powerSpawnerAddSpawnerCost", powerSpawnerAddSpawnerCost,
        "The number of levels it costs to add a broken spawner").getInt(powerSpawnerAddSpawnerCost);    

    useModMetals = config.get(sectionRecipe.name, "useModMetals", useModMetals,
        "If true copper and tin will be used in recipes when registered in the ore dictionary").getBoolean(useModMetals);

    nutrientFoodBoostDelay = config.get(sectionFluid.name, "nutrientFluidFoodBoostDelay", nutrientFoodBoostDelay,
        "The delay in ticks between when nutrient distillation boosts your food value.").getInt((int) nutrientFoodBoostDelay);

    killerJoeNutrientUsePerAttackMb = config.get(sectionKiller.name, "killerJoeNutrientUsePerAttackMb", killerJoeNutrientUsePerAttackMb,
        "The number of millibuckets of nutrient fluid used per attack.").getInt(killerJoeNutrientUsePerAttackMb);

    killerJoeAttackHeight = config.get(sectionKiller.name, "killerJoeAttackHeight", killerJoeAttackHeight,
        "The reach of attacks above and bellow Joe.").getDouble(killerJoeAttackHeight);
    killerJoeAttackWidth = config.get(sectionKiller.name, "killerJoeAttackWidth", killerJoeAttackWidth,
        "The reach of attacks to each side of Joe.").getDouble(killerJoeAttackWidth);
    killerJoeAttackLength = config.get(sectionKiller.name, "killerJoeAttackLength", killerJoeAttackLength,
        "The reach of attacks in front of Joe.").getDouble(killerJoeAttackLength);   
    killerJoeHooverXpLength = config.get(sectionKiller.name, "killerJoeHooverXpLength", killerJoeHooverXpLength,
        "The distance from which XP will be gathered to each side of Joe.").getDouble(killerJoeHooverXpLength);
    killerJoeHooverXpWidth = config.get(sectionKiller.name, "killerJoeHooverXpWidth", killerJoeHooverXpWidth,
        "The distance from which XP will be gathered in front of Joe.").getDouble(killerJoeHooverXpWidth);
    killerJoeMaxXpLevel = config.get(sectionMisc.name, "killerJoeMaxXpLevel", killerJoeMaxXpLevel, "Maximum level of XP the killer joe can contain.").getInt();

    killerJoeMustSee = config.get(sectionKiller.name, "killerJoeMustSee", killerJoeMustSee, "Set whether the Killer Joe can attack through blocks.").getBoolean();

    isGasConduitEnabled = config.getString("isGasConduitEnabled", sectionItems.name, isGasConduitEnabled,
        "Can be set to 'auto', 'true' or 'false'. When set to auto the gas conduit will only be enabled when Mekanism is installed.");
    enableMEConduits = config.getBoolean("enableMEConduits", sectionItems.name, enableMEConduits,
        "Allows ME conduits. Only has an effect with AE2 installed.");
    
    soulVesselBlackList = config.getStringList("soulVesselBlackList", sectionSoulBinder.name, soulVesselBlackList,
        "Entities listed here will can not be captured in a Soul Vial");

    soulVesselCapturesBosses = config.getBoolean("soulVesselCapturesBosses", sectionSoulBinder.name, soulVesselCapturesBosses,
        "When set to false, any mob with a 'boss bar' won't be able to be captured in the Soul Vial");

    soulBinderLevelOnePowerPerTickRF = config.get(sectionSoulBinder.name, "soulBinderLevelOnePowerPerTickRF", soulBinderLevelOnePowerPerTickRF,
        "The number of RF/t consumed by an unupgraded soul binder.").getInt(soulBinderLevelOnePowerPerTickRF);
    soulBinderLevelTwoPowerPerTickRF = config.get(sectionSoulBinder.name, "soulBinderLevelTwoPowerPerTickRF", soulBinderLevelTwoPowerPerTickRF,
        "The number of RF/t consumed by a soul binder with a double layer capacitor upgrade.").getInt(soulBinderLevelTwoPowerPerTickRF);
    soulBinderLevelThreePowerPerTickRF = config.get(sectionSoulBinder.name, "soulBinderLevelThreePowerPerTickRF", soulBinderLevelThreePowerPerTickRF,
        "The number of RF/t consumed by a soul binder with an octadic capacitor upgrade.").getInt(soulBinderLevelThreePowerPerTickRF);
    soulBinderBrokenSpawnerRF = config.get(sectionSoulBinder.name, "soulBinderBrokenSpawnerRF", soulBinderBrokenSpawnerRF,
        "The number of RF required to change the type of a broken spawner.").getInt(soulBinderBrokenSpawnerRF);
    soulBinderReanimationRF = config.get(sectionSoulBinder.name, "soulBinderReanimationRF", soulBinderReanimationRF,
        "The number of RF required to to re-animated a mob head.").getInt(soulBinderReanimationRF);
    soulBinderEnderCystalRF = config.get(sectionSoulBinder.name, "soulBinderEnderCystalRF", soulBinderEnderCystalRF,
        "The number of RF required to create an ender crystal.").getInt(soulBinderEnderCystalRF);
    soulBinderAttractorCystalRF = config.get(sectionSoulBinder.name, "soulBinderAttractorCystalRF", soulBinderAttractorCystalRF,
        "The number of RF required to create an attractor crystal.").getInt(soulBinderAttractorCystalRF);
    soulBinderEnderRailRF = config.get(sectionSoulBinder.name, "soulBinderEnderRailRF", soulBinderEnderRailRF,
        "The number of RF required to create an ender rail.").getInt(soulBinderEnderRailRF);
    
    soulBinderAttractorCystalLevels = config.get(sectionSoulBinder.name, "soulBinderAttractorCystalLevels", soulBinderAttractorCystalLevels,
        "The number of levels required to create an attractor crystal.").getInt(soulBinderAttractorCystalLevels);
    soulBinderEnderCystalLevels = config.get(sectionSoulBinder.name, "soulBinderEnderCystalLevels", soulBinderEnderCystalLevels,
        "The number of levels required to create an ender crystal.").getInt(soulBinderEnderCystalLevels);
    soulBinderReanimationLevels = config.get(sectionSoulBinder.name, "soulBinderReanimationLevels", soulBinderReanimationLevels,
        "The number of levels required to re-animate a mob head.").getInt(soulBinderReanimationLevels);
    soulBinderBrokenSpawnerLevels = config.get(sectionSoulBinder.name, "soulBinderBrokenSpawnerLevels", soulBinderBrokenSpawnerLevels,
        "The number of levels required to change the type of a broken spawner.").getInt(soulBinderBrokenSpawnerLevels);
    soulBinderEnderRailLevels = config.get(sectionSoulBinder.name, "soulBinderEnderRailLevels", soulBinderEnderRailLevels,
        "The number of levels required to create an ender rail.").getInt(soulBinderEnderRailLevels);    

    soulBinderMaxXpLevel = config.get(sectionSoulBinder.name, "soulBinderMaxXPLevel", soulBinderMaxXpLevel, "Maximum level of XP the soul binder can contain.").getInt();


    sliceAndSpliceLevelOnePowerPerTickRF = config.get(sectionPower.name, "sliceAndSpliceLevelOnePowerPerTickRF", sliceAndSpliceLevelOnePowerPerTickRF,
        "The number of RF/t consumed by an unupgraded Slice'N'Splice").getInt(sliceAndSpliceLevelOnePowerPerTickRF);
    sliceAndSpliceLevelTwoPowerPerTickRF = config.get(sectionPower.name, "sliceAndSpliceLevelTwoPowerPerTickRF", sliceAndSpliceLevelTwoPowerPerTickRF,
        "The number of RF/t consumed by a Slice'N'Splice with a double layer capacitor upgrade.").getInt(sliceAndSpliceLevelTwoPowerPerTickRF);
    sliceAndSpliceLevelThreePowerPerTickRF = config.get(sectionPower.name, "sliceAndSpliceLevelThreePowerPerTickRF", sliceAndSpliceLevelThreePowerPerTickRF,
        "The number of RF/t consumed by a Slice'N'Splice with an octadic capacitor upgrade.").getInt(sliceAndSpliceLevelThreePowerPerTickRF);
    
    attractorRangeLevelOne = config.get(sectionAttractor.name, "attractorRangeLevelOne", attractorRangeLevelOne, 
        "The range of the mob attractor with no upgrades").getInt(attractorRangeLevelOne);
    attractorRangeLevelTwo = config.get(sectionAttractor.name, "attractorRangeLevelTwo", attractorRangeLevelTwo, 
        "The range of the mob attractor with a double layer capacitor upgrade").getInt(attractorRangeLevelTwo);
    attractorRangeLevelThree = config.get(sectionAttractor.name, "attractorRangeLevelThree", attractorRangeLevelThree, 
        "The range of the mob attractor with an octadic capacitor upgrade").getInt(attractorRangeLevelThree);    
    attractorPowerPerTickLevelOne = config.get(sectionAttractor.name, "attractorPowerPerTickLevelOne", attractorPowerPerTickLevelOne, 
        "The RF/t  power use of a levele 1 mob attractor").getInt(attractorPowerPerTickLevelOne);
    attractorPowerPerTickLevelTwo = config.get(sectionAttractor.name, "attractorPowerPerTickLevelTwo", attractorPowerPerTickLevelTwo, 
        "The RF/t  power use of a levele 2 mob attractor").getInt(attractorPowerPerTickLevelTwo);
    attractorPowerPerTickLevelThree = config.get(sectionAttractor.name, "attractorPowerPerTickLevelThree", attractorPowerPerTickLevelThree, 
        "The RF/t  power use of a levele 3 mob attractor").getInt(attractorPowerPerTickLevelThree);
    
    
    spawnGuardRangeLevelOne = config.get(sectionAttractor.name, "spawnGuardRangeLevelOne", spawnGuardRangeLevelOne, 
        "The range of the spawn guard with no upgrades").getInt(spawnGuardRangeLevelOne);
    spawnGuardRangeLevelTwo = config.get(sectionAttractor.name, "spawnGuardRangeLevelTwo", spawnGuardRangeLevelTwo, 
        "The range of the spawn guard with a double layer capacitor upgrade").getInt(spawnGuardRangeLevelTwo);
    spawnGuardRangeLevelThree = config.get(sectionAttractor.name, "spawnGuardRangeLevelThree", spawnGuardRangeLevelThree, 
        "The range of the spawn guard with an octadic capacitor upgrade").getInt(spawnGuardRangeLevelThree);    
    spawnGuardPowerPerTickLevelOne = config.get(sectionAttractor.name, "spawnGuardPowerPerTickLevelOne", spawnGuardPowerPerTickLevelOne, 
        "The RF/t  power use of a levele 1 spawn guard").getInt(spawnGuardPowerPerTickLevelOne);
    spawnGuardPowerPerTickLevelTwo = config.get(sectionAttractor.name, "spawnGuardPowerPerTickLevelTwo", spawnGuardPowerPerTickLevelTwo, 
        "The RF/t  power use of a levele 2 spawn guard").getInt(spawnGuardPowerPerTickLevelTwo);
    spawnGuardPowerPerTickLevelThree = config.get(sectionAttractor.name, "spawnGuardPowerPerTickLevelThree", spawnGuardPowerPerTickLevelThree, 
        "The RF/t  power use of a levele 3 spawn guard").getInt(spawnGuardPowerPerTickLevelThree);
    spawnGuardStopAllSlimesDebug = config.getBoolean("spawnGuardStopAllSlimesDebug", sectionAttractor.name, spawnGuardStopAllSlimesDebug, 
        "When true slimes wont be allowed to spawn at all. Only added to aid testing in super flat worlds.");    
    spawnGuardStopAllSquidSpawning = config.getBoolean("spawnGuardStopAllSquidSpawning", sectionAttractor.name, spawnGuardStopAllSquidSpawning, 
        "When true no squid will be spawned.");

    weatherObeliskClearItem = config.get(sectionWeather.name, "weatherObeliskClearItem", weatherObeliskClearItem,
        "The item required to set the world to clear weather.").getString();
    weatherObeliskRainItem = config.get(sectionWeather.name, "weatherObeliskRainItem", weatherObeliskRainItem,
        "The item required to set the world to rainy weather.").getString();
    weatherObeliskThunderItem = config.get(sectionWeather.name, "weatherObeliskThunderItem", weatherObeliskThunderItem,
        "The item required to set the world to thundering weather.").getString();
    weatherObeliskClearPower = config.get(sectionWeather.name, "weatherObeliskClearPower", weatherObeliskClearPower,
        "The power required to set the world to clear weather").getInt();
    weatherObeliskRainPower = config.get(sectionWeather.name, "weatherObeliskRainPower", weatherObeliskRainPower,
        "The power required to set the world to rainy weather").getInt();
    weatherObeliskThunderPower = config.get(sectionWeather.name, "weatherObeliskThunderPower", weatherObeliskThunderPower,
        "The power required to set the world to thundering weather").getInt();

    // Loot Config
    lootDarkSteel = config.getBoolean("lootDarkSteel", sectionLootConfig.name, lootDarkSteel, "Adds Darksteel Ingots to loot tables");
    lootItemConduitProbe = config.getBoolean("lootItemConduitProbe", sectionLootConfig.name, lootItemConduitProbe, "Adds ItemConduitProbe to loot tables");
    lootQuartz = config.getBoolean("lootQuartz", sectionLootConfig.name, lootQuartz, "Adds quartz to loot tables");
    lootNetherWart = config.getBoolean("lootNetherWart", sectionLootConfig.name, lootNetherWart, "Adds nether wart to loot tables");
    lootEnderPearl = config.getBoolean("lootEnderPearl", sectionLootConfig.name, lootEnderPearl, "Adds ender pearls to loot tables");
    lootElectricSteel = config.getBoolean("lootElectricSteel", sectionLootConfig.name, lootElectricSteel, "Adds Electric Steel Ingots to loot tables");
    lootRedstoneAlloy = config.getBoolean("lootRedstoneAlloy", sectionLootConfig.name, lootRedstoneAlloy, "Adds Redstone Alloy Ingots to loot tables");
    lootPhasedIron = config.getBoolean("lootPhasedIron", sectionLootConfig.name, lootPhasedIron, "Adds Phased Iron Ingots to loot tables");
    lootPhasedGold = config.getBoolean("lootPhasedGold", sectionLootConfig.name, lootPhasedGold, "Adds Phased Gold Ingots to loot tables");
    lootTravelStaff = config.getBoolean("lootTravelStaff", sectionLootConfig.name, lootTravelStaff, "Adds Travel Staff to loot tables");
    lootTheEnder = config.getBoolean("lootTheEnder", sectionLootConfig.name, lootTheEnder, "Adds The Ender to loot tables");
    lootDarkSteelBoots = config.getBoolean("lootDarkSteelBoots", sectionLootConfig.name, lootDarkSteelBoots, "Adds Darksteel Boots to loot tables");
    
    enderRailEnabled = config.getBoolean("enderRailEnabled", sectionRailConfig.name, enderRailEnabled, "Wether Ender Rails are enabled");
    enderRailPowerRequireCrossDimensions = config.get(sectionRailConfig.name, "enderRailPowerRequireCrossDimensions", enderRailPowerRequireCrossDimensions, 
        "The amount of power required to transport a cart across dimensions").getInt(enderRailPowerRequireCrossDimensions);
    enderRailPowerRequiredPerBlock = config.get(sectionRailConfig.name, "enderRailPowerRequiredPerBlock", enderRailPowerRequiredPerBlock, 
        "The amount of power required to teleport a cart per block in the same dimension").getInt(enderRailPowerRequiredPerBlock);
    enderRailCapSameDimensionPowerAtCrossDimensionCost = config.getBoolean("enderRailCapSameDimensionPowerAtCrossDimensionCost", sectionRailConfig.name, enderRailCapSameDimensionPowerAtCrossDimensionCost, 
        "When set to true the RF cost of sending a cart within the same dimension will be capped to the cross dimension cost");
    enderRailTicksBeforeForceSpawningLinkedCarts = config.get(sectionRailConfig.name, "enderRailTicksBeforeForceSpawningLinkedCarts", enderRailTicksBeforeForceSpawningLinkedCarts, 
        "The number of ticks to wait for the track to clear before force spawning the next cart in a (RailCraft) linked set").getInt(enderRailTicksBeforeForceSpawningLinkedCarts);
    enderRailTeleportPlayers = config.getBoolean("enderRailTeleportPlayers", sectionRailConfig.name, enderRailTeleportPlayers, "If true player in minecarts will be teleported. WARN: WIP, seems to cause a memory leak.");
    
    dumpMobNames = config.getBoolean("dumpMobNames", sectionMobConfig.name, dumpMobNames, 
        "When set to true a list of all registered mobs will be dumped to config/enderio/mobTypes.txt The names are in the format required by EIOs mob blacklists.");
    
    xpObeliskMaxXpLevel = config.get(sectionMisc.name, "xpObeliskMaxXpLevel", xpObeliskMaxXpLevel, "Maximum level of XP the xp obelisk can contain.").getInt();
    xpJuiceName = config.getString("xpJuiceName", sectionMisc.name, xpJuiceName, "Id of liquid XP fluid (WARNING: only for users who know what they are doing - changing this id can break worlds) - this should match the with OpenBlocks when installed");
    
    clearGlassSameTexture = config.getBoolean("clearGlassSameTexture", sectionMisc.name, clearGlassSameTexture, "If true, quite clear glass will use the fused quartz border texture for the block instead of the white border.");
    clearGlassConnectToFusedQuartz = config.getBoolean("clearGlassConnectToFusedQuartz", sectionMisc.name, clearGlassConnectToFusedQuartz, "If true, quite clear glass will connect textures with fused quartz.");

    enchantmentSoulBoundEnabled = config.getBoolean("enchantmentSoulBoundEnabled", sectionEnchantments.name, enchantmentSoulBoundEnabled,
        "If false the soul bound enchantment will not be available");
    enchantmentSoulBoundId = config.get(sectionEnchantments.name, "enchantmentSoulBoundId", enchantmentSoulBoundId,
        "The id of the enchantment. If set to -1 the lowest unassigned id will be used.").getInt(enchantmentSoulBoundId);
    enchantmentSoulBoundWeight = config.get("enchantmentSoulBoundWeight", sectionEnchantments.name, enchantmentSoulBoundWeight,
        "The chance of getting this enchantment in the enchantment table").getInt(enchantmentSoulBoundWeight);


    replaceWitherSkeletons = config.get("replaceWitherSkeletons", sectionMisc.name, replaceWitherSkeletons,
            "Separates wither and normal skeletons into different entities, enables the powered spawner to treat them differently [EXPERIMENTAL - MAY CAUSE ISSUES WITH OTHER MODS]").getBoolean();
  }

  public static void init() {
    WeatherTask.CLEAR.setRequiredItem(getStackForString(weatherObeliskClearItem));
    WeatherTask.RAIN.setRequiredItem(getStackForString(weatherObeliskRainItem));
    WeatherTask.STORM.setRequiredItem(getStackForString(weatherObeliskThunderItem));
  }

  public static void postInit() {
    for (String s : hoeStrings) {
      ItemStack hoe = getStackForString(s);
      if(hoe != null) {
        farmHoes.add(hoe);
      }
    }
  }

  public static ItemStack getStackForString(String s) {
    String[] nameAndMeta = s.split(";");
    int meta = nameAndMeta.length == 1 ? 0 : Integer.parseInt(nameAndMeta[1]);
    String[] data = nameAndMeta[0].split(":");
    ItemStack stack = GameRegistry.findItemStack(data[0], data[1], 1);
    if(stack == null) {
      return null;
    }
    stack.setItemDamage(meta);
    return stack;
  }
  
  private Config() {
  }
}

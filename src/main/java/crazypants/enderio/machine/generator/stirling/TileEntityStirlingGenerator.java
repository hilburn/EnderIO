package crazypants.enderio.machine.generator.stirling;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import crazypants.enderio.ModObject;
import crazypants.enderio.config.Config;
import crazypants.enderio.machine.SlotDefinition;
import crazypants.enderio.machine.generator.AbstractGeneratorEntity;
import crazypants.enderio.network.PacketHandler;
import crazypants.enderio.power.Capacitors;
import crazypants.enderio.power.PowerDistributor;
import crazypants.util.BlockCoord;

public class TileEntityStirlingGenerator extends AbstractGeneratorEntity implements ISidedInventory {

  public static final int ENERGY_PER_TICK = Config.stirlingGeneratorBaseRfPerTick;

  // public for alloy smelter
  public static final String SOUND_NAME = "generator.stirling";

  /** How many ticks left until the item is burnt. */
  public int burnTime = 0;
  public int totalBurnTime;

  private PowerDistributor powerDis;
  
  public TileEntityStirlingGenerator() {
    super(new SlotDefinition(1, 0));        
  }

  @Override
  public String getMachineName() {
    return ModObject.blockStirlingGenerator.unlocalisedName;
  }

  @Override
  public String getInventoryName() {
    return "Stirling Generator";
  }

  @Override
  public boolean isMachineItemValidForSlot(int i, ItemStack itemstack) {
    return TileEntityFurnace.isItemFuel(itemstack);
  }

  @Override
  public int[] getAccessibleSlotsFromSide(int var1) {
    return new int[] { 0 };
  }

  @Override
  public boolean canInsertItem(int i, ItemStack itemstack, int j) {
    return isItemValidForSlot(i, itemstack);
  }

  @Override
  public boolean canExtractItem(int i, ItemStack itemstack, int j) {
    return !TileEntityFurnace.isItemFuel(itemstack);
  }

  @Override
  public boolean isActive() {
    return burnTime > 0;
  }

  @Override
  public float getProgress() {
    if(totalBurnTime <= 0) {
      return 0;
    }
    return (float) burnTime / (float) totalBurnTime;
  }

  @Override
  public String getSoundName() {
    return SOUND_NAME;
  }
  
  @Override
  public void readCustomNBT(NBTTagCompound nbtRoot) {
    super.readCustomNBT(nbtRoot);
    burnTime = nbtRoot.getInteger("burnTime");
    totalBurnTime = nbtRoot.getInteger("totalBurnTime");
  }

  @Override
  public void writeCustomNBT(NBTTagCompound nbtRoot) {
    super.writeCustomNBT(nbtRoot);
    nbtRoot.setInteger("burnTime", burnTime);
    nbtRoot.setInteger("totalBurnTime", totalBurnTime);
  }

  @Override
  public void onNeighborBlockChange(Block blockId) {
    super.onNeighborBlockChange(blockId);
    if(powerDis != null) {
      powerDis.neighboursChanged();
    }
  }

  @Override
  public int getPowerUsePerTick() {
    return Math.round(ENERGY_PER_TICK * getEnergyMultiplier());
  }

  public int getBurnTime(ItemStack item) {
    return Math.round(TileEntityFurnace.getItemBurnTime(item) / getBurnTimeMultiplier());
  }

  @Override
  protected boolean processTasks(boolean redstoneCheckPassed) {
    boolean needsUpdate = false;
    boolean sendBurnTimePacket = false;
    
    if(burnTime > 0) {
      if(getEnergyStored() < getMaxEnergyStored()) {
        setEnergyStored(getEnergyStored() + getPowerUsePerTick());        
      }
      burnTime--;
      sendBurnTimePacket = worldObj.getTotalWorldTime() % 20 == 1 || burnTime == 0;    
    }

    transmitEnergy();

    if(redstoneCheckPassed) {

      if(burnTime <= 0 && getEnergyStored() < getMaxEnergyStored()) {
        if(inventory[0] != null && inventory[0].stackSize > 0) {
          burnTime = getBurnTime(inventory[0]);
          if(burnTime > 0) {
            totalBurnTime = burnTime;
            ItemStack containedItem = inventory[0].getItem().getContainerItem(inventory[0]);
            if(containedItem != null) {
              inventory[0] = containedItem;
            } else {
              decrStackSize(0, 1);
            }
            needsUpdate = true;
          }
        }
      }
    }
    if(!needsUpdate && sendBurnTimePacket) {
      PacketHandler.sendToAllAround(new PacketBurnTime(this), this);
    }

    return needsUpdate;
  }

  @Override
  protected boolean doPush(ForgeDirection dir) {
    if(inventory[0] == null) {
      return false;
    }

    if(worldObj.getTotalWorldTime() % 20 != 0) {
      return false;
    }

    if(!canExtractItem(0, inventory[0], 0)) {
      return false;
    }

    BlockCoord loc = getLocation().getLocation(dir);
    TileEntity te = worldObj.getTileEntity(loc.x, loc.y, loc.z);

    return doPush(dir, te, 0, 0);
  }

  public static float getEnergyMultiplier(Capacitors capacitorType) {
    if(capacitorType == Capacitors.ACTIVATED_CAPACITOR) {
      return 2;
    } else if(capacitorType == Capacitors.ENDER_CAPACITOR) {
      return 4;
    }
    return 1;
  }

  private float getEnergyMultiplier() {
    return getEnergyMultiplier(getCapacitorType());
  }

  public static float getBurnTimeMultiplier(Capacitors capacitorType) {
    if(capacitorType == Capacitors.ACTIVATED_CAPACITOR) {
      return 1.5f;
    } else if(capacitorType == Capacitors.ENDER_CAPACITOR) {
      return 1.5f;
    }
    return 2;
  }

  public float getBurnTimeMultiplier() {
    return getBurnTimeMultiplier(getCapacitorType());
  }

  //private PowerDistributor powerDis;
  private boolean transmitEnergy() {
    if(powerDis == null) {
      powerDis = new PowerDistributor(new BlockCoord(this));
    }
    int canTransmit = Math.min(getEnergyStored(), getPowerUsePerTick() * 2);
    if(canTransmit <= 0) {
      return false;
    }
    int transmitted = powerDis.transmitEnergy(worldObj, canTransmit);
    setEnergyStored(getEnergyStored() - transmitted);    
    return transmitted > 0;
  }

  @Override
  public boolean hasCustomInventoryName() {
    return false;
  }
  
  
}

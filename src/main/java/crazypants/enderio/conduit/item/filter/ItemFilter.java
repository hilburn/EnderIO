package crazypants.enderio.conduit.item.filter;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import crazypants.enderio.conduit.item.NetworkedInventory;
import crazypants.enderio.network.NetworkUtil;
import crazypants.gui.GhostSlot;

public class ItemFilter implements IInventory, IItemFilter {

  private static final boolean DEFAULT_BLACKLIST = false;

  private static final boolean DEFAULT_META = true;

  private static final boolean DEFAULT_MBT = true;

  private static final boolean DEFAULT_ORE_DICT = false;

  private static final boolean DEFAULT_STICKY = false;

  boolean isBlacklist = DEFAULT_BLACKLIST;
  boolean matchMeta = true;
  boolean matchNBT = true;
  boolean useOreDict = false;
  boolean sticky = false;

  ItemStack[] items;

  final List<int[]> oreIds;

  private boolean isAdvanced; 

  public ItemFilter() {
    this(5, false);
  }
  
  public ItemFilter(boolean advanced) {
    this(advanced ? 10 : 5, advanced);
  }

  private ItemFilter(int numItems, boolean isAdvanced) {
    this.isAdvanced = isAdvanced;
    items = new ItemStack[numItems];
    oreIds = new ArrayList<int[]>(numItems);
    for(int i=0;i<numItems;i++) {
      oreIds.add(null);
    }
  }

  @Override
  public boolean doesFilterCaptureStack(NetworkedInventory inv, ItemStack item) {
    return isSticky() && itemMatched(item);
  }

  @Override
  public boolean doesItemPassFilter(NetworkedInventory inv, ItemStack item) {
    return doesItemPassFilter(item);
  }

  public boolean doesItemPassFilter(ItemStack item) {
    if(!isValid()) {
      return true;
    }
    boolean matched = itemMatched(item);
    return isBlacklist ? !matched : matched;
  }

  private boolean itemMatched(ItemStack item) {
    if(item == null) {
      return false;
    }
    boolean matched = false;
    int i = 0;
    for (ItemStack it : items) {
      if(it != null && Item.getIdFromItem(item.getItem()) == Item.getIdFromItem(it.getItem())) {
        matched = true;
        if(matchMeta && item.getItemDamage() != it.getItemDamage()) {
          matched = false;
        } else if(matchNBT && !isNBTMatch(item, it)) {
          matched = false;
        }        
      }
      if(!matched && useOreDict && isOreDicMatch(i, item)) {
        matched = true;
      }
      if(matched) {
        break;
      }
      i++;
    }
    return matched;
  }

  private boolean isOreDicMatch(int filterItemIndex, ItemStack item) {
    int[] ids1 = getCachedIds(filterItemIndex);
    if(ids1 == null || ids1.length == 0) {
      return false;
    }
    int[] ids2 = OreDictionary.getOreIDs(item);
    if(ids2 == null || ids2.length == 0) {
      return false;
    }
    for(int id1 : ids1) {
      for(int id2 : ids2) {
        if(id1 == id2) {
          return true;
        }
      }
    }    
    return false;
  }
  
  private boolean isNBTMatch(ItemStack filter, ItemStack item)
  {
    if (filter.stackTagCompound == null && item.stackTagCompound == null) return true;
    if (filter.stackTagCompound == null || item.stackTagCompound == null) return false;
    if (!filter.getTagCompound().hasKey("GEN")) return filter.stackTagCompound.equals(item.stackTagCompound);
    NBTTagCompound filterTag = (NBTTagCompound) filter.getTagCompound().copy();
    NBTTagCompound itemTag = (NBTTagCompound) item.getTagCompound().copy();
    filterTag.removeTag("GEN");
    itemTag.removeTag("GEN");
    return filterTag.equals(itemTag);
  }

  private int[] getCachedIds(int filterItemIndex) {   
    int[] res = oreIds.get(filterItemIndex);
    if(res == null) {
      ItemStack item = items[filterItemIndex];
      if(item == null) {
        res = new int[0];
      } else {
        res = OreDictionary.getOreIDs(item);
        if(res == null) {
          res = new int[0];
        }
      }
      oreIds.set(filterItemIndex, res);
    }
    return res;
  }

  @Override
  public boolean isValid() {
    for (ItemStack item : items) {
      if(item != null) {
        return true;
      }
    }
    return false;
  }

  public boolean isBlacklist() {
    return isBlacklist;
  }

  public void setBlacklist(boolean isBlacklist) {
    this.isBlacklist = isBlacklist;
  }

  public boolean isMatchMeta() {
    return matchMeta;
  }

  public void setMatchMeta(boolean matchMeta) {
    this.matchMeta = matchMeta;
  }

  public boolean isMatchNBT() {
    return matchNBT;
  }

  public void setMatchNBT(boolean matchNbt) {
    matchNBT = matchNbt;
  }

  public boolean isUseOreDict() {
    return useOreDict;
  }

  public void setUseOreDict(boolean useOreDict) {
    this.useOreDict = useOreDict;
  }

  @Override
  public boolean isSticky() {
    return sticky;
  }

  public void setSticky(boolean sticky) {
    this.sticky = sticky;
  }

  @Override
  public void writeToNBT(NBTTagCompound nbtRoot) {
    nbtRoot.setBoolean("isBlacklist", isBlacklist);
    nbtRoot.setBoolean("matchMeta", matchMeta);
    nbtRoot.setBoolean("matchNBT", matchNBT);
    nbtRoot.setBoolean("useOreDict", useOreDict);
    nbtRoot.setBoolean("sticky", sticky);
    nbtRoot.setBoolean("isAdvanced", isAdvanced);

    int i = 0;
    for (ItemStack item : items) {
      NBTTagCompound itemTag = new NBTTagCompound();
      if(item != null) {
        item.writeToNBT(itemTag);
        nbtRoot.setTag("item" + i, itemTag);
      }
      i++;
    }

  }

  @Override
  public void readFromNBT(NBTTagCompound nbtRoot) {
    isBlacklist = nbtRoot.getBoolean("isBlacklist");
    matchMeta = nbtRoot.getBoolean("matchMeta");
    matchNBT = nbtRoot.getBoolean("matchNBT");
    useOreDict = nbtRoot.getBoolean("useOreDict");
    sticky = nbtRoot.getBoolean("sticky");
    isAdvanced = nbtRoot.getBoolean("isAdvanced");

    int numItems = isAdvanced ? 10 : 5;
    items = new ItemStack[numItems];
    oreIds.clear();
    for(int i=0;i<numItems;i++) {
      oreIds.add(null);
    }
    for (int i = 0; i < numItems; i++) {      
      NBTBase tag = nbtRoot.getTag("item" + i);
      if(tag instanceof NBTTagCompound) {
        items[i] = ItemStack.loadItemStackFromNBT((NBTTagCompound) tag);
      } else {
        items[i] = null;
      }
    }
  }

  @Override
  public void writeToByteBuf(ByteBuf buf) {
    NBTTagCompound root = new NBTTagCompound();
    writeToNBT(root);
    NetworkUtil.writeNBTTagCompound(root, buf);
  }

  @Override
  public void readFromByteBuf(ByteBuf buf) {
    NBTTagCompound tag = NetworkUtil.readNBTTagCompound(buf);
    readFromNBT(tag);
  }

  @Override
  public int getSizeInventory() {
    return items.length;
  }

  @Override
  public ItemStack getStackInSlot(int i) {
    if(i < 0 || i >= items.length) {
      return null;
    }
    return items[i];
  }

  @Override
  public ItemStack decrStackSize(int fromSlot, int amount) {
    oreIds.set(fromSlot, null);
    ItemStack item = items[fromSlot];
    items[fromSlot] = null;
    if(item == null) {
      return null;
    }
    item.stackSize = 0;
    return item;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int i) {
    return null;
  }

  @Override
  public void setInventorySlotContents(int i, ItemStack itemstack) {
    if(itemstack != null) {
      items[i] = itemstack.copy();
      items[i].stackSize = 0;
    } else {
      items[i] = null;
    }
    oreIds.set(i, null);
  }

  @Override
  public String getInventoryName() {
    return "Item Filter";
  }

  @Override
  public int getInventoryStackLimit() {
    return 0;
  }

  @Override
  public boolean hasCustomInventoryName() {
    return false;
  }

  @Override
  public void markDirty() {    
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer entityplayer) {
    return true;
  }

  @Override
  public void openInventory() {
  }

  @Override
  public void closeInventory() {
  }

  @Override
  public boolean isItemValidForSlot(int i, ItemStack itemstack) {
    return true;
  }

  @Override
  public void createGhostSlots(List<GhostSlot> slots, int xOffset, int yOffset, Runnable cb) {
    int topY = yOffset;
    int leftX = xOffset;
    int index = 0;    
    int numRows = isAdvanced ? 2 : 1;
    for (int row = 0; row < numRows; ++row) {
      for (int col = 0; col < 5; ++col) {
        int x = leftX + col * 18;
        int y = topY + row * 20;
        slots.add(new ItemFilterGhostSlot(index, x, y, cb));
        index++;
      }
    }
  }

  @Override
  public int getSlotCount() { 
    return getSizeInventory();
  }

  public boolean isAdvanced() {    
    return isAdvanced;
  }
  
  public boolean isDefault() {
    return !isAdvanced && !isValid() && isBlacklist == DEFAULT_BLACKLIST &&
        matchMeta == DEFAULT_META &&
        matchNBT == DEFAULT_MBT &&
        useOreDict == DEFAULT_ORE_DICT &&
        sticky == DEFAULT_STICKY;
  }

  @Override
  public String toString() {
//    return "ItemFilter [isBlacklist=" + isBlacklist + ", matchMeta=" + matchMeta + ", matchNBT=" + matchNBT + ", useOreDict=" + useOreDict + ", sticky="
//        + sticky + ", items=" + Arrays.toString(items) + ", oreIds=" + Arrays.toString(oreIds) + ", isAdvanced=" + isAdvanced + "]";
    return "ItemFilter [isAdvanced=" + isAdvanced + ", items=" + Arrays.toString(items)  + "]";
  }

  class ItemFilterGhostSlot extends GhostSlot {
    private final int slot;
    private final Runnable cb;

    ItemFilterGhostSlot(int slot, int x, int y, Runnable cb) {
      this.x = x;
      this.y = y;
      this.slot = slot;
      this.cb = cb;
    }

    @Override
    public void putStack(ItemStack stack) {
      if(stack != null) {
        stack = stack.copy();
        stack.stackSize = 1;
      }
      items[slot] = stack;
      cb.run();
    }

    @Override
    public ItemStack getStack() {
      return items[slot];
    }
  }
}

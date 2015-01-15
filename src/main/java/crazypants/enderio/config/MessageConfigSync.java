package crazypants.enderio.config;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage>
{
  protected byte[] bytes;

  public MessageConfigSync()
  {
    bytes = getSyncMessage(Config.class);
  }

  /**
   * Convert from the supplied buffer into your specific message type
   *
   * @param buf
   */
  @Override
  public void fromBytes(ByteBuf buf)
  {
    bytes = buf.array();
  }

  /**
   * Deconstruct your message into the supplied byte buffer
   *
   * @param buf
   */
  @Override
  public void toBytes(ByteBuf buf)
  {
    buf.setBytes(0, bytes);
  }

  /**
   * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
   * is needed.
   *
   * @param message The message
   * @param ctx
   * @return an optional return message
   */
  @Override
  public IMessage onMessage(MessageConfigSync message, MessageContext ctx)
  {
    if (ctx.side == Side.CLIENT)
    {
      loadSyncMessage(message.bytes);
      Config.postInit();
    }
    return null;
  }

  /**
   * Example usage: byte[] bytes = getSyncMessage(Config.class)
   *
   * @param clazz class to be synced
   * @return byte array representation of all static, non-final fields in the class
   */
  public static byte[] getSyncMessage(Class clazz)
  {
    Field[] fields = clazz.getFields();
    NBTTagCompound nbtConfig = new NBTTagCompound();
    nbtConfig.setString("C", clazz.getName());
    nbtConfig.setShort("L", (short)fields.length);
    for (short i = 0; i < fields.length; i++)
    {
      try
      {
        Field field = fields[i];
        int mod = field.getModifiers();
        if (Modifier.isStatic(mod) && !Modifier.isFinal(mod))
        {
          Object obj = field.get(null);
          if (obj == null) continue;
          NBTTagCompound tagCompound = NBTHelper.writeToNBT(obj);
          if (!tagCompound.hasNoTags()) nbtConfig.setTag(String.valueOf(i), tagCompound);
        }
      } catch (IllegalAccessException e)
      {
      }
    }
    byte[] bytes = new byte[0];
    try
    {
      bytes = CompressedStreamTools.compress(nbtConfig);
    } catch (IOException e)
    {
    }
    return bytes;
  }


  /**
   * Unpacks a byte array and sets all static fields to their new synced values
   *
   * @param bytes byte array generated by getSyncMessage
   */
  public static void loadSyncMessage(byte[] bytes)
  {
    try
    {
      NBTTagCompound config = CompressedStreamTools.func_152457_a(bytes, new NBTSizeTracker(bytes.length * 8));
      Field[] fields = Class.forName(config.getString("C")).getFields();
      short length = config.getShort("L");
      for (short i = 0; i < length; i++)
      {
        Object obj = NBTHelper.rawFromNBT(config.getCompoundTag(String.valueOf(i)));
        if (obj != null)
          fields[i].set(null, obj);
      }
    } catch (Exception e)
    {
    }
  }
}

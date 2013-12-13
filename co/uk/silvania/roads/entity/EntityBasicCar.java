package co.uk.silvania.roads.entity;

import co.uk.silvania.roads.PacketHandler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBasicCar extends Entity implements IEntityAdditionalSpawnData {
	
	private boolean redCar;
	String forward = "";

	public EntityBasicCar(World world) {
		super(world);
		setSize(4.0F, 2.0F);
	}
	
	public boolean isRed() {
		return redCar;
	}
	
	public void setRed() {
		redCar = true;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}
	
	@Override
	public boolean isInRangeToRenderVec3D(Vec3 vec) {
		return true;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity entity) {
		if (entity != riddenByEntity) {
			return entity.boundingBox;	
		} else {
			return null;
		}
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!worldObj.isRemote && riddenByEntity == null) {
			player.mountEntity(this);
		}
		return true;
	}
	
	@Override
	public double getMountedYOffset() {
		return 0.75;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!worldObj.isRemote) {
			if (worldObj.isAirBlock((int) posX, (int)posY - 1, (int)posZ)) {
				motionY = -0.5;
			} else {
				motionY = 0;
			}
			if (riddenByEntity != null && worldObj.isAirBlock((int)posX + 1, (int)posY, (int)posZ)) {
				if (forward.equals("true")) {
					motionZ = 0.5;
				}
			} else {
				motionZ = 0;
			}
		} else {
			sendInformation();
		}
		
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}
	
	private boolean lastPressedState;
	
	private void sendInformation() {
		boolean state = Minecraft.getMinecraft().gameSettings.keyBindJump.pressed;
		boolean pressedForward = Minecraft.getMinecraft().gameSettings.keyBindForward.isPressed();
		
		if (pressedForward && !lastPressedState && riddenByEntity == Minecraft.getMinecraft().thePlayer) {
			PacketHandler.sendCarMovePacket(this);
		}
		
		lastPressedState = pressedForward;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		redCar = nbt.getBoolean("RedCar");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("RedCar", redCar);
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeBoolean(redCar);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		redCar = data.readBoolean();
	}

	public void doDrop() {
		System.out.println("You did a drop!");
		if (!worldObj.isRemote) {
			System.out.println("Checking if you're riding it and it can move");
			if (riddenByEntity != null && worldObj.isAirBlock((int)posX + 1, (int)posY, (int)posZ)) {
				System.out.println("Correct");
				motionZ = 0.5;
			} else {
				System.out.println("Incorrect");
				motionZ = 0;
			}
		}
		System.out.println("Moving you");
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}
}

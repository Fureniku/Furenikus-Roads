package co.uk.silvania.roads.entity;

import java.util.Random;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class EntityVehicle extends EntityBoat {

    private boolean field_70279_a;
	private boolean hasBeenDyed; //False if the Vehicle has not been dyed by Player interaction. Will be Depreciated soon
    public double speedMultiplier; //The speed at which the Vehicle travels.
	public boolean canFireArrows = true; //Whether or not the vehicle can fire arrows when the "R" key is pressed
	public boolean arrowsCrit = true;//Whether arrows fired display the critical hit particles 
	public String speedPar = "splash"; //Particles the Vehicle emits when it reaches a certain speed. Used by boats to generate splash, and by Airships for smoke
    int fuel; //Amount of fuel currently in the vehicle
	int fuelSource = Item.coal.itemID; //The itemID to be used as a fuel source. Multiple fuel sources will come later
    int colour; // the colour (as a dye metadata)
	int rateOfFire; //Amount of time it takes before the first arrow can be fired
	int actualRateOfFire; //Time it takes between arrows firing after the first arrow is fired
	int ammo = Item.arrow.itemID; //The itemID of the item to be used as ammo

    public EntityVehicle(World par1World) {
        super(par1World);
        this.field_70279_a = true;
        this.speedMultiplier = 0.07D; 
        this.preventEntitySpawning = true; 
        this.setSize(1.5F, 0.6F);
        this.height *= 8.0F;
        this.yOffset = this.height / 16.0F;
        this.fuel = 0; //Start off with no fuel
        this.colour = 14; //Default colour
		this.rateOfFire = 20; //Default starting rate of fire
		this.actualRateOfFire = 15; //Default actual rate of fire
		this.hasBeenDyed = false; //Vehicle has not been dyed
    }
	
	public void crashVehicle()//Called when the player crashes
	{
	//What exactly happens when the player crashes
	}
	
	public void onFireKeyPressed() //Called every time the fire key is pressed, the player has ammo and the vehile can shoot arrows
	{
		fireArrow();
	}
	
	public void onPlayerBreak() //Called when a player breaks the vehicle
	{
		this.setDead();
	}
	
	public void onFuelAddedByInteract(ItemStack var2, EntityPlayer var1)
	{
		//Called when fuel is added to the Vehicle by right click.
		this.fuel += 100;
        if (!var1.capabilities.isCreativeMode && var2.stackSize > 0)
        {
            --var2.stackSize;
        }
	}

	public void fireArrow() //Fires an arrow
	{
		Vec3 vec = ((EntityPlayer)this.riddenByEntity).getLook(1.0F);
		double d8 = 4D;
		double d1 = this.posX + vec.xCoord * d8;
		double d2 = this.posY + (double) (height / 4.0F);
		double d3 = this.posZ + vec.zCoord * d8;
		EntityArrow arrow = new EntityArrow(this.worldObj, ((EntityPlayer)this.riddenByEntity), 1.0F);
		arrow.setLocationAndAngles(d1,d2,d3,2.6F,6F);
		arrow.setDamage(1.0D);
		arrow.setIsCritical(arrowsCrit);
		if (!this.worldObj.isRemote && this.rateOfFire == 0)
		{
			this.worldObj.spawnEntityInWorld(arrow); 
			this.worldObj.playSoundAtEntity(((EntityPlayer)this.riddenByEntity), "random.bow", 1.0F,1.0F / (new Random().nextFloat() * 0.4F + 0.8F));
			((EntityPlayer)this.riddenByEntity).inventory.consumeInventoryItem(ammo);
			rateOfFire = actualRateOfFire;
		}
	}
     
    protected boolean canTriggerWalking()//returns if this entity triggers Block.onEntityWalking on the blocks they walk on. Causes mobs to trample crops etc.
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
		this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
    }

   
    //Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be pushable on contact.
	
    public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return par1Entity.boundingBox;
    }

  
    //Returns true if the Entity can be pushed by other mobs.
    public boolean canBePushed()
    {
        return true;
    }

    public EntityVehicle(World par1World, double par2, double par4, double par6)
    {
        this(par1World);
        this.setPosition(par2, par4 + (double)this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }
	
	public int getColor()
    {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    public void setColor(int var1)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(var1 & 15)));
    }
	
	public void onFuelAdded()
	{
		//Called when fuel is added to the Vehicle. Useful for playing sounds or for GUI
	}
	
	public void playerGotOn(EntityPlayer par1EntityPlayer)
	{
		//Called when a player mounts the Vehicle
	}
	
	public void playerGotOff(EntityPlayer par1EntityPlayer)
	{
		//Called when the player gets off the Vehicle - Untested - Can anyone confirm if this works?
	}
	

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            boolean var3 = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

            if (var3 || this.getDamageTaken() > 40.0F)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                this.onPlayerBreak();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

   
    public void onUpdate()
    {
        super.onUpdate();
		
		if (this.rateOfFire > 0)
		{
			this.rateOfFire -= 1;
		}
		
		if (Keyboard.isKeyDown(19) && ((EntityPlayer)this.riddenByEntity).inventory.hasItem(ammo) && canFireArrows == true)
        {	
			this.onFireKeyPressed();
		}
		

        if (this.riddenByEntity != null && this.fuel == 0 && ((EntityPlayer)this.riddenByEntity).inventory.hasItem(fuelSource))
        {
            this.fuel += 100;
			this.onFuelAdded();
            ((EntityPlayer)this.riddenByEntity).inventory.consumeInventoryItem(fuelSource);
        }
		
		double var23 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
		if (this.isCollidedHorizontally && var23 > 0.2D)
            {
                if (!this.worldObj.isRemote && !this.isDead)
                {
					this.crashVehicle();
					this.setDead();
				}
			}		
    }


    protected void writeEntityToNBT(NBTTagCompound var1) {
		if (this.fuel > 0)
        {
            var1.setInteger("fuel", this.fuel);
        }
		//Important NBT tags
        var1.setInteger("colour", this.colour);
        var1.setByte("collarColor", (byte)this.getColor());
		var1.setBoolean("hasBeenDyed", this.hasBeenDyed);
		
		//Tags for map makers/modders
		var1.setInteger("fuelSource", this.fuelSource);
		var1.setInteger("rateOfFire", this.rateOfFire);
		var1.setInteger("actualRateOfFire", this.actualRateOfFire);
		var1.setBoolean("canFireArrows", this.canFireArrows);
		var1.setBoolean("arrowsCrit", this.arrowsCrit);
	}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1) {
		//Loading important NBT tags
        if (var1.hasKey("fuel"))
        {
            this.fuel = var1.getInteger("fuel");
        }
        else
        {
            this.fuel = 0;
        }

        if (var1.hasKey("colour"))
        {
            this.colour = var1.getInteger("colour");
        }

        if (var1.hasKey("CollarColor"))
        {
            this.setColor(var1.getByte("CollarColor"));
        }
		
		if (var1.hasKey("hasBeenDyed"))
        {
            this.hasBeenDyed = var1.getBoolean("hasBeenDyed");
        }
		
		//Load NBT tags for map makers/modders
		if (var1.hasKey("fuelSource"))
        {
            this.fuelSource = var1.getInteger("fuelSource");
        }
		if (var1.hasKey("rateOfFire"))
        {
            this.rateOfFire = var1.getInteger("rateOfFire");
        }
		if (var1.hasKey("actualRateOfFire"))
        {
            this.colour = var1.getInteger("actualRateOfFire");
        }
		if (var1.hasKey("canFireArrows"))
        {
            this.canFireArrows = var1.getBoolean("canFireArrows");
        }
		if (var1.hasKey("arrowsCrit"))
        {
            this.arrowsCrit= var1.getBoolean("arrowsCrit");
        }
	}


    public boolean func_130002_c(EntityPlayer var1)//Called when the player interacts with the vehicle
    {
        ItemStack var2 = var1.inventory.getCurrentItem();
        if (var2 != null)
        {
            if (var2.itemID != fuelSource && var2.itemID != Item.dyePowder.itemID)
            {
                if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != var1)
                {
                    return true;
					
                }

                if (!this.worldObj.isRemote)
                {
                    var1.mountEntity(this);
					this.playerGotOn(var1);
                }
            }

            if (var2.itemID == fuelSource)
            {
				this.onFuelAddedByInteract(var2, var1);
            }

            if (var2.stackSize <= 0)
            {
                var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
            }
        }
        else
        {
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != var1)
            {
                return true;
            }

            if (!this.worldObj.isRemote)
            {
                var1.mountEntity(this);
            }
        }
		if (!var1.capabilities.isCreativeMode)
		{
        var1.capabilities.disableDamage = false;
		}
        return true;
    }

    public void func_70270_d(boolean par1)
    {
        this.field_70279_a = par1;
    }
}



/*package co.uk.silvania.roads.entity;

import java.util.Random;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class EntityVehicle extends EntityBoat {

	private boolean field_70279_a;
	private boolean hasBeenDyed; //False if the Vehicle has not been dyed by Player interaction. Will be Depreciated soon
    public double speedMultiplier; //The speed at which the Vehicle travels.
	public boolean canFireArrows = true; //Whether or not the vehicle can fire arrows when the "R" key is pressed
	public boolean arrowsCrit = true;//Whether arrows fired display the critical hit particles 
	public String speedPar = "splash"; //Particles the Vehicle emits when it reaches a certain speed. Used by boats to generate splash, and by Airships for smoke
    int fuel; //Amount of fuel currently in the vehicle
	int fuelSource = Item.coal.itemID; //The itemID to be used as a fuel source. Multiple fuel sources will come later
    int colour; // the colour (as a dye metadata)
	int rateOfFire; //Amount of time it takes before the first arrow can be fired
	int actualRateOfFire; //Time it takes between arrows firing after the first arrow is fired
	int ammo = Item.arrow.itemID; //The itemID of the item to be used as ammo


    public EntityVehicle(World par1World) {
        super(par1World);
    	System.out.println("Stage 0");
        this.field_70279_a = true;
        this.speedMultiplier = 0.07D; 
        this.preventEntitySpawning = true; 
        this.setSize(1.5F, 0.6F);
        this.height *= 8.0F;
        this.yOffset = this.height / 16.0F;
        this.fuel = 0; //Start off with no fuel
        this.colour = 14; //Default colour
		this.rateOfFire = 20; //Default starting rate of fire
		this.actualRateOfFire = 15; //Default actual rate of fire
		this.hasBeenDyed = false; //Vehicle has not been dyed
    }
	
	public void crashVehicle() {
	}
	
	public void onFireKeyPressed() {
		fireArrow();
	}
	
	public void onPlayerBreak() {
		this.setDead();
	}
	
	public void onFuelAddedByInteract(ItemStack var2, EntityPlayer var1) {
		this.fuel += 100;
        if (!var1.capabilities.isCreativeMode && var2.stackSize > 0) {
            --var2.stackSize;
        }
	}

	public void fireArrow() {
		Vec3 vec = ((EntityPlayer)this.riddenByEntity).getLook(1.0F);
		double d8 = 4D;
		double d1 = this.posX + vec.xCoord * d8;
		double d2 = this.posY + (double) (height / 4.0F);
		double d3 = this.posZ + vec.zCoord * d8;
		EntityArrow arrow = new EntityArrow(this.worldObj, ((EntityPlayer)this.riddenByEntity), 1.0F);
		arrow.setLocationAndAngles(d1,d2,d3,2.6F,6F);
		arrow.setDamage(1.0D);
		arrow.setIsCritical(arrowsCrit);
		if (!this.worldObj.isRemote && this.rateOfFire == 0) {
			this.worldObj.spawnEntityInWorld(arrow); 
			this.worldObj.playSoundAtEntity(((EntityPlayer)this.riddenByEntity), "random.bow", 1.0F,1.0F / (new Random().nextFloat() * 0.4F + 0.8F));
			((EntityPlayer)this.riddenByEntity).inventory.consumeInventoryItem(ammo);
			rateOfFire = actualRateOfFire;
		}
	}
     
    protected boolean canTriggerWalking() {
        return false;
    }

    protected void entityInit() {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
		this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
    }

   

    public AxisAlignedBB getCollisionBox(Entity par1Entity) {
        return par1Entity.boundingBox;
    }
    
    public boolean canBePushed() {
        return true;
    }

    public EntityVehicle(World par1World, double par2, double par4, double par6) {
        this(par1World);
        this.setPosition(par2, par4 + (double)this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }
	
	public int getColor() {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    public void setColor(int var1) {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(var1 & 15)));
    }
	
	public void onFuelAdded() {

	}
	
	public void playerGotOn(EntityPlayer par1EntityPlayer) {
	}
	
	public void playerGotOff(EntityPlayer par1EntityPlayer) {
	}
	

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        
        else if (!this.worldObj.isRemote && !this.isDead) {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            boolean var3 = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

            if (var3 || this.getDamageTaken() > 40.0F) {
                if (this.riddenByEntity != null) {
                    this.riddenByEntity.mountEntity(this);
                }

                this.onPlayerBreak();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

   
    public void onUpdate() {
        super.onUpdate();
		
		if (this.rateOfFire > 0) {
			this.rateOfFire -= 1;
		}
		
		if (Keyboard.isKeyDown(19) && ((EntityPlayer)this.riddenByEntity).inventory.hasItem(ammo) && canFireArrows == true) {	
			this.onFireKeyPressed();
		}
		

        if (this.riddenByEntity != null && this.fuel == 0 && ((EntityPlayer)this.riddenByEntity).inventory.hasItem(fuelSource)) {
            this.fuel += 100;
			this.onFuelAdded();
            ((EntityPlayer)this.riddenByEntity).inventory.consumeInventoryItem(fuelSource);
        }
		
		double var23 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
		if (this.isCollidedHorizontally && var23 > 0.2D) {
                if (!this.worldObj.isRemote && !this.isDead) {
					this.crashVehicle();
					this.setDead();
				}
			}		
    }


    protected void writeEntityToNBT(NBTTagCompound var1) {
		if (this.fuel > 0) {
            var1.setInteger("fuel", this.fuel);
        }
        var1.setInteger("colour", this.colour);
        var1.setByte("collarColor", (byte)this.getColor());
		var1.setBoolean("hasBeenDyed", this.hasBeenDyed);
		
		var1.setInteger("fuelSource", this.fuelSource);
		var1.setInteger("rateOfFire", this.rateOfFire);
		var1.setInteger("actualRateOfFire", this.actualRateOfFire);
		var1.setBoolean("canFireArrows", this.canFireArrows);
		var1.setBoolean("arrowsCrit", this.arrowsCrit);
	}

    protected void readEntityFromNBT(NBTTagCompound var1) {
		//Loading important NBT tags
        if (var1.hasKey("fuel")) {
            this.fuel = var1.getInteger("fuel");
        } else {
            this.fuel = 0;
        }

        if (var1.hasKey("colour")) {
            this.colour = var1.getInteger("colour");
        }

        if (var1.hasKey("CollarColor")) {
            this.setColor(var1.getByte("CollarColor"));
        }
		
		if (var1.hasKey("hasBeenDyed")) {
            this.hasBeenDyed = var1.getBoolean("hasBeenDyed");
        }
		
		if (var1.hasKey("fuelSource")) {
            this.fuelSource = var1.getInteger("fuelSource");
        }
		
		if (var1.hasKey("rateOfFire")) {
            this.rateOfFire = var1.getInteger("rateOfFire");
        }
		
		if (var1.hasKey("actualRateOfFire")) {
            this.colour = var1.getInteger("actualRateOfFire");
        }
		
		if (var1.hasKey("canFireArrows")) {
            this.canFireArrows = var1.getBoolean("canFireArrows");
        }
		
		if (var1.hasKey("arrowsCrit"))
        {
            this.arrowsCrit= var1.getBoolean("arrowsCrit");
        }
	}


    public boolean onPlayerInteract(EntityPlayer var1) {
        ItemStack var2 = var1.inventory.getCurrentItem();
        if (var2 != null) {
            if (var2.itemID != fuelSource && var2.itemID != Item.dyePowder.itemID) {
                if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != var1) {
                    return true;
                }

                if (!this.worldObj.isRemote) {
                    var1.mountEntity(this);
					this.playerGotOn(var1);
                }
            }

            if (var2.itemID == fuelSource) {
				this.onFuelAddedByInteract(var2, var1);
            }

            if (var2.stackSize <= 0) {
                var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
            }
        } else {
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != var1) {
                return true;
            }

            if (!this.worldObj.isRemote) {
                var1.mountEntity(this);
            }
        }
        
		if (!var1.capabilities.isCreativeMode) {
			var1.capabilities.disableDamage = false;
		}
        return true;
    }

    public void func_70270_d(boolean par1) {
        this.field_70279_a = par1;
    }
}*/

package co.uk.silvania.roads.liquid;

import java.util.Random;

import co.uk.silvania.roads.Roads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class FlowingLiquidBlock extends BlockFlowing implements IFluidBlock {
	
    int numAdjacentSources;
    boolean[] isOptimalFlowDirection = new boolean[4];
    int[] flowCost = new int[4];
    
    public FlowingLiquidBlock(int id) {
		super(id, Material.water);
		this.setCreativeTab(Roads.tabRoads);
		this.setHardness(100.0F);
		this.setLightOpacity(1);
		this.setTickRandomly(true);
	}
    
    private void updateFlow(World par1World, int par2, int par3, int par4) {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, this.blockID + 1, l, 2);
    }
    
    private boolean blockBlocksFlow(World par1World, int par2, int par3, int par4) {
        int l = par1World.getBlockId(par2, par3, par4);

        if (l != Block.doorWood.blockID && l != Block.doorIron.blockID && l != Block.signPost.blockID && l != Block.ladder.blockID && l != Block.reed.blockID) {
            if (l == 0) {
                return false;
            } else {
                Material material = Block.blocksList[l].blockMaterial;
                return material == Material.portal ? true : material.blocksMovement();
            }
        } else {
            return true;
        }
    }
    
    private boolean[] getOptimalFlowDirections(World par1World, int par2, int par3, int par4) {
        int l;
        int i1;

        for (l = 0; l < 4; ++l) {
            this.flowCost[l] = 1000;
            i1 = par2;
            int j1 = par4;

            if (l == 0) {
                i1 = par2 - 1;
            }

            if (l == 1) {
                ++i1;
            }

            if (l == 2) {
                j1 = par4 - 1;
            }

            if (l == 3) {
                ++j1;
            }

            if (!this.blockBlocksFlow(par1World, i1, par3, j1) && (par1World.getBlockMaterial(i1, par3, j1) != this.blockMaterial || par1World.getBlockMetadata(i1, par3, j1) != 0)) {
                if (this.blockBlocksFlow(par1World, i1, par3 - 1, j1)) {
                    this.flowCost[l] = this.calculateFlowCost(par1World, i1, par3, j1, 1, l);
                } else {
                    this.flowCost[l] = 0;
                }
            }
        }

        l = this.flowCost[0];

        for (i1 = 1; i1 < 4; ++i1) {
            if (this.flowCost[i1] < l) {
                l = this.flowCost[i1];
            }
        }

        for (i1 = 0; i1 < 4; ++i1) {
            this.isOptimalFlowDirection[i1] = this.flowCost[i1] == l;
        }

        return this.isOptimalFlowDirection;
    }
    
    private int calculateFlowCost(World par1World, int par2, int par3, int par4, int par5, int par6) {
        int j1 = 1000;

        for (int k1 = 0; k1 < 4; ++k1) {
            if ((k1 != 0 || par6 != 1) && (k1 != 1 || par6 != 0) && (k1 != 2 || par6 != 3) && (k1 != 3 || par6 != 2)) {
                int l1 = par2;
                int i2 = par4;

                if (k1 == 0) {
                    l1 = par2 - 1;
                }

                if (k1 == 1) {
                    ++l1;
                }

                if (k1 == 2) {
                    i2 = par4 - 1;
                }

                if (k1 == 3) {
                    ++i2;
                }

                if (!this.blockBlocksFlow(par1World, l1, par3, i2) && (par1World.getBlockMaterial(l1, par3, i2) != this.blockMaterial || par1World.getBlockMetadata(l1, par3, i2) != 0)) {
                    if (!this.blockBlocksFlow(par1World, l1, par3 - 1, i2)) {
                        return par5;
                    }

                    if (par5 < 4) {
                        int j2 = this.calculateFlowCost(par1World, l1, par3, i2, par5 + 1, k1);

                        if (j2 < j1) {
                            j1 = j2;
                        }
                    }
                }
            }
        }

        return j1;
    }

	
	@Override
	public int getRenderType() {
		return 4;
	}

	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int l = this.getFlowDecay(par1World, par2, par3, par4);
        byte b0 = 1;

        if (this.blockMaterial == Material.lava && !par1World.provider.isHellWorld) {
            b0 = 2;
        }

        boolean flag = true;
        int i1 = this.tickRate(par1World);
        int j1;

        if (l > 0) {
            byte b1 = -100;
            this.numAdjacentSources = 0;
            int k1 = this.getSmallestFlowDecay(par1World, par2 - 1, par3, par4, b1);
            k1 = this.getSmallestFlowDecay(par1World, par2 + 1, par3, par4, k1);
            k1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 - 1, k1);
            k1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 + 1, k1);
            j1 = k1 + b0;

            if (j1 >= 8 || k1 < 0) {
                j1 = -1;
            }

            if (this.getFlowDecay(par1World, par2, par3 + 1, par4) >= 0) {
                int l1 = this.getFlowDecay(par1World, par2, par3 + 1, par4);

                if (l1 >= 8) {
                    j1 = l1;
                } else {
                    j1 = l1 + 8;
                }
            }

            if (this.blockMaterial == Material.lava && l < 8 && j1 < 8 && j1 > l && par5Random.nextInt(4) != 0) {
                i1 *= 4;
            }

            if (j1 == l) {
                if (flag) {
                    this.updateFlow(par1World, par2, par3, par4);
                }
            } else {
                l = j1;

                if (j1 < 0) {
                    par1World.setBlockToAir(par2, par3, par4);
                } else {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, j1, 2);
                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, i1);
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                }
            }
        } else {
            this.updateFlow(par1World, par2, par3, par4);
        }

        if (this.liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4)) {
            if (this.blockMaterial == Material.lava && par1World.getBlockMaterial(par2, par3 - 1, par4) == Material.water) {
                this.triggerLavaMixEffects(par1World, par2, par3 - 1, par4);
                return;
            }

            if (l >= 8) {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l);
            } else {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l + 8);
            }
        }
        else if (l >= 0 && (l == 0 || this.blockBlocksFlow(par1World, par2, par3 - 1, par4))) {
            boolean[] aboolean = this.getOptimalFlowDirections(par1World, par2, par3, par4);
            j1 = l + b0;

            if (l >= 8) {
                j1 = 1;
            }

            if (j1 >= 8) {
                return;
            }

            if (aboolean[0]) {
                this.flowIntoBlock(par1World, par2 - 1, par3, par4, j1);
            }

            if (aboolean[1]) {
                this.flowIntoBlock(par1World, par2 + 1, par3, par4, j1);
            }

            if (aboolean[2]) {
                this.flowIntoBlock(par1World, par2, par3, par4 - 1, j1);
            }

            if (aboolean[3]) {
                this.flowIntoBlock(par1World, par2, par3, par4 + 1, j1);
            }
        }
    }
	
    private void flowIntoBlock(World par1World, int par2, int par3, int par4, int par5) {
        if (this.liquidCanDisplaceBlock(par1World, par2, par3, par4)) {
            int i1 = par1World.getBlockId(par2, par3, par4);

            if (i1 > 0) {
                if (this.blockMaterial == Material.lava) {
                    this.triggerLavaMixEffects(par1World, par2, par3, par4);
                }
                else if (i1 != Block.snow.blockID) {
                    Block.blocksList[i1].dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                }
            }

            par1World.setBlock(par2, par3, par4, this.blockID, par5, 3);
        }
    }
	
    private boolean liquidCanDisplaceBlock(World par1World, int par2, int par3, int par4) {
        Material material = par1World.getBlockMaterial(par2, par3, par4);
        return material == this.blockMaterial ? false : (material == Material.lava ? false : !this.blockBlocksFlow(par1World, par2, par3, par4));
    }
	
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.motionX *= 0.8D;
    	entity.motionY *= 0.8D;
        entity.motionZ *= 0.8D;
    }
    
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[] {iconRegister.registerIcon(Roads.modid + ":TarFlowing"), iconRegister.registerIcon(Roads.modid + ":TarFlowing")};
	}

	@Override
	public Fluid getFluid() {
		return null;
	}

	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canDrain(World world, int x, int y, int z) {
		return false;
	}

}

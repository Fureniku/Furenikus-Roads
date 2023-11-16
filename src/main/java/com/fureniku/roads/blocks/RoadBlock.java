package com.fureniku.roads.blocks;

import com.fureniku.metropolis.blocks.MetroBlockBase;
import com.fureniku.metropolis.datagen.MetroBlockStateProvider;
import com.fureniku.metropolis.utils.Debug;
import com.fureniku.roads.FurenikusRoads;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.RegistryObject;

public class RoadBlock extends MetroBlockBase {

    private final int HEIGHT;
    private final VoxelShape BLOCK_SHAPE;
    private RoadBlockSet _roadBlockSet;
    private ResourceLocation _resourceLocation;

    public RoadBlock(int height, RoadBlockSet blockSet) {
        this(height, blockSet, new ResourceLocation(FurenikusRoads.MODID, "block/roads/" + blockSet.getSetName()));
    }

    /**
     * Constructor to take in a specific texture file
     * @param height the height of the roadblock; 1-16
     * @param blockSet the blockset this block belongs to
     * @param texture the texture the roadblock should use
     */
    public RoadBlock(int height, RoadBlockSet blockSet, ResourceLocation texture) {
        super(Properties.of()
                .strength(1.0f)
                .sound(SoundType.STONE));
        HEIGHT = height;
        BLOCK_SHAPE = Block.box(0, 0, 0, 16, height, 16);
        _roadBlockSet = blockSet;
        _resourceLocation = texture;
    }

    @Override
    protected void onRightClickRemote(BlockState state, BlockPos pos, Player player) {
        ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);
        //TODO various interactions from items
    }

    @Override
    protected VoxelShape getShapeFromBlockState(BlockState pState) {
        return BLOCK_SHAPE;
    }

    @Override
    public void generateBlockState(RegistryObject<Block> blockRegistryObject, MetroBlockStateProvider blockStateProvider) {
        Block block = blockRegistryObject.get();
        ModelFile modelFile = blockStateProvider.getModelFilesWithTexture(block, "", "blocks/roads/road_block_" + HEIGHT, _resourceLocation);
        blockStateProvider.simpleBlockWithItem(block, modelFile);
    }
}
package com.silvaniastudios.roads.blocks.diagonal;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertyBlockPos implements IUnlistedProperty<BlockPos> {
	
	private final String name;
	
	public UnlistedPropertyBlockPos(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isValid(BlockPos val) {
		return true;
	}
	
	@Override
	public Class<BlockPos> getType() {
		return BlockPos.class;
	}
	
	@Override
	public String valueToString(BlockPos val) {
		return val.toString();
	}
}

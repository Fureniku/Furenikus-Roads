package com.silvaniastudios.roads.blocks.diagonal;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertySideBlock implements IUnlistedProperty<IBlockState> {
	
	private final String name;
	
	public UnlistedPropertySideBlock(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isValid(IBlockState val) {
		return true;
	}
	
	@Override
	public Class<IBlockState> getType() {
		return IBlockState.class;
	}
	
	@Override
	public String valueToString(IBlockState val) {
		return val.toString();
	}

}

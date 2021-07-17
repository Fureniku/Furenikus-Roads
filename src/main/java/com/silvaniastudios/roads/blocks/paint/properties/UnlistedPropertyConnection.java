package com.silvaniastudios.roads.blocks.paint.properties;

import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertyConnection implements IUnlistedProperty<Boolean> {
	
	private final String name;
	
	public UnlistedPropertyConnection(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isValid(Boolean val) {
		return true;
	}
	
	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}
	
	@Override
	public String valueToString(Boolean value) {
		return value.toString();
	}

}

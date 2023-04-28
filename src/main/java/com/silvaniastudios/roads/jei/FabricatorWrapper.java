package com.silvaniastudios.roads.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class FabricatorWrapper implements IRecipeWrapper {

	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack input3;
	private final ItemStack input4;
	private final ItemStack input5;
	private final ItemStack input6;
	private final ItemStack output;
	
	public FabricatorWrapper(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack output) {
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
		this.input5 = input5;
		this.input6 = input6;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List <List<ItemStack>> list = asList(
				asList(input1),
				asList(input2),
				asList(input3),
				asList(input4),
				asList(input5),
				asList(input6)
				);

		ingredients.setInputLists(VanillaTypes.ITEM, list);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	private static <T> List<T> asList(T ... items) {
        List<T> list = new ArrayList<T>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }
}

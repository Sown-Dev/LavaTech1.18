package com.misha.datagen;

import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class Upgrades extends UpgradeRecipeBuilder {
    public Upgrades(RecipeSerializer<?> p_126381_, Ingredient p_126382_, Ingredient p_126383_, Item p_126384_) {
        super(p_126381_, p_126382_, p_126383_, p_126384_);
    }

    public static UpgradeRecipeBuilder smithing(Ingredient p_126386_, Ingredient p_126387_, Item item) {
        return new UpgradeRecipeBuilder(RecipeSerializer.SMITHING, p_126386_, p_126387_, item);
    }

}

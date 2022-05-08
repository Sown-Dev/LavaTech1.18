package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.InductionFurnaceRecipe;
import com.misha.setup.Registration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class InductionFurnaceRecipeCategory implements IRecipeCategory<InductionFurnaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "inductionfurnace");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/inductionfurnace_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public InductionFurnaceRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 5, 5 ,140,68);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.INDUCTIONFURNACE.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Induction Smelting");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends InductionFurnaceRecipe> getRecipeClass() {
        return InductionFurnaceRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InductionFurnaceRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 39, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 17).addItemStack(recipe.getResultItem());



    }
}

package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.CoalInfuserRecipe;
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

public class CoalInfuserRecipeCategory implements IRecipeCategory<CoalInfuserRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "coalinfusing");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/coalinfuser_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public CoalInfuserRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0 ,176,65);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.COALINFUSER.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Coal Infuser");
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
    public Class<? extends CoalInfuserRecipe> getRecipeClass() {
        return CoalInfuserRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CoalInfuserRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem());



    }
}

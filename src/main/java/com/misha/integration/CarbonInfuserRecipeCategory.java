package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.CarbonInfuserRecipe;
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

public class CarbonInfuserRecipeCategory implements IRecipeCategory<CarbonInfuserRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "carboninfusing");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/carboninfuser_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public CarbonInfuserRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0 ,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.CARBONINFUSER.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Carbon Infuser");
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
    public Class<? extends CarbonInfuserRecipe> getRecipeClass() {
        return CarbonInfuserRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CarbonInfuserRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 60, 7).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 60, 36).addIngredients(Ingredient.of((Items.COAL).getDefaultInstance()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 7).addItemStack(recipe.getResultItem());



    }
}

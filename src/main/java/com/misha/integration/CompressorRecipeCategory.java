package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.CompressorRecipe;
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

public class CompressorRecipeCategory implements IRecipeCategory<CompressorRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "compressor");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/compressor_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public CompressorRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 5, 5 ,140,68);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.COMPRESSOR.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Compressing");
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
    public Class<? extends CompressorRecipe> getRecipeClass() {
        return CompressorRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompressorRecipe recipe, IFocusGroup focuses) {
        int count = 4;
        if(recipe.getResultItem().getItem()==Items.NETHERITE_INGOT){ count=6;}
        if(recipe.getResultItem().getItem()==Items.DIAMOND){ count=10;}
        if(recipe.getResultItem().getItem()==Items.ENDER_PEARL){ count=32;}

        builder.addSlot(RecipeIngredientRole.INPUT, 39, 17).addItemStack(new ItemStack(recipe.getIngredients().get(0).getItems()[0].getItem(),count));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 17).addItemStack(recipe.getResultItem());



    }
}

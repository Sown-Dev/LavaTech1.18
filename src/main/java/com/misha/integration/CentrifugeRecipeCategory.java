package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.CentrifugeRecipe;
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

public class CentrifugeRecipeCategory implements IRecipeCategory<CentrifugeRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "centrifuge");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/centrifuge_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public CentrifugeRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 5, 2 ,170,61);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.CENTRIFUGE.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Lava Centrifuge");
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
    public Class<? extends CentrifugeRecipe> getRecipeClass() {
        return CentrifugeRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CentrifugeRecipe recipe, IFocusGroup focuses) {


        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 5).addItemStack(new ItemStack(Items.RAW_IRON));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 130, 5).addItemStack(new ItemStack(Items.IRON_NUGGET,4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 5).addItemStack(new ItemStack(Items.SAND));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 23).addItemStack(new ItemStack(Items.OBSIDIAN));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 130, 23).addItemStack(new ItemStack(Items.QUARTZ));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 23).addItemStack(new ItemStack(Items.AMETHYST_SHARD));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 41).addItemStack(new ItemStack(Items.RAW_GOLD));








    }
}

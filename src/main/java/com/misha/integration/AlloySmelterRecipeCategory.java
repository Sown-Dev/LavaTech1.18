package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.AlloySmelterRecipe;
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

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "alloysmelting");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/alloysmelter_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public AlloySmelterRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 5, 5 ,166,75);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.ALLOYSMELTER.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Alloying");
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
    public Class<? extends AlloySmelterRecipe> getRecipeClass() {
        return AlloySmelterRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 14).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 31, 14).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 53, 14).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 31, 50).addIngredients(Ingredient.of((Items.COAL).getDefaultInstance()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 30).addItemStack(recipe.getResultItem());



    }
}

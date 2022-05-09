package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.UpgraderRecipe;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class UpgraderRecipeCategory implements IRecipeCategory<UpgraderRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(LavaPlus.MODID, "upgrading");
    public final static ResourceLocation TEXTURE = new ResourceLocation(LavaPlus.MODID, "textures/gui/upgrader_gui.png");

    private final IDrawable background;
    private final IDrawable icon;


    public UpgraderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 160, 68);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.UPGRADER.get()));
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Upgrading");
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
    public Class<? extends UpgraderRecipe> getRecipeClass() {
        return UpgraderRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, UpgraderRecipe recipe, IFocusGroup focuses) {
        int count = 0;
        if (recipe.getResultItem().getItem() == Registration.INFERNALBRICK.get().asItem()) {
            count = 9;
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 17).addItemStack(new ItemStack(recipe.getIngredients().get(0).getItems()[0].getItem(), count));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 17).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 17).addItemStack(recipe.getResultItem());


    }
}

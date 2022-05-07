package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.CarbonInfuserRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEILavaTechPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(LavaPlus.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                CarbonInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        //video time: 4:55
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CarbonInfuserRecipe> recipes = rm.getAllRecipesFor(CarbonInfuserRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CarbonInfuserRecipeCategory.UID, CarbonInfuserRecipe.class), recipes);
    }
}

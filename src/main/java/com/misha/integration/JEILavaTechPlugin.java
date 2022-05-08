package com.misha.integration;

import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.AlloySmelterRecipe;
import com.misha.recipes.CarbonInfuserRecipe;
import com.misha.recipes.CoalInfuserRecipe;
import com.misha.recipes.CompressorRecipe;
import com.misha.setup.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
        registration.addRecipeCategories(new
                CoalInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                AlloySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                CompressorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<CoalInfuserRecipe> coalrecipes = rm.getAllRecipesFor(CoalInfuserRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CoalInfuserRecipeCategory.UID, CoalInfuserRecipe.class), coalrecipes);

        List<CarbonInfuserRecipe> carbrecipes = rm.getAllRecipesFor(CarbonInfuserRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CarbonInfuserRecipeCategory.UID, CarbonInfuserRecipe.class), carbrecipes);

        List<AlloySmelterRecipe> alloyrecipes = rm.getAllRecipesFor(AlloySmelterRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(AlloySmelterRecipeCategory.UID, AlloySmelterRecipe.class), alloyrecipes);

        List<CompressorRecipe> comprecipes = rm.getAllRecipesFor(CompressorRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CompressorRecipeCategory.UID, CompressorRecipe.class), comprecipes);


    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Registration.COALINFUSER.get().asItem()), CoalInfuserRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(Registration.CARBONINFUSER.get().asItem()), CarbonInfuserRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(Registration.ALLOYSMELTER.get().asItem()), AlloySmelterRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(Registration.COMPRESSOR.get().asItem()), CompressorRecipeCategory.UID);


    }
}

package com.misha.recipes;

import com.misha.lavaplus.LavaPlus;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS=
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LavaPlus.MODID);

    public static final RegistryObject<RecipeSerializer<CarbonInfuserRecipe>> CARBONINFUSER_SERIALIZER=
            SERIALIZERS.register("carboninfusing", () -> CarbonInfuserRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CoalInfuserRecipe>> COALINFUSER_SERIALIZER=
            SERIALIZERS.register("coalinfusing", () -> CoalInfuserRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CompressorRecipe>> COMPRESSOR_SERIALIZER=
            SERIALIZERS.register("compressor", () -> CompressorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AlloySmelterRecipe>> ALLOYSMELTER_SERIALIZER=
            SERIALIZERS.register("alloysmelting", () -> AlloySmelterRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<UpgraderRecipe>> UPGRADER_SERIALIZER=
            SERIALIZERS.register("upgrading", () -> UpgraderRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<FuelProcessorRecipe>> FUELPROCESSOR_SERIALIZER=
            SERIALIZERS.register("fuelprocessor", () -> FuelProcessorRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<InductionFurnaceRecipe>> INDUCTIONFURNACE_SERIALIZER=
            SERIALIZERS.register("inductionfurnace", () -> InductionFurnaceRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CentrifugeRecipe>> CENTRIFUGE_SERIALIZER=
            SERIALIZERS.register("centrifuge", () -> CentrifugeRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}

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


    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}

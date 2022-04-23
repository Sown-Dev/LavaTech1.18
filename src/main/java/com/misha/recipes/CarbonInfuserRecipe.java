package com.misha.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.misha.lavaplus.LavaPlus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CarbonInfuserRecipe implements Recipe<Container> {

    public static final Serializer SERIALIZER = new Serializer();

    private final Ingredient input;
    private final ItemStack output;
    private ResourceLocation id;
    private final int time;
    private final int carbonReq;


    public CarbonInfuserRecipe(ResourceLocation id, Ingredient input, ItemStack output, int time, int carbonReq) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.time = time;
        this.carbonReq = carbonReq;
    }


    @Override
    public boolean matches(Container inv, Level lev) {
            return (this.input.test(inv.getItem(0)));
    }

    @Override
    public ItemStack assemble(Container p_44001_) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return null; //LavaPlus.CARBONINFUSING;
    }


    //Serializer
    private static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CarbonInfuserRecipe> {
        Serializer() {

            // This registry name is what people will specify in their json files.
            this.setRegistryName(new ResourceLocation(LavaPlus.MODID, "carboninfusing"));
        }

        @Override
        public CarbonInfuserRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            // Reads a recipe from json.

            // Reads the inputs. Accepts items, tags, and anything else that
            // Ingredient.deserialize can understand.
            final JsonElement inputElement = GsonHelper.isArrayNode(json, "input") ? GsonHelper.getAsJsonArray(json, "input") : GsonHelper.getAsJsonObject(json, "input");
            final Ingredient input = Ingredient.fromJson(inputElement);


            // Reads the output. The common utility method in ShapedRecipe is what all vanilla
            // recipe classes use for this.
            final ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            final int time = GsonHelper.getAsInt(json, "time", 20);
            final int carbonReq = GsonHelper.getAsInt(json, "carbon", 20);

            return new CarbonInfuserRecipe(recipeId, input, output, time, carbonReq);
        }

        @Override
        public CarbonInfuserRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            // Reads a recipe from a packet buffer. This code is called on the client.
            final Ingredient input = Ingredient.fromNetwork(buffer);
            final ItemStack output = buffer.readItem();
            final int time = buffer.readInt();
            final int carbonReq = buffer.readInt();

            return new CarbonInfuserRecipe(recipeId, input, output, time, carbonReq);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CarbonInfuserRecipe recipe) {

            // Writes the recipe to a packet buffer. This is called on the server when a player
            // connects or when /reload is used.
            recipe.input.toNetwork(buffer);
            buffer.writeItemStack(recipe.output, true);
            buffer.writeInt(recipe.time);
            buffer.writeInt(recipe.carbonReq);

        }
    }
}

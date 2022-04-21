package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WaterMillScreen extends AbstractContainerScreen<WaterMillContainer> {

    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/lavagenerator_gui.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");

    public static int active=0;
    public static int generate=40;
    public WaterMillScreen(WaterMillContainer container, Inventory inv, Component name) {

        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);

    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().font,   "Stored: "+ menu.getEnergy()+"FE", 38, 6, 0xffffff);
        drawString(matrixStack, Minecraft.getInstance().font,   generate*menu.blockEntity.cactive+"FE/Tick", 50, 15, 0xffffff);

    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth+2, this.imageHeight);

        RenderSystem.setShaderTexture(0, BAR);

        int barX = relX+9;
        int barY = relY+58-(int)(((double)menu.getEnergy()/ (double)WaterMillBE.capacity)*52);

        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) menu.getEnergy()/ (double) WaterMillBE.capacity)*52));
        if(menu.blockEntity.cactive>0) {
            RenderSystem.setShaderTexture(0, FLAME);
            this.blit(matrixStack, relX + 75, relY + 32, 0, 0, 16, 16);

        }

    }
}
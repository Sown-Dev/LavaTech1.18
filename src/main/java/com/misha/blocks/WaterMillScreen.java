package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WaterMillScreen extends AbstractContainerScreen<WaterMillContainer> {

    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/lavagenerator_gui.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");


    public static int active=0;
    public static int generate=WaterMillBE.generate;
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
        drawString(matrixStack, Minecraft.getInstance().font,   generate*menu.getFlows()+"FE/Tick", 50, 15, 0xffffff);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        if(mouseX>relX+11 && mouseX< relX+28 && mouseY>relY+13 && mouseY<relY+65){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+UpgraderBE.capacity+"FE", mouseX-relX+16, mouseY-relY, 0xffffff);

        }
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth+6, this.imageHeight);

        RenderSystem.setShaderTexture(0, BAR);

        int barX = relX+9;
        int barY = relY+58-(int)(((double)menu.getEnergy()/ (double)WaterMillBE.capacity)*52);

        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) menu.getEnergy()/ (double) WaterMillBE.capacity)*52));


    }
}
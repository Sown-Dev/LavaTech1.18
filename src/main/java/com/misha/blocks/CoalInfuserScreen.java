package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoalInfuserScreen extends AbstractContainerScreen<CoalInfuserContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/coalinfuser_gui.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");
    private ResourceLocation ARROW= new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");

    public CoalInfuserScreen(CoalInfuserContainer container, Inventory inv, Component name) {
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

           }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth+2, this.imageHeight);

        if(menu.getActive()>0) {
            RenderSystem.setShaderTexture(0, FLAME);
            this.blit(matrixStack, relX + 45, relY + 55, 0, 0, 16, 16);
        }
        // System.out.println(menu.getActive());

        if(menu.getCounter()>0 && menu.getActive()>0){
            RenderSystem.setShaderTexture(0, ARROW);
            int bwidth=(int)(30*((double) menu.getCounter()/(  (double)CoalInfuserBE.baseTime/(double)menu.getActive()  )  ));

            this.blit(matrixStack, relX + 71, relY + 34, 0, 0, bwidth, 17);

        }
           }
}

package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CompressorScreen extends AbstractContainerScreen<CompressorContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/compressor_gui.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");
    private ResourceLocation ARROW= new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    public CompressorScreen(CompressorContainer container, Inventory inv, Component name) {

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
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        if(mouseX>relX+11 && mouseX< relX+28 && mouseY>relY+13 && mouseY<relY+65){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+CompressorBE.capacity+"FE", mouseX-relX+9, mouseY-relY, 0xffffff);
            drawString(matrixStack, Minecraft.getInstance().font,   "uses " + CompressorBE.baseUsage*menu.getActive()+"FE/tick", mouseX-relX+9, mouseY-relY+8, 0xffffff);

        }
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth+2, this.imageHeight);


        if(menu.getActive()>0) {
            RenderSystem.setShaderTexture(0, FLAME);
            this.blit(matrixStack, relX + 45, relY + 52, 0, 0, 16, 16);
        }
        //System.out.println(counter+""+ active);

        if(menu.getCounter()>0 && menu.getActive()>0){
            RenderSystem.setShaderTexture(0, ARROW);
            int bwidth=(int)(30*((double) menu.getCounter()/((double)menu.blockEntity.time/(double)menu.getActive())));

            this.blit(matrixStack, relX + 71, relY + 21, 0, 0, bwidth, 17);

        }
        int barX = relX+10;
        int barY = relY+65-(int)(((double)menu.getEnergy()/(double) CompressorBE.capacity)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) menu.getEnergy()/ (double) CompressorBE.capacity)*52));

    }
}

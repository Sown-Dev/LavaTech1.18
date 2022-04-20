package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class HydroponicsScreen extends AbstractContainerScreen<HydroponicsContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/hydroponics_gui.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");
    private ResourceLocation ARROW= new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    public static int counter=0;
    public static int active =0;

    public HydroponicsScreen(HydroponicsContainer container, Inventory inv, Component name) {

        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);

        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        if(mouseX>relX+8 && mouseX< relX+25 && mouseY>relY+6 && mouseY<relY+58){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+HydroponicsBE.capacity+"FE", mouseX+9, mouseY, 0xffffff);

        }


    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX-2, relY, 0, 0, this.imageWidth+4, this.imageHeight);


        //System.out.println(counter+""+ active);

        if(menu.blockEntity.ccounter>0){
            RenderSystem.setShaderTexture(0, ARROW);
            int bwidth=(int)(30*((double) menu.blockEntity.ccounter/((double)menu.blockEntity.time)));

            this.blit(matrixStack, relX + 71, relY + 21, 0, 0, bwidth, 17);

        }

        int energy=menu.getEnergy();
        int cap = HydroponicsBE.capacity;
        int barX = relX+8;
        int barY = relY+58-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));


    }
}

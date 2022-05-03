package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ReactorPanelScreen extends AbstractContainerScreen<ReactorPanelContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/reactor_gui.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    private ResourceLocation HEATBAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/heatbar.png");
    private ResourceLocation CARBONBAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/carbonbar.png");
    private ResourceLocation FUELBAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/reactorfuelbar.png");



    public static int counter=0;
    public static int active =0;

    public ReactorPanelScreen(ReactorPanelContainer container, Inventory inv, Component name) {

        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);

        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2 +20;

        if(mouseX>relX+8 && mouseX< relX+25 && mouseY>relY-3 && mouseY<relY+53){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+ReactorPanelBE.capacity+"FE", mouseX+9, mouseY, 0xffffff);
        }
        if(mouseX>relX+33 && mouseX< relX+45 && mouseY>relY+1 && mouseY<relY+58){
            drawString(matrixStack, Minecraft.getInstance().font,   + (double)menu.getHeat()/10.0D+"ºC/"+ReactorPanelBE.heatcap+"mB", mouseX+9, mouseY, 0xffffff);
        }
        if(mouseX>relX+151 && mouseX< relX+163 && mouseY>relY+1 && mouseY<relY+58){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getCarbon()+"mB/"+ReactorPanelBE.carbonCap+"mB", mouseX+9, mouseY, 0xffffff);
        }


    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        font.draw(matrixStack, new TranslatableComponent("block.lavaplus.reactorpanel")  , 5,10, 0x444444);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2 +5;
        this.blit(matrixStack, relX-2, relY, 0, 0, this.imageWidth+4, this.imageHeight+20);

//heat
        int heatmax= ReactorPanelBE.heatcap;
        int heat =  menu.getHeat();
        int hbarY = relY+71 -(int)(((double)heat/(double) heatmax)*52);
        RenderSystem.setShaderTexture(0, HEATBAR);
        this.blit(matrixStack, relX+36,hbarY, 0, 0, 17, (int)(((double) heat/ (double) heatmax)*52));

        int fuelmax= ReactorPanelBE.fuelcap;
        int fuel =  menu.getFuel();
        int fbarY = relY+34 -(int)(((double)fuel/(double) fuelmax)*8);
        RenderSystem.setShaderTexture(0, FUELBAR);
        this.blit(matrixStack, relX+83,fbarY, 0, 0, 17, (int)(((double) fuel/ (double) fuelmax)*8));



        int carbmax= ReactorPanelBE.carbonCap;
        int carb =  menu.getCarbon();
        int cbarY = relY+70 -(int)(((double)carb/(double) carbmax)*52);
        RenderSystem.setShaderTexture(0, CARBONBAR);
        this.blit(matrixStack, relX+152,cbarY, 0, 0, 17, (int)(((double) carb/ (double) carbmax)*52));


        int energy=menu.getEnergy();
        int cap = ReactorPanelBE.capacity;
        int barX = relX+7;
        int barY = relY+71-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));

    }
}

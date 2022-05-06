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

public class AdvancedBeaconScreen extends AbstractContainerScreen<AdvancedBeaconContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/AdvancedBeacon_gui.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    //effects
    private ResourceLocation SPEED= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/speed.png");
    private ResourceLocation HASTE= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/haste.png");
    private ResourceLocation FIRERESIST= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/fire_resistance.png");
    private ResourceLocation NV= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/night_vision.png");
    private ResourceLocation WATER= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/water_breathing.png");
    private ResourceLocation DMG= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/strength.png");

    public static int counter=0;
    public static int active =0;

    public AdvancedBeaconScreen(AdvancedBeaconContainer container, Inventory inv, Component name) {

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
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+AdvancedBeaconBE.capacity+"FE", mouseX+9, mouseY, 0xffffff);
            drawString(matrixStack, Minecraft.getInstance().font,   "Max Usage: "+AdvancedBeaconBE.baseUsage+"FE/t", mouseX+9, mouseY+8, 0xffffff);

        }


    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        font.draw(matrixStack, new TranslatableComponent("block.lavaplus.AdvancedBeacon")  , 5,10, 0x444444);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2 +5;
        this.blit(matrixStack, relX-2, relY, 0, 0, this.imageWidth+4, this.imageHeight+20);


        this.addRenderableWidget(new Button(relX+46, relY+30, 20, 20,new TranslatableComponent("<"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,1);
        }));

        this.addRenderableWidget(new Button(relX+100, relY+30, 20, 20,new TranslatableComponent("gui.lavaplus.rightarrow"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,2);
        }));


        if(menu.getEffect()==0){
            RenderSystem.setShaderTexture(0, SPEED);
        }
        if(menu.getEffect()==1){
            RenderSystem.setShaderTexture(0, HASTE);
        }
        if(menu.getEffect()==2){
            RenderSystem.setShaderTexture(0,FIRERESIST);
        }
        if(menu.getEffect()==3){
            RenderSystem.setShaderTexture(0, NV);
        }
        if(menu.getEffect()==4){
            RenderSystem.setShaderTexture(0,WATER);
        }
        if(menu.getEffect()==5){
            RenderSystem.setShaderTexture(0,DMG);
        }

        this.blit(matrixStack, relX+74,relY+31, 0,0, 36,36);


        int energy=menu.getEnergy();
        int cap = AdvancedBeaconBE.capacity;
        int barX = relX+7;
        int barY = relY+67-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));


    }
}

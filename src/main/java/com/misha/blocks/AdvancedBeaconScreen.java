package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedBeaconScreen extends AbstractContainerScreen<AdvancedBeaconContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/advancedbeacon_gui.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    //effects
    private ResourceLocation SPEED= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/speed.png");
    private ResourceLocation HASTE= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/haste.png");
    private ResourceLocation FIRERESIST= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/fire_resistance.png");
    private ResourceLocation NV= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/night_vision.png");
    private ResourceLocation WATER= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/water_breathing.png");
    private ResourceLocation DMG= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/strength.png");
    private ResourceLocation REGEN= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/regeneration.png");
    private ResourceLocation ABSORB= new ResourceLocation( LavaPlus.MODID,"textures/gui/mob_effect/invisibility.png");

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

        if(mouseX>relX+5 && mouseX< relX+25 && mouseY>relY-13 && mouseY<relY+74){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+AdvancedBeaconBE.capacity+"FE", mouseX+9, mouseY, 0xffffff);
            drawString(matrixStack, Minecraft.getInstance().font,   "Max Usage: "+AdvancedBeaconBE.baseUsage+"FE/t", mouseX+9, mouseY+8, 0xffffff);

        }


    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        //font.draw(matrixStack, new TranslatableComponent("block.lavaplus.AdvancedBeacon")  , 5,10, 0x444444);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2 +5;
        this.blit(matrixStack, relX-2, relY, 0, 0, this.imageWidth+4, this.imageHeight+20);


        this.addRenderableWidget(new Button(relX+49, relY+12, 20, 20,new TextComponent("<"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,1);
        }));

        this.addRenderableWidget(new Button(relX+103, relY+12, 20, 20,new TextComponent(">"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,2);
        }));


        //2nd level of buttons
        this.addRenderableWidget(new Button(relX+49, relY+37, 20, 20,new TextComponent("<"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,3);
        }));

        this.addRenderableWidget(new Button(relX+103, relY+37, 20, 20,new TextComponent(">"), pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,4);
        }));

        //3rd level of buttons
        this.addRenderableWidget(new Button(relX+49, relY+62, 20, 20,new TextComponent("<"),pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,5);
        }));

        this.addRenderableWidget(new Button(relX+103, relY+62, 20, 20,new TextComponent(">"), pButton -> {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId,6);
        }));

        // EFFECT 1
        if(menu.getEffect()==0){RenderSystem.setShaderTexture(0, SPEED);}
        if(menu.getEffect()==1){RenderSystem.setShaderTexture(0, HASTE);}
        if(menu.getEffect()==2){RenderSystem.setShaderTexture(0,FIRERESIST);}
        if(menu.getEffect()==3){RenderSystem.setShaderTexture(0, NV);}
        if(menu.getEffect()==4){RenderSystem.setShaderTexture(0,WATER);}
        if(menu.getEffect()==5){RenderSystem.setShaderTexture(0,DMG);}
        if(menu.getEffect()==6){RenderSystem.setShaderTexture(0,REGEN);}
        if(menu.getEffect()==7){RenderSystem.setShaderTexture(0,ABSORB);}
        this.blit(matrixStack, relX+77,relY+13, 0,0, 36,36);

        // EFFECT 2
        if(menu.getEffect2()==0){RenderSystem.setShaderTexture(0, SPEED);}
        if(menu.getEffect2()==1){RenderSystem.setShaderTexture(0, HASTE);}
        if(menu.getEffect2()==2){RenderSystem.setShaderTexture(0,FIRERESIST);}
        if(menu.getEffect2()==3){RenderSystem.setShaderTexture(0, NV);}
        if(menu.getEffect2()==4){RenderSystem.setShaderTexture(0,WATER);}
        if(menu.getEffect2()==5){RenderSystem.setShaderTexture(0,DMG);}
        if(menu.getEffect2()==6){RenderSystem.setShaderTexture(0,REGEN);}
        if(menu.getEffect2()==7){RenderSystem.setShaderTexture(0,ABSORB);}
        this.blit(matrixStack, relX+77,relY+38, 0,0, 36,36);

        // EFFECT 3
        if(menu.getEffect3()==0){RenderSystem.setShaderTexture(0, SPEED);}
        if(menu.getEffect3()==1){RenderSystem.setShaderTexture(0, HASTE);}
        if(menu.getEffect3()==2){RenderSystem.setShaderTexture(0,FIRERESIST);}
        if(menu.getEffect3()==3){RenderSystem.setShaderTexture(0, NV);}
        if(menu.getEffect3()==4){RenderSystem.setShaderTexture(0,WATER);}
        if(menu.getEffect3()==5){RenderSystem.setShaderTexture(0,DMG);}
        if(menu.getEffect3()==6){RenderSystem.setShaderTexture(0,REGEN);}
        if(menu.getEffect3()==7){RenderSystem.setShaderTexture(0,ABSORB);}
        this.blit(matrixStack, relX+77,relY+63, 0,0, 36,36);




        int energy=menu.getEnergy();
        int cap = AdvancedBeaconBE.capacity;
        int barX = relX+6;
        int barY = relY+74-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));


    }
}

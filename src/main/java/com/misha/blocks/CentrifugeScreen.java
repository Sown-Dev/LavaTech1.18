package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CentrifugeScreen extends AbstractContainerScreen<CentrifugeContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/centrifuge_gui.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");
    private ResourceLocation ARROW= new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    private ResourceLocation LAVABAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/lavabar.png");

    public static int counter=0;
    public static int active =0;

    public CentrifugeScreen(CentrifugeContainer container, Inventory inv, Component name) {

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
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.getEnergy()+"FE/"+CentrifugeBE.capacity+"FE", mouseX+9, mouseY, 0xffffff);
            drawString(matrixStack, Minecraft.getInstance().font,   "Uses "+ CentrifugeBE.baseUsage*menu.blockEntity.cactive+"FE/tick", mouseX+9, mouseY+8, 0xffffff);

        }
        if(mouseX>relX+44 && mouseX< relX+60 && mouseY>relY+6 && mouseY<relY+58){
            drawString(matrixStack, Minecraft.getInstance().font,   + menu.blockEntity.cfilled+"mB/"+menu.blockEntity.fluidcap+"mB", mouseX+9, mouseY, 0xffffff);

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


        if(menu.blockEntity.cactive>0) {
            RenderSystem.setShaderTexture(0, FLAME);
            //this.blit(matrixStack, relX + 45, relY + 42, 0, 0, 16, 16);
        }
        //System.out.println(counter+""+ active);

        if(menu.blockEntity.ccounter>0 && menu.blockEntity.cactive>0){
            RenderSystem.setShaderTexture(0, ARROW);
            int bwidth=(int)(30*((double) menu.blockEntity.ccounter/((double)CentrifugeBE.basetime/(double)menu.blockEntity.cactive)));

            this.blit(matrixStack, relX + 73, relY + 23, 0, 0, bwidth, 17);

        }
        if(menu.blockEntity.cfilled>0){

            RenderSystem.setShaderTexture(0, LAVABAR);
            int cap=menu.blockEntity.fluidcap;
            int fill=menu.blockEntity.cfilled;
            int lbarX = relX+44;
            int lbarY = relY+58-(int)(((double)fill/(double) cap)*52);
            this.blit(matrixStack, lbarX,lbarY, 0, 0, 17, (int)(  (  (double) fill/  (double)cap  ) * 52));


        }
        int energy=menu.getEnergy();
        int cap = CentrifugeBE.capacity;
        int barX = relX+8;
        int barY = relY+58-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));

        ResourceLocation FLUIDBARS= new ResourceLocation(LavaPlus.MODID, "textures/gui/centrifuge_bars.png");
        RenderSystem.setShaderTexture(0, FLUIDBARS);
        this.blit(matrixStack, relX-2,relY, 0, 0, 170,100);

    }
}

package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IronDrillScreen extends AbstractContainerScreen<IronDrillContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/IronDrill_gui.png");
    private ResourceLocation FLAME= new ResourceLocation(LavaPlus.MODID, "textures/gui/flame.png");
    private ResourceLocation ARROW= new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");
    private ResourceLocation BAR= new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    private ResourceLocation GREENBALL= new ResourceLocation(LavaPlus.MODID, "textures/gui/greenball.png");
    private ResourceLocation REDBALL= new ResourceLocation(LavaPlus.MODID, "textures/gui/redball.png");
    private ResourceLocation YELLOWBALL= new ResourceLocation(LavaPlus.MODID, "textures/gui/yellowball.png");


    private ResourceLocation DRILLPROGRESS= new ResourceLocation(LavaPlus.MODID, "textures/gui/drillprogress.png");

    public static int counter=0;


    public IronDrillScreen(IronDrillContainer container, Inventory inv, Component name) {

        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);

        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;

        if(mouseX>relX+6 && mouseX< relX+25 && mouseY>relY+6 && mouseY<relY+58){
            matrixStack.pushPose();
            matrixStack.translate(0,0,30);
            drawString(matrixStack, Minecraft.getInstance().font,    menu.getEnergy()+"FE/"+IronDrillBE.capacity+"FE\r\nUses "+IronDrillBE.baseUsage+"FE/t", mouseX+9, mouseY, 0xffffff);

        }


    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        font.draw(matrixStack, ""+(menu.blockEntity.getBlockPos().getY()-menu.getDepth())  , 147,5, 0x222222);
        font.draw(matrixStack, "Copper Drill"  , 3,-8, 0x444444);



    }


    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = ((this.height - this.imageHeight) / 2)-13;
        this.blit(matrixStack, relX-2, relY, 0, 0, this.imageWidth+40, this.imageHeight+40);


        //System.out.println(counter+""+ active);

        if(menu.getCounter()>0){
            RenderSystem.setShaderTexture(0, DRILLPROGRESS);
            int bheight=(int)(29*((double) menu.getCounter()/((double)menu.blockEntity.time)));

            this.blit(matrixStack, relX + 138, relY + 36, 0, 0,21, bheight);

        }
        RenderSystem.setShaderTexture(0, REDBALL);
        if(menu.getErrCode()==0){
            RenderSystem.setShaderTexture(0, GREENBALL);
        }
        if(menu.getErrCode()>=4){
            RenderSystem.setShaderTexture(0, YELLOWBALL);
        }
        if(menu.getErrCode()>0 && menu.getErrCode()<4){
            RenderSystem.setShaderTexture(0, REDBALL);
        }
        this.blit(matrixStack, relX+182, relY+11, 0,0, 20, 20);



        int energy=menu.getEnergy();
        int cap = IronDrillBE.capacity;
        int barX = relX+7;
        int barY = relY+71-(int)(((double)energy/(double) cap)*52);
        RenderSystem.setShaderTexture(0, BAR);
        this.blit(matrixStack, barX,barY, 0, 0, 17, (int)(((double) energy/ (double) cap)*52));


    }
}

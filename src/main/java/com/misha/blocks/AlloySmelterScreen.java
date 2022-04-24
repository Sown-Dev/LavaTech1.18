package com.misha.blocks;

import com.misha.lavaplus.LavaPlus;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AlloySmelterScreen extends AbstractContainerScreen<AlloySmelterContainer> {
    private ResourceLocation GUI = new ResourceLocation(LavaPlus.MODID, "textures/gui/alloysmelter_gui.png");
    private ResourceLocation FLAME = new ResourceLocation(LavaPlus.MODID, "textures/gui/flame2.png");
    private ResourceLocation ARROW = new ResourceLocation(LavaPlus.MODID, "textures/gui/arrow.png");
    private ResourceLocation BAR = new ResourceLocation(LavaPlus.MODID, "textures/gui/bar2.png");

    public static int counter = 0;
    public static int energy = 0;
    public static int active = 0;

    public AlloySmelterScreen(AlloySmelterContainer container, Inventory inv, Component name) {

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

    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth + 2, this.imageHeight + 10);


        if (menu.getFuel() > 0) {
            int cut = (int) Math.round(((((double) (menu.getFuel()) / (double) (AlloySmelterBE.baseTime)) * -13) + 13));

            RenderSystem.setShaderTexture(0, FLAME);
            this.blit(matrixStack, relX + 36, relY + 38 + cut, 0, cut, 16, 16);

        }
        //System.out.println(counter+""+ active);

        if (menu.getCounter() > 0) {
            RenderSystem.setShaderTexture(0, ARROW);
            int bwidth = (int) (30 * ((double) menu.blockEntity.counter / ((double) AlloySmelterBE.baseTime)));

            this.blit(matrixStack, relX + 82, relY + 34, 0, 0, bwidth, 17);

        }

    }
}

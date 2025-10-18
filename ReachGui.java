package com.reachmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class ReachGui extends Screen {
    
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 250;
    
    public ReachGui() {
        super(new StringTextComponent("Reach Mod"));
    }
    
    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int startX = centerX - PANEL_WIDTH / 2;
        int startY = centerY - PANEL_HEIGHT / 2;
        
        this.addButton(new Button(startX + 20, startY + 40, 120, 20, 
            new StringTextComponent("Enabled: " + (ReachMod.config.enabled ? "§aON" : "§cOFF")),
            button -> {
                ReachMod.config.toggle();
                button.setMessage(new StringTextComponent("Enabled: " + (ReachMod.config.enabled ? "§aON" : "§cOFF")));
            }
        ));
        
        this.addButton(new Button(startX + 20, startY + 70, 50, 20,
            new StringTextComponent("-"),
            button -> {
                ReachMod.config.decreaseAttackReach();
            }
        ));
        
        this.addButton(new Button(startX + 230, startY + 70, 50, 20,
            new StringTextComponent("+"),
            button -> {
                ReachMod.config.increaseAttackReach();
            }
        ));
        
        this.addButton(new Button(startX + 20, startY + 120, 50, 20,
            new StringTextComponent("-"),
            button -> {
                ReachMod.config.decreaseBlockReach();
            }
        ));
        
        this.addButton(new Button(startX + 230, startY + 120, 50, 20,
            new StringTextComponent("+"),
            button -> {
                ReachMod.config.increaseBlockReach();
            }
        ));
        
        this.addButton(new Button(startX + 20, startY + 170, 120, 20,
            new StringTextComponent("Effects: " + (ReachMod.config.visualEffects ? "§aON" : "§cOFF")),
            button -> {
                ReachMod.config.visualEffects = !ReachMod.config.visualEffects;
                button.setMessage(new StringTextComponent("Effects: " + (ReachMod.config.visualEffects ? "§aON" : "§cOFF")));
            }
        ));
        
        this.addButton(new Button(startX + 85, startY + 210, 130, 20,
            new StringTextComponent("Close"),
            button -> this.onClose()
        ));
    }
    
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int startX = centerX - PANEL_WIDTH / 2;
        int startY = centerY - PANEL_HEIGHT / 2;
        
        fill(matrixStack, startX, startY, startX + PANEL_WIDTH, startY + PANEL_HEIGHT, 0xE0000000);
        fill(matrixStack, startX, startY, startX + PANEL_WIDTH, startY + 30, 0xFF1A1A1A);
        fill(matrixStack, startX, startY, startX + PANEL_WIDTH, startY + 2, 0xFF64C8FF);
        
        drawCenteredString(matrixStack, this.font, "§l§nReach Mod", centerX, startY + 10, 0x64C8FF);
        
        drawString(matrixStack, this.font, "Attack Reach: §a" + String.format("%.1f", ReachMod.config.attackReach), 
            startX + 75, startY + 75, 0xFFFFFF);
        
        drawString(matrixStack, this.font, "Block Reach: §a" + String.format("%.1f", ReachMod.config.blockReach),
            startX + 75, startY + 125, 0xFFFFFF);
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

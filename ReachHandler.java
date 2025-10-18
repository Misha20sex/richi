package com.reachmod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.lang.reflect.Field;

public class ReachHandler {
    
    private static Field reachDistanceField;
    
    static {
        try {
            reachDistanceField = Minecraft.getInstance().playerController.getClass().getDeclaredField("field_78382_b");
            reachDistanceField.setAccessible(true);
        } catch (Exception e) {
            try {
                reachDistanceField = Minecraft.getInstance().playerController.getClass().getDeclaredField("blockReachDistance");
                reachDistanceField.setAccessible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void applyReach() {
        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc.playerController != null && ReachMod.config.enabled) {
                reachDistanceField.setFloat(mc.playerController, ReachMod.config.getBlockReach());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (ReachMod.config.enabled && event.getPlayer() != null) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == event.getPlayer()) {
                applyReach();
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && ReachMod.config.visualEffects) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && ReachMod.config.enabled) {
                String text = String.format("§lReach: §a%.1f", ReachMod.config.getAttackReach());
                mc.fontRenderer.drawStringWithShadow(event.getMatrixStack(), text, 5, 5, 0x00FF00);
            }
        }
    }
}

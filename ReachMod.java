package com.reachmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod("reachmod")
public class ReachMod {
    
    public static final String MOD_ID = "reachmod";
    public static ReachConfig config = new ReachConfig();
    public static ReachGui gui = null;
    private static KeyBinding menuKey;
    
    public ReachMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ReachHandler());
    }
    
    private void clientSetup(FMLClientSetupEvent event) {
        menuKey = new KeyBinding("key.reachmod.menu", GLFW.GLFW_KEY_R, "key.categories.reachmod");
        ClientRegistry.registerKeyBinding(menuKey);
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (menuKey.isPressed()) {
            Minecraft mc = Minecraft.getInstance();
            if (gui == null) {
                gui = new ReachGui();
            }
            mc.displayGuiScreen(gui);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ReachHandler.applyReach();
        }
    }
}

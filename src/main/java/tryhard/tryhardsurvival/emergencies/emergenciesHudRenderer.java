package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class emergenciesHudRenderer {
    private static final Identifier[] trumpets = {
            Identifier.of("tryhardsurvival", "/textures/hud/emergency1.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency2.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency3.png")
    };

    public static void init() {
        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> {
            layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, trumpets[emergencyLvlMgmt.getEmergencyLvl()], emergenciesHudRenderer::renderHUD);
        });
    }

    private static void renderHUD(DrawContext context, RenderTickCounter tickCounter) {
        if (emergencyLvlMgmt.getEmergencyLvl() == 0) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        // Draw full-screen image
        context.drawTexture(RenderLayer::getGuiTextured, trumpets[emergencyLvlMgmt.getEmergencyLvl() - 1],
                0, 0,  // X, Y position (top-left corner)
                0, 0,  // Texture X, Texture Y
                screenWidth, screenHeight,  // Width and height (fill the screen)
                screenWidth, screenHeight); // Texture width and height (scale to fit)
    }

}

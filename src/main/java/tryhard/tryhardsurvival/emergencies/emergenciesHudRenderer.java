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

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.drawTexture(RenderLayer::getGuiTextured, trumpets[emergencyLvlMgmt.getEmergencyLvl()], 0, 0, 0, 0, width, height, 1280, 720);
    }

}

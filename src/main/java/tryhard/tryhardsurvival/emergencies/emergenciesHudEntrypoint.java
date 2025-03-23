package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public final class emergenciesHudEntrypoint implements ClientModInitializer {
    private static final Identifier[] trumpets = {
            Identifier.of("tryhardsurvival", "/textures/hud/emergency1.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency2.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency3.png")
    };

    public static int blowTrumpet() {
        int currentThreatScore = dangerScoreMgmt.getThreatScore();

        if (currentThreatScore >= 20 && currentThreatScore < 50) {
            return 1;
        } else if (currentThreatScore >= 50 && currentThreatScore < 80) {
            return 2;
        } else if (currentThreatScore >= 80) {
            return 3;
        }
        return 0;
    }

    @Override
    public void onInitializeClient() {
        int emergencyLvl = emergenciesHudEntrypoint.blowTrumpet();
        if (emergencyLvl != 0) {
            HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, trumpets[emergencyLvl], emergenciesHudEntrypoint::onRenderGUI));
        }
    }

    private static void onRenderGUI(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        int x = 0;
        int y = 720;
        int w = 1280;
        int h = 720;
        drawContext.drawTexture(RenderLayer::getGuiTextured, trumpets[emergenciesHudEntrypoint.blowTrumpet()], x, y,
                0, 0, w, h, w, h);
    }
}



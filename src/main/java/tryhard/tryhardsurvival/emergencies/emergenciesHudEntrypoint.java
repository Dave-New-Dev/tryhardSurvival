package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public final class emergenciesHudEntrypoint implements ClientModInitializer {
    private static final Identifier[] trumpets = {
            Identifier.of("tryhardsurvival", "/textures/hud/emergency1.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency2.png"),
            Identifier.of("tryhardsurvival", "/textures/hud/emergency3.png")
    };

    public static int blowTrumpet() {
        int currentThreatScore = threatScoreMgmt.getThreatScore();

        if (currentThreatScore >= 10 && currentThreatScore < 50) {
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
        threatScoreMgmt.init();

        int emergencyLvl = emergenciesHudEntrypoint.blowTrumpet();
        if (emergencyLvl != 0) {
            HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, trumpets[emergencyLvl], emergenciesHudEntrypoint::onRenderGUI));
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("dangerscore")
            .executes(context -> {
                int threatScore = threatScoreMgmt.getThreatScore();
                Formatting formatColor = Formatting.WHITE;
                if (emergencyLvl == 0) { formatColor = Formatting.GRAY; }
                else if (emergencyLvl == 1) { formatColor = Formatting.YELLOW; }
                else if (emergencyLvl >= 2) { formatColor = Formatting.RED; }
                Text message = Text.literal("<Angela>: The current threat score is: " + threatScore)
                        .formatted(formatColor)
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                Text.literal("Threat score detection sys"))));

                context.getSource().sendFeedback(message);
                return 1;
            })));
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



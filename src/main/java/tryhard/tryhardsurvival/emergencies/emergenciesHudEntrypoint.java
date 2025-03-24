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

    @Override
    public void onInitializeClient() {
        threatScoreMgmt.init();
        trumpeteer.init();

        int emergencyLvl = trumpeteer.blowTrumpet();
        if (emergencyLvl != 0) {
            HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, trumpets[emergencyLvl], emergenciesHudEntrypoint::onRenderGUI));
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("dangerscore")
            .executes(context -> {
                int threatScore = threatScoreMgmt.getThreatScore();
//                Formatting formatColor = Formatting.WHITE;
//
//                Text message = Text.literal("null");
//                if (emergencyLvl == 0) {
//                    message = Text.literal("<Angela>: The facility is at safety, for now.")
//                            .formatted(formatColor)
//                            .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
//                                    Text.literal("Threat score detection sys"))));
//                } else if (emergencyLvl > 0) {
//                    if (emergencyLvl == 1) { formatColor = Formatting.YELLOW; }
//                    else if (emergencyLvl == 2 || emergencyLvl == 3) { formatColor = Formatting.RED; }
//                    message = Text.literal("<Angela>: The current threat score is: " + threatScore)
//                            .formatted(formatColor)
//                            .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
//                                    Text.literal("Threat score detection sys"))));
//                }
//
//                context.getSource().sendFeedback(message);
                Text message = Text.literal(String.join("Danger score:",String.valueOf(threatScore),"\nEmergency level:",String.valueOf(emergencyLvl)));
                context.getSource().sendFeedback(message);
                return 1;
            })));
    }

    private static void onRenderGUI(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        int x = 0;
        int y = 720;
        int w = 1280;
        int h = 720;
        drawContext.drawTexture(RenderLayer::getGuiTextured, trumpets[trumpeteer.blowTrumpet()], x, y,
                0, 0, w, h, w, h);
    }
}



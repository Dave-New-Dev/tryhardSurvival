package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public final class emergenciesHudEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        threatScoreMgmt.init();
        trumpeteer.init();
        emergenciesHudRenderer.init();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("threatscore")
                .executes(context -> {
                int emergencyLvl = trumpeteer.getEmergencyLvl();
                int threatScore = threatScoreMgmt.getThreatScore();
                Formatting formatColor = Formatting.WHITE;
                Text AngelaDesc = Text.literal("I am Angela, an AI. I am your assistant, your secretary, and someone to whom you can talk. I hope I can help make your time here a little more comfortable.");

                Text message = Text.literal("null");
                if (emergencyLvl == 0) {
                    message = Text.literal("<Angela>: The situation is back to normal; everybody back to work.")
                            .formatted(formatColor)
                            .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    AngelaDesc)));
                }
                else if (emergencyLvl > 0) {
                    if (emergencyLvl == 1) {
                        formatColor = Formatting.YELLOW;
                        message = Text.literal("<Angela>: The current threat score is at " + threatScore + ". First trumpet protocol is in effect. Employees proceed with caution, supress all abnormalities immediately.")
                                .formatted(formatColor)
                                .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        AngelaDesc)));
                    } else if (emergencyLvl == 2) {
                        formatColor = Formatting.RED;
                        message = Text.literal("<Angela>: The current threat score is at " + threatScore)
                                .formatted(formatColor)
                                .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        AngelaDesc)));
                    } else if (emergencyLvl == 3) {
                        formatColor = Formatting.RED;
                        message = Text.literal("<Angela>: The current threat score is at " + threatScore)
                                .formatted(formatColor)
                                .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        AngelaDesc)));
                    }
                }
                    context.getSource().sendFeedback(message);
                    return 1;
                })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("threatscore")
            .executes(context -> {
                int threatScore = threatScoreMgmt.getThreatScore();

                Text message = Text.literal(String.valueOf(threatScore))
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                Text.literal("Threat score detection sys"))));
                context.getSource().sendFeedback(message);
                return 1;
            })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("emergencylvl")
                .executes(context -> {
                    int trumpetLvl = trumpeteer.getEmergencyLvl();

                    Text message = Text.literal(String.valueOf(trumpetLvl))
                            .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                   Text.literal("Trumpet protocol sys"))));
                    context.getSource().sendFeedback(message);
                    return 1;
                })));
    }

}



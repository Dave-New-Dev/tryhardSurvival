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
        emergencyLvlMgmt.init();
        trumpeter.init();
        emergenciesHudRenderer.init();


        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("angelos")
                .executes(context -> {
                    int emergencyLvl = emergencyLvlMgmt.getEmergencyLvl();
                    int threatScore = threatScoreMgmt.getThreatScore();
                    Text AngelaDesc = Text.literal("I am Angela, an AI. I am your assistant, your secretary, and someone to whom you can talk. I hope I can help make your time here a little more comfortable.");

                    Text message = switch (emergencyLvl) {
                        default -> Text.literal("<Angela>: The situation is back to normal; everybody back to work.")
                                .formatted(Formatting.WHITE)
                                .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        AngelaDesc)));
                        case 1 -> Text.literal("<Angela>: The current threat score is at " + threatScore + ". First trumpet protocols are in effect. Employees are to proceed with caution and supress all abnormalities on sight.")
                                    .formatted(Formatting.YELLOW)
                                    .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            AngelaDesc)));

                        case 2 -> Text.literal("<Angela>: The current threat score is at " + threatScore + ". Second trumpet protocols are in effect. Multiple abnormalities have escaped; supress them immediately. ")
                                    .formatted(Formatting.RED)
                                    .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            AngelaDesc)));
                        case 3 -> Text.literal("<Angela>: The current threat score is at " + threatScore + ". We have reached third trumpet. Good luck, may we meet again, manager.")
                                    .formatted(Formatting.DARK_RED)
                                    .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            AngelaDesc)));
                    };

                    context.getSource().sendFeedback(message);
                    return 1;
                })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("threatscore")
            .executes(context -> {
                int threatScore = threatScoreMgmt.getThreatScore();

                Text message = Text.literal(String.valueOf(threatScore));
                context.getSource().sendFeedback(message);
                return 1;
            })));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("emergencylvl")
                .executes(context -> {
                    int trumpetLvl = emergencyLvlMgmt.getEmergencyLvl();

                    Text message = Text.literal(String.valueOf(trumpetLvl));
                    context.getSource().sendFeedback(message);
                    return 1;
                })));
    }

}



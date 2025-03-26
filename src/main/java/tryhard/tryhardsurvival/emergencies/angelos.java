package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class angelos {
    private static int lastEmergencyLvl = -1;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            int currentEmergencyLvl = emergencyLvlMgmt.getEmergencyLvl();

            if (currentEmergencyLvl != lastEmergencyLvl) {
                lastEmergencyLvl = currentEmergencyLvl;

                if (currentEmergencyLvl > -1) {
                    angela(currentEmergencyLvl);
                    trumpeter.blowTrumpet();
                }
            }
        });
    }
    
    private static void angela(int emergencyLvl) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null) {
            Text AngelaDesc = Text.literal("I am Angela, your AI assistant, secretary, and only companion. I am the manager and bridge for the sephirot, Da'at; The Knowning I.");
            Text DefaultDesc = Text.literal("If an abnormality escapes during management or employees are killed or panic, the alarm will sound depending on the severity.");

            Text message = switch (emergencyLvl) {
                case 1 -> Text.literal("<Angelos>: We have arrived at the emergency level of first trumpet; first trumpet protocols are in effect. Employees are to proceed with caution and supress all abnormalities on sight.")
                        .formatted(Formatting.YELLOW)
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                AngelaDesc)));

                case 2 -> Text.literal("<Angela>: We have arrived at the emergency level of second trumpet; second trumpet protocols are in effect. Many abnormalities have escaped; employees are to supress them immediately.")
                        .formatted(Formatting.RED)
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                AngelaDesc)));
                case 3 -> Text.literal("<Angela>: We have arrived at third trumpet. Good luck, and may we meet again, manager.")
                        .formatted(Formatting.DARK_RED)
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                AngelaDesc)));
                default -> Text.literal("<Angelos>: The situation is back to normal; everybody back to work.")
                        .formatted(Formatting.WHITE)
                        .styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                DefaultDesc)));
            };

            client.inGameHud.getChatHud().addMessage(message);
        }
    }
}

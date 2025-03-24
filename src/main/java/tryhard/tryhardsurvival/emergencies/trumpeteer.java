package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class trumpeteer {
    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                blowTrumpet();
            }
        });
    }

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
}

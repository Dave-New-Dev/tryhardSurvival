package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import static tryhard.tryhardsurvival.emergencies.threatScoreMgmt.getThreatScore;

public class trumpeteer {

    private static int trumpetLvl = 0;

    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                blowTrumpet();
            }
        });
    }

    public static void blowTrumpet() {
        trumpetLvl = 0;
        if (getThreatScore() >= 10 && getThreatScore() < 50) {
            trumpetLvl = 1;
        } else if (getThreatScore() >= 50 && getThreatScore() < 80) {
            trumpetLvl = 2;
        } else if (getThreatScore() >= 80) {
            trumpetLvl = 3;
        }
    }

    public static int getEmergencyLvl() {
        return trumpetLvl;
    }
}

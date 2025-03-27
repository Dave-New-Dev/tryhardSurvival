package tryhard.tryhardsurvival.emergencies;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;

public class trumpeter {

    public static void blowTrumpet(int emergencyLvl) {
        if (MinecraftClient.getInstance().player == null) return;
        if (emergencyLvl <= 0) return;

        SoundEvent trumpet = registerTrumpets.EMERGENCIES[emergencyLvl - 1];

        if (trumpet != null) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(trumpet,1.0F,1.0F));
        }
    }
}

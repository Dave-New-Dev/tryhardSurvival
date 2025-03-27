package tryhard.tryhardsurvival.emergencies;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public class trumpeter {
    private static final Identifier[] trumpets = {
            Identifier.of("tryhardsurvival", "/sounds/emergency1.ogg"),
            Identifier.of("tryhardsurvival", "/sounds/emergency2.ogg"),
            Identifier.of("tryhardsurvival", "/sounds/emergency3.ogg")
    };

    public static void blowTrumpet(int emergencyLvl) {
        if (MinecraftClient.getInstance().player == null) return;
        if (emergencyLvl <= 0) return;

        SoundEvent soundEvent = Registries.SOUND_EVENT.get(trumpets[emergencyLvl-1]);
        if (soundEvent != null) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(soundEvent, 1.0F, 1.0F));
        }
    }
}

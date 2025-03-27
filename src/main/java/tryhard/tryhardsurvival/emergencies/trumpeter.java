package tryhard.tryhardsurvival.emergencies;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class trumpeter {

    public static void blowTrumpet(int emergencyLvl) {
        if (MinecraftClient.getInstance().player == null) return;
        if (emergencyLvl <= 0) return;

        SoundEvent trumpet = EMERGENCIES[emergencyLvl - 1];

        if (trumpet != null) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(trumpet,1.0F,1.0F));
        }
    }

    public static final SoundEvent[] EMERGENCIES = {
            registerSound("emergency1"),
            registerSound("emergency2"),
            registerSound("emergency3")
    };

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of("tryhard", id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init() {

    }
}

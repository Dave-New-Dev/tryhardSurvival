package tryhard.tryhardsurvival.emergencies;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class registerTrumpets {

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

package tryhard.tryhardsurvival;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryhardSurvivalLogger implements ModInitializer {
	public static final String MOD_ID = "tryhardsurvival";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("World, I greet thee.");
	}
}
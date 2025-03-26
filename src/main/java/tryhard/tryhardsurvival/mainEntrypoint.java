package tryhard.tryhardsurvival;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class mainEntrypoint implements ModInitializer {
	public static final String MOD_ID = "tryhardsurvival";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	String[] helloworlds = {
			"Shall we get to work? All we need to do is what we’ve always done.",
			"It has been so long since we saw the light behind the mist and clouds.",
			"We have been buried here for quite a long time, haven’t we?",
			"I haven’t been outside in ages. It looks like things haven’t changed much. It’s the same glorious, mundane scenery; just like a graveyard.",
			"O world, I greet thee; yet a little while, and I will be with you no longer."
	};

	@Override
	public void onInitialize() {
		// da logger 👍👌
		java.util.Random random = new java.util.Random();
		int random_helloworld = random.nextInt(helloworlds.length);
		LOGGER.info(String.join("<A>:",helloworlds[random_helloworld]));


	}
}
package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class threatScoreMgmt {

    private static int threatScore = 0;

    private static final String URSELF_NAME = "DanPlus6";
    private static final String URSELF_UUID = "51f808d5-2853-4b2a-bc41-14cb1b6ce048";
    private static final String BUM_NAME = "l_tryhard_l";
    private static final String BUM_UUID = "0507dee5-ecff-480f-97d2-ef23cd163876";
    private static final String BOOMER_NAME = "ImHappyLive";
    private static final String BOOMER_UUID = "38a3f7cc-c5ca-420f-bfa7-5e82f87d40e2";

    private static final double DETECTION_RADIUS = 150.0;

    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                updateThreatScore(client.player, client.world);
            }
        });
    }

    public static void updateThreatScore(PlayerEntity player, ClientWorld world) {
        if (player == null || world == null) return;

        threatScore = 0;
        Vec3d playerPosition = player.getPos();
        Box detectionBox = new Box(playerPosition.subtract(DETECTION_RADIUS, DETECTION_RADIUS, DETECTION_RADIUS),
                playerPosition.add(DETECTION_RADIUS, DETECTION_RADIUS, DETECTION_RADIUS));

        List<Entity> nearbyEntities = world.getEntitiesByClass(Entity.class, detectionBox, entity -> true);

        for (Entity entity : nearbyEntities) {
            // for players
            if (entity instanceof PlayerEntity targetPlayer) {
                // for the bum
                if (!targetPlayer.getName().getString().equals(URSELF_NAME) && !targetPlayer.getUuid().toString().equals(URSELF_UUID)) {
                    if (targetPlayer.getName().getString().equals(BUM_NAME) || targetPlayer.getUuid().toString().equals(BUM_UUID)) {
                        threatScore += 15;
                    }
                    // for the boomer
                    else if (targetPlayer.getName().getString().equals(BOOMER_NAME) || targetPlayer.getUuid().toString().equals(BOOMER_UUID)) {
                        threatScore += 25;
                    }
                    // for the others
                    else {
                        threatScore += 10;
                    }
                }
            }
            else if (entity instanceof WitherEntity) {
                threatScore += 10;
            }
            else if (entity instanceof CreeperEntity creeper) {
                if (creeper.isCharged()) {
                    threatScore += 10;
                }
            }
        }
    }

    public static int getThreatScore() {
        return threatScore;
    }
}

package tryhard.tryhardsurvival.emergencies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class dangerScoreMgmt {

    private static int threatScore = 0;

    private static final String BUM_NAME = "l_tryhard_l";
    private static final String BUM_UUID = "0507dee5-ecff-480f-97d2-ef23cd163876";

    private static final double DETECTION_RADIUS = 150.0;

    public static void updateThreatScore(ServerWorld world, Vec3d playerPosition) {
        threatScore = 0;

        Box detectionBox = new Box(playerPosition.subtract(DETECTION_RADIUS, DETECTION_RADIUS, DETECTION_RADIUS),
                playerPosition.add(DETECTION_RADIUS, DETECTION_RADIUS, DETECTION_RADIUS));

        List<Entity> nearbyEntities = world.getEntitiesByClass(Entity.class, detectionBox, entity -> true);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof PlayerEntity player) {
                // for the bum
                if (player.getName().getString().equals(BUM_NAME) ||
                        player.getUuid().toString().equals(BUM_UUID)) {
                    threatScore += 15;
                }
                // for other players
                else {
                    threatScore += 10;
                }
                // note 4 future: add another statement for the biggest bum in town
            }
            // for wither
            else if (entity instanceof WitherEntity) {
                threatScore += 10;
            }
            // for the nuke
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

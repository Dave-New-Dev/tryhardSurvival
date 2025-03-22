package tryhard.tryhardsurvival.emergencies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static net.minecraft.entity.Entity.MoveEffect.EVENTS;

public final class emergenciesHudEntrypoint implements ClientModInitializer {
    private final Identifier[] trumpets = {
            Identifier.of("tryhardsurvival","/textures/hud/emergency1.png"),
            Identifier.of("tryhardsurvival","/textures/hud/emergency2.png"),
            Identifier.of("tryhardsurvival","/textures/hud/emergency3.png")
    };

    @Override
    public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, placeholder, emergenciesHudEntrypoint::onRenderGUI));
    }


    public static void onRenderGUI(DrawContext context, float partialTicks)
    {

        int x = 0;
        int y = 0;
        int w = 1280;
        int h = 720;
        context.drawTexture(RenderLayer::getGuiTextured, placeholder, x, y,
                0, 0, w, h, w, h);
    }
}


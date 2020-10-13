package overlordsiii.github.io.kingdomcraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import overlordsiii.github.io.kingdomcraft.client.renderer.block.KingdomCrystalBlockEntityRenderer;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;


@Environment(EnvType.CLIENT)
public class KingdomCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(BlockEntityRegistry.CrystalEntityType, KingdomCrystalBlockEntityRenderer::new);

    }
}

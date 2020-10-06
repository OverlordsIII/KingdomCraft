package overlordsiii.github.io.kingdomcraft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow private ClientWorld world;
    @Shadow @Final private BufferBuilderStorage bufferBuilders;

    @Shadow @Final private MinecraftClient client;

    @Environment(EnvType.CLIENT)
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/debug/DebugRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;DDD)V"))
    private void inject(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci){
        Vec3d camPos = camera.getPos();
        Profiler profiler = world.getProfiler();
        profiler.swap("kingdom");
        assert MinecraftClient.getInstance().world != null;
        KingdomCraft.KINGDOMS.get(MinecraftClient.getInstance().world).getKingdoms().entries().forEach(claim -> {
            BlockPos claimPos = claim.getKey().getPos();
            int radius = claim.getKey().getRadius();

            matrices.push();
            matrices.translate(claimPos.getX() - camPos.x, claimPos.getY() - camPos.y, claimPos.getZ() - camPos.z);
            WorldRenderer.drawBox(matrices, bufferBuilders.getEffectVertexConsumers().getBuffer(RenderLayer.getLines()), -radius, -radius, -radius, radius, radius, radius, 0.9F, 0.9F, 0.9F, 1.0F, 0.5F, 0.5F, 0.5F);
            matrices.pop();
        });
    }
}

package overlordsiii.github.io.kingdomcraft.client.renderer.block;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Quaternion;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

import java.util.Objects;

import static net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer.BEAM_TEXTURE;

public class KingdomCrystalBlockEntityRenderer extends BlockEntityRenderer<KingdomCrystalBlockEntity> {
    private static ItemStack stack = new ItemStack(Items.JUKEBOX, 1);
    public KingdomCrystalBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    private DyeColor BEAM_COLOR = DyeColor.LIGHT_BLUE;
    @Override
    public void render(KingdomCrystalBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
            double offset = Math.sin((Objects.requireNonNull(blockEntity.getWorld()).getTime() + tickDelta) / 8.0) / 4.0;
            matrices.translate(0.5, 1.25 + offset, 0.5);
            int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
           MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
           matrices.pop();
            matrices.push();
            float width = 27;
           matrices.translate(0.5F, 2.5F, 0.5F);
        matrices.translate(-MinecraftClient.getInstance().textRenderer.getWidth(new LiteralText("Hello World!")) / 2F, 2, 0);
           matrices.scale(-0.025F, -0.025F, -0.025F);
        assert MinecraftClient.getInstance().player != null;
        matrices.multiply(new Quaternion(Vector3f.NEGATIVE_Y, MinecraftClient.getInstance().player.getYaw(MinecraftClient.getInstance().getTickDelta()), true));
        TextRenderer renderer = this.dispatcher.getTextRenderer();

        renderer.drawWithShadow(matrices, "Hello World!", width/2, 16, 16777215, false);
        matrices.pop();
          // drawRect(matrices, vertexConsumers.getBuffer(RenderLayer.getTextSeeThrough(new Identifier(""))), -width*2 , -16, width*2 , 16, 0xFF000000);
       //     drawRect(matrices, vertexConsumers.getBuffer(RenderLayer.getLightning()), -width/2, -2, progressionWidth, 2, 0xFF44FF44);
        Text name = new LiteralText("Hello World!");
        matrices.push();
        assert MinecraftClient.getInstance().player != null;
        matrices.multiply(new Quaternion(Vector3f.NEGATIVE_Y, MinecraftClient.getInstance().player.getYaw(tickDelta), true));
        matrices.translate(0, 0, -0.4);

        float scale = -0.025F;
        matrices.scale(scale, scale, scale);

        int color = name.getStyle().getColor() == null ? 0xFFFFFF : name.getStyle().getColor().getRgb();
        matrices.translate(-MinecraftClient.getInstance().textRenderer.getWidth(name) / 2F, 2, 0);
        this.dispatcher.getTextRenderer().drawWithShadow(matrices, name, 0, 2, color);
           matrices.pop();
           matrices.push();
            long time = blockEntity.getWorld().getTime();
            BeaconBlockEntityRenderer.renderLightBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 2.0f, time, 3, 256, BEAM_COLOR.getColorComponents(), 0.2F, 0.25F);
           matrices.pop();
        }

    private void drawRect(MatrixStack stack, VertexConsumer vc, float minX, float minY, float maxX, float maxY, int color) {
        int alpha = ((color & 0xFF000000) >> 24);
        int red = ((color & 0xFF0000) >> 16);
        int green = ((color & 0xFF00) >> 8);
        int blue = ((color & 0xFF));
        float z = 0;
        vc.vertex(stack.peek().getModel(), minX, maxY, z).color(red, green, blue, alpha).texture(0.09F, 0.1F).light(0xFFFFFFFF).next();
        vc.vertex(stack.peek().getModel(), maxX, maxY, z).color(red, green, blue, alpha).texture(0.1F, 0.1F).light(0xFFFFFFFF).next();
        vc.vertex(stack.peek().getModel(), maxX, minY, z).color(red, green, blue, alpha).texture(0.1F, 0.09F).light(0xFFFFFFFF).next();
        vc.vertex(stack.peek().getModel(), minX, minY, z).color(red, green, blue, alpha).texture(0.09F, 0.09F).light(0xFFFFFFFF).next();
    }

    }




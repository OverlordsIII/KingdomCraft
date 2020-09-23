package overlordsiii.github.io.kingdomcraft.block.entity.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

public class KingdomCrystalBlockEntityRenderer extends BlockEntityRenderer<KingdomCrystalBlockEntity> {
    private static ItemStack stack = new ItemStack(Items.JUKEBOX, 1);
    public KingdomCrystalBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(KingdomCrystalBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        if (blockEntity.getKingdomName() != null) {
            double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
            matrices.translate(0.5, 1.25 + offset, 0.5);
            int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        /*
        TextRenderer textRenderer = this.dispatcher.getTextRenderer();
        if (blockEntity.getKingdomName() != null) {
            String string = blockEntity.getKingdomName().asString();
            float s = (float) (-textRenderer.getWidth(string) / 2);
            textRenderer.draw(string, s, 5f, 16777215, true, matrices.peek().getModel(), vertexConsumers, false, 0, 15728880);
        }
         */
        }
         matrices.pop();

        }



    }




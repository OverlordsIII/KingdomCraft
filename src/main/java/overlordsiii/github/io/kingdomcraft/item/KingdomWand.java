package overlordsiii.github.io.kingdomcraft.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
import overlordsiii.github.io.kingdomcraft.client.screen.ChunkClaimScreen;
import overlordsiii.github.io.kingdomcraft.registry.BlockRegistry;

public class KingdomWand extends Item {
    public KingdomCrystalBlockEntity crystalentity;
    public KingdomWand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (world.getBlockState(pos).isOf(BlockRegistry.crystalBlock)){
            if (world.getBlockEntity(pos) instanceof KingdomCrystalBlockEntity){
                KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity)world.getBlockEntity(pos);
                assert entity != null;
                entity.linkedWand = this;
                this.crystalentity = entity;
                context.getPlayer().sendMessage(new TranslatableText("linked.wand"), true);
            }
        }
        return ActionResult.SUCCESS;
    }
    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (crystalentity != null && hand == Hand.MAIN_HAND){
            if (world.isClient){
                MinecraftClient.getInstance().openScreen(new ChunkClaimScreen(crystalentity.getPos(), user.getBlockPos()));
                return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
            }
        }
        else{
            user.sendMessage(new TranslatableText("link.fail").formatted(Formatting.RED), true);
        }
        return new TypedActionResult<>(ActionResult.PASS, user.getStackInHand(hand));
    }
}

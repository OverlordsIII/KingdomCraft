package overlordsiii.github.io.kingdomcraft.block;


import com.jamieswhiteshirt.rtree3i.Entry;
import com.jamieswhiteshirt.rtree3i.Selection;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.core.Kingdom;
import overlordsiii.github.io.kingdomcraft.core.KingdomArea;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
import overlordsiii.github.io.kingdomcraft.client.screen.KingdomNameScreen;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;
import overlordsiii.github.io.kingdomcraft.util.KingdomUtil;

import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("deprecation")
public class KingdomCrystalBlock extends BlockWithEntity {
    private final int radius;
    public KingdomCrystalBlock(Settings settings, int radius) {
        super(settings);
        this.radius = radius;
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
   public BlockEntity createBlockEntity(BlockView world) {
        return new KingdomCrystalBlockEntity();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
       if (!world.isClient && placer instanceof PlayerEntity){
    Kingdom kingdom;
    if (itemStack.hasCustomName()) {
        kingdom = new Kingdom(placer.getUuid(), pos, itemStack.getName().asString());
    }
    else{
        kingdom = new Kingdom(placer.getUuid(), pos, "Choose a kingdom name!");
    }
               if (world.getBlockEntity(pos) instanceof KingdomCrystalBlockEntity){
                   KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity)world.getBlockEntity(pos);
                   assert entity != null;
                   entity.setPos(pos);
               }
           kingdom.attachCrystal(world);
           KingdomCraft.KINGDOMS.get(world).add(new KingdomArea(pos, radius), kingdom);
       }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return KingdomUtil.checkKingdomsIntersect(world, pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius)).isEmpty();
    }


    /**
     * Called when this block is used by a player.
     * This, by default, is bound to using the right mouse button.
     *
     * <p>This method is called on both the logical client and logical server, so take caution when overriding this method.
     * The logical side can be checked using {@link World#isClient() world.isClient()}.
     *
     * <p>If the action result is successful on a logical client, then the action will be sent to the logical server for processing.
     *
     * @param state
     * @param world
     * @param pos
     * @param player
     * @param hand
     * @param hit
     * @return an action result that specifies if using the block was successful.
     */
    @Environment(EnvType.CLIENT)
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient && !(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof KingdomWand) && !player.isSneaking()){
              Selection<Entry<KingdomArea, Kingdom>> hellow =   KingdomUtil.getKingdoms(world, pos);
              AtomicBoolean bl = new AtomicBoolean(false);
              hellow.forEach(kingdomAreaKingdomEntry -> {
                  if (kingdomAreaKingdomEntry.getValue().getRuler().equals(player.getUuid())){
                      bl.set(true);
                  }
              });
                if (bl.get()){
                    MinecraftClient.getInstance().openScreen(new CottonClientScreen(new KingdomNameScreen()));
                }
                else{
                    player.sendMessage(new TranslatableText("player.uuid.notequal").formatted(Formatting.RED), true);

                }
            }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        KingdomUtil.getKingdoms(world, pos).forEach(kingdomAreaKingdomEntry -> {
            KingdomCraft.KINGDOMS.get(world).remove(kingdomAreaKingdomEntry.getKey());
        });
        super.onBreak(world, pos, state, player);
    }


}

package overlordsiii.github.io.kingdomcraft.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

public class KingdomCrystalBlock extends Block implements BlockEntityProvider {

    public KingdomCrystalBlock(Settings settings) {
        super(settings);
    }

    @Override
   public BlockEntity createBlockEntity(BlockView world) {
        return new KingdomCrystalBlockEntity();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
       BlockEntity entity =  world.getBlockEntity(pos);
        if (entity instanceof KingdomCrystalBlockEntity){
            KingdomCrystalBlockEntity kingdomCrystal = (KingdomCrystalBlockEntity)entity;
            kingdomCrystal.setPos(pos);
            kingdomCrystal.addChunk(pos);
        }
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
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hand, hit);
    }
}

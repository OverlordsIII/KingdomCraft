package overlordsiii.github.io.kingdomcraft.block;


import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

public class KingdomCrystalBlock extends BlockWithEntity {

    public KingdomCrystalBlock(Settings settings) {
        super(settings);
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
       BlockEntity entity =  world.getBlockEntity(pos);
        if (entity instanceof KingdomCrystalBlockEntity && placer instanceof PlayerEntity){
            KingdomCrystalBlockEntity kingdomCrystal = (KingdomCrystalBlockEntity)entity;
            kingdomCrystal.setPos(pos);
            kingdomCrystal.addChunk(pos);
            kingdomCrystal.linkedPlayer = (PlayerEntity)placer;
            ((PlayerEntity) placer).sendMessage(new TranslatableText("chunks.claim.chunk"), true);
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
     * @param state the state of the block
     * @param world the world that the block is in
     * @param pos   the position of the block when click on
     * @param player   the player that "used" the block
     * @param hand  the hand the player used to click the block
     * @param hit
     * @return an action result that specifies if using the block was successful.
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public @Nullable NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return super.createScreenHandlerFactory(state, world, pos);
    }
}

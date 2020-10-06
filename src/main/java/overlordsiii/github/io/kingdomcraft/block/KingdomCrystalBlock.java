package overlordsiii.github.io.kingdomcraft.block;


import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.api.Kingdom;
import overlordsiii.github.io.kingdomcraft.api.KingdomArea;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
import overlordsiii.github.io.kingdomcraft.client.screen.libgui.KingdomNameScreen;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;

@SuppressWarnings("deprecation")
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
       if (!world.isClient && placer instanceof PlayerEntity){


           Kingdom kingdom = new Kingdom(placer.getUuid(), pos);
                kingdom.attachCrystal(world);
               if (world.getBlockEntity(pos) instanceof KingdomCrystalBlockEntity){
                   KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity)world.getBlockEntity(pos);
                   assert entity != null;
                   entity.setLinkedPlayer((PlayerEntity) placer);
                   entity.setPos(pos);
               }
           KingdomCraft.KINGDOMS.get(world).add(new KingdomArea(pos, 15), kingdom);
       }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
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
            BlockEntity e = world.getBlockEntity(pos);
            if (e instanceof KingdomCrystalBlockEntity){
                KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity)e;
                if (player.equals(entity.getLinkedPlayer())){
                    MinecraftClient.getInstance().openScreen(new CottonClientScreen(new KingdomNameScreen()));
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}

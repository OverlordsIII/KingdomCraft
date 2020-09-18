package overlordsiii.github.io.kingdomcraft.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
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
        if (entity instanceof KingdomCrystalBlockEntity && placer instanceof PlayerEntity){
            KingdomCrystalBlockEntity kingdomCrystal = (KingdomCrystalBlockEntity)entity;
            kingdomCrystal.setPos(pos);
            kingdomCrystal.addChunk(pos);
            kingdomCrystal.linkedPlayer = (PlayerEntity)placer;
            ((PlayerEntity) placer).sendMessage(new TranslatableText("chunks.claim.chunk"), true);
        }
    }


}

package overlordsiii.github.io.kingdomcraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
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

}

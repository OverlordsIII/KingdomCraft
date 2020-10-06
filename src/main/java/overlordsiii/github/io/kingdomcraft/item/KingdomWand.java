package overlordsiii.github.io.kingdomcraft.item;

import com.jamieswhiteshirt.rtree3i.Entry;
import com.jamieswhiteshirt.rtree3i.Selection;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.api.Kingdom;
import overlordsiii.github.io.kingdomcraft.api.KingdomArea;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
import overlordsiii.github.io.kingdomcraft.registry.BlockRegistry;
import overlordsiii.github.io.kingdomcraft.util.KingdomUtil;

import java.util.Objects;

public class KingdomWand extends Item {
    private KingdomCrystalBlockEntity crystalentity;

    public KingdomWand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (world.getBlockState(pos).isOf(BlockRegistry.crystalBlock)) {
            if (world.getBlockEntity(pos) instanceof KingdomCrystalBlockEntity) {
                KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity) world.getBlockEntity(pos);
                assert entity != null;
                entity.setLinkedWand(this);
                this.crystalentity = entity;
                Objects.requireNonNull(context.getPlayer()).sendMessage(new TranslatableText("linked.wand"), true);
                Selection<Entry<KingdomArea, Kingdom>> hi = KingdomUtil.getKingdoms(world, pos);
                KingdomUtil.getKingdoms(world, context.getBlockPos()).forEach(claimedArea -> {
                    KingdomUtil.setRadius(claimedArea.getValue(), claimedArea.getKey(), 20, world);
                });
            }

        }
        return ActionResult.SUCCESS;
    }
}

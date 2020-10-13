package overlordsiii.github.io.kingdomcraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;
import overlordsiii.github.io.kingdomcraft.util.KingdomUtil;

import java.util.Objects;

public class KingdomWand extends Item {
    private KingdomCrystalBlockEntity crystalentity;

    public KingdomWand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        System.out.println("WOOTOWOOWOOTOT");
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        System.out.println("before = " + world.getBlockEntity(pos));
            if (world.getBlockEntity(pos) instanceof KingdomCrystalBlockEntity) {
                KingdomCrystalBlockEntity entity = (KingdomCrystalBlockEntity) world.getBlockEntity(pos);
                assert entity != null;
                System.out.println("after = " + entity.getLinkedWand());
                if (entity.getLinkedWand() == null) {
                    entity.setLinkedWand(this);
                    this.crystalentity = entity;
                    Objects.requireNonNull(context.getPlayer()).sendMessage(new TranslatableText("linked.wand"), true);
                }
                else {
                    KingdomUtil.getKingdoms(world, context.getBlockPos()).forEach(claimedArea -> {
                        KingdomUtil.setRadius(claimedArea.getValue(), claimedArea.getKey(), claimedArea.getKey().getRadius() + 20, world);
                    });
                }
            }
        return ActionResult.SUCCESS;
    }
}

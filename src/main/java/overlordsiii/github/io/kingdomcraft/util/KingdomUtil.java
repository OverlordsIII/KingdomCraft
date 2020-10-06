package overlordsiii.github.io.kingdomcraft.util;

import com.jamieswhiteshirt.rtree3i.Box;
import com.jamieswhiteshirt.rtree3i.Entry;
import com.jamieswhiteshirt.rtree3i.Selection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.api.Kingdom;
import overlordsiii.github.io.kingdomcraft.api.KingdomArea;

import java.util.UUID;

public class KingdomUtil {
    public static void setRadius(Kingdom kingdom, KingdomArea area, int newRadius, World world){
        KingdomCraft.KINGDOMS.get(world).getKingdoms().entries().forEach((kingdomAreaKingdomEntry -> {
            if (kingdomAreaKingdomEntry.getValue().getPos().equals(kingdom.getPos()) && kingdomAreaKingdomEntry.getKey().getPos().equals(area.getPos())){
                UUID ruler = kingdom.getRuler();
                BlockPos pos = kingdom.getPos();
                KingdomCraft.KINGDOMS.get(world).remove(area);
                Kingdom king = new Kingdom(ruler, pos);
                king.attachCrystal(world);
                KingdomCraft.KINGDOMS.get(world).add(new KingdomArea(pos, newRadius), king);
            }
        }));
    }
    public static Selection<Entry<KingdomArea, Kingdom>> getKingdoms(WorldView world, BlockPos pos) {
        Box checkBox = Box.create(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
        return KingdomCraft.KINGDOMS.get(world).getKingdoms().entries(box -> box.contains(checkBox));
    }
}

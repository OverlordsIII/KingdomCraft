package overlordsiii.github.io.kingdomcraft.cca;

import com.jamieswhiteshirt.rtree3i.ConfigurationBuilder;
import com.jamieswhiteshirt.rtree3i.RTreeMap;
import nerdhub.cardinal.components.api.ComponentType;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.api.Kingdom;
import overlordsiii.github.io.kingdomcraft.api.KingdomArea;

public class KingdomWorldComponent implements KingdomComponent {
    private RTreeMap<KingdomArea, Kingdom> kingdoms = RTreeMap.create(new ConfigurationBuilder().star().build(), KingdomArea::toBox);
    private final World world;
    public KingdomWorldComponent(World world) {
        this.world = world;
    }

    @Override
    public RTreeMap<KingdomArea, Kingdom> getKingdoms() {
        return kingdoms;
    }

    @Override
    public void add(KingdomArea area, Kingdom kingdom) {
        this.kingdoms = this.kingdoms.put(area, kingdom);
        sync();
        System.out.println(this.kingdoms.size());
        System.out.println("ADDED CLAIM");
    }

    @Override
    public void remove(KingdomArea area) {
        this.kingdoms = this.kingdoms.remove(area);
        sync();
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.kingdoms = RTreeMap.create(new ConfigurationBuilder().star().build(), KingdomArea::toBox);

        ListTag listTag = tag.getList("Kingdoms", NbtType.COMPOUND);

        listTag.forEach(child -> {
            CompoundTag childCompound = (CompoundTag) child;
            KingdomArea area = areaFromTag((CompoundTag) childCompound.get("Area"));
            Kingdom kingdom = Kingdom.fromTag((CompoundTag) childCompound.get("KingdomInfo"));
            kingdom.attachCrystal(world);
            add(area, kingdom);
        });
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        ListTag listTagKingdoms = new ListTag();

        kingdoms.entries().forEach(claim -> {
            CompoundTag claimTag = new CompoundTag();

            claimTag.put("Area", toBoxTag(claim.getKey()));
            claimTag.put("KingdomInfo", claim.getValue().toTag());

            listTagKingdoms.add(claimTag);
        });

        tag.put("Kingdoms", listTagKingdoms);
        return tag;
    }

    public CompoundTag toBoxTag(KingdomArea area) {
        CompoundTag areaTag = new CompoundTag();

        areaTag.putLong("OriginPos", area.getPos().asLong());
        areaTag.putInt("Radius", area.getRadius());

        return areaTag;
    }

    public KingdomArea areaFromTag(CompoundTag tag) {
        BlockPos originPos = BlockPos.fromLong(tag.getLong("OriginPos"));
        int radius = tag.getInt("Radius");
        return new KingdomArea(originPos, radius);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return KingdomCraft.KINGDOMS;
    }

}

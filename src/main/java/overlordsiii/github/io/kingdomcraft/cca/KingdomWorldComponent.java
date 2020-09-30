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
    private RTreeMap<KingdomArea, Kingdom> claims = RTreeMap.create(new ConfigurationBuilder().star().build(), KingdomArea::toBox);
    private final World world;
    public KingdomWorldComponent(World world) {
        this.world = world;
    }

    @Override
    public RTreeMap<KingdomArea, Kingdom> getKingdoms() {
        return claims;
    }

    @Override
    public void add(KingdomArea box, Kingdom info) {
        this.claims = this.claims.put(box, info);
        sync();
        System.out.println(this.claims.size());
        System.out.println("ADDED CLAIM");
    }

    @Override
    public void remove(KingdomArea box) {
        this.claims = this.claims.remove(box);
        sync();
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.claims = RTreeMap.create(new ConfigurationBuilder().star().build(), KingdomArea::toBox);

        ListTag listTag = tag.getList("Claims", NbtType.COMPOUND);

        listTag.forEach(child -> {
            CompoundTag childCompound = (CompoundTag) child;
            KingdomArea box = boxFromTag((CompoundTag) childCompound.get("Box"));
            Kingdom claimInfo = Kingdom.fromTag((CompoundTag) childCompound.get("Info"));
            add(box, claimInfo);
        });
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        ListTag listTagClaims = new ListTag();

        claims.entries().forEach(claim -> {
            CompoundTag claimTag = new CompoundTag();

            claimTag.put("Box", serializeBox(claim.getKey()));
            claimTag.put("Info", claim.getValue().toTag());

            listTagClaims.add(claimTag);
        });

        tag.put("Claims", listTagClaims);
        return tag;
    }

    public CompoundTag serializeBox(KingdomArea box) {
        CompoundTag boxTag = new CompoundTag();

        boxTag.putLong("OriginPos", box.getPos().asLong());
        boxTag.putInt("Radius", box.getRadius());

        return boxTag;
    }

    public KingdomArea boxFromTag(CompoundTag tag) {
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
        return KingdomCraft.KINGDOM;
    }

}

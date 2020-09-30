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

import java.util.UUID;

public class KingdomWorldComponent implements KingdomComponent {
    private RTreeMap<KingdomArea, Kingdom> kingdoms = RTreeMap.create(new ConfigurationBuilder().build(), KingdomArea::toBox);
    private final World world;
    public KingdomWorldComponent(World world){
        this.world = world;
        System.out.println("constructed...");
    }
    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        this.kingdoms = RTreeMap.create(new ConfigurationBuilder().star().build(), KingdomArea::toBox);
        ListTag listTag = compoundTag.getList("kingdoms", NbtType.COMPOUND);
        listTag.forEach(kingdom ->{
            CompoundTag tag = (CompoundTag)kingdom;
            KingdomArea area = KingdomArea.deserialize((CompoundTag) tag.get("kingdomarea"));
            Kingdom kingdom1 = desearilize((CompoundTag) tag.get("kingdom"));
            add(kingdom1, area);
        });
        System.out.println("TAG FROM");
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        ListTag listTag = new ListTag();
        kingdoms.entries().forEach((kingdom -> {
            CompoundTag tag = new CompoundTag();
            tag.put("kingdomarea", kingdom.getKey().serialize());
            tag.put("kingdom", kingdom.getValue().toTag());
            listTag.add(tag);
        }));
        compoundTag.put("kingdoms", listTag);
        System.out.println("TAGGERS" + compoundTag);
        return compoundTag;

    }

    /**
     * {@inheritDoc}
     *
     * @implNote The default implementation should generally be overridden.
     * This implementation performs a linear-time lookup on the provider to find the component type
     * this component is associated with.
     * Implementing classes can nearly always provide a better implementation.
     */
    @Override
    public ComponentType<?> getComponentType() {
        return KingdomCraft.KINGDOM;
    }

    @Override
    public RTreeMap<KingdomArea, Kingdom> getKingdoms() {
        return kingdoms;
    }

    @Override
    public void add(Kingdom kingdom, KingdomArea area) {
        kingdoms.put(area, kingdom);
        sync();
        System.out.println("ADDED CLAIM!");
        System.out.println(kingdoms);
        System.out.println(kingdoms.size());
    }

    @Override
    public void remove(Kingdom kingdom, KingdomArea area) {
        kingdoms.remove(area, kingdom);
        System.out.println("REMOVEd");
        sync();
    }
    public Kingdom desearilize(CompoundTag tag) {
        UUID ruler = tag.getUuid("ruler");
        BlockPos pos = BlockPos.fromLong(tag.getLong("pos"));
        return new Kingdom(ruler, pos);
    }

}

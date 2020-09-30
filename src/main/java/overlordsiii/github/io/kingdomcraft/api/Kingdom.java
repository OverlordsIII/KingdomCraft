package overlordsiii.github.io.kingdomcraft.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class Kingdom {
    public final UUID ruler;
    public final BlockPos pos;

    public Kingdom(UUID owner, BlockPos posofCrystal) {
        this.ruler = owner;
        this.pos = posofCrystal;
    }

    public boolean isRuler(UUID uuid) {
        return uuid.equals(ruler);
    }

    public UUID getRuler() {
        return ruler;
    }

    public BlockPos getPos() {
        return pos;
    }

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        tag.putUuid("ruler", ruler);
        tag.putLong("pos", pos.asLong());
        return tag;
    }



}

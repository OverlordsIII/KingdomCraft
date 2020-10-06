package overlordsiii.github.io.kingdomcraft.api;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

import java.util.UUID;

public class Kingdom {
    private final UUID ruler;
    private final BlockPos pos;
    private KingdomCrystalBlockEntity entity;
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
    public KingdomCrystalBlockEntity getEntity(){
        return entity;
    }
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        tag.putUuid("ruler", ruler);
        tag.putLong("pos", pos.asLong());

        return tag;
    }
    public static Kingdom fromTag(CompoundTag tag){
        UUID uuid = tag.getUuid("ruler");
        BlockPos blockPos = BlockPos.fromLong(tag.getLong("pos"));
        return new Kingdom(uuid, blockPos);
    }
    public void attachCrystal(World world){
       BlockEntity entity =  world.getBlockEntity(this.pos);
       if (entity instanceof KingdomCrystalBlockEntity){
           this.entity = (KingdomCrystalBlockEntity)entity;
           KingdomCrystalBlockEntity entityy = (KingdomCrystalBlockEntity)entity;
           entityy.setKingdom(this);
       }
    }



}

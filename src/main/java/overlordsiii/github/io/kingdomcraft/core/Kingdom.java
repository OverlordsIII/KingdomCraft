package overlordsiii.github.io.kingdomcraft.core;

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
    private String kingdomName;
    public Kingdom(UUID owner, BlockPos posofCrystal, String kingdomName) {
        this.ruler = owner;
        this.pos = posofCrystal;
        this.kingdomName = kingdomName;
    }
    public String getKingdomName(){
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
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
        tag.putString("name", kingdomName);
        return tag;
    }
    public static Kingdom fromTag(CompoundTag tag){
        UUID uuid = tag.getUuid("ruler");
        BlockPos blockPos = BlockPos.fromLong(tag.getLong("pos"));
        String kingomname = tag.getString("name");
        return new Kingdom(uuid, blockPos, kingomname);
    }
    public void attachCrystal(World world){
       BlockEntity entity =  world.getBlockEntity(this.pos);
       if (entity instanceof KingdomCrystalBlockEntity){
           this.entity = (KingdomCrystalBlockEntity)entity;
           KingdomCrystalBlockEntity entityy = (KingdomCrystalBlockEntity)entity;
           entityy.setKingdom(this);
       }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();

        return new Kingdom(this.ruler, this.pos, this.kingdomName);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Kingdom){
            Kingdom kingdom = (Kingdom)obj;
            if (this.kingdomName.equals(kingdom.kingdomName) && this.ruler.equals(kingdom.ruler) && this.pos.equals(kingdom.pos)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append("pos=").append(pos.toString()).append(",");
        builder.append("ruler=").append(ruler.toString()).append(",");
        builder.append("name=").append(kingdomName);
        builder.append("]");
        return builder.toString();
    }
}

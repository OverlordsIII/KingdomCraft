package overlordsiii.github.io.kingdomcraft.core;

import com.jamieswhiteshirt.rtree3i.Box;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;

public class KingdomArea {

    private BlockPos pos;

    private int radius;

    public KingdomArea(BlockPos pos, int radius){
        this.pos = pos;
        this.radius = radius;
    }

    public Box toBox() {
        BlockPos lower = pos.add(-radius, -radius, -radius);
        BlockPos upper = pos.add(radius, radius, radius);
        return Box.create(lower.getX(), lower.getY(), lower.getZ(), upper.getX(), upper.getY(), upper.getZ());
    }

    public BlockPos getPos(){
        return pos;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }

    public CompoundTag serialize(){
        CompoundTag tag = new CompoundTag();
        tag.putInt("radius", this.radius);
        tag.putLong("pos", this.pos.asLong());
        return tag;
    }

    public static KingdomArea deserialize(CompoundTag tag){
       int radius = tag.getInt("radius");
       BlockPos pos = BlockPos.fromLong(tag.getLong("pos"));
       return new KingdomArea(pos, radius);
    }

}

package overlordsiii.github.io.kingdomcraft.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import overlordsiii.github.io.kingdomcraft.core.Kingdom;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;

public class KingdomCrystalBlockEntity extends BlockEntity {
    private KingdomWand linkedWand;
    private Text kingdomName;
    private Kingdom kingdom;

    public KingdomCrystalBlockEntity() {
        super(BlockEntityRegistry.CrystalEntityType);
    }




    public void setLinkedWand(KingdomWand wand){
        this.linkedWand = wand;
    }
    public KingdomWand getLinkedWand(){
        return this.linkedWand;
    }
    public void setKingdom(Kingdom kingdom){
        this.kingdom = kingdom;
    }
    public Kingdom getKingdom(){
        return this.kingdom;
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (kingdomName != null) {
            tag.putString("kingdomname", kingdomName.asString());
        }
      return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);

       kingdomName = new LiteralText(tag.getString("kingdomname"));
    }
    @Override
    public double getSquaredRenderDistance() {
        return 256.0D;
    }


}

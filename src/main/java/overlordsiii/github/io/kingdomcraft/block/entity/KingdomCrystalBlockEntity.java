package overlordsiii.github.io.kingdomcraft.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;

import java.util.HashMap;

public class KingdomCrystalBlockEntity extends BlockEntity {
    private HashMap<ChunkPos, WorldChunk> chunksOwned = new HashMap<>();
    public PlayerEntity linkedPlayer;
    public KingdomWand linkedWand;
    private Text kingdomName;


    public KingdomCrystalBlockEntity() {
        super(BlockEntityRegistry.CrystalEntityType);
    }

    public int getBlockRange(){
        return 128;
    }
    public Text getKingdomName(){
        return kingdomName;
    }
    public void setKingdomName(Text text){
        kingdomName = text;
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


}

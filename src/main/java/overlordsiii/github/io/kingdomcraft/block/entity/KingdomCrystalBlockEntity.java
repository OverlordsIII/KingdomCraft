package overlordsiii.github.io.kingdomcraft.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;

import java.util.HashMap;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.LOGGER;

public class KingdomCrystalBlockEntity extends BlockEntity {
    public HashMap<BlockPos, Chunk> chunksOwned = new HashMap<>();
    public PlayerEntity linkedPlayer;
    public KingdomWand linkedWand;
    private Text kingdomName;
    protected int chunkLimit = 4;

    public KingdomCrystalBlockEntity() {
        super(BlockEntityRegistry.CrystalEntityType);
    }

    public void addChunk(BlockPos pos){
       if (this.world.getChunk(pos)!=null) {
           if (this.getBlockRange() > pos.getSquaredDistance(this.pos)) {
               if (this.chunkLimit > chunksOwned.size()) {
                   this.chunksOwned.put(pos, this.world.getChunk(pos));
               }
               else{
                   linkedPlayer.sendMessage(new TranslatableText("chunks.amount.overload"), true);
               }
           }
           else{
               linkedPlayer.sendMessage(new TranslatableText("chunk.distance.fail").formatted(Formatting.RED), true);
           }
       }
       else {
           LOGGER.warn("Could not add chunk to chunks list");
       }

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
        tag.putString("kingdomname", kingdomName.asString());
        tag.putInt("chunksowned", chunksOwned.size());
        tag.putInt("chunklimit", chunkLimit);
        chunksOwned.keySet().forEach((BlockPos pos)->{

        });
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
       kingdomName = new LiteralText(tag.getString("kingdomname"));
        chunkLimit = tag.getInt("chunklimit");
    }


}

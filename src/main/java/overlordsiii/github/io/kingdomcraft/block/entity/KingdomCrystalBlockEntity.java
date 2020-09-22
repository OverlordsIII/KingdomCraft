package overlordsiii.github.io.kingdomcraft.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;
import java.util.ArrayList;
import java.util.List;
import static overlordsiii.github.io.kingdomcraft.KingdomCraft.LOGGER;

public class KingdomCrystalBlockEntity extends BlockEntity {
    public List<Chunk> chunksOwned = new ArrayList<>();
    public PlayerEntity linkedPlayer;
    public KingdomWand linkedWand;
    public String kingdomName;
    public KingdomCrystalBlockEntity() {
        super(BlockEntityRegistry.CrystalEntityType);
    }
    public void addChunk(BlockPos pos){
       if (this.world.getChunk(pos)!=null) {
           if (this.getChunkRange() > pos.getSquaredDistance(this.pos)) {
               this.chunksOwned.add(this.world.getChunk(pos));
           }
           else{
               linkedPlayer.sendMessage(new TranslatableText("chunk.distance.fail").formatted(Formatting.RED), true);
           }
       }
       else {
           LOGGER.warn("Could not add chunk to chunks list");
       }

    }
    public int getChunkRange(){
        return 128;
    }


}

package overlordsiii.github.io.kingdomcraft.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;

import java.util.ArrayList;
import java.util.List;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.*;
public class KingdomCrystalBlockEntity extends BlockEntity {
    public List<Chunk> chunksOwned = new ArrayList<>();
    public PlayerEntity linkedPlayer;
    public KingdomCrystalBlockEntity() {
        super(BlockEntityRegistry.CrystalEntityType);
    }
    public void addChunk(BlockPos pos){
       if (this.world.getChunk(pos)!=null) {
           this.chunksOwned.add(this.world.getChunk(pos));
       }
       else {
           LOGGER.warn("Could not add chunk to chunks list");
       }

    }


}

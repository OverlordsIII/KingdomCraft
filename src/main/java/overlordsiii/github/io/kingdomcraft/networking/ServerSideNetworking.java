package overlordsiii.github.io.kingdomcraft.networking;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.LOGGER;
public class ServerSideNetworking {
    public static void initialize(){
        ServerSidePacketRegistry.INSTANCE.register(PacketIds.chunkClaimedId, (packetContext, attachedData) -> {
            BlockPos entitypos = attachedData.readBlockPos();
            BlockPos chunkPos = attachedData.readBlockPos();
            packetContext.getTaskQueue().execute(() -> {
               BlockEntity entity =  packetContext.getPlayer().world.getBlockEntity(entitypos);
                if (entity instanceof KingdomCrystalBlockEntity){
                    KingdomCrystalBlockEntity kingdom = (KingdomCrystalBlockEntity)entity;
                    kingdom.addChunk(chunkPos);
                }
                else{
                    packetContext.getPlayer().sendMessage(new TranslatableText("chunk.claim.fail").formatted(Formatting.RED), true);
                    LOGGER.error("provided block pos was not the blockpos of crystal blockentity");
                }
            });
        });
    }
}

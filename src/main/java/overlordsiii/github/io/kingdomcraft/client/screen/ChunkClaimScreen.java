package overlordsiii.github.io.kingdomcraft.client.screen;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import overlordsiii.github.io.kingdomcraft.networking.PacketIds;

public class ChunkClaimScreen extends Screen {
    public BlockPos entitypos;
    public BlockPos playerpos;
    public ChunkClaimScreen(BlockPos crystalPos, BlockPos playerPos) {
        super(new TranslatableText("gui.claim.text"));
        this.entitypos = crystalPos;
        this.playerpos = playerPos;
    }

    @Override
    protected void init() {
        this.addButton(new ButtonWidget(0, 0, 0, 0, new TranslatableText("claim.chunk.yes"), (buttonWidget)->{
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeBlockPos(entitypos);
            byteBuf.writeBlockPos(playerpos);
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketIds.chunkClaimedId, byteBuf);
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 96, 150, 20, new TranslatableText("claim.chunk.no"), (buttonWidget) -> {

        }));

    }
}

package overlordsiii.github.io.kingdomcraft.client.screen;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import overlordsiii.github.io.kingdomcraft.networking.PacketIds;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.MOD_ID;

public class ChunkClaimScreen extends LightweightGuiDescription {
    public BlockPos entitypos;
    public BlockPos playerpos;
    public ChunkClaimScreen(BlockPos crystalPos, BlockPos playerPos) {
        this.entitypos = crystalPos;
        this.playerpos = playerPos;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);

        WButton button = new WButton(new TranslatableText("chunk.claim.yes"));
        button.setOnClick(() -> {
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeBlockPos(entitypos);
            byteBuf.writeBlockPos(playerpos);
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketIds.chunkClaimedId, byteBuf);
        });
        button.setAlignment(HorizontalAlignment.LEFT);
        button.setIcon(new TextureIcon(new Texture(new Identifier(MOD_ID, "textures/libgui/green_checkmark.png"))));
        root.add(button, 0, 3, 8, 1);
        root.validate(this);
    }

}

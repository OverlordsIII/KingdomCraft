package overlordsiii.github.io.kingdomcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import overlordsiii.github.io.kingdomcraft.registry.BlockRegistry;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;

public class KingdomCraft implements ModInitializer {
    public static final ItemGroup KingdomCraftGroup = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(BlockRegistry.crystalBlock));
    public static String MOD_ID = "kingdomcraft";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {

        BlockRegistry.initialize();
        BlockEntityRegistry.initialize();

       LOGGER.info("Initialized Kingdom Craft!");
    }
}

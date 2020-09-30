package overlordsiii.github.io.kingdomcraft;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import overlordsiii.github.io.kingdomcraft.cca.KingdomComponent;
import overlordsiii.github.io.kingdomcraft.cca.KingdomWorldComponent;
import overlordsiii.github.io.kingdomcraft.networking.ServerSideNetworking;
import overlordsiii.github.io.kingdomcraft.registry.BlockEntityRegistry;
import overlordsiii.github.io.kingdomcraft.registry.BlockRegistry;
import overlordsiii.github.io.kingdomcraft.registry.ItemRegistry;

public class KingdomCraft implements ModInitializer{
    public static ComponentType<KingdomComponent> KINGDOM = ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier("kingdomcraft", "kingdoms"),
            KingdomComponent.class
    ).attach(WorldComponentCallback.EVENT, (KingdomWorldComponent::new));
    public static final ItemGroup KingdomCraftGroup = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(BlockRegistry.crystalBlock));
    public static String MOD_ID = "kingdomcraft";
    public static Logger LOGGER = LogManager.getLogger("KingdomCraft");
    @Override
    public void onInitialize() {

        BlockRegistry.initialize();
        BlockEntityRegistry.initialize();
        ItemRegistry.initialize();
        ServerSideNetworking.initialize();
       LOGGER.info("Initialized Kingdom Craft!");
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootTableRange.create(1))
                    .withEntry(ItemEntry.builder(Items.ACACIA_BOAT).build());

            fabricLootSupplierBuilder.pool(poolBuilder);
        });
    }


}

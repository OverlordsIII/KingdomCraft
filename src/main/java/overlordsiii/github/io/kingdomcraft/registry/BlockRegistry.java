package overlordsiii.github.io.kingdomcraft.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.block.KingdomCrystalBlock;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.MOD_ID;

public class BlockRegistry {
    public static KingdomCrystalBlock crystalBlock = new KingdomCrystalBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f, 2));
    public static void initialize(){
        register("kingdom_crystal", crystalBlock);
    }
    public static Block register(String id, Block block){
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), new BlockItem(block, new Item.Settings().group(KingdomCraft.KingdomCraftGroup) ));
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);

    }
}

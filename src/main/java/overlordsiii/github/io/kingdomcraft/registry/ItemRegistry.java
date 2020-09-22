package overlordsiii.github.io.kingdomcraft.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import overlordsiii.github.io.kingdomcraft.KingdomCraft;
import overlordsiii.github.io.kingdomcraft.item.KingdomWand;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.MOD_ID;

public class ItemRegistry {
    public static KingdomWand wand = new KingdomWand(new Item.Settings().group(KingdomCraft.KingdomCraftGroup).maxCount(1).rarity(Rarity.RARE));
    public static void initialize(){
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "kingdom_wand"), wand);
    }
}

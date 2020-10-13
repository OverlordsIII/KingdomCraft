package overlordsiii.github.io.kingdomcraft.registry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import overlordsiii.github.io.kingdomcraft.block.entity.KingdomCrystalBlockEntity;

import static overlordsiii.github.io.kingdomcraft.KingdomCraft.MOD_ID;
public class BlockEntityRegistry {
    public static BlockEntityType<KingdomCrystalBlockEntity> CrystalEntityType = BlockEntityType.Builder.create(KingdomCrystalBlockEntity::new, BlockRegistry.crystalBlock).build(null);
    public static void initialize(){
      Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "kingdomcrystal_entity_type"), CrystalEntityType);
    }
}

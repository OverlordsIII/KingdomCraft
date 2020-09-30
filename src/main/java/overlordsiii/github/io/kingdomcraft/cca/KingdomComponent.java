package overlordsiii.github.io.kingdomcraft.cca;

import com.jamieswhiteshirt.rtree3i.RTreeMap;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import overlordsiii.github.io.kingdomcraft.api.Kingdom;
import overlordsiii.github.io.kingdomcraft.api.KingdomArea;

public interface KingdomComponent extends WorldSyncedComponent {
    RTreeMap<KingdomArea, Kingdom> getKingdoms();
    void add(KingdomArea kingdom, Kingdom area);
    void remove(KingdomArea area);
}

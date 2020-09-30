package overlordsiii.github.io.kingdomcraft.client.screen.libgui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.minecraft.text.TranslatableText;

public class KingdomNameScreen extends LightweightGuiDescription {
    public KingdomNameScreen(){
        WGridPanel panel = new WGridPanel();
        setRootPanel(panel);
        panel.setSize(200, 300);
        WTextField field = new WTextField(new TranslatableText("name.field.suggestion"));
        panel.add(field, 4, 5, 6, 2);
        panel.validate(this);
    }
}

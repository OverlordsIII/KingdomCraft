package overlordsiii.github.io.kingdomcraft.client.screen;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.minecraft.text.TranslatableText;

public class KingdomNameScreen extends LightweightGuiDescription {
    public KingdomNameScreen(){
        WGridPanel panel = new WGridPanel();
        setRootPanel(panel);
        panel.setSize(200, 200);
        WTextField field = new WTextField(new TranslatableText("name.field.suggestion"));
        panel.add(field, 4, 5, 6, 2);
        WButton button = new WButton(new TranslatableText("submit"));
        button.setOnClick(() -> {
        });
        panel.add(button, 3, 5, 3, 1);
        panel.validate(this);
    }
}

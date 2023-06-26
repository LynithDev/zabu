package dev.lynith.core.ui.screen;

import dev.lynith.core.ui.Screen;
import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.components.Button;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.styles.Spacing;
import dev.lynith.core.utils.GuiScreens;

public class MainMenu extends Screen<MainMenu, ComponentStyles<MainMenu>> {

    public MainMenu() {
        super(GuiScreens.MAIN_MENU);
    }

    @Override
    public void init() {
        style(styles -> {
            styles.setPadding(Spacing.from(10));
        });

        add(
            new Button("Singleplayer")
                .position(0, 0)
                .size(100, 20)
                .listener(MouseClick.class, (mouseX, mouseY) ->
                    bridge().getRenderer().setCurrentScreen(GuiScreens.SINGLEPLAYER_SELECT, GuiScreens.MAIN_MENU)
                ),
            new Button("Multiplayer")
                .position(0, 30)
                .size(100, 20)
                .listener(MouseClick.class, (mouseX, mouseY) ->
                    bridge().getRenderer().setCurrentScreen(GuiScreens.MULTIPLAYER_SELECT, GuiScreens.MAIN_MENU)
                ),
            new Button("Options")
                .position(0, 60)
                .size(100, 20)
                .listener(MouseClick.class, (mouseX, mouseY) ->
                    bridge().getRenderer().setCurrentScreen(GuiScreens.OPTIONS, GuiScreens.MAIN_MENU)
                ),
            new Button("Quit")
                .position(0, 90)
                .size(100, 20)
                .listener(MouseClick.class, (mouseX, mouseY) ->
                    bridge().getGame().shutdown()
                )
        );

        super.init();
    }
}

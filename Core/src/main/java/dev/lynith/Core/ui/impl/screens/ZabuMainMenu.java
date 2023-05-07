package dev.lynith.Core.ui.impl.screens;

import dev.lynith.Core.ui.Component;
import dev.lynith.Core.ui.impl.Panel;
import dev.lynith.Core.ui.impl.UIScreen;
import dev.lynith.Core.ui.impl.components.Button;
import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.renderer.IRenderer;

public class ZabuMainMenu extends UIScreen {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), ZabuColor.from(0));
        super.render(renderer);

        renderer.rect(0, 0, 200, 1, ZabuColor.from(0, 0, 255));
        renderer.rect(0, 0, 1, 150, ZabuColor.from(0, 255, 255));
    }

    @Override
    public void init() {
        Button singlePlayerButton = new Button("Singleplayer");

        singlePlayerButton.setOnClick(((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.SINGLEPLAYER_SELECT, GuiScreens.MAIN_MENU);
        }));

        Button multiplayerButton = new Button("Multiplayer");
        multiplayerButton.setOnClick(((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.MULTIPLAYER_SELECT, GuiScreens.MAIN_MENU);
        }));

        Button optionsButton = new Button("Options");
        optionsButton.setOnClick(((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.OPTIONS_SCREEN, GuiScreens.MAIN_MENU);
        }));

        Button exitButton = new Button("Exit");
        exitButton.setOnClick(((mouseX, mouseY) -> {
            bridge.getGame().shutdown();
        }));

        Panel horizontalPanel = Panel.of(Panel.Direction.ROW, optionsButton, exitButton);
        horizontalPanel.setEqualSizeChildren(true);
        horizontalPanel.outline = ZabuColor.from(0, 255, 0);
        horizontalPanel.setFillWidth(true);

        Panel panel = Panel.of(singlePlayerButton, multiplayerButton);
        panel.setEqualSizeChildren(true);
        panel.outline = ZabuColor.from(255, 0, 0);

        panel.setSpacing(10);
        panel.setWidth(200);
        panel.setHeight(150);

        addChild(panel);
        super.init();
    }
}

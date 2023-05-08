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

        Button exitButton1 = new Button("Exit");
        exitButton1.setOnClick(((mouseX, mouseY) -> {
            bridge.getGame().shutdown();
        }));

        Panel horizontalPanel = Panel.of(Panel.Direction.ROW, optionsButton, exitButton, exitButton1);
        horizontalPanel.setEqualSizeChildren(true);

        Panel panel = Panel.of(singlePlayerButton, multiplayerButton, horizontalPanel);
        panel.setEqualSizeChildren(true);

        panel.setSpacing(10);

        setOnResize((w, h) -> {
            panel.setWidth(w - 50);
            panel.setHeight(h - 50);
            panel.update();
        });

        addChild(panel);
        super.init();
    }
}

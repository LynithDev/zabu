package dev.lynith.Core.ui.impl.screens;

import dev.lynith.Core.ui.impl.UIPanel;
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
    }

    @Override
    public void init() {
        Button optionsButton = new Button("Options");

        optionsButton.setOnClick((mouseX, mouseY) -> {
            System.out.println("Options button clicked");
            bridge.getRenderer().setCurrentScreen(GuiScreens.OPTIONS_SCREEN, GuiScreens.MAIN_MENU);
        });

        addChild(optionsButton);

//        Button singlePlayerButton = new Button("Singleplayer");
//        Button multiplayerButton = new Button("Multiplayer");
//        Button optionsButton = new Button("Options");
//        Button quitButton = new Button("Quit");
//
//        optionsButton.setOnClick((mouseX, mouseY) -> {
//            System.out.println("Options button clicked");
//            bridge.getRenderer().setCurrentScreen(GuiScreens.OPTIONS_SCREEN);
//        });
//
//        UIPanel horizontalButtons = UIPanel.of(UIPanel.Direction.ROW, optionsButton, quitButton);
//        UIPanel panel = UIPanel.of(singlePlayerButton, multiplayerButton, horizontalButtons);
//
//        panel.update();
//
//        panel.setX(bridge.getRenderer().getWindowWidth() / 2 - panel.getWidth() / 2);
//
//        addChild(panel);
        super.init();
    }

}

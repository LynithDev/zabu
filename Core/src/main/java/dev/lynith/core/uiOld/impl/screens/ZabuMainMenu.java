package dev.lynith.core.uiOld.impl.screens;

import dev.lynith.core.uiOld.impl.PanelOld;
import dev.lynith.core.uiOld.impl.Screen;
import dev.lynith.core.uiOld.impl.components.Button;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.MathHelper;
import dev.lynith.core.versions.renderer.IRenderer;

public class ZabuMainMenu extends Screen {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), from(0));
        super.render(renderer);
    }

    @Override
    public void init() {
        Button singlePlayerButton = new Button("Singleplayer");

        singlePlayerButton.addCallback(ClickCallback.class, ((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.SINGLEPLAYER_SELECT, GuiScreens.MAIN_MENU);
        }));

        Button multiplayerButton = new Button("Multiplayer");
        multiplayerButton.addCallback(ClickCallback.class, ((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.MULTIPLAYER_SELECT, GuiScreens.MAIN_MENU);
        }));

        Button optionsButton = new Button("Options");
        optionsButton.addCallback(ClickCallback.class, ((mouseX, mouseY) -> {
            bridge.getRenderer().setCurrentScreen(GuiScreens.OPTIONS_SCREEN, GuiScreens.MAIN_MENU);
        }));

        Button exitButton = new Button("Exit");
        exitButton.addCallback(ClickCallback.class, ((mouseX, mouseY) -> {
            bridge.getGame().shutdown();
        }));

        PanelOld horizontalPanel = PanelOld.of(PanelOld.Direction.ROW, optionsButton, exitButton);
        horizontalPanel.setSpacing(2);
        horizontalPanel.setEqualSizeChildren(true);

        PanelOld panel = PanelOld.of(singlePlayerButton, multiplayerButton, horizontalPanel);
        panel.setSpacing(5);

        addCallback(ResizeCallback.class, (w, h) -> {
            panel.setWidth(MathHelper.clamp(w / 2, 150, 200));
            panel.setHeight(100);
            panel.setX(w / 2 - panel.getWidth() / 2);
            panel.setY(h / 2 - panel.getHeight() / 2);
            panel.update();
        });

        addChild(panel);
        super.init();
    }
}

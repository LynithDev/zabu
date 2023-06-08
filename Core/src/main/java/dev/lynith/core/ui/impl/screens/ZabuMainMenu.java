package dev.lynith.core.ui.impl.screens;

import dev.lynith.core.ui.impl.Panel;
import dev.lynith.core.ui.impl.Screen;
import dev.lynith.core.ui.impl.components.Button;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.MathHelper;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class ZabuMainMenu extends Screen {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), ZabuColor.from(0));
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

        Panel horizontalPanel = Panel.of(Panel.Direction.ROW, optionsButton, exitButton);
        horizontalPanel.setSpacing(2);
        horizontalPanel.setEqualSizeChildren(true);

        Panel panel = Panel.of(singlePlayerButton, multiplayerButton, horizontalPanel);
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

package dev.lynith.onenineteenfour;

import dev.lynith.core.versions.IGame;
import dev.lynith.core.versions.IProfile;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.onenineteenfour.renderer.Renderer;
import lombok.Getter;

public class Version implements IVersion {
    @Override
    public IProfile getProfile() {
        return null;
    }

    @Override
    public String getVersion() {
        return "1.19.4";
    }

    @Getter
    private final IGame game = new Game();

    @Getter
    private final IRenderer renderer = new Renderer();
}

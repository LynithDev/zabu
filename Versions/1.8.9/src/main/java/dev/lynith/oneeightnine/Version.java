package dev.lynith.oneeightnine;

import dev.lynith.core.versions.IGame;
import dev.lynith.core.versions.IProfile;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.oneeightnine.renderer.Renderer;
import lombok.Getter;

public class Version implements IVersion {

    @Getter
    private final IProfile profile = new Profile();

    @Override
    public String getVersion() {
        return "1.8.9";
    }

    @Getter
    private final IGame game = new Game();

    @Getter
    private final IRenderer renderer = new Renderer();

}

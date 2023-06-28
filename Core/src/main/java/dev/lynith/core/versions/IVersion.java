package dev.lynith.core.versions;

import dev.lynith.core.versions.renderer.IRenderer;

public interface IVersion {

    IGame getGame();

    IRenderer getRenderer();

    IProfile getProfile();

    IModule getModule();

    String getVersion();

}

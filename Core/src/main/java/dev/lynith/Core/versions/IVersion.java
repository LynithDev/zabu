package dev.lynith.Core.versions;

import dev.lynith.Core.versions.renderer.IRenderer;

public interface IVersion {

    IGame getGame();

    IRenderer getRenderer();

    String getVersion();

}

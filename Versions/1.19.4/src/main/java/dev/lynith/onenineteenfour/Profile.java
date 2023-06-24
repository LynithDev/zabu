package dev.lynith.onenineteenfour;

import dev.lynith.core.versions.IProfile;
import net.minecraft.client.MinecraftClient;

public class Profile implements IProfile {

    @Override
    public String getUsername() {
        return MinecraftClient.getInstance().getSession().getUsername();
    }

    @Override
    public String getUUID() {
        return MinecraftClient.getInstance().getSession().getUuid();
    }

    @Override
    public String getSkinURL() {
        // Do not know whether this works
        return MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getSkinTexture().getPath() : null;
    }

}

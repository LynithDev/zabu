package dev.lynith.oneeightnine;

import dev.lynith.core.versions.IProfile;
import net.minecraft.client.MinecraftClient;

public class Profile implements IProfile {

    @Override
    public String getUsername() {
        return MinecraftClient.getInstance().getSession().getProfile().getName();
    }

    @Override
    public String getUUID() {
        return MinecraftClient.getInstance().getSession().getUuid();
    }

    @Override
    public String getSkinURL() {
        return MinecraftClient.getInstance().player.getSkinId().getPath();
    }

}

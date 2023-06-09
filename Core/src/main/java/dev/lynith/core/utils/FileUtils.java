package dev.lynith.core.utils;

import dev.lynith.core.ClientStartup;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public final static Path MC_DIR = Paths.get(ClientStartup.getInstance().getBridge().getGame().getGameDir().getAbsolutePath());
    public final static Path CONFIG_PATH = Paths.get(MC_DIR.toString(), "config", "zabu");

}

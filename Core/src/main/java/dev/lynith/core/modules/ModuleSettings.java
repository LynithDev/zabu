package dev.lynith.core.modules;

import dev.lynith.core.input.Key;
import dev.lynith.core.utils.MinecraftVersion;
import dev.lynith.core.utils.VersionUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Supplier;

@Builder
@Getter
public class ModuleSettings {

    private String name;
    private String description;
    private Key key;
    private boolean enabledByDefault;
    private boolean visibleInMenu;
    private Supplier<Boolean> isAllowed;

}

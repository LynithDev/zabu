package dev.lynith.core.ui.theme;

import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.ui.theme.base.ThemeBase;
import dev.lynith.core.ui.theme.dark.ThemeDark;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;

public class ThemeManager {

    private final HashSet<AbstractTheme> themes = new HashSet<>();

    @Getter
    private AbstractTheme currentTheme;

    public ThemeManager() {
        ThemeBase themeBase = new ThemeBase();
        currentTheme = themeBase;

        themes.add(themeBase);
        themes.add(new ThemeDark());
    }

    public void setCurrentTheme(Class<? extends AbstractTheme> currentTheme) {
        this.currentTheme = getTheme(currentTheme);
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = getTheme(currentTheme);
    }

    public AbstractTheme getTheme(String name) {
        for (AbstractTheme theme : themes) {
            if (theme.getName().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public AbstractTheme getTheme(Class<? extends AbstractTheme> clazz) {
        for (AbstractTheme theme : themes) {
            if (theme.getClass().equals(clazz)) {
                return theme;
            }
        }
        return null;
    }

}

package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.components.Component;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter @Accessors(chain = true)
public abstract class ComponentStyles<C extends Component<C, CS>, CS extends ComponentStyles<C, CS>> {

    private final C component;

    public ComponentStyles(C component) {
        this.component = component;
    }

    private final Map<String, Style<?, ?>> styles = new HashMap<>();

    public <S extends Style<S, ?>> CS addStyle(S style) {
        styles.put(style.getName(), style);
        return (CS) this;
    }

    public <S extends Style<S, ?>> S getStyle(String name) {
        return (S) styles.get(name);
    }

    public List<Style<?, ?>> getStyles() {
        return new ArrayList<>(styles.values());
    }

}

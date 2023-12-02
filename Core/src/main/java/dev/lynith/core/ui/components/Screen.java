package dev.lynith.core.ui.components;

import dev.lynith.core.ui.components.features.Children;
import dev.lynith.core.ui.styles.ComponentStyles;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Screen extends Component<ComponentStyles<?, ?>> implements Children {

    private List<Component<?>> children;

}

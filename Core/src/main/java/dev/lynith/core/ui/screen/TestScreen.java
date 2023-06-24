package dev.lynith.core.ui.screen;

import dev.lynith.core.ui.components.Button;
import dev.lynith.core.ui.components.Text;
import dev.lynith.core.ui.styles.ComponentStyles;

public class TestScreen extends Screen<TestScreen, ComponentStyles<TestScreen>> {

    @Override
    public void init() {
        Button testButton = new Button("I am a test button");
        testButton.x(10).y(10).width(200).height(50);

        Button button = new Button();
        button.x(10).y(70).width(200).height(50);

        Text label = new Text("I am a label");
        label.x(10).y(130).width(200).height(50);

        add(testButton, button, label);

        super.init();
    }
}

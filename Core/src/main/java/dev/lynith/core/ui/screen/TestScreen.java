package dev.lynith.core.ui.screen;

import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.components.Button;
import dev.lynith.core.ui.components.Text;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.ZabuColor;

public class TestScreen extends Screen<TestScreen, ComponentStyles<TestScreen>> {

    @Override
    public void init() {
        add(
                new Button("Click me!")
                        .x(10)
                        .y(10)
                        .width(100) // Chaining methods !
                        .height(100)
                        .listener(MouseClick.class, (mouseX, mouseY) -> {
                            System.out.println("I was clicked!"); // Per component instance listeners
                        })
                        .style(styles -> {
                            styles.setBackground(ZabuColor.from(40)); // Per component instance styles
                        }),

                new Text("I am a label!")
                        .x(50)
                        .y(5),

                new Button("I am another button")
                        .x(10)
                        .y(120)
                        .width(100)
                        .height(100)
                        .listener(MouseClick.class, (mouseX, mouseY) -> {
                            System.out.println("The default button was clicked");
                        })
        );

        super.init();
    }
}

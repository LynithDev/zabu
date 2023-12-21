package dev.lynith.core.ui.styles.impl;

import dev.lynith.core.ui.styles.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Padding extends Style<Padding, Style.FourValue<Integer, Integer, Integer, Integer>> {

    private int top = 0;
    private int bottom = 0;
    private int left = 0;
    private int right = 0;

    @Override
    public String getName() {
        return "padding";
    }

    @Override
    public String getValueSerialized() {
        return top + " " + right + " " + bottom + " " + left;
    }

    @Override
    public FourValue<Integer, Integer, Integer, Integer> getValue() {
        return new FourValue<>(top, right, bottom, left);
    }

    @Override
    public void setValue(FourValue<Integer, Integer, Integer, Integer> value) {
        top = value.getFirst();
        right = value.getSecond();
        bottom = value.getThird();
        left = value.getFourth();
    }
}

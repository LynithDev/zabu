package dev.lynith.core.uiOld.features;

public interface TextFeature {

    void setText(String text, boolean updateSize);
    default void setText(String text) { setText(text, true); }

    String getText();

}

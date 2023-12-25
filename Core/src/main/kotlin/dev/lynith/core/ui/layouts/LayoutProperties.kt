package dev.lynith.core.ui.layouts

import dev.lynith.core.ui.styles.impl.Padding

class LayoutProperties(
    var direction: Direction = Direction.Horizontal,
    var align: Align = Align.Start,
    var justify: Justify = Justify.Start,
    var wrap: Wrap = Wrap.Wrap,
    var gap: Gap = Gap(),
) {

    class Gap(
        var y: Float = 0f,
        var x: Float = 0f
    )

    enum class Direction {
        Horizontal,
        Vertical,
        HorizontalReverse,
        VerticalReverse;

        fun isHorizontal() = this == Horizontal || this == HorizontalReverse
        fun isVertical() = this == Vertical || this == VerticalReverse
    }

    enum class Align {
        Start,
        Center,
        End
    }

    enum class Justify {
        Start,
        Center,
        End
    }

    enum class Wrap {
        None,
        Wrap,
    }

}
package dev.lynith.core.ui.nvg

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.styles.impl.*
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG.*
import kotlin.math.round

open class NanoVGHelper() {

    val ctx: Long = Platform.nvgContext

    fun createFrame() {
        nvgBeginFrame(ctx, Platform.renderer.windowWidth.toFloat(), Platform.renderer.windowHeight.toFloat(), 1f)
        nvgSave(ctx)
    }

    fun endFrame() {
        nvgRestore(ctx)
        nvgEndFrame(ctx)
    }

    fun scale(ctx: Long, x: Float, y: Float) {
        nvgScale(ctx, x, y)
    }

    fun createColor(r: Int, g: Int, b: Int, a: Int): NVGColor {
        val nvgColor = NVGColor.create()
        nvgRGBAf(r.toFloat() / 255, g.toFloat() / 255, b.toFloat() / 255, a.toFloat() / 255, nvgColor)
        return nvgColor
    }

    fun createColor(color: Color): NVGColor {
        return createColor(color.red, color.green, color.blue, color.alpha)
    }

    fun background(color: Color = Platform.themeManager.currentTheme.colorScheme.background) {
        rectangle(
            0f,
            0f,
            Platform.renderer.windowWidth.toFloat(),
            Platform.renderer.windowHeight.toFloat(),
            color
        )
    }

    fun rectangle(left: Float, top: Float, width: Float, height: Float, color: Color) {
        nvgBeginPath(ctx)
        nvgFillColor(ctx, createColor(color))
        nvgRect(ctx, left, top, width, height)
        nvgFill(ctx)
        nvgClosePath(ctx)
    }

    @JvmOverloads
    fun rectangle(bounds: BoundingBox, color: Color, border: Border? = null, cornerRadius: CornerRadius? = null, round: Boolean = true) {
        var radius = 0
        if (cornerRadius != null) {
            radius = cornerRadius.value
        }

        val (x, y, w, h) = if (round) {
            arrayOf(round(bounds.x), round(bounds.y), round(bounds.width), round(bounds.height))
        } else {
            arrayOf(bounds.x, bounds.y, bounds.width, bounds.height)
        }

        // Background
        nvgBeginPath(ctx)
        nvgFillColor(ctx, createColor(color))
        nvgPathWinding(ctx, NVG_CCW)
        nvgRoundedRect(ctx, x, y, w, h, radius.toFloat())
        nvgPathWinding(ctx, NVG_CW)
        nvgFill(ctx)
        nvgClosePath(ctx)

        // Border
        if (border != null && border.thickness > 0) {
            nvgBeginPath(ctx)
            nvgStrokeColor(ctx, createColor(border.color))
            nvgStrokeWidth(ctx, border.thickness)
            nvgPathWinding(ctx, NVG_CCW)
            nvgRoundedRect(ctx, x, y, w, h, radius.toFloat())
            nvgPathWinding(ctx, NVG_CW)
            nvgStroke(ctx)
            nvgClosePath(ctx)
        }
    }

    fun text(text: String, bounds: BoundingBox, font: FontStyles = FontStyles(), color: Color) {
        nvgBeginPath(ctx)
        nvgFontFace(ctx, Platform.fontHelper.getOrDefault(font.family, font.weight).formatted())
        nvgFontSize(ctx, font.size)
        nvgTextAlign(ctx, textAlign(font.horizontalAlign) or NVG_ALIGN_BASELINE)
        nvgFillColor(ctx, createColor(color))
        nvgTextLetterSpacing(ctx, font.letterSpacing)

        val y = when (font.verticalAlign) {
            Font.VerticalAlign.TOP -> bounds.y + textHeight(text, font)
            Font.VerticalAlign.CENTER -> bounds.y + bounds.height / 2 + textHeight(text, font) / 2
            Font.VerticalAlign.BOTTOM -> bounds.y + bounds.height
        }

        nvgTextBox(
            ctx,
            round(bounds.x),
            round(y),
            bounds.width,
            text
        )
        nvgClosePath(ctx)
    }

    fun textWidth(text: String, styles: FontStyles): Float {
        val bounds = FloatArray(4)
        nvgFontSize(ctx, styles.size)
        nvgTextLetterSpacing(ctx, styles.letterSpacing)
        nvgFontFace(ctx, Platform.fontHelper.getOrDefault(styles.family, styles.weight).formatted())
        return nvgTextBounds(ctx, 0f, 0f, text, bounds)
    }

    fun textHeight(text: String, styles: FontStyles): Float {
        val bounds = FloatArray(4)
        val font = Platform.fontHelper.getOrDefault(styles.family, styles.weight)

        nvgFontSize(ctx, styles.size)
        nvgFontFace(ctx, font.formatted())
        nvgTextLetterSpacing(ctx, styles.letterSpacing)
        nvgTextLineHeight(ctx, styles.lineHeight)
        nvgTextBoxBounds(ctx, 0f, 0f, textWidth(text, styles), text, bounds)

        return (bounds[3] - bounds[1]) - styles.size / font.offset
    }

    fun textAlign(align: Font.HorizontalAlign): Int {
        return when (align) {
            Font.HorizontalAlign.LEFT -> NVG_ALIGN_LEFT
            Font.HorizontalAlign.CENTER -> NVG_ALIGN_CENTER
            Font.HorizontalAlign.RIGHT -> NVG_ALIGN_RIGHT
        }
    }

    fun boxShadow(bounds: BoundingBox, shadow: Shadow) {
        val paint = NVGPaint.create()

        nvgBoxGradient(ctx,
            bounds.x + shadow.offsetX - shadow.spread,
            bounds.y + shadow.offsetY - shadow.spread,
            bounds.width + (2 * shadow.spread),
            bounds.height + (2 * shadow.spread),
            shadow.radius.value + shadow.spread,
            shadow.blur,
            createColor(shadow.innerColor),
            createColor(shadow.outerColor),
            paint
        )

        nvgBeginPath(ctx)
        nvgPathWinding(ctx, NVG_SOLID)
        nvgRoundedRect(ctx,
            bounds.x + shadow.offsetX - shadow.spread - shadow.blur,
            bounds.y + shadow.offsetY - shadow.spread - shadow.blur,
            bounds.width + (2 * shadow.spread) + (2 * shadow.blur),
            bounds.height + (2 * shadow.spread) + (2 * shadow.blur),
            shadow.radius.value + shadow.spread
        )
        nvgPathWinding(ctx, NVG_HOLE)
        nvgRoundedRect(ctx, bounds.x, bounds.y, bounds.width, bounds.height, shadow.radius.value.toFloat())
        nvgFillPaint(ctx, paint)
        nvgFill(ctx)
        nvgClosePath(ctx)
    }

}

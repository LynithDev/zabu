package dev.lynith.core.ui.nvg

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.CornerRadius
import dev.lynith.core.ui.styles.impl.FontStyles
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.opengl.GL11

open class NanoVGHelper {

    val ctx: Long = Platform.nvgContext

    fun createFrame() {
        nvgBeginFrame(ctx, Platform.renderer.windowWidth.toFloat(), Platform.renderer.windowHeight.toFloat(), 1f)
        nvgSave(ctx)
    }

    fun endFrame() {
        nvgRestore(ctx)
        nvgEndFrame(ctx)
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
    fun rectangle(bounds: BoundingBox, color: Color, border: Border? = null, cornerRadius: CornerRadius? = null) {
        var radius = 0
        if (cornerRadius != null) {
            radius = cornerRadius.value
        }

        // Background
        nvgBeginPath(ctx)
        nvgFillColor(ctx, createColor(color))
        nvgRoundedRect(ctx, bounds.x, bounds.y, bounds.width, bounds.height, radius.toFloat())
        nvgFill(ctx)
        nvgClosePath(ctx)

        // Border
        if (border != null && border.thickness > 0) {
            nvgBeginPath(ctx)
            nvgStrokeColor(ctx, createColor(border.color))
            nvgStrokeWidth(ctx, border.thickness.toFloat())
            nvgRoundedRect(ctx, bounds.x, bounds.y, bounds.width, bounds.height, radius.toFloat())
            nvgStroke(ctx)
            nvgClosePath(ctx)
        }
    }

    fun circle(centerX: Float, centerY: Float, radius: Float, color: Color) {
        nvgBeginPath(ctx)
        nvgFillColor(ctx, createColor(color))
        nvgCircle(ctx, centerX, centerY, radius)
        nvgFill(ctx)
        nvgClosePath(ctx)
    }

    fun text(text: String, left: Float, top: Float, size: Float, color: Color, font: Font) {
        nvgBeginPath(ctx)
        nvgFontBlur(ctx, 0.1f)
        nvgFontFace(ctx, font.formatted())
        nvgFontSize(ctx, size)
        nvgTextAlign(ctx, NVG_ALIGN_LEFT or NVG_ALIGN_MIDDLE)
        nvgFillColor(ctx, createColor(color))
        nvgText(ctx, left, top, text)
        nvgClosePath(ctx)
    }

    fun text(text: String, bounds: BoundingBox, fontStyles: FontStyles, color: Color) {
        nvgBeginPath(ctx)
        nvgFontBlur(ctx, 0.1f)
        nvgFontFace(ctx, Platform.fontHelper.getOrDefault(fontStyles.name, fontStyles.weight).formatted())
        nvgFontSize(ctx, fontStyles.size)
        nvgTextAlign(ctx, textAlign(fontStyles.align) or NVG_ALIGN_BOTTOM)
        nvgFillColor(ctx, createColor(color))
        nvgTextLetterSpacing(ctx, fontStyles.letterSpacing)
        nvgTextBox(
            ctx,
            bounds.x,
            bounds.y + textHeight(text, fontStyles),
            bounds.width,
            text
        )
        nvgClosePath(ctx)
    }

    fun textWidth(text: String, styles: FontStyles): Float {
        val bounds = FloatArray(4)
        nvgFontSize(ctx, styles.size)
        nvgTextLetterSpacing(ctx, styles.letterSpacing)
        nvgFontFace(ctx, Platform.fontHelper.getOrDefault(styles.name, styles.weight).formatted())
        return nvgTextBounds(ctx, 0f, 0f, text, bounds)
    }

    fun textHeight(text: String, styles: FontStyles): Float {
        val bounds = FloatArray(4)
        val font = Platform.fontHelper.getOrDefault(styles.name, styles.weight)

        nvgFontSize(ctx, styles.size)
        nvgFontFace(ctx, font.formatted())
        nvgTextLetterSpacing(ctx, styles.letterSpacing)
        nvgTextLineHeight(ctx, styles.lineHeight)
        nvgTextBoxBounds(ctx, 0f, 0f, textWidth(text, styles), text, bounds)

        return (bounds[3] - bounds[1]) + font.offset
    }

    fun textAlign(align: Font.FontAlign): Int {
        return when (align) {
            Font.FontAlign.LEFT -> NVG_ALIGN_LEFT
            Font.FontAlign.CENTER -> NVG_ALIGN_CENTER
            Font.FontAlign.RIGHT -> NVG_ALIGN_RIGHT
        }
    }
}

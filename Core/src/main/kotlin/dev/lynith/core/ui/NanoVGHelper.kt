package dev.lynith.core.ui

import dev.lynith.core.ClientStartup
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.CornerRadius
import dev.lynith.core.ui.styles.impl.FontStyles
import dev.lynith.core.ui.theme.ThemeManager
import dev.lynith.core.utils.nvg.Font
import dev.lynith.core.utils.nvg.FontHelper
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*

open class NanoVGHelper {
    companion object {
        fun createFrame() {
            nvgBeginFrame(ctx(), ClientStartup.instance.version.getRenderer().getWindowWidth().toFloat(), ClientStartup.instance.version.getRenderer().getWindowHeight().toFloat(), 1f)
            nvgSave(ctx())
        }

        fun endFrame() {
            nvgRestore(ctx())
            nvgEndFrame(ctx())
        }

        fun createColor(r: Int, g: Int, b: Int, a: Int): NVGColor {
            val nvgColor = NVGColor.create()
            nvgRGBAf(r.toFloat() / 255, g.toFloat() / 255, b.toFloat() / 255, a.toFloat() / 255, nvgColor)
            return nvgColor
        }

        fun background(color: Color = ThemeManager.instance.currentTheme.colorScheme.background) {
            rectangle(
                0f,
                0f,
                ClientStartup.instance.version.getRenderer().getWindowWidth().toFloat(),
                ClientStartup.instance.version.getRenderer().getWindowHeight().toFloat(),
                color
            )
        }

        fun createColor(color: Color): NVGColor {
            return createColor(color.red, color.green, color.blue, color.alpha)
        }

        fun rectangle(left: Float, top: Float, width: Float, height: Float, color: Color) {
            nvgBeginPath(ctx())
            nvgFillColor(ctx(), createColor(color))
            nvgRect(ctx(), left, top, width, height)
            nvgFill(ctx())
            nvgClosePath(ctx())
        }

        @JvmOverloads
        fun rectangle(bounds: BoundingBox, color: Color, border: Border? = null, cornerRadius: CornerRadius? = null) {
            var radius = 0
            if (cornerRadius != null) {
                radius = cornerRadius.value
            }

            // Background
            nvgBeginPath(ctx())
            nvgFillColor(ctx(), createColor(color))
            nvgRoundedRect(ctx(), bounds.x.toFloat(), bounds.y.toFloat(), bounds.width.toFloat(), bounds.height.toFloat(), radius.toFloat())
            nvgFill(ctx())
            nvgClosePath(ctx())

            // Border
            if (border != null && border.thickness > 0) {
                nvgBeginPath(ctx())
                nvgStrokeColor(ctx(), createColor(border.color))
                nvgStrokeWidth(ctx(), border.thickness.toFloat())
                nvgRoundedRect(ctx(), bounds.x.toFloat(), bounds.y.toFloat(), bounds.width.toFloat(), bounds.height.toFloat(), radius.toFloat())
                nvgStroke(ctx())
                nvgClosePath(ctx())
            }
        }

        fun circle(centerX: Float, centerY: Float, radius: Float, color: Color) {
            nvgBeginPath(ctx())
            nvgFillColor(ctx(), createColor(color))
            nvgCircle(ctx(), centerX, centerY, radius)
            nvgFill(ctx())
            nvgClosePath(ctx())
        }

        fun text(text: String, left: Float, top: Float, size: Float, color: Color, font: Font) {
            nvgBeginPath(ctx())
            nvgFontFace(ctx(), font.formatted())
            nvgFontSize(ctx(), size)
            nvgTextAlign(ctx(), NVG_ALIGN_LEFT or NVG_ALIGN_MIDDLE)
            nvgFillColor(ctx(), createColor(color))
            nvgText(ctx(), left, top, text)
            nvgClosePath(ctx())
        }

        fun text(text: String, bounds: BoundingBox, fontStyles: FontStyles, color: Color) {
            nvgBeginPath(ctx())
            nvgFontFace(ctx(), FontHelper.getOrDefault(fontStyles.name, fontStyles.weight).formatted())
            nvgFontSize(ctx(), fontStyles.size)
            nvgTextAlign(ctx(), textAlign(fontStyles.align) or NVG_ALIGN_BOTTOM)
            nvgFillColor(ctx(), createColor(color))
            nvgTextLetterSpacing(ctx(), fontStyles.letterSpacing)
            nvgTextBox(
                ctx(),
                bounds.x,
                bounds.y + textHeight(text, fontStyles),
                bounds.width,
                text
            )
            nvgClosePath(ctx())
        }

        fun textWidth(text: String, styles: FontStyles): Float {
            val bounds = FloatArray(4)
            nvgFontSize(ctx(), styles.size)
            nvgTextLetterSpacing(ctx(), styles.letterSpacing)
            nvgFontFace(ctx(), FontHelper.getOrDefault(styles.name, styles.weight).formatted())
            return nvgTextBounds(ctx(), 0f, 0f, text, bounds)
        }

        fun textHeight(text: String, styles: FontStyles): Float {
            val bounds = FloatArray(4)
            val font = FontHelper.getOrDefault(styles.name, styles.weight)

            nvgFontSize(ctx(), styles.size)
            nvgFontFace(ctx(), font.formatted())
            nvgTextLetterSpacing(ctx(), styles.letterSpacing)
            nvgTextLineHeight(ctx(), styles.lineHeight)
            nvgTextBoxBounds(ctx(), 0f, 0f, textWidth(text, styles), text, bounds)

            return (bounds[3] - bounds[1]) + font.offset
        }

        fun textAlign(align: Font.FontAlign): Int {
            return when (align) {
                Font.FontAlign.LEFT -> NVG_ALIGN_LEFT
                Font.FontAlign.CENTER -> NVG_ALIGN_CENTER
                Font.FontAlign.RIGHT -> NVG_ALIGN_RIGHT
            }
        }

        fun ctx() = ClientStartup.instance.nvgContext
    }
}

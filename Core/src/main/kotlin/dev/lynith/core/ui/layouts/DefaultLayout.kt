package dev.lynith.core.ui.layouts

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.ComponentWithChildren
import dev.lynith.core.ui.styles.impl.Position

class DefaultLayout : Layout() {

    override fun position(component: ComponentWithChildren<*, *>) {
        var children = component.children.filter {
            it.styles.position.type != Position.PositionType.ABSOLUTE
        }

        if (children.isEmpty()) return

        children = direction(children)
        justify(component, children)
        align(component, children)

        for (child in children) {
            if (child is ComponentWithChildren<*, *>) {
                child.layout.position(child)
            }
        }
    }

    private fun direction(children: List<Component<*, *>>): List<Component<*, *>> {
        when (properties.direction) {
            LayoutProperties.Direction.Horizontal -> {
                return children
            }

            LayoutProperties.Direction.Vertical -> {
                return children
            }

            LayoutProperties.Direction.HorizontalReverse -> {
                return children.reversed()
            }

            LayoutProperties.Direction.VerticalReverse -> {
                return children.reversed()
            }
        }
    }

    private fun justify(component: ComponentWithChildren<*, *>, children: List<Component<*, *>>) {
        val padding = component.styles.padding

        when (properties.justify) {
            LayoutProperties.Justify.Start -> {
                var startY = (padding.top).toFloat() + component.bounds.y

                for (child in children) {
                    child.bounds.y = startY

                    if (properties.direction.isVertical()) {
                        startY += child.bounds.height + component.layout.properties.gap.y
                    }
                }
            }

            LayoutProperties.Justify.Center -> {
                var totalHeight = 0f
                for (child in children) {
                    totalHeight += child.bounds.height
                }

                var startY = ((component.bounds.height - totalHeight) / 2) + component.bounds.y - component.layout.properties.gap.y
                for (child in children) {
                    if (properties.direction.isHorizontal()) {
                        startY = (component.bounds.height - child.bounds.height) / 2f + component.bounds.y
                    }

                    child.bounds.y = startY

                    if (properties.direction.isVertical()) {
                        startY += (child.bounds.height) + component.layout.properties.gap.y
                    }
                }
            }

            LayoutProperties.Justify.End -> {
                var totalHeight = component.layout.properties.gap.y * (children.size - 1)
                for (child in children) {
                    totalHeight += child.bounds.height
                }

                var startY = (component.bounds.height - totalHeight - padding.bottom) + component.bounds.y
                for (child in children) {
                    child.bounds.y = startY
                    startY += child.bounds.height + component.layout.properties.gap.y
                }
            }
        }
    }

    private fun align(component: ComponentWithChildren<*, *>, children: List<Component<*, *>>) {
        val padding = component.styles.padding

        when (properties.align) {
            LayoutProperties.Align.Start -> {
                var startX = (padding.left).toFloat() + component.bounds.x

                for (child in children) {
                    child.bounds.x = startX

                    if (properties.direction.isHorizontal()) {
                        startX += child.bounds.width + component.layout.properties.gap.x
                    }
                }
            }

            LayoutProperties.Align.Center -> {
                var totalWidth = 0f
                for (child in children) {
                    totalWidth += child.bounds.width
                }

                var startX = ((component.bounds.width - totalWidth) / 2f) + component.bounds.x - component.layout.properties.gap.x
                for (child in children) {
                    if (properties.direction.isVertical()) {
                        startX = (component.bounds.width - child.bounds.width) / 2f + component.bounds.x
                    }

                    child.bounds.x = startX

                    if (properties.direction.isHorizontal()) {
                        startX += (child.bounds.width) + component.layout.properties.gap.x
                    }
                }
            }

            LayoutProperties.Align.End -> {
                var totalWidth = component.layout.properties.gap.x * (children.size - 1)
                for (child in children) {
                    totalWidth += child.bounds.width
                }

                var startX = (component.bounds.width - totalWidth - padding.right) + component.bounds.x
                for (child in children) {
                    child.bounds.x = startX
                    startX += child.bounds.width + component.layout.properties.gap.x
                }
            }
        }
    }

}

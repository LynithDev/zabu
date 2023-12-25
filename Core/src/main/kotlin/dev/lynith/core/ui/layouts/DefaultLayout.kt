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

        if (properties.direction.isReverse()) {
            children = children.reversed()
        }

        childWidth(component, children)
        childHeight(component, children)

        justify(component, children)
        align(component, children)

        for (child in children) {
            if (child is ComponentWithChildren<*, *>) {
                child.layout.position(child)
            }
        }
    }

    private fun childWidth(component: ComponentWithChildren<*, *>, children: List<Component<*, *>>) {
        when (properties.childWidth) {
            LayoutProperties.ChildSize.Fixed -> {}
            LayoutProperties.ChildSize.Fill -> {
                if (properties.direction.isHorizontal()) {
                    val containerWidth = component.bounds.width - component.styles.padding.left - component.styles.padding.right - (component.layout.properties.gap.x * (children.size - 1))
                    val singularChildWidth = containerWidth / children.size

                    for (child in children) {
                        child.bounds.width = singularChildWidth
                    }
                } else {
                    for (child in children) {
                        child.bounds.width = component.bounds.width - component.styles.padding.left - component.styles.padding.right
                    }
                }
            }
        }
    }

    private fun childHeight(component: ComponentWithChildren<*, *>, children: List<Component<*, *>>) {
        when (properties.childHeight) {
            LayoutProperties.ChildSize.Fixed -> {}
            LayoutProperties.ChildSize.Fill -> {
                if (properties.direction.isVertical()) {
                    val containerHeight = component.bounds.height - component.styles.padding.top - component.styles.padding.bottom - ((component.layout.properties.gap.y / 2) * (children.size - 1))
                    val singularChildHeight = containerHeight / children.size

                    for (child in children) {
                        child.bounds.height = singularChildHeight
                    }
                } else {
                    for (child in children) {
                        child.bounds.height = component.bounds.height - component.styles.padding.top - component.styles.padding.bottom
                    }
                }
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

                var startY = ((component.bounds.height - totalHeight) / 2) + component.bounds.y - (component.layout.properties.gap.y / 2)
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

                var startX = ((component.bounds.width - totalWidth) / 2f) + component.bounds.x - (component.layout.properties.gap.x / 2)
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

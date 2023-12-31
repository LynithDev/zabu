package dev.lynith.core.config

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigOption(
    val name_serialized: String = "",
    val name_display: String = "",
    val description: String = "",
    val category: Category = Category.Uncategorized,
)

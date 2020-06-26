package com.taleb.flutter_platformview
import io.flutter.plugin.common.PluginRegistry.Registrar

object BlurViewWidgetPlugin {
    fun registerWith(registrar: Registrar) {
        registrar
                .platformViewRegistry()
                .registerViewFactory(
                        "plugins/blur_view_widget", BlurViewWidgetFactory(registrar.messenger()))
    }
}
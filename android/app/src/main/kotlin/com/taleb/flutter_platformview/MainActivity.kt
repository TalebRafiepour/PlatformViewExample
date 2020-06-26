package com.taleb.flutter_platformview

import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BlurViewWidgetPlugin.registerWith(this.registrarFor("com.taleb.flutter_platformview.BlurViewWidgetPlugin"))
    }
}


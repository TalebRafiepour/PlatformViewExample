package com.taleb.flutter_platformview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.platform.PlatformView

class BlurViewWidget internal constructor(context: Context, id: Int, messenger: BinaryMessenger) : PlatformView, MethodCallHandler {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.blur_view_widget, null)
    private val methodChannel: MethodChannel = MethodChannel(messenger, "plugins/blur_view_widget_$id")

    override fun getView(): View {
        return view
    }

    init {
        methodChannel.setMethodCallHandler(this)
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "draggable" -> draggable(methodCall, result)
            else -> result.notImplemented()
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun draggable(methodCall: MethodCall, result: Result) {
        val isDraggable: Boolean = methodCall.arguments as Boolean
        if (isDraggable)
            view.findViewById<View>(R.id.blur_frame).setOnTouchListener(touchListener)
        else
            view.findViewById<View>(R.id.blur_frame).setOnTouchListener(null)

        result.success(null)
    }

    override fun dispose() {
    }

    private val touchListener: View.OnTouchListener = object : View.OnTouchListener {
        var dx = 0f
        var dy = 0f
        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            val view = view.findViewById<View>(R.id.blur_frame)
            if (event.action == MotionEvent.ACTION_DOWN) {
                dx = view.x - event.rawX
                dy = view.y - event.rawY
            } else if (event.action == MotionEvent.ACTION_MOVE) {
                view.x = event.rawX + dx
                view.y = event.rawY + dy
            }
            return true
        }
    }


}
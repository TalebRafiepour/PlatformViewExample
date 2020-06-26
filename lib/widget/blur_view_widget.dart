import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef BlurViewWidgetCreatedCallback = void Function(
    BlurViewWidgetController controller);

class BlurViewWidget extends StatefulWidget {
  const BlurViewWidget({
    Key key,
    this.onBlurViewWidgetCreated,
  }) : super(key: key);

  final BlurViewWidgetCreatedCallback onBlurViewWidgetCreated;

  @override
  State<StatefulWidget> createState() => _BlurViewWidgetState();
}

class _BlurViewWidgetState extends State<BlurViewWidget> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'plugins/blur_view_widget',
        onPlatformViewCreated: _onPlatformViewCreated,
      );
    }
    return const Text('iOS platform version is not implemented yet.');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onBlurViewWidgetCreated == null) {
      return;
    }
    widget.onBlurViewWidgetCreated(BlurViewWidgetController._(id));
  }
}

class BlurViewWidgetController {
  BlurViewWidgetController._(int id)
      : _channel = MethodChannel('plugins/blur_view_widget_$id');

  final MethodChannel _channel;

  Future<void> ping() async {
    return _channel.invokeMethod('ping');
  }

  Future<void> draggable(bool value) async {
    return _channel.invokeMethod('draggable',value);
  }
}

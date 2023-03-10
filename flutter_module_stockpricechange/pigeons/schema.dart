import 'package:pigeon/pigeon.dart';

enum VisualisationType { table, chart }

// This class is needed because Pigeon only supports enums as class properties
class Visualisation {
  Visualisation(this.visualisationType);

  final VisualisationType visualisationType;
}

@FlutterApi()
abstract class FlutterStockApi {
  void chooseVisualisationType(Visualisation visualisation);
}

@HostApi()
abstract class HostNavigationApi {
  void goBack();
}

import 'package:flutter/material.dart';
import 'package:flutter_module_stockpricechange/redux/store.dart';
import 'package:flutter_module_stockpricechange/stock_price_app.dart';

void main() {
  final store = createReduxStore();
  runApp(StockPriceApp(store));
}

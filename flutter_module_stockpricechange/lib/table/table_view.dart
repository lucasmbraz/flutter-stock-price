import 'package:collection/collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module_stockpricechange/models/trading_day.dart';
import 'package:flutter_module_stockpricechange/redux/actions.dart';
import 'package:flutter_module_stockpricechange/redux/app_state.dart';
import 'package:flutter_module_stockpricechange/stock_info.dart';
import 'package:flutter_module_stockpricechange/widgets/error_with_retry.dart';
import 'package:flutter_module_stockpricechange/widgets/loading.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:intl/intl.dart';
import 'package:sp_design_system/sp_design_system.dart';

class TableView extends StatelessWidget {
  const TableView({super.key});

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      onInit: (store) => store.dispatch(LoadTradingDaysAction()),
      converter: (store) => store.state,
      builder: (context, state) {
        switch (state.dataStatus) {
          case DataStatus.initial:
            return Container();
          case DataStatus.loading:
            return const Loading();
          case DataStatus.loaded:
            return _Content(state.tradingDays);
          case DataStatus.failure:
            return const ErrorWithRetry();
        }
      },
    );
  }
}

class _Content extends StatelessWidget {
  const _Content(this._tradingDays);

  final List<TradingDay> _tradingDays;

  @override
  Widget build(BuildContext context) {
    final rows = _tradingDays.mapIndexed((index, element) {
      return TableRow(
        // TODO(lucas): Format currency and percentage using NumberFormat
        children: [
          _TableItem((index + 1).toString()),
          // TODO(lucas): Move formatting logic to appropriate location
          _TableItem(DateFormat.yMd('pt_BR').format(element.day)),
          _TableItem(('R\$${element.openPrice.toStringAsFixed(2)}').toString()),
          _TableItem(
            (element.previousDayChange == null ? '-' : '${(element.previousDayChange! * 100).toStringAsFixed(2)}%')
                .toString(),
          ),
          _TableItem(
            (element.thirtyDaysChange == null ? '-' : '${(element.thirtyDaysChange! * 100).toStringAsFixed(2)}%')
                .toString(),
          ),
        ],
      );
    }).toList();

    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 24),
      child: SingleChildScrollView(
        child: Column(
          children: [
            const StockInfo(),
            const SizedBox(height: 32),
            Table(
              border: TableBorder.all(
                color: context.spColors.border,
                width: 1,
                borderRadius: BorderRadius.circular(8),
              ),
              columnWidths: const {
                0: FlexColumnWidth(1),
                1: FlexColumnWidth(3),
                2: FlexColumnWidth(2),
                3: FlexColumnWidth(2),
                4: FlexColumnWidth(2),
              },
              children: [
                TableRow(
                  decoration: const BoxDecoration(
                    color: SpColors.green,
                    borderRadius: BorderRadius.vertical(top: Radius.circular(8)),
                  ),
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Center(child: SpText.bodyMedium14('Dia', color: context.spColors.body)),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Center(child: SpText.bodyMedium14('Data', color: context.spColors.body)),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Center(child: SpText.bodyMedium14('Valor', color: context.spColors.body)),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Center(child: SpText.bodyMedium14('% D-1', color: context.spColors.body)),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Center(child: SpText.bodyMedium14('% Total', color: context.spColors.body)),
                    ),
                  ],
                ),
                ...rows,
              ],
            ),
            const SizedBox(height: 16),
          ],
        ),
      ),
    );
  }
}

class _TableItem extends StatelessWidget {
  const _TableItem(this.value);

  final String value;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Center(child: SpText.bodyRegular14(value, color: context.spColors.body)),
    );
  }
}
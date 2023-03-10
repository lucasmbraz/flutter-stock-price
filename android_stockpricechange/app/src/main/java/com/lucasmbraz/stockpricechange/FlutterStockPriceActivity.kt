package com.lucasmbraz.stockpricechange

import android.content.Context
import android.content.Intent
import com.lucasmbraz.stockpricechange.Api.Visualisation
import com.lucasmbraz.stockpricechange.Api.VisualisationType
import com.lucasmbraz.stockpricechange.Api.HostNavigationApi
import com.lucasmbraz.stockpricechange.Api.FlutterStockApi
import com.lucasmbraz.stockpricechange.StockPriceApplication.Companion.ENGINE_ID
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class FlutterStockPriceActivity : FlutterActivity() {
    companion object {
        const val EXTRA_VISUALISATION_TYPE = "visualisation_type"

        fun withVisualisationType(context: Context, visualisationType: VisualisationType): Intent {
            return CachedEngineStockPriceIntentBuilder(ENGINE_ID).build(context).putExtra(
                EXTRA_VISUALISATION_TYPE, visualisationType
            )
        }
    }

    // Intent builder class to build a FlutterStockPriceActivity instance instead of the default FlutterActivity.
    class CachedEngineStockPriceIntentBuilder(engineId: String) :
        CachedEngineIntentBuilder(FlutterStockPriceActivity::class.java, engineId) {}

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        // Called shortly after the activity is created, when the activity is bound to a
        // FlutterEngine responsible for rendering the Flutter activity's content.
        super.configureFlutterEngine(flutterEngine)

        val visualisationType =
            intent.getSerializableExtra(EXTRA_VISUALISATION_TYPE) as VisualisationType

        val visualisation = Visualisation.Builder()
            .setVisualisationType(visualisationType)
            .build()

        HostNavigationApi.setup(flutterEngine.dartExecutor, HostNavigationHandler())

        // Send in the visualisationType to Flutter
        FlutterStockApi(flutterEngine.dartExecutor)
            .chooseVisualisationType(visualisation) {
                // ignore callback
            }
    }

    inner class HostNavigationHandler : HostNavigationApi {
        override fun goBack() {
            finish()
        }
    }
}
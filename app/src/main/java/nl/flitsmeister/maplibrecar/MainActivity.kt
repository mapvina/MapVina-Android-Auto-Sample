package nl.flitsmeister.mapvinacar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import nl.flitsmeister.car_common.R
import nl.flitsmeister.mapvinacar.ui.theme.MapVinaCarTheme
import com.mapvina.android.MapVina
import com.mapvina.android.camera.CameraPosition
import com.mapvina.android.geometry.LatLng
import com.mapvina.android.maps.MapVinaMap
import com.mapvina.android.maps.MapVinaMapOptions
import com.mapvina.android.maps.MapView
import com.mapvina.android.maps.Style

class MainActivity : ComponentActivity() {

    var mapView: MapView? = null
    var mapVinaMap: MapVinaMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapVinaCarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AndroidView(modifier = Modifier.padding(innerPadding), factory = { context ->
                        //TextView(context).apply { setText("Hello MapVinaCar") }
                        val mapVinaMapOptions = MapVinaMapOptions.createFromAttributes(context).apply {
                            textureMode(true)
                            camera(
                                CameraPosition.Builder()
                                    .zoom(2.0)
                                    .target(LatLng(48.507879, 8.363795))
                                    .build()
                            )
                        }
                        MapVina.getInstance(context)
                        mapView = MapView(context, mapVinaMapOptions)
                        mapView?.onCreate(savedInstanceState)
                        mapView?.getMapAsync {
                            mapVinaMap = it
                            initMap(it)
                        }
                        mapView!!
                    })
                }
            }
        }
    }

    private fun initMap(map: MapVinaMap) {
        try {
            map.setStyle(
                //TODO: Set your own style here!
                Style.Builder().fromUri("https://maps.mapvina.com/styles/v1/streets.json?key=public_key")
            )
        } catch (e: Exception) {
            Log.e("MapVinaCar", "Error setting local style", e)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }
}
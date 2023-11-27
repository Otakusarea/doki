package at.ac.fhstp.doki.tools

import androidx.appcompat.app.AppCompatActivity
import at.ac.fhstp.doki.Report
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

import android.Manifest
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

data class LatLong(val latitude: Double, val longitude: Double)

suspend fun loadData(context: Context): ArrayList<Report> {
    // Erstellen Sie eine Instanz von SharedPreferences
    val sharedPreferences = context.getSharedPreferences("shared preferences",
        AppCompatActivity.MODE_PRIVATE)

    // Abrufen des JSON-Strings aus SharedPreferences
    //val json = sharedPreferences.getString("reports_list", null)
    val json = sharedPreferences.getString("reports_list", "No data")
    // Konvertieren Sie den JSON-String zurück in eine ArrayList
    val gson = Gson()
    val type = object : TypeToken<ArrayList<Report>>() {}.type

    return try {
        gson.fromJson<ArrayList<Report>>(json, type) ?: ArrayList()
    } catch (e: Exception) {
        ArrayList()
    }
}

suspend fun saveData(context: Context, report: Report) {
    //lese gespeicherte Reports
    val reports = loadData(context)
    var _report = reports.find { it.id == report.id }
    if(_report != null){
        reports.remove(_report)
    }
    reports.add(report)

    // Erstellen Sie eine Instanz von SharedPreferences
    val sharedPreferences = context.getSharedPreferences("shared preferences",
        AppCompatActivity.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Konvertieren Sie die Reports-ArrayList in einen JSON-String
    val gson = Gson()
    val json = gson.toJson(reports)

    // Speichern Sie den JSON-String in SharedPreferences
    editor.putString("reports_list", json)
    editor.apply()
}

suspend fun _saveData(context: Context, report: Report) {
    // Erstellen Sie eine Instanz von SharedPreferences
    val sharedPreferences = context.getSharedPreferences("shared preferences",
        AppCompatActivity.MODE_PRIVATE
    )
    val editor = sharedPreferences.edit()

    // Konvertieren Sie die usedModules-ArrayList in einen JSON-String
    val gson = Gson()
    val json = gson.toJson(report)

    // Speichern Sie den JSON-String in SharedPreferences
    editor.putString("used modules", json)
    editor.apply()
}


fun getLocation(context: Context): LatLong {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // Erlaubnis Anfrage
        // Bitte beachten Sie, dass dies nicht in einer Hilfsfunktion funktioniert. Sie müssen dies in einer Activity oder einem Fragment tun.
        return LatLong(43.1, 18.3)
    } else {
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        return if (location != null) {
            LatLong(location.latitude, location.longitude)
        } else {
            LatLong(43.1, 18.3)
        }
    }
}

class DividerItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space
        }
    }
}
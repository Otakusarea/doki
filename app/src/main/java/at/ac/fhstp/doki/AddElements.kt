package at.ac.fhstp.doki

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhstp.doki.entries.*
import at.ac.fhstp.doki.tools.*
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp


class AddElements : AppCompatActivity() {

    var sourceModules: ArrayList<ModuleEntry>  = ArrayList<ModuleEntry>()
    var sourceModulesEntrieView: ArrayList<EntrieViewHolder>?  = ArrayList<EntrieViewHolder>()
    var usedModules:ArrayList<ModuleEntry>  = ArrayList<ModuleEntry>()
    var photo1: PhotoEntry = PhotoEntry("CameraActivity")
    var photo2: PhotoEntry = PhotoEntry("Start Scanner")
    //var photo3: PhotoEntry = PhotoEntry("789")
    var text1: TextfieldEntry = TextfieldEntry("Please Scan the order number!")
    var text2: TextfieldEntry = TextfieldEntry("Enter a quality feedback!")
    var sig1: SignatureEntry = SignatureEntry("Max Mustermann")
    //var singleChoice1: SingleChoiceEntry = SingleChoiceEntry("Was ist das beste Obst?", arrayOf("Apfel", "Birne", "Kirsche", "Orange"), 2)
    var singleChoice2: SingleChoiceEntry = SingleChoiceEntry("Which level of quality has the object? (5 best - 1 lowest quality)", arrayOf("1", "2", "3", "4", "5"), 0)
    //private lateinit var recyclerView: RecyclerView
    var btnSaveElements: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relative_layout)

        // Erzeuge eine Instanz des LayoutInflater
        val inflater = LayoutInflater.from(this)

        // Finde die RecyclerView in der relative_layout.xml
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewSource)
        val recyclerViewSource: RecyclerView = findViewById(R.id.recyclerviewSource)
        val recyclerViewDestination: RecyclerView = findViewById(R.id.recyclerviewDestination)
        val destinationIntent = Intent(this, ReportsActivity::class.java)
        //usedModules = _usedModules
// Layout-Manager setzen
        recyclerViewSource.layoutManager = LinearLayoutManager(this)
        recyclerViewDestination.layoutManager = LinearLayoutManager(this)

// Scrollbar anzeigen
        recyclerViewSource.isVerticalScrollBarEnabled = true
        recyclerViewDestination.isVerticalScrollBarEnabled = true

        // Erzeuge eine ArrayList mit den Daten für jedes RelativeLayout
        sourceModules?.add(text1)
        sourceModules?.add(photo2)
        sourceModules?.add(photo1)
        //usedModules?.add(singleChoice1)
        //usedModules?.add(multipleChoice)
        sourceModules?.add(singleChoice2)
        //usedModules?.add(photo3)
        sourceModules?.add(text2)
        sourceModules?.add(sig1)


        // OnClickListener für den Quelladapter definieren
        val sourceClickListener: (ModuleEntry) -> Unit = { module ->
            // Wenn auf ein Element in der Quellliste geklickt wird, wird es zur Ziel-ArrayList hinzugefügt
            usedModules.add(module)
            // Benachrichtigen Sie den Zieladapter über die Änderung
            recyclerViewDestination.adapter?.notifyDataSetChanged()
        }

        // OnClickListener für den Zieladapter definieren
        val destinationClickListener: (ModuleEntry) -> Unit = { module ->
            // Wenn auf ein Element in der Zielliste geklickt wird, wird es aus der Ziel-ArrayList entfernt
            usedModules.remove(module)
            // Benachrichtigen Sie den Zieladapter über die Änderung
            recyclerViewDestination.adapter?.notifyDataSetChanged()
        }

        // Erzeuge den RecyclerView-Adapter und setze ihn für die RecyclerView
        val customAdapter = CustomAdapter("+", sourceModules, inflater, sourceClickListener)
        recyclerViewSource.adapter = customAdapter
        recyclerViewSource.layoutManager = LinearLayoutManager(this)
        recyclerViewSource.apply { addItemDecoration(DividerItemDecoration(16)) }

        // Erzeuge den RecyclerView-Adapter und setze ihn für die recyclerViewDestination
        val destinationAdapter = CustomAdapter("-", usedModules, inflater, destinationClickListener)
        recyclerViewDestination.adapter = destinationAdapter
        recyclerViewDestination.layoutManager = LinearLayoutManager(this)
        recyclerViewDestination.apply { addItemDecoration(DividerItemDecoration(16)) }
        val incomingIntent = intent
        var reportId  = incomingIntent.getLongExtra("reportID",0)
        var reportName  = incomingIntent.getStringExtra("report_name")
        var reportList: ArrayList<Report>  = ArrayList<Report>()
        runBlocking {
            reportList = loadData(this@AddElements)
            var report = reportList.find { it.id == reportId }
            if (report == null) {
                val maxIdReport = reportList.maxByOrNull { it.id }
                reportId = maxIdReport?.id?.plus(1) ?: 1
            }else{
                val modEntries = report.moduleEntries
                if(modEntries != null)
                {
                    usedModules.addAll(modEntries)
                }
            }
        }

        btnSaveElements = findViewById(R.id.btnSave)
        btnSaveElements?.setOnClickListener {
            runBlocking {
                var latLong: LatLong = getLocation(this@AddElements)
                var _report = Report(
                    reportId as Long, reportName as String, latLong.latitude, latLong.longitude,
                    Timestamp(System.currentTimeMillis()), usedModules
                )
                saveData(this@AddElements,_report)
                startActivity(destinationIntent)
            }

        }

    }
}

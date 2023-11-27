package at.ac.fhstp.doki

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhstp.doki.tools.loadData
import at.ac.fhstp.doki.tools.DividerItemDecoration
import kotlinx.coroutines.runBlocking

class ReportsActivity: AppCompatActivity() {

        var btnAddElements: Button? = null
        var reportList: ArrayList<Report>  = ArrayList<Report>()
        var report:Report? = null

        lateinit var recyclerviewElements: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.create_report_layout)

            val inflater = LayoutInflater.from(this)
            val editTextProcName: TextView = findViewById(R.id.editTextProcName)
            editTextProcName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    // Nichts tun
                }
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // Nichts tun
                }
                override fun afterTextChanged(s: Editable) {
                    if (s.toString().trim().isNotEmpty()) {
                        btnAddElements?.isEnabled = true
                    } else {
                        btnAddElements?.isEnabled = false
                    }
                }
            })

            btnAddElements = findViewById(R.id.addElements)
            btnAddElements?.setOnClickListener {
                val intent = Intent(this, AddElements::class.java)
                val id = report?.id ?: if (reportList.isNotEmpty()) {
                    reportList.maxOf { it.id } + 1
                } else {
                    1
                }
                intent.apply {
                    putExtra("reportID", id)
                    putExtra("report_name", editTextProcName.text.toString())
                }
                startActivity(intent)
            }
            recyclerviewElements = findViewById(R.id.recyclerviewElements)
            recyclerviewElements.apply {
                isVerticalScrollBarEnabled = true
                // Hinzufügen der ItemDecoration
                addItemDecoration(DividerItemDecoration(16))  // 16dp space
            }
            //recyclerviewElements.layoutManager = LinearLayoutManager(this)
            //recyclerviewElements.isVerticalScrollBarEnabled = true

            val viewElementsClickListener: (Report) -> Unit = { _report ->
                // Wenn auf ein Element in der Zielliste geklickt wird, wird es aus der Ziel-ArrayList entfernt
                report = _report
                // Benachrichtigen Sie den Zieladapter über die Änderung
                //recyclerviewElements.adapter?.notifyDataSetChanged()
            }

            val viewElementsAdapter =  ReportAdapter( reportList, inflater)
            recyclerviewElements.adapter = viewElementsAdapter
            recyclerviewElements.layoutManager = LinearLayoutManager(this)
           /* val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.clear()
            editor.apply()*/

            val context = this
            runBlocking {
                reportList = loadData(context)
                viewElementsAdapter.updateData(reportList)
                editTextProcName.isEnabled=true
                //val notifyDataSetChanged = recyclerviewElements.adapter?.notifyDataSetChanged()
            }
        }
    }

package at.ac.fhstp.doki

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import at.ac.fhstp.doki.entries.ModuleEntry


class ReportAdapter (private val sourceReportist: ArrayList<Report>,
                    private val inflater: LayoutInflater): RecyclerView.Adapter<ReportAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Erzeuge eine Instanz des entrie_layout.xml-Layouts für jedes RecyclerView-Element
        val view = inflater.inflate(R.layout.entrie_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = sourceReportist[position]

        holder.textView.text = data.name

    }

    override fun getItemCount(): Int {
        return sourceReportist.size
    }

    fun updateData(newData: ArrayList<Report>) {
        this.sourceReportist.clear()
        this.sourceReportist.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val button: Button = itemView.findViewById(R.id.button)
        init {
            button.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val report = sourceReportist[position]
                    val context = itemView.context
                    val intent = Intent(context, AddElements::class.java)
                    intent.putExtra("reportID", report.id)
                    intent.putExtra("report_name", report.name)
                    context.startActivity(intent)
                }
            }
        }
    }
}



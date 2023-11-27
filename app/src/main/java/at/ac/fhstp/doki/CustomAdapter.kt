
package at.ac.fhstp.doki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhstp.doki.entries.ModuleEntry

class CustomAdapter(
    private val buttonText: String,
    private val sourceDataList: ArrayList<ModuleEntry>,
    private val inflater: LayoutInflater,
    private val buttonClickListener: (ModuleEntry) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.entrie_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = sourceDataList[position]
        holder.textView.text = data.toString()
        holder.button.text = buttonText
        holder.button.setOnClickListener { buttonClickListener(data) }
    }

    override fun getItemCount(): Int {
        return sourceDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val button: Button = itemView.findViewById(R.id.button)
    }
}

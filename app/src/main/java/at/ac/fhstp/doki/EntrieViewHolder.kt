package at.ac.fhstp.doki

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class EntrieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView? = null
    var button: Button? = null

    fun EntrieViewHolder(itmView: View) {
        textView = itemView.findViewById(R.id.textView)
        button = itemView.findViewById(R.id.button)
    }
}

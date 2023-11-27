package at.ac.fhstp.doki.ui.modules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import at.ac.fhstp.doki.R
import at.ac.fhstp.doki.entries.PhotoEntry
import at.ac.fhstp.doki.entries.TextfieldEntry

class PhotoModule(val photo: PhotoEntry?) : Fragment(R.layout.photo_module) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.photo_module, null)

        photo?.let { Log.d("PhotoModule", it.photo) }
        val photo = (rootView as ConstraintLayout).findViewById<TextView>(R.id.idPhoto)
        return rootView
    }
}
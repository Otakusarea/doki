package at.ac.fhstp.doki.ui.modules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.ac.fhstp.doki.R
import at.ac.fhstp.doki.entries.TextfieldEntry


class TextfieldModule(val textField: TextfieldEntry?) : Fragment(R.layout.textfield_module) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.textfield_module, null)

        textField?.let { Log.d("TextfieldModule", it.text) }

        return rootView
    }

}
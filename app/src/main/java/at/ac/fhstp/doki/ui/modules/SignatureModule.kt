package at.ac.fhstp.doki.ui.modules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.ac.fhstp.doki.R
import at.ac.fhstp.doki.entries.SignatureEntry
import at.ac.fhstp.doki.entries.SingleChoiceEntry
import at.ac.fhstp.doki.entries.TextfieldEntry

class SignatureModule(val signature: SignatureEntry?) : Fragment(R.layout.signature_module) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.signature_module, null)

        signature?.let { Log.d("SignatureModule", it.text) }
        return rootView
    }
}
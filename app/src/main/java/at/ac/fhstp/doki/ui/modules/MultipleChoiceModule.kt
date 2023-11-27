package at.ac.fhstp.doki.ui.modules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import at.ac.fhstp.doki.R
import at.ac.fhstp.doki.entries.MultipleChoiceEntry
import at.ac.fhstp.doki.entries.TextfieldEntry

class MultipleChoiceModule(val multipleChoice: MultipleChoiceEntry?) : Fragment(R.layout.multiple_choice_module) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.multiple_choice_module, null)

        rootView.findViewById<TextView>(R.id.txtMultipleChoice).setText(multipleChoice!!.question)
        multipleChoice?.let { Log.d("MultipleChoiceModule", it.question) }
        for (i in 0 until multipleChoice.options.size) {
            rootView = addCheckBox(multipleChoice.options[i], multipleChoice.choices[i], rootView, i)
        }
        //rootView = addCheckBox(multipleChoice!!.options[0], multipleChoice.choices[0], rootView)
        return rootView
    }

    private fun addCheckBox(hint: String, isChecked: Boolean, rootView: View, index: Int): View {
        val checkBox = CheckBox(requireContext())
        checkBox.setHint(hint)
        checkBox.id = index
        checkBox.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        checkBox.setPadding(20,20,20,20)
        if(isChecked){
            checkBox.isChecked = true
        }
        checkBox.setOnCheckedChangeListener{ buttonView, check ->
            Log.d("MultipleChoice", "" + buttonView.id)
            val msg = "You have " + (if (check) "checked" else "unchecked") + " this Checkbox."
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        (rootView as ConstraintLayout).findViewById<LinearLayout>(R.id.llMultipleChoice).addView(checkBox)
        return rootView
    }
}
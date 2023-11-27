package at.ac.fhstp.doki.ui.modules

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import at.ac.fhstp.doki.R
import at.ac.fhstp.doki.entries.SingleChoiceEntry
import at.ac.fhstp.doki.entries.TextfieldEntry

class SingleChoiceModule(var singleChoiceEntry: SingleChoiceEntry?) : Fragment(R.layout.single_choice_module) {
    val singleChoice: SingleChoiceEntry? = singleChoiceEntry

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.single_choice_module, null)

        singleChoice?.let { Log.d("SingleChoiceModule", it.question) }
        displaySingleChoice(rootView)
        return rootView
    }

    @SuppressLint("ResourceType")
    private fun displaySingleChoice(rootView: View){
        val radioButtons = ArrayList<RadioButton>()
        for (i in 0 until singleChoice!!.options.size) {
            radioButtons.add(RadioButton(context))
            radioButtons[i].layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radioButtons[i].setText(singleChoice.options[i])
            if(singleChoice.choices==i){
                radioButtons[i].isChecked = true
            }
            radioButtons[i].id = i
        }
        //val radioButton1 = RadioButton(context)
        //val radioButton2 = RadioButton(context)
        /*radioButton1.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        radioButton1.setText(singleChoice!!.options[0])
        radioButton1.id = 0
        radioButton2.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        radioButton2.setText(singleChoice!!.options[1])
        radioButton2.id = 1*/

        val radioGroup = (rootView as ConstraintLayout).findViewById<LinearLayout>(R.id.llSingleChoice).findViewById<RadioGroup>(R.id.rbgSingleChoice)
        radioButtons.forEach {
            radioGroup.addView(it)

        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            singleChoice.choices = checkedId
            Log.d("SingleChoice",  "" + singleChoice.choices)
        }
        /*val checkBox = CheckBox(requireContext())
        checkBox.setHint(hint)
        checkBox.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        checkBox.setPadding(20,20,20,20)
        checkBox.setOnCheckedChangeListener{ _, check ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " this Checkbox."
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }*/
    }
}
package at.ac.fhstp.doki

import DragDropListener
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import at.ac.fhstp.doki.entries.*

class DesignActivity : AppCompatActivity(){

    var viewPager: ViewPager2? = null
    //var textfieldFragment: TextfieldModule = TextfieldModule()
    //var singleChoiceFragment: SingleChoiceModule = SingleChoiceModule()
    var button: Button? = null
    var btnDesign: Button? = null
    var sourceModules: ArrayList<ModuleEntry>?  = ArrayList<ModuleEntry>()
    var usedModules: ArrayList<ModuleEntry>?  = ArrayList<ModuleEntry>()
    var photo1: PhotoEntry = PhotoEntry("CameraActivity")
    var photo2: PhotoEntry = PhotoEntry("Start Scanner")
    //var photo3: PhotoEntry = PhotoEntry("789")
    var text1: TextfieldEntry = TextfieldEntry("Please Scan the order number!")
    var text2: TextfieldEntry = TextfieldEntry("Enter a quality feedback!")
    var sig1: SignatureEntry = SignatureEntry("Max Mustermann")
    //var singleChoice1: SingleChoiceEntry = SingleChoiceEntry("Was ist das beste Obst?", arrayOf("Apfel", "Birne", "Kirsche", "Orange"), 2)
    var singleChoice2: SingleChoiceEntry = SingleChoiceEntry("Which level of quality has the object? (5 best - 1 lowest quality)", arrayOf("1", "2", "3", "4", "5"), 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.design_page)

        val itemsSourceLayout : LinearLayout = findViewById(R.id.items_source)

        sourceModules?.add(text1)
        sourceModules?.add(photo2)
        sourceModules?.add(photo1)
        //usedModules?.add(singleChoice1)
        //usedModules?.add(multipleChoice)
        sourceModules?.add(singleChoice2)
        //usedModules?.add(photo3)
        sourceModules?.add(text2)
        sourceModules?.add(sig1)

        sourceModules?.let { list ->
            for (moduleEntry in list) {
                itemsSourceLayout.addView(addEntryToScrollView( true,moduleEntry))
            }
        }
    }

    private fun addModulesToProcess(entry: ModuleEntry){
        val itemsDestinationLayout : LinearLayout = findViewById(R.id.items_dest)

        usedModules?.add(entry)
        itemsDestinationLayout.removeAllViews();
        var i=0;
        usedModules?.let { list ->
            for (moduleEntry in list) {
                itemsDestinationLayout.addView(addEntryToScrollView( false,moduleEntry,i))
                i++
            }
        }
    }

    private fun delModulesToProcess(entry: ModuleEntry, index : Int){
        val itemsDestinationLayout : LinearLayout = findViewById(R.id.items_dest)

        usedModules?.removeAt(index)
        itemsDestinationLayout.removeAllViews();
        var i=0;
        usedModules?.let { list ->
            for (moduleEntry in list) {
                itemsDestinationLayout.addView(addEntryToScrollView( false,moduleEntry, i))
                i++
            }
        }
    }

    private fun addEntryToScrollView(add: Boolean, entry: ModuleEntry, index: Int = 0):LinearLayout{
        //Neues Layout wird für jedes Element neu erzeugt

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.layoutParams = layoutParams

        val textViewLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        textViewLayoutParams.weight = 0.6f
        val textView = TextView(this)
        textView.text = entry.toString()
        textView.layoutParams = textViewLayoutParams//linearLayout.layoutParams
        textView.setBackgroundResource(R.drawable.textview_border)
        //textView.setOnTouchListener(DragTouchListener())

        val button = Button(this)
        button.text = if(add) "+" else "-"
        button.layoutParams = LayoutParams(
            150,
            LayoutParams.WRAP_CONTENT
        )
        button.background = ContextCompat.getDrawable(this, R.drawable.button_border)
        button.setOnClickListener {
            if(add)
                addModulesToProcess(entry)
            else
                delModulesToProcess(entry, index)
        }
        linearLayout.addView(textView)
        linearLayout.addView(button)
        linearLayout.setOnTouchListener(DragDropListener())
        return linearLayout
    }


}
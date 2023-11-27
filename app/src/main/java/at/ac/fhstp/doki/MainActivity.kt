package at.ac.fhstp.doki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import at.ac.fhstp.doki.entries.*

import java.sql.Timestamp


class MainActivity : AppCompatActivity() {

    var viewPager: ViewPager2? = null
    //var textfieldFragment: TextfieldModule = TextfieldModule()
    //var singleChoiceFragment: SingleChoiceModule = SingleChoiceModule()
    var button: Button? = null
    var btnDesign: Button? = null
    var usedModules: ArrayList<ModuleEntry>?  = ArrayList<ModuleEntry>()
    var photo1: PhotoEntry = PhotoEntry("CameraActivity")
    var photo2: PhotoEntry = PhotoEntry("Start Scanner")
    //var photo3: PhotoEntry = PhotoEntry("789")
    var text1: TextfieldEntry = TextfieldEntry("Please Scan the order number!")
    var text2: TextfieldEntry = TextfieldEntry("Enter a quality feedback!")
    var sig1: SignatureEntry = SignatureEntry("Max Mustermann")
    //var singleChoice1: SingleChoiceEntry = SingleChoiceEntry("Was ist das beste Obst?", arrayOf("Apfel", "Birne", "Kirsche", "Orange"), 2)
    var singleChoice2: SingleChoiceEntry = SingleChoiceEntry("Which level of quality has the object? (5 best - 1 lowest quality)", arrayOf("1", "2", "3", "4", "5"), 0)
    var multipleChoice: MultipleChoiceEntry = MultipleChoiceEntry("Which level of quality has the object? (5 best - 1 lowest quality)", arrayOf("1", "2", "3", "4", "5"), arrayOf(false, true, true, false, true))
    private var firstReport: Report? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewpager)
        button = findViewById(R.id.btnSwitch)
        button?.setOnClickListener { switchToTextfieldModule(); }

        usedModules?.add(text1)
        //usedModules?.add(photo2)
        usedModules?.add(photo1)
        //usedModules?.add(singleChoice1)
        usedModules?.add(multipleChoice)
        usedModules?.add(singleChoice2)
        //usedModules?.add(photo3)
        usedModules?.add(text2)
        usedModules?.add(sig1)



        btnDesign = findViewById(R.id.btnDesign)
        btnDesign?.setOnClickListener {
            val intent = Intent(this, ReportsActivity::class.java)
            startActivity(intent)
        }


        firstReport = Report(1, "First", 43.1, 18.3, Timestamp(System.currentTimeMillis()), usedModules)
        Log.d("MainActivity Report", "" + firstReport!!.moduleEntries?.get(5)?.EntryType)

        if (savedInstanceState == null) {
            setupFragments(supportFragmentManager, findViewById(R.id.viewpager), this.lifecycle)
            /*supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TextfieldModule>(R.id.textfield_module)
            }*/
        }

    }

    private fun setupFragments(fragmentManager: FragmentManager, viewPager: ViewPager2, lifeCycle: Lifecycle) {
        val fragmentAdapter = FragmentAdapter(fragmentManager, lifeCycle)
        //TODO: Add All Fragment To List
        firstReport?.moduleEntries?.forEach{
            Log.d("Mainactivtiy FromInt", "" + ModuleEntryTypes.fromInt(it.EntryType))
            //fragmentAdapter.add(textfieldFragment, ModuleEntryTypes.TEXTFIELD.name)
            //fragmentAdapter.add(fragmentAdapter.createFragment(firstReport!!.moduleEntries?.get(5)?.EntryType!!), ModuleEntryTypes.fromInt(firstReport!!.moduleEntries?.get(5)?.EntryType)!!.name)
            fragmentAdapter.add(fragmentAdapter.createFragment(it.EntryType, it), ModuleEntryTypes.fromInt(it.EntryType)!!.name)
        }
        //Log.d("Mainactivtiy FromInt", "" + ModuleEntryTypes.fromInt(1))
        //TODO Check why null
        Log.d("Mainactivity SetupFragments","" + fragmentAdapter.itemCount)
        viewPager.adapter = fragmentAdapter
    }

    private fun switchToTextfieldModule(){
        /*var fragment: Fragment? = null

        when (R.layout.textfield_module) {
            R.layout.textfield_module -> {
                fragment = TextfieldModule()
            }
        }

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.btnSwitch, fragment)
            transaction.commit()
        }*/
        viewPager?.currentItem = 0
    }
}

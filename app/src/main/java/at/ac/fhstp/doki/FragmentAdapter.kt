package at.ac.fhstp.doki

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.ac.fhstp.doki.entries.*
import at.ac.fhstp.doki.ui.modules.*

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragment: ArrayList<Fragment> = ArrayList() //Fragment List
    private val namePage: ArrayList<String> = ArrayList() // Fragment Name List

    @SuppressLint("NotifyDataSetChanged")
    fun add(Frag: Fragment, Title: String) {
        fragment.add(Frag)
        namePage.add(Title)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        //return (fragment[position])
        //val position: Int = moduleEntry.EntryType
        Log.d("FragmentAdapter createFragment override","" + fragment.size)
        Log.d("FragmentAdapter createFragment override","" + position)
        Log.d("FragmentAdapter createFragment override","" + ModuleEntryTypes.fromInt(position))
        //Log.d("FragmentAdapter when","" + ModuleEntryTypes.SINGLECHOICE)
        //TODO add when for different entries https://github.com/abhishekBansal/ViewPager2Demo/blob/master/app/src/main/java/com/abhishek/viewpager2demo/ViewPager2Adapter.kt

        Log.d("FragmentAdapter return override","" + position)
        return fragment.elementAt(position)
    }

    fun createFragment(position: Int, entryModule: ModuleEntry): Fragment {
        //return (fragment[position])
        //val position: Int = moduleEntry.EntryType
        Log.d("FragmentAdapter createFragment","" + fragment.size)
        Log.d("FragmentAdapter createFragment","" + position)
        Log.d("FragmentAdapter createFragment","" + ModuleEntryTypes.fromInt(position))
        //Log.d("FragmentAdapter when","" + ModuleEntryTypes.SINGLECHOICE)
        //TODO add when for different entries https://github.com/abhishekBansal/ViewPager2Demo/blob/master/app/src/main/java/com/abhishek/viewpager2demo/ViewPager2Adapter.kt

        Log.d("FragmentAdapter return","" + position)
        //TODO exclude from adapter
        return when (ModuleEntryTypes.fromInt(position)) {
            ModuleEntryTypes.TEXTFIELD -> TextfieldModule(entryModule as TextfieldEntry)//TextfieldEntry("testing"))//(moduleEntry to TextfieldEntry).text)
            ModuleEntryTypes.SIGNATURE -> SignatureModule(entryModule as SignatureEntry)
            ModuleEntryTypes.PHOTO -> PhotoModule(entryModule as PhotoEntry)
            ModuleEntryTypes.MULTIPLECHOICE -> MultipleChoiceModule(entryModule as MultipleChoiceEntry)
            ModuleEntryTypes.SINGLECHOICE -> SingleChoiceModule(entryModule as SingleChoiceEntry)
            else -> {
                TextfieldModule(TextfieldEntry("else"))
            }
        }
    }
}

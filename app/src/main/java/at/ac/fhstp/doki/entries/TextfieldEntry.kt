package at.ac.fhstp.doki.entries

import android.os.Parcel
import android.os.Parcelable

class TextfieldEntry(
    val text: String
) : ModuleEntry(ModuleEntryTypes.TEXTFIELD.value) {
    override fun toString(): String {
        return text
    }
}
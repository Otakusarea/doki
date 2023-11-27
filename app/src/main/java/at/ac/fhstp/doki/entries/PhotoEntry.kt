package at.ac.fhstp.doki.entries

class PhotoEntry(
    val photo: String
) : ModuleEntry(ModuleEntryTypes.PHOTO.value) {

    override fun toString(): String {
        return photo//this::class.java.simpleName
    }
}
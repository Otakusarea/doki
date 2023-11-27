package at.ac.fhstp.doki.entries

class SignatureEntry(
    val text: String
) : ModuleEntry(ModuleEntryTypes.SIGNATURE.value) {
    override fun toString(): String {
        return text//this::class.java.simpleName
    }
}

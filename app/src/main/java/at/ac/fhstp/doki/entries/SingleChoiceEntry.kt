package at.ac.fhstp.doki.entries

class SingleChoiceEntry(
    val question: String,
    val options: Array<String>,
    var choices: Int
) : ModuleEntry(ModuleEntryTypes.SINGLECHOICE.value) {
    override fun toString(): String {
        return question //ModuleEntryTypes.SINGLECHOICE.toString()
    }
}
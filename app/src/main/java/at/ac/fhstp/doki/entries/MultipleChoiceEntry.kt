package at.ac.fhstp.doki.entries

class MultipleChoiceEntry(
    val question: String,
    val options: Array<String>,
    val choices: Array<Boolean>
) : ModuleEntry(ModuleEntryTypes.MULTIPLECHOICE.value) {
    override fun toString(): String {
        return question//this::class.java.simpleName
    }
}
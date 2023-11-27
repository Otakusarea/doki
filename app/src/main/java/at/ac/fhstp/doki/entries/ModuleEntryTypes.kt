package at.ac.fhstp.doki.entries

enum class ModuleEntryTypes(val value: Int) {
    TEXTFIELD(1),
    MULTIPLECHOICE(2),
    PHOTO(3),
    SIGNATURE(4),
    SINGLECHOICE(5);

    companion object {
        private val map = ModuleEntryTypes.values().associateBy(ModuleEntryTypes::value)
        fun fromInt(type: Int?) = map[type]
    }
}
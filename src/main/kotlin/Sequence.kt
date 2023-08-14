/**
 * Data classes to store sequences.
 * For exon and query sequences.
 */
data class Exon(
    val name: String,
    val profiles: List<String?>,
    val sequence: List<Literal>
) {
    override fun toString(): String {
        val profileStr = profiles.joinToString("\t")
        val sequenceStr = sequence.joinToString("") { it.char.toString() }
        return "Exon(name=$name, profiles=$profileStr, sequence=$sequenceStr)"
    }
}

data class Query(
    val name: String,
    val sequence: List<Literal>
) {
    override fun toString(): String {
        val sequenceStr = sequence.joinToString("") { it.char.toString() }
        return "Query(name=$name, sequence=$sequenceStr)"
    }
}

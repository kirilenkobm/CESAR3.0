import java.io.File

class EmissionTable(private val filePath: String) {
    private val entries: List<ProfileEntry> = parseProfileTable()

    private fun parseProfileTable(): List<ProfileEntry> {
        val lines = File(filePath).readLines()
        val parsedEntries = mutableListOf<ProfileEntry>()

        // Skip the header line
        for (line in lines.drop(1)) {
            val parts = line.split("\t").map { it.toDouble() }
            val entry = ProfileEntry(
                mapOf(
                    Literal.LITERAL_A to parts[0],
                    Literal.LITERAL_T to parts[1],
                    Literal.LITERAL_C to parts[2],
                    Literal.LITERAL_G to parts[3]
                )
            )
            parsedEntries.add(entry)
        }

        return parsedEntries
    }

    fun getProbabilityAtPosition(position: Int, nucleotide: Literal): Double {
        val entry = entries[position]
        return entry.probabilities[nucleotide] ?: throw IllegalArgumentException("Invalid nucleotide: $nucleotide")
    }

    fun getEmissionProbability(sequence: List<Literal>): Double {
        return sequence.mapIndexed { index, literal ->
            getProbabilityAtPosition(index, literal)
        }.reduce { acc, prob -> acc * prob }
    }

    // Handle Ns in sequences
    fun getVariants(sequence: List<Literal>): List<List<Literal>> {
        if (sequence.isEmpty()) return listOf(emptyList())

        val first = sequence.first()
        val rest = getVariants(sequence.drop(1))

        return if (first == Literal.LITERAL_N) {
            Literal.values().flatMap { literal ->
                rest.map { listOf(literal) + it }
            }
        } else {
            rest.map { listOf(first) + it }
        }
    }
}

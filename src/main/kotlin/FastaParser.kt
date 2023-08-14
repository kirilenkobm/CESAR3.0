import java.io.File

/**
 * Class to handle input fasta file parsing.
 * to not forget to write the specification for the input format.
 */
class FastaParser(private val fastaFilePath: String) {

    fun parse(): Pair<List<Exon>, List<Query>> {
        val content = File(fastaFilePath).readText()
        val sections = content.split(Regex("#+")).filter { it.isNotBlank() }

        if (sections.size != 2) {
            throw IllegalArgumentException("The FASTA file must be split into two sections by a sequence of '#' characters.")
        }

        val exonSection = sections[0]
        val querySection = sections[1]

        val exons = parseExons(exonSection)
        val queries = parseQueries(querySection)

        return Pair(exons, queries)
    }

    private fun parseExons(section: String): List<Exon> {
        val chunks = section.split(">").filter { it.isNotBlank() }
        val exons = mutableListOf<Exon>()

        for (chunk in chunks) {
            val lines = chunk.split("\n").filter { it.isNotBlank() }
            if (lines.size != 2) {
                throw IllegalArgumentException("Each exon chunk must have a header and a sequence.")
            }

            val header = lines[0]
            if (!sanityCheck(lines[1])) {
                throw IllegalArgumentException("Invalid characters in exon sequence.")
            }
            val sequence = lines[1].map { Literal.fromChar(it) }

            val parts = header.split("\t")
            val exonName = parts[0]
            val exonProfiles = when (parts.size) {
                3 -> listOf(parts[1], parts[2])
                1 -> emptyList()
                else -> throw IllegalArgumentException("There must be either 0 or 2 profiles for each exon.")
            }

            exons.add(Exon(exonName, exonProfiles, sequence))
        }

        return exons
    }

    private fun parseQueries(section: String): List<Query> {
        val chunks = section.split(">").filter { it.isNotBlank() }
        val queries = mutableListOf<Query>()

        for (chunk in chunks) {
            val lines = chunk.split("\n").filter { it.isNotBlank() }
            if (lines.size != 2) {
                throw IllegalArgumentException("Each query chunk must have a header and a sequence.")
            }

            val header = lines[0]
            if (!sanityCheck(lines[1])) {
                throw IllegalArgumentException("Invalid characters in query sequence.")
            }
            val sequence = lines[1].map { Literal.fromChar(it) }

            queries.add(Query(header, sequence))
        }

        return queries
    }

    private fun sanityCheck(sequence: String): Boolean {
        return sequence.all { it in "ATGCNatgcn" }
    }
}

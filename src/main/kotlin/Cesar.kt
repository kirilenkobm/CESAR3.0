// Contains the main application logic and is the entry point of the program.

object Cesar {
    private val fastaParser by lazy { FastaParser() }
    private val viterbi by lazy { Viterbi() }

    suspend fun run(args: Array<String>) {
        val arguments = CesarArgsParser.parse(args)
        val fasta = fastaParser.parse(arguments.fastaFile)
        val profiles = fasta.references.map { Profile(it) }
        val hmm = HMM(profiles)
        val alignments = fasta.queries.map { query ->
            async { viterbi.run(hmm, query) }
        }
        alignments.awaitAll().forEach { it.print() }
    }
}

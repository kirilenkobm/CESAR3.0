// Contains the main application logic and is the entry point of the program.

object Cesar {

    fun run(args: Array<String>) {
        val arguments = CesarArgsParser.parse(args)
        val (exons, queries) = FastaParser(arguments.fastaFile).parse()

        exons.forEach { println(it) }
        queries.forEach { println(it) }
    }
}

// Contains the main application logic and is the entry point of the program.


object Cesar {

    fun run(args: Array<String>) {
        val cesarArgs = CesarArgsParser.parse(args)
        println(cesarArgs)
    }
}
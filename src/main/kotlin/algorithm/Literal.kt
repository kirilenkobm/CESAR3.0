package algorithm

/**
 * Class that represents legal nucleotides.
 */
enum class Literal(val char: Char) {
    A('A'),
    C('C'),
    G('G'),
    T('T'),
    N('N');

    companion object {
        fun fromChar(c: Char): Literal {
            return when (c.uppercaseChar()) {
                'A' -> A
                'C' -> C
                'G' -> G
                'T' -> T
                'N' -> N
                else -> throw IllegalArgumentException("Unknown literal: $c")
            }
        }
    }
}

// To be encapsulated later
fun literalsToString(literals: List<Literal>): String {
    return literals.joinToString(separator = "") { it.char.toString() }
}

fun literalsToUInt(literals: List<Literal>): Int {
    var byte = 0
    for (literal in literals) {
        byte = byte shl 2
        byte += literal.ordinal
    }
    return byte
}

fun countNs(literals: List<Literal>): Int {
    return literals.count { it == Literal.N }
}

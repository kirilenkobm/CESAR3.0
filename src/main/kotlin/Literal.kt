/**
 * In the original C code, sequences were structs containing sequences of Literal enums
 * instead of chars. This class was created to recapitulate that behaviour.
 */
enum class Literal(val char: Char) {
    LITERAL_A('A'),
    LITERAL_C('C'),
    LITERAL_G('G'),
    LITERAL_T('T'),
    LITERAL_N('N');

    companion object {
        fun fromChar(c: Char): Literal {
            return when (c.uppercaseChar()) {
                'A' -> LITERAL_A
                'C' -> LITERAL_C
                'G' -> LITERAL_G
                'T' -> LITERAL_T
                'N' -> LITERAL_N
                else -> throw IllegalArgumentException("Unknown literal: $c")
            }
        }
    }
}

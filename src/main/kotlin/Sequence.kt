import algorithm.Literal

class Sequence {
    var length: Int = 0
    var sequence: MutableList<Literal> = mutableListOf()
    var name: String = ""
    var startSplitLength: Int = 0
    var endSplitLength: Int = 0
    var codonsOffset: Int = 0
    var numCodons: Int = 0
    var numCodonBases: Int = 0
    var numStates: Int = 0
    var numAlignSpaces: Int = 0
    var hasStartCodonAsSplitCodonState: Boolean = false
    var acceptor: String = ""
    var donor: String = ""

    fun append(literal: Literal) {
        sequence.add(literal)
        length++
    }

    fun setProfiles(acceptor: String, donor: String) {
        this.acceptor = acceptor
        this.donor = donor
    }
}

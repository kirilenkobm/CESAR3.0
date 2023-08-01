package algorithm

class State(
    var name: String,
    var numEmissions: Int,
    var emissionReference: Array<Literal>,
    var emissionTable: EmissionTable?
) {
    var id: Int = nextId++
    var numIncoming = 0
    var incoming: MutableList<Transition> = mutableListOf()


    companion object {
        private var nextId = 0

        fun createUniform(name: String, numEmissions: Int, emissionTable: EmissionTable): State {
            val emissionReference = Array(numEmissions) { Literal.A }
            return State(name, numEmissions, emissionReference, emissionTable)
        }

        fun createSilent(name: String): State {
            return State(name, 0, emptyArray(), null)
        }
    }

    fun addIncoming(logodd: Double, target: State) {
        if (target.numIncoming > Constants.STATE_MAX_NUM_INCOMING) {
            throw Exception("Too many incoming transitions for state ${target.name} (${target.id}).")
        }
        val transition = Transition(this.id, logodd)
        target.incoming.add(transition)
        target.numIncoming++
    }


    override fun toString(): String {
        val tmp = emissionReference.joinToString("")
        return "State { .name=\"$name\", .id=$id, .numEmissions=$numEmissions, .reference=\"$tmp\", .numIncoming=$numIncoming }"
    }

    // Get the emission probability for a given literal at a given distance
    fun getEmissionProbability(literal: Literal, distance: Int): Double {
        return emissionTable?.getEmissionProbability(literal, distance) ?: 0.0
    }

    // Get the transition probability to another state
    fun getTransitionProbability(to: State): Double {
        val transition = incoming.find { it.origin == to.id }
        return transition?.getProbability() ?: 0.0
    }
}

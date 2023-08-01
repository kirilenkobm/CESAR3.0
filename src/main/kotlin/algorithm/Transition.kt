package algorithm

// class Transition(private val probabilities: Map<Pair<State, State>, Double>) {
class Transition(val origin: Int, val logodd: Double) {

    // Convert the log-odds value to a probability
    fun getProbability(): Double {
        return Math.exp(logodd) / (1 + Math.exp(logodd))
    }
}

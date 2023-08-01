package algorithm

class HiddenMarkovModel(private val states: List<State>, private val transition: Transition) {

    // Get a state by its name
    fun getState(name: String): State? {
        return states.find { it.name == name }
    }

    // Get the transition probability from one state to another
//    fun getTransitionProbability(from: State, to: State): Double {
//        return transition.getProbability(from, to)
//    }
}

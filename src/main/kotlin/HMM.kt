class HMM(
    val states: MutableList<State> = mutableListOf(),
    val starts: MutableList<Transition> = mutableListOf(),
    val ends: MutableList<Transition> = mutableListOf()
) {
    // Methods related to HMM can be added here, like adding a state, setting a start or end transition, etc.
}

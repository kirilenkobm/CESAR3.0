data class State(
    val id: Int,
    val name: String,
    val numEmissions: Int,
    val emissionReference: List<Literal>,
    val emissionTable: EmissionTable,
    val incoming: MutableList<Transition> = mutableListOf()
) {
    fun addIncoming(transition: Transition) {
        if (incoming.size >= Constants.STATE_MAX_NUM_INCOMING) {
            throw Exception("Too many incoming transitions for state $name ($id).")
        }
        incoming.add(transition)
    }
}

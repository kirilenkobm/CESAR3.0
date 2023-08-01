package algorithm

import java.io.File

class EmissionTable(private val probabilities: Map<Literal, DoubleArray>) {

    // Get the emission probability for a given literal at a given distance
    fun getEmissionProbability(literal: Literal, distance: Int): Double {
        return probabilities[literal]?.get(distance) ?: 0.0
    }

    // Get the emission probability for a given symbol at a given distance
    fun getProbability(symbol: String, distance: Int): Double {
        val literal = Literal.valueOf(symbol) // Convert the string to a Literal
        return getEmissionProbability(literal, distance)
    }
}

fun readEmissionTableFromFile(filename: String): EmissionTable {
    val file = File(filename)
    val lines = file.readLines()
    val probabilities = mutableMapOf<Literal, MutableList<Double>>()

    // Initialize the lists
    probabilities[Literal.A] = mutableListOf()
    probabilities[Literal.T] = mutableListOf()
    probabilities[Literal.C] = mutableListOf()
    probabilities[Literal.G] = mutableListOf()

    // Skip the first line (header)
    for (i in 1..<lines.size) {
        val line = lines[i]
        val parts = line.split("\t")
        val aProb = parts[0].toDouble()
        val tProb = parts[1].toDouble()
        val cProb = parts[2].toDouble()
        val gProb = parts[3].toDouble()

        // Append the probabilities for each literal
        probabilities[Literal.A]?.add(aProb)
        probabilities[Literal.T]?.add(tProb)
        probabilities[Literal.C]?.add(cProb)
        probabilities[Literal.G]?.add(gProb)
    }

    // Convert the lists to arrays
    val probabilityArrays = probabilities.mapValues { (_, v) -> v.toDoubleArray() }

    return EmissionTable(probabilityArrays)
}

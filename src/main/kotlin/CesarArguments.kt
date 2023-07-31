/**
 * Recapitulates CLI arguments of the original CESAR2.0
 */
data class CesarArguments (
    val fastaFile: String,
    val clade: String,
    val matrixFile: String,
    val profiles: String,
    val splitCodonEmissions: String,
    val firstExon: Boolean,
    val lastExon: Boolean,
    val maxMemory: Int,
    val verbosity: Int,
    val version: Boolean,
    val set: String,
    val sanityChecks: Boolean
) {
    override fun toString(): String {
        return """
            fastaFile: $fastaFile
            clade: $clade
            matrixFile: $matrixFile
            profiles: $profiles
            splitCodonEmissions: $splitCodonEmissions
            firstExon: $firstExon
            lastExon: $lastExon
            maxMemory: $maxMemory
            verbosity: $verbosity
            version: $version
            set: $set
            sanityChecks: $sanityChecks
        """.trimIndent()
    }
}

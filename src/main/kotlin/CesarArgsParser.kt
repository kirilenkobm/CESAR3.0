import kotlinx.cli.*

object CesarArgsParser {
    fun parse(args: Array<String>): CesarArguments {
        val parser = ArgParser("cesar")
        val fastaFile by parser.argument(ArgType.String, description = "Fasta file")
        val clade by parser.option(ArgType.String, shortName = "c", fullName = "clade", description = "Clade")
        val matrixFile by parser.option(ArgType.String, shortName = "m", fullName = "matrix", description = "Matrix file")
        val profiles by parser.option(ArgType.String, shortName = "p", fullName = "profiles", description = "Profiles")
        val splitCodonEmissions by parser.option(ArgType.String, shortName = "i", fullName = "split_codon_emissions", description = "Split codon emissions")
        val firstExon by parser.option(ArgType.Boolean, shortName = "f", fullName = "firstexon", description = "First exon")
        val lastExon by parser.option(ArgType.Boolean, shortName = "l", fullName = "lastexon", description = "Last exon")
        val maxMemory by parser.option(ArgType.Int, shortName = "x", fullName = "max-memory", description = "Max memory")
        val verbosity by parser.option(ArgType.Int, shortName = "v", fullName = "verbosity", description = "Verbosity")
        val version by parser.option(ArgType.Boolean, shortName = "V", fullName = "version", description = "Version")
        val set by parser.option(ArgType.String, shortName = "s", fullName = "set", description = "Set")
        val sanityChecks by parser.option(ArgType.Boolean, shortName = "S", fullName = "sanityChecks", description = "Sanity checks")

        parser.parse(args)

        return CesarArguments(
            fastaFile = fastaFile,
            clade = clade ?: "",
            matrixFile = matrixFile ?: "",
            profiles = profiles ?: "",
            splitCodonEmissions = splitCodonEmissions ?: "",
            firstExon = firstExon ?: false,
            lastExon = lastExon ?: false,
            maxMemory = maxMemory ?: 0,
            verbosity = verbosity ?: 0,
            version = version ?: false,
            set = set ?: "",
            sanityChecks = sanityChecks ?: false
        )
    }
}

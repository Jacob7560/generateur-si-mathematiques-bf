package bf.maths.generateursi
data class SituationRequest(
    val niveau: String, val classe: String, val notions: String, val competence: String,
    val contexte: String, val difficulte: String, val duree: String, val bareme: Int
)
data class Situation(
    val titre: String, val classe: String, val enonce: String, val consignes: List<String>,
    val corrige: String, val grille: String, val source: String
)

package bf.maths.generateursi
object SituationGenerator {
    fun offline(r: SituationRequest): Situation {
        val contexte = when (r.contexte) {
            "Commerce et gestion d'un budget" -> "Un groupement de commerçants prépare une commande et doit respecter un budget."
            "Agriculture et élevage" -> "Un producteur organise sa campagne agricole en tenant compte des ressources disponibles."
            "Construction et aménagement" -> "Un comité local veut aménager un espace et comparer plusieurs propositions."
            else -> "Au Burkina Faso, un comité prépare une activité communautaire et doit prendre une décision à partir de données."
        }
        val enonce = "$contexte Les notions à mobiliser sont : ${r.notions}. La compétence visée est : ${r.competence}. L'enseignant doit compléter les données numériques pour correspondre précisément au programme de ${r.classe}."
        val consignes = listOf(
            "Identifier les données utiles et la question à résoudre.",
            "Élaborer une démarche et résoudre le problème en mobilisant : ${r.notions}.",
            "Interpréter les résultats et rédiger une conclusion justifiée."
        )
        val grille = """
            Grille indicative sur ${r.bareme} points.
            CM1 — Pertinence de la démarche : compréhension et stratégie adaptée.
            CM2 — Outils mathématiques : propriétés, calculs, unités et raisonnement.
            CM3 — Cohérence et interprétation : vérification et sens des résultats.
            CP — Perfectionnement : clarté, organisation et communication mathématique.
            Attention : répartir les points sur les indicateurs après finalisation des consignes.
        """.trimIndent()
        return Situation("Situation d'intégration — ${r.classe}", r.classe, enonce, consignes,
            "Modèle hors ligne : ce modèle ne fournit pas encore de corrigé numérique complet. Ajoutez des données précises puis vérifiez les calculs avant distribution.",
            grille, "Modèle hors ligne")
    }
}

package bf.maths.generateursi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { GeneratorScreen() } }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratorScreen() {
    var niveau by remember { mutableStateOf("Secondaire") }
    var classe by remember { mutableStateOf("Première D") }
    var notions by remember { mutableStateOf("Applications, fonctions, équations") }
    var competence by remember { mutableStateOf("Résoudre un problème concret en mobilisant plusieurs acquis") }
    var contexte by remember { mutableStateOf("Vie quotidienne au Burkina Faso") }
    var difficulte by remember { mutableStateOf("Standard") }
    var duree by remember { mutableStateOf("1 heure") }
    var bareme by remember { mutableStateOf("20") }
    var result by remember { mutableStateOf<Situation?>(null) }
    Scaffold(topBar = { TopAppBar(title = { Text("Générateur SI Mathématiques BF") }) }) { padding ->
        Column(Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Concevoir · Enseigner · Évaluer", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(niveau, { niveau = it }, label = { Text("Niveau") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(classe, { classe = it }, label = { Text("Classe") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(notions, { notions = it }, label = { Text("Notions à mobiliser") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(competence, { competence = it }, label = { Text("Compétence visée") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(contexte, { contexte = it }, label = { Text("Contexte") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(difficulte, { difficulte = it }, label = { Text("Difficulté") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(duree, { duree = it }, label = { Text("Durée") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(bareme, { bareme = it.filter(Char::isDigit).take(2) }, label = { Text("Barème") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                result = SituationGenerator.offline(SituationRequest(niveau, classe, notions, competence,
                    contexte, difficulte, duree, bareme.toIntOrNull() ?: 20))
            }, modifier = Modifier.fillMaxWidth()) { Text("Générer hors ligne") }
            result?.let { s ->
                HorizontalDivider()
                Text(s.titre, style = MaterialTheme.typography.headlineSmall)
                Text("Classe : ${s.classe} · ${s.source}")
                Text("Situation-problème", style = MaterialTheme.typography.titleMedium)
                Text(s.enonce)
                Text("Consignes", style = MaterialTheme.typography.titleMedium)
                s.consignes.forEachIndexed { i, c -> Text("${i + 1}. $c") }
                Text("Corrigé", style = MaterialTheme.typography.titleMedium)
                Text(s.corrige)
                Text("Grille d'évaluation", style = MaterialTheme.typography.titleMedium)
                Text(s.grille)
            }
            Text("Prototype initial : le mode IA, la bibliothèque persistante et l'export PDF/Word sont à intégrer dans les prochaines étapes. Vérifiez les données et calculs avant toute utilisation en classe.",
                style = MaterialTheme.typography.bodySmall)
        }
    }
}

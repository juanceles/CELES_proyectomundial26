package Nos.Fuimos.Mudiales.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Nos.Fuimos.Mudiales.models.DTOPartidosLista

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidosListaScreen(
    partidos: List<DTOPartidosLista>,
    onPartidoClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Mundial 2026 - Selección Argentina", fontWeight = FontWeight.Bold) })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(partidos) { partido ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPartidoClick(partido.id) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${partido.equipo1} vs ${partido.equipo2}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Fase: ${partido.grupo}", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(text = "Horario: ${partido.fecha}", fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
                    }
                }
            }
        }
    }
}
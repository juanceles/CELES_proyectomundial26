package Nos.Fuimos.Mudiales.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidoDetalleScreen(
    partido: DTOPartidosDetalle,
    onVolverClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Detalle del Partido") })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*
            =======================================================================
            🌎 // Usamos las propiedades estrictas en español de tu archivo físico
            // de modelos para evitar cualquier tipo de congelamiento en la UI.
            =======================================================================
            */
            Text(text = "${partido.equipo1} vs ${partido.equipo2}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(text = "Fase: ${partido.grupo}", fontSize = 16.sp)
            Text(text = "Fecha y Hora: ${partido.fecha}", fontSize = 16.sp)
            Text(text = "Estadio Sede: ${partido.estadio}", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(text = "Precio estimado: ${partido.precio} USD", fontSize = 18.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onVolverClick) {
                Text("Volver a la Lista")
            }
        }
    }
}
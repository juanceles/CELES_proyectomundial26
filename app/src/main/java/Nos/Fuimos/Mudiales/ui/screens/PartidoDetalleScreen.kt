package Nos.Fuimos.Mudiales.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidoDetalleScreen(
    partido: DTOPartidosDetalle,
    onVolverClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Partido", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Tarjeta de equipos que encuadra las banderas reales de internet
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Equipo 1: Argentina
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier.size(60.dp, 40.dp),
                            shape = RoundedCornerShape(6.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            // 1. Primer bloque (Para la bandera de Argentina)
                            // 1. Para la bandera del primer equipo (Argentina)
                            AsyncImage(
                                model = partido.flag1,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                                error = androidx.compose.ui.res.painterResource(id = android.R.drawable.stat_notify_error)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = partido.equipo1, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                    }

                    // VS Circular perfectamente centrado
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "VS",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    // Equipo 2: Argelia, Austria, etc.
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier.size(60.dp, 40.dp),
                            shape = RoundedCornerShape(6.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            // 2. Segundo bloque (Para la bandera de Argelia, línea 118 de tu pantalla)
                            // 2. Para la bandera del segundo equipo (Argelia)
                            AsyncImage(
                                model = partido.flag2,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                                error = androidx.compose.ui.res.painterResource(id = android.R.drawable.stat_notify_error)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = partido.equipo2, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = MaterialTheme.colorScheme.outlineVariant)

            // Tarjeta de datos técnicos
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "Fase: ${partido.grupo}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Text(text = "Fecha y Hora: ${partido.fecha}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Text(text = "Estadio Sede: ${partido.estadio}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    // Contenedor de precio horizontal protegido contra fuentes gigantes
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Precio de Entrada:",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = partido.precio,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.ExtraBold,
                                softWrap = false,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onVolverClick,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Volver a la Lista", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
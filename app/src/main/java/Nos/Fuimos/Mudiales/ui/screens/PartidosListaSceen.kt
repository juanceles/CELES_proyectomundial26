package Nos.Fuimos.Mudiales.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Nos.Fuimos.Mudiales.models.DTOPartidosLista

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidosListaScreen(
    partidos: List<DTOPartidosLista>,
    onPartidoClick: (Int) -> Unit,
    onBackToBienvenida: () -> Unit
) {
    // Escucha el botón nativo de atrás de Android (<)
    BackHandler {
        onBackToBienvenida()
    }

    // Animación de pulso infinita para las estrellas (idéntica a la pantalla de bienvenida)
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Barra superior con alineación corregida
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF6650a4),
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mundial 2026",
                fontSize = 22.sp,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.height(28.dp)
            ) {
                // Etiqueta Fixture corregida con Row y sin Font Padding
                Surface(
                    shape = CircleShape,
                    color = Color(0xFF007F00),
                    modifier = Modifier
                        .height(28.dp)
                        .padding(end = 10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 14.dp)
                    ) {
                        Text(
                            text = "Fixture",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false // Elimina el espacio fantasma de Android
                                )
                            )
                        )
                    }
                }

                // Fila animada con los 3 círculos al mismo nivel y altura que Fixture
                Row(
                    modifier = Modifier
                        .height(28.dp)
                        .scale(scale),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { index ->
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF007F00),
                            modifier = Modifier.size(24.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        if (index < 2) {
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }
        }

        // Lista de partidos con scroll
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(partidos) { partido ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPartidoClick(partido.id) },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE2E2E6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "${partido.equipo1} vs ${partido.equipo2}",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Fase: ${partido.grupo}",
                            fontSize = 15.sp,
                            color = Color(0xFFB58D53),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "Horario: ${partido.fecha}",
                            fontSize = 15.sp,
                            color = Color(0xFF1A1A1A),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
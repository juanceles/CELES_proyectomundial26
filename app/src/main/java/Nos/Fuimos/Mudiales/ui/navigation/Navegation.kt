package Nos.Fuimos.Mudiales.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import Nos.Fuimos.Mudiales.ui.screens.PartidosListaScreen
import Nos.Fuimos.Mudiales.ui.screens.PartidoDetalleScreen
import Nos.Fuimos.Mudiales.viewmodels.MundialViewModel

enum class MundialRutas {
    LISTA_PARTIDOS,
    DETALLE_PARTIDO
}

@Composable
fun MundialNavGraph(
    mainViewModel: MundialViewModel,
    modifier: Modifier = Modifier
) {
    // 📌 Esto lee el estado dinámico del ciclo de vida de Android de forma reactiva
    val state = mainViewModel.uiState

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            // El enrutador evalúa el estado retenido por el ciclo de vida
            val rutaActual = if (state.partidoSeleccionado == null) {
                MundialRutas.LISTA_PARTIDOS
            } else {
                MundialRutas.DETALLE_PARTIDO
            }

            when (rutaActual) {
                MundialRutas.LISTA_PARTIDOS -> {
                    PartidosListaScreen(
                        partidos = state.partidos,
                        onPartidoClick = { id -> mainViewModel.seleccionarPartido(id) }
                    )
                }
                MundialRutas.DETALLE_PARTIDO -> {
                    state.partidoSeleccionado?.let { partido ->
                        PartidoDetalleScreen(
                            partido = partido,
                            onVolverClick = { mainViewModel.volverAlListado() }
                        )
                    }
                }
            }
        }
    }
}
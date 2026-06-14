package Nos.Fuimos.Mudiales.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import Nos.Fuimos.Mudiales.ui.screens.PartidosListaScreen
import Nos.Fuimos.Mudiales.ui.screens.PartidoDetalleScreen
import Nos.Fuimos.Mudiales.viewmodels.MundialViewModel

// 1️⃣ DEFINICIÓN DE RUTAS SERIALIZABLES (Reemplazan al viejo enum)
@Serializable
object ListaPartidosRoute

@Serializable
data class DetallePartidoRoute(
    val partidoId: Int
)

@Composable
fun MundialNavGraph(
    mainViewModel: MundialViewModel,
    modifier: Modifier = Modifier
) {
    val state = mainViewModel.uiState

    // 2️⃣ CREACIÓN DEL CONTROLADOR DE NAVEGACIÓN OFICIAL
    val navController = rememberNavController()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            // 3️⃣ ESTRUCTURA DEL NAVHOST CON TIPADO ESTRICTO
            NavHost(
                navController = navController,
                startDestination = ListaPartidosRoute
            ) {
                // Pantalla de la Lista
                composable<ListaPartidosRoute>() {
                    PartidosListaScreen(
                        partidos = state.partidos,
                        onPartidoClick = { id ->
                            // Buscamos los datos en el ViewModel primero
                            mainViewModel.seleccionarPartido(id)
                            // Navegamos pasando la ruta con su ID correspondiente
                            navController.navigate(DetallePartidoRoute(partidoId = id))
                        }
                    )
                }

                // Pantalla del Detalle
                composable<DetallePartidoRoute>() { backStackEntry ->
                    // Extraemos los parámetros de la ruta de forma segura y tipada
                    val rutaDetalle = backStackEntry.toRoute<DetallePartidoRoute>()

                    state.partidoSeleccionado?.let { partido ->
                        PartidoDetalleScreen(
                            partido = partido,
                            onVolverClick = {
                                mainViewModel.volverAlListado()
                                // Regresa a la pantalla anterior destruyendo el detalle de la pila
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
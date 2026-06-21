package Nos.Fuimos.Mudiales.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import Nos.Fuimos.Mudiales.ui.screens.BienvenidaScreen
import Nos.Fuimos.Mudiales.ui.screens.LoginScreen
import Nos.Fuimos.Mudiales.ui.screens.RegistroScreen // Nueva importación agregada
import Nos.Fuimos.Mudiales.ui.screens.PartidosListaScreen
import Nos.Fuimos.Mudiales.ui.screens.PartidoDetalleScreen
import Nos.Fuimos.Mudiales.viewmodels.MundialViewModel
import Nos.Fuimos.Mudiales.viewmodels.AuthViewModel

@Serializable
object BienvenidaRoute

@Serializable
object LoginRoute

@Serializable
object RegistroRoute // Nueva ruta creada para el alta de usuario

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
    val navController = rememberNavController()
    // Cambiado para forzar la persistencia de estados en Jetpack Compose
    val authViewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            NavHost(
                navController = navController,
                startDestination = BienvenidaRoute
            ) {
                // 1. Pantalla de Bienvenida
                composable<BienvenidaRoute> {
                    BienvenidaScreen(
                        onIngresarClick = {
                            navController.navigate(LoginRoute)
                        }
                    )
                }

                // 2. Pantalla de Login (Actualizada para soportar navegación al Registro)
                composable<LoginRoute> {
                    LoginScreen(
                        viewModel = authViewModel,
                        onLoginSuccess = {
                            navController.navigate(ListaPartidosRoute) {
                                popUpTo(LoginRoute) { inclusive = true }
                            }
                        },
                        onRegistrarseClick = {
                            navController.navigate(RegistroRoute) // Navega a la pantalla de crear cuenta
                        }
                    )
                }

                // 3. Pantalla de Registro (Nueva en el Grafo de Navegación)
                composable<RegistroRoute> {
                    RegistroScreen(
                        viewModel = authViewModel,
                        onRegistroSuccess = {
                            // Al crearse la cuenta con éxito, lo mandamos al login para que ingrese
                            navController.navigate(LoginRoute) {
                                popUpTo(RegistroRoute) { inclusive = true }
                            }
                        },
                        onVolverClick = {
                            navController.popBackStack() // Regresa a la pantalla de inicio de sesión
                        }
                    )
                }

                // 4. Pantalla de la Lista de Partidos
                composable<ListaPartidosRoute> {
                    PartidosListaScreen(
                        partidos = state.partidos,
                        onPartidoClick = { id ->
                            mainViewModel.seleccionarPartido(id)
                            navController.navigate(DetallePartidoRoute(partidoId = id))
                        },
                        onBackToBienvenida = {
                            navController.navigate(BienvenidaRoute)
                        }
                    )
                }

                // 5. Pantalla del Detalle del Partido
                composable<DetallePartidoRoute> { backStackEntry ->
                    val rutaDetalle = backStackEntry.toRoute<DetallePartidoRoute>()

                    state.partidoSeleccionado?.let { partido ->
                        PartidoDetalleScreen(
                            partido = partido,
                            onVolverClick = {
                                mainViewModel.volverAlListado()
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
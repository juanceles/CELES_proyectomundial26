package Nos.Fuimos.Mudiales.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Nos.Fuimos.Mudiales.models.DTOPartidosDetalle
import Nos.Fuimos.Mudiales.models.DTOPartidosLista
import Nos.Fuimos.Mudiales.repository.MundialRepository
import kotlinx.coroutines.launch

data class MundialUiState(
    val partidos: List<DTOPartidosLista> = emptyList(),
    val partidoSeleccionado: DTOPartidosDetalle? = null,
    val isLoading: Boolean = false
)

class MundialViewModel : ViewModel() {

    private val repository = MundialRepository()
    var uiState by mutableStateOf(MundialUiState())
        private set

    init {
        cargarPartidosDesdeRepositorio()
    }

    fun cargarPartidosDesdeRepositorio() {
        viewModelScope.launch {
            uiState = uiState.copy(
                partidos = repository.fetchPartidosListaLocal(),
                isLoading = false
            )
        }
    }

    fun seleccionarPartido(id: Int) {
        viewModelScope.launch {
            val detalle = repository.fetchPartidoDetalleLocal(id)
            uiState = uiState.copy(
                partidoSeleccionado = detalle,
                isLoading = false
            )
        }
    }

    fun volverAlListado() {
        uiState = uiState.copy(partidoSeleccionado = null)
    }
}
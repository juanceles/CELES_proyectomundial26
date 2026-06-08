package Nos.Fuimos.Mudiales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import Nos.Fuimos.Mudiales.ui.navigation.MundialNavGraph
import Nos.Fuimos.Mudiales.viewmodels.MundialViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Guardamos el ViewModel de forma reactiva en la memoria de Compose
                    val mainViewModel = remember { MundialViewModel() }

                    // Llamamos al enrutador formal pasando el parámetro limpio
                    MundialNavGraph(
                        mainViewModel = mainViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
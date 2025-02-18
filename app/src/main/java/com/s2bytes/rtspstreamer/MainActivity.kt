package com.s2bytes.rtspstreamer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.s2bytes.rtspstreamer.ui.pages.main.MainPage
import com.s2bytes.rtspstreamer.ui.theme.RtspStreamerTheme
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel = ViewModelProvider(this)[MainVM::class.java]
        val isFirstCreation = savedInstanceState?.getBoolean("isFirstTime") ?: true
        if(isFirstCreation) viewModel.initializeModel(this)

        enableEdgeToEdge()
        setContent {
            RtspStreamerTheme {
                MainPage(
                    mainSt = viewModel.getMainStates(),
                    drawerSt = viewModel.getDrawerStates(),
                    dashboardSt = viewModel.getDashboardStates(),
                    onAction = remember { { viewModel.handelAction(it, this) } }
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isFirstTime", false)
        super.onSaveInstanceState(outState)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RtspStreamerTheme {
        Greeting("Android")
    }
}
package com.example.firstcomposesampleapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposesampleapp.ui.theme.FirstComposeSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onError
                ) {
                    MessageCard(message = "Maadar...")
                }
            }
        }
    }
}

@Composable
fun MessageCard(modifier: Modifier = Modifier, message: String) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Salam $message"
    )
}

@Preview("Light Mode", showSystemUi = false, showBackground = true)
@Preview(
    "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFFF0EAE2, showSystemUi = false
)
@Composable
fun MessageCardPreview() {
    FirstComposeSampleAppTheme {
        MessageCard(message = "Maadar ....")
    }
}

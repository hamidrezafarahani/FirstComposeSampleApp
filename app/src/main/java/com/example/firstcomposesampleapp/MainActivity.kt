package com.example.firstcomposesampleapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposesampleapp.ui.theme.FirstComposeSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MessageCard(message = Message("Ahmad", "Salam"))
                }
            }
        }
    }
}

@Composable
fun MessageCard(modifier: Modifier = Modifier, message: Message) {
    Row(modifier = modifier.padding(all = 8.dp)) {
        Image(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Content profile picture"
        )

        Spacer(modifier = modifier.width(8.dp))

        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }

        Column(modifier = modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                modifier = modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
                text = "${message.author}:"
            )

            Spacer(modifier = modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(
                    modifier = modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    text = message.message
                )
            }
        }
    }
}

@Preview("Light Mode", showBackground = true)
@Preview(
    "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun MessageCardPreview() {
    FirstComposeSampleAppTheme {
        MessageCard(message = Message("Ahmad", "Salam"))
    }
}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) {
            MessageCard(message = it)
        }
    }
}

@Preview
@Composable
fun ConversationPreview() {
    FirstComposeSampleAppTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

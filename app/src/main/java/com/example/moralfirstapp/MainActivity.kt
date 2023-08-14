package com.example.moralfirstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moralfirstapp.ui.theme.MoralFirstAppTheme
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {

    private val gameViewModel: CookieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("myApp", "MainActivity onCreate was called")
        setContent {
            MoralFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CookieClickerScreen(gameViewModel)
                }
            }
        }
    }
}

@Composable
fun CookieClickerScreen(viewModel: CookieViewModel, modifier: Modifier = Modifier) {

    val cookieCount by viewModel.cookieCount.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Cookie Clicker App",
            modifier = modifier
        )

        Spacer(modifier = modifier.size(16.dp))

        Text(
            text = "Click the cookie to get points!",
            modifier = modifier
        )
        Text(
            text = "Points: ${cookieCount}",
            modifier = modifier
        )

        Spacer(modifier = modifier.size(16.dp))

        // show a count of the cookies

        Button(
            onClick = { viewModel.incrementCookieCount() },
            modifier = modifier.size(200.dp)
            ) {
            Image(
                painter = painterResource(id = R.drawable.cookie),
                contentDescription = "Cookie",
                modifier = modifier
                    .size(300.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCookieClickerScreen() {
    var viewModel = CookieViewModel()
    MoralFirstAppTheme {
        CookieClickerScreen(viewModel = viewModel)
    }
}
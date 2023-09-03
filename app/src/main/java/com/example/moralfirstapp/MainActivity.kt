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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp

//import android.view.WindowManager


class MainActivity : ComponentActivity() {

    private val gameViewModel: CookieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("myApp", "MainActivity onCreate was called")
        setContent {
            MoralFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().background(color = Color.White)

                ) {
                    CookieClickerScreen(gameViewModel)
                }
            }
        }
    }
}

@Composable
fun CookieClickerScreen(viewModel: CookieViewModel,
                        modifier: Modifier = Modifier) {

    val cookieCount by viewModel.cookieCount.collectAsState()
    val countdownActive by viewModel.countdownActive.collectAsState()
    val isButtonClickable by viewModel.isButtonClickable.collectAsState()
    val clock by viewModel.clock.collectAsState()

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
            enabled = isButtonClickable,
            onClick = {
                viewModel.onCookieClicked()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.cookie),
                contentDescription = "Cookie",
                modifier = modifier
                    .size(300.dp)
                    .background(Color.Transparent)
            )
        }
        if (countdownActive) {
            Text(
                text = clock.toString(),
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 48.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }

}




@Preview(showBackground = true)
@Composable
fun PreviewCookieClickerScreen() {
    val viewModel = CookieViewModel()
    MoralFirstAppTheme {
        CookieClickerScreen(viewModel = viewModel)
    }
}
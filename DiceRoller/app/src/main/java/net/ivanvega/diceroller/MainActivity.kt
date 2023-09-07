package net.ivanvega.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.ivanvega.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.foundation.layout.Arrangement

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable

fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result1 by remember { mutableStateOf(1) }
    var result2 by remember { mutableStateOf(1) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = getRandomDiceImage(result1)),
            contentDescription = stringResource(R.string.image_cara_1)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = getRandomDiceImage(result2)),
            contentDescription = stringResource(R.string.image_cara_1)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result1 = (1..6).random()
                result2 = (1..6).random()
                Log.d("NumALe", "Valor aleatorio dado 1: $result1, dado 2: $result2")
            }
        ) {
            Text(text = stringResource(R.string.btn_roller))
        }
    }
}
@Composable
fun DiceRollerApp() { // Define the DiceRollerApp composable
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceWithButtonAndImage()
    }
}

private fun getRandomDiceImage(result: Int): Int {
    val diceImages = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    // If you want to ensure the same image isn't repeated on both dice, you can use a separate list for each.
    val filteredDiceImages = diceImages.filter { it != result }
    val randomIndex = (0 until filteredDiceImages.size).random()
    return filteredDiceImages[randomIndex]
}

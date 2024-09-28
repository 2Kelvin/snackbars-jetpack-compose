package com.example.snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbar.ui.theme.SnackbarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackbarTheme {
                DefaultSnackBarApp()
            }
        }
    }
}

/** Scaffold with a snack bar */
@Composable
fun DefaultSnackBarApp(modifier: Modifier = Modifier) {
    // snack-bar queue state -> holds all snack-bars and displays one snack-bar at a time to the UI
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        // SNACK BAR
        snackbarHost = { SnackbarHost(snackBarHostState) },

        // FAB
        floatingActionButton = {
            LargeFloatingActionButton(
                // when action button is clicked a snack-bar should appear stating that an item has been edited
                onClick = {
                    scope.launch {
                        // default snack-bar usage displaying the message "Edited"
                        snackBarHostState.showSnackbar("Edited")
                    }
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(innerPadding)
        ) {
            Text(text = "App content here")
        }
    }
}

/** MyApp preview */
@Preview(showBackground = true)
@Composable
fun DefaultSnackBarAppPreview() {
    SnackbarTheme {
        DefaultSnackBarApp()
    }
}
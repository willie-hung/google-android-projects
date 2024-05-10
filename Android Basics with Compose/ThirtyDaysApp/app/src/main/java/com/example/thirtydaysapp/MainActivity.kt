package com.example.thirtydaysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysapp.data.Datasource
import com.example.thirtydaysapp.model.Day
import com.example.thirtydaysapp.ui.theme.ThirtyDaysAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DayApp(
                        days = Datasource().loadAffirmations()
                    )
                }
            }
        }
    }
}

@Composable
fun DayApp(
    days: List<Day>,
) {
    Scaffold(
        topBar = {
            DayTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(days) {day ->
                DayCard(
                    day = day,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )
        },
        modifier = modifier
    )
}

@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded.value = !expanded.value }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = LocalContext.current.getString(day.dayId)
            )
            Text(
                text = LocalContext.current.getString(day.title)
            )
            Image(
                painter = painterResource(day.imageResourceId),
                contentDescription = stringResource(day.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            AnimatedVisibility(visible = expanded.value) {
                Text(
                    text = LocalContext.current.getString(day.description)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirtyDaysAppPreview() {
    ThirtyDaysAppTheme {
        DayApp(
            days = Datasource().loadAffirmations()
        )
    }
}
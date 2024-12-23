package com.example.kt_grid_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kt_grid_list.data.DataSource
import com.example.kt_grid_list.model.Topic
import com.example.kt_grid_list.ui.theme.KtGridlistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtGridlistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicCardList(
                        topicList = DataSource.topics,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopicCardList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(8.dp),
        columns =  GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topicList) { topic ->
            TopicCard( topic = topic )
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(

        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3E1E5),
        ),
        shape = RoundedCornerShape(8.dp),

        ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = stringResource(topic.name),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 0.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment =  Alignment.Start,
            ) {
                Text(
                    text = LocalContext.current.getString(topic.name),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF48474B),
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        tint = Color(0xFF48474B),
                        contentDescription = "dotsRandom",
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF48474B),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 0.dp),
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicCardPreview() {
    KtGridlistTheme {
        TopicCard(DataSource.topics[1])
    }
}
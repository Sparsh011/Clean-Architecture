package com.example.cleanarchitecturedictionaryapp.ui.screens.stackoverflow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Item
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.StackOverflow

@Composable
fun ArticlesList(articles: StackOverflow, paddingValues: PaddingValues) {
    LazyColumn (
        contentPadding = paddingValues
    ) {
        articles.items?.size?.let { it ->
            items(it) {
                ItemLayout(item = articles.items!![it])
            }
        }
    }
}

@Composable
fun ItemLayout(item: Item) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp)
    ) {
        Text(text = item.title, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Link: ${item.link}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Creation Date: ${item.creation_date}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Last Edit Date: ${item.last_edit_date}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Owner: ${item.owner.display_name}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Tags: ${item.tags.joinToString(", ")}", fontSize = 14.sp, color = Color.Gray)
    }
}
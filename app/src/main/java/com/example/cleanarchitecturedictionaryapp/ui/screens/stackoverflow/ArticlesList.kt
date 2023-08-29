package com.example.cleanarchitecturedictionaryapp.ui.screens.stackoverflow

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Item
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.StackOverflow
import java.time.Instant
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemLayout(item: Item) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp)
    ) {
        val creationDate = getFormattedTime(item.creation_date)
        val lastEditDate = getFormattedTime(item.last_edit_date) ?: "Unavailable"

        Text(text = item.title, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Creation Date: $creationDate", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Last Edit Date: $lastEditDate", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Owner: ${item.owner.display_name}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Tags: ${item.tags.joinToString(", ")}", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Link : ", fontSize = 14.sp, color = Color.Gray)
        HyperlinkText(link = item.link)
        Spacer(modifier = Modifier.height(10.dp))
        Divider()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedTime(date: Int): String? {
    if (date == 0) return null
    val fromInstant = Instant.ofEpochSecond(date.toLong())
    val zone = ZoneId.of("UTC")
    return fromInstant.atZone(zone).toLocalDate().toString()
}

@Composable
fun HyperlinkText(link: String) {
    val annotatedString = buildAnnotatedString {
        append(link)
        addStyle(
            style = SpanStyle(
                color = Color.Gray,
                textDecoration = TextDecoration.Underline
            ),
            start = 0,
            end = link.length
        )
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = 0,
            end = link.length
        )
    }
    val context = LocalContext.current

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                    startActivity(context, browserIntent, null)
                }
        }
    )
}
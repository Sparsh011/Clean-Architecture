package com.example.cleanarchitecturedictionaryapp.ui.screens.dictionary


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo
import com.example.cleanarchitecturedictionaryapp.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WordInfoItemComposable(
    wordInfoList: List<WordInfo>,
    viewModel: SharedViewModel,
    paddingValues: PaddingValues
    ) {

    Scaffold {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .padding(paddingValues = paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search Here...")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(wordInfoList.size) { index ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        WordInfoDetailsComposable(wordInfoList[index])
                        if (index < wordInfoList.size - 1) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WordInfoDetailsComposable(wordInfo: WordInfo) {
    Column {
        wordInfo.word?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        wordInfo.meanings?.forEach { meaning ->
            Text(text = meaning.partOfSpeech)
            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let {
                    Text(text = "Example : $it")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}
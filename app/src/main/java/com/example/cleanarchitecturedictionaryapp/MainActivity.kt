package com.example.cleanarchitecturedictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecturedictionaryapp.ui.components.BottomBar
import com.example.cleanarchitecturedictionaryapp.ui.navigation.BottomNavGraph
import com.example.cleanarchitecturedictionaryapp.ui.theme.CleanArchitectureDictionaryAppTheme
import com.example.cleanarchitecturedictionaryapp.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SharedViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureDictionaryAppTheme {
                val articles = viewModel.stackOverflowArticles.value
                val navHostController = rememberNavController()
                val wordInfo = viewModel.wordInfo.value.wordInfoItems

                Scaffold (
                    bottomBar = {
                        BottomBar(navHostController = navHostController)
                    }
                ){
                    BottomNavGraph(navController = navHostController, articles = articles, wordInfo = wordInfo, viewModel = viewModel, paddingValues = it)
                }
            }
        }
    }
}

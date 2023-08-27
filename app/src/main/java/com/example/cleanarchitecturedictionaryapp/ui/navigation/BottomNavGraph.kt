package com.example.cleanarchitecturedictionaryapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.StackOverflow
import com.example.cleanarchitecturedictionaryapp.ui.screens.BottomBarScreen
import com.example.cleanarchitecturedictionaryapp.ui.screens.dictionary.WordInfoItemComposable
import com.example.cleanarchitecturedictionaryapp.ui.screens.stackoverflow.ArticlesList
import com.example.cleanarchitecturedictionaryapp.ui.viewmodel.SharedViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    articles: StackOverflow,
    wordInfo: List<WordInfo>,
    viewModel: SharedViewModel,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.StackOverflow.route) {
        composable(route = BottomBarScreen.StackOverflow.route) {
            ArticlesList(articles = articles, paddingValues = paddingValues)
        }

        composable(route = BottomBarScreen.Dictionary.route) {
            WordInfoItemComposable(wordInfoList = wordInfo, viewModel = viewModel, paddingValues = paddingValues)
        }
    }
}
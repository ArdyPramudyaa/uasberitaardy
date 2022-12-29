package com.sijanneupane.mvvmnews.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sijanneupane.mvvmnews.repository.NewsRepository
/*
Untuk menentukan bagaimana model tampilan kita harus dibuat
 */
class NewsViewModelProviderFactory(
    val app: Application,
    val newsRepository: NewsRepository
    ) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}
package com.example.nytimes

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nytimes.model.Article
import com.example.nytimes.model.ArticlesService
import com.example.nytimes.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var articlesService: ArticlesService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Article>>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getArticlesSuccess(){
        //val article = Article("Amazing day","By Me", "15-04-2020","bla bla bla")
        //val articlesList = arrayListOf(article)
        //testSingle = Single.just(articlesList)
        testSingle = Single.error(Throwable())
        `when`(articlesService.getArticles()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1,listViewModel.articles.value?.size)
        Assert.assertEquals(false,listViewModel.articleLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }
    @Test
    fun getArticlesFail(){
        testSingle = Single.error(Throwable())

        `when`(articlesService.getArticles()).thenReturn(testSingle)
        listViewModel.refresh()

        Assert.assertEquals(true,listViewModel.articleLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)

    }

    @Before
    fun setUpRxSchedulers(){
        val immediate = object : Scheduler(){
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run,0,unit)
        }
            override fun createWorker(): Worker{
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}
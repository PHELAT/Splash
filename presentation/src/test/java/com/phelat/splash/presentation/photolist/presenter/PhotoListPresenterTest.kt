package com.phelat.splash.presentation.photolist.presenter

import android.arch.lifecycle.MutableLiveData
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.data.repository.photolist.PhotoListRepository
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class PhotoListPresenterTest {

    @Spy
    lateinit var repository: PhotoListRepository

    @Spy
    lateinit var compositeDisposable: CompositeDisposable

    @Spy
    lateinit var splashThread: SplashThread

    @Mock
    lateinit var viewModel: PhotoListViewModel

    @InjectMocks
    lateinit var presenter: PhotoListPresenter

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        `when`(splashThread.getScheduler())
                .thenReturn(Schedulers.trampoline())
    }

    @Test
    fun shouldFetchListOfPhotos() {

        `when`(repository.getListOfPhotos())
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(repository, times(1)).getListOfPhotos()
    }

    @Test
    fun shouldAddRequestToCompositeDisposable() {

        `when`(repository.getListOfPhotos())
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(compositeDisposable, times(1)).add(Mockito.any())
    }

    @Test
    fun shouldUseSchedulersInTheChain() {

        `when`(repository.getListOfPhotos())
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(splashThread, times(2)).getScheduler()
    }

    @Test
    fun shouldPassTheSuccessValueToViewModel() {

        val listOfPhotos = ArrayList<PhotoEntity>()
        `when`(repository.getListOfPhotos())
                .thenReturn(Single.just(listOfPhotos))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(viewModel, times(1)).accept(listOfPhotos)
    }

    @Test
    fun shouldClearCompositeDisposableWhenUnsubscribe() {

        presenter.unsubscribe()

        verify(compositeDisposable, times(1)).clear()
    }

}
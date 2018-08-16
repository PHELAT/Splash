package com.phelat.splash.presentation.photolist.presenter

import android.arch.lifecycle.MutableLiveData
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.data.repository.photolist.PhotoListRepository
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

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
    @Spy
    lateinit var presenter: PhotoListPresenter

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        `when`(splashThread.getScheduler())
                .thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `should set view when subscribed`() {

        val view = mock(PhotoListContract.View::class.java)

        presenter.subscribe(view)

        assertThat(presenter.view, equalTo(view))
    }

    @Test
    fun `should fetch list of photos when setUp`() {

        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(repository, times(1)).getListOfPhotos(Mockito.any())
    }

    @Test
    fun `should add fetch photos request to compositeDisposable`() {

        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(compositeDisposable, times(1)).add(Mockito.any())
    }

    @Test
    fun `should use schedulers in the chain`() {

        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(splashThread, times(2)).getScheduler()
    }

    @Test
    fun `should pass the success value to viewModel`() {

        val listOfPhotos = ArrayList<PhotoEntity>()
        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(listOfPhotos))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(viewModel, times(1)).accept(listOfPhotos)
    }

    @Test
    fun `should clear compositeDisposable when unsubscribe`() {

        presenter.unsubscribe()

        verify(compositeDisposable, times(1)).clear()
    }

    @Test
    fun `should request for new page when near to end of the recyclerView`() {

        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(ArrayList()))

        val view = mock(PhotoListContract.View::class.java)

        doReturn(24)
                .`when`(view)
                .getTotalItems()

        doReturn(23)
                .`when`(view)
                .getLastVisibleItem()

        presenter.subscribe(view)

        presenter.onPageScroll()

        verify(repository, times(1)).getListOfPhotos(Mockito.any())
    }

    @Test
    fun `should not request for new page when not near to end of the recyclerView`() {

        val view = mock(PhotoListContract.View::class.java)

        doReturn(24)
                .`when`(view)
                .getTotalItems()

        doReturn(18)
                .`when`(view)
                .getLastVisibleItem()

        presenter.subscribe(view)

        presenter.onPageScroll()

        verify(repository, never()).getListOfPhotos(Mockito.any())
    }

    @Test
    fun `should calculate distance to new page request gap when scrolling`() {

        val view = mock(PhotoListContract.View::class.java)

        doReturn(24)
                .`when`(view)
                .getTotalItems()

        doReturn(18)
                .`when`(view)
                .getLastVisibleItem()

        presenter.subscribe(view)

        presenter.onPageScroll()

        verify(view, times(1)).getLastVisibleItem()
        verify(view, times(1)).getTotalItems()
    }

}
package com.phelat.splash.presentation.photolist.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

@RunWith(PowerMockRunner::class)
class PhotoListPresenterTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PhotoListRepository

    @Mock
    lateinit var splashThread: SplashThread

    @Mock
    lateinit var viewModel: PhotoListViewModel

    @Spy
    val compositeDisposable = CompositeDisposable()

    lateinit var presenter: PhotoListPresenter

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        `when`(splashThread.getScheduler())
                .thenReturn(Schedulers.trampoline())

        presenter = PhotoListPresenter(repository,
                compositeDisposable,
                splashThread,
                splashThread)
    }

    @Test
    fun `should set view when subscribed`() {

        val view = mock(PhotoListContract.View::class.java)

        presenter.subscribe(view)

        assertThat(presenter.view, equalTo(view))
    }

    @Test
    fun `should fetch list of photos when setUp and viewModel has no data`() {

        `when`(repository.getListOfPhotos(Mockito.any()))
                .thenReturn(Single.just(ArrayList()))

        `when`(viewModel.photosObservable)
                .thenReturn(MutableLiveData())

        presenter.setUp(viewModel)

        verify(repository, times(1)).getListOfPhotos(Mockito.any())
    }

    @Test
    fun `should not fetch list of photos when viewModel already has data`() {

        val liveData = MutableLiveData<MutableList<PhotoEntity>>()
        liveData.value = ArrayList()

        `when`(viewModel.photosObservable)
                .thenReturn(liveData)

        presenter.setUp(viewModel)

        verify(repository, never()).getListOfPhotos(Mockito.any())
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

        presenter.viewModel = viewModel

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
    fun `should not request for new page when already requested for new page!`() {

        val view = mock(PhotoListContract.View::class.java)

        Whitebox.setInternalState(presenter, "isLoading", true)

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
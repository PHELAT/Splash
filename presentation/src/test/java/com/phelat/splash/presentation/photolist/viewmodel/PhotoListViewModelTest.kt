package com.phelat.splash.presentation.photolist.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.provider.EmptyPhotoEntityProvider
import com.phelat.splash.data.provider.base.Provider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoListViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MutableList<PhotoEntity>>

    @Spy
    lateinit var viewModel: PhotoListViewModel

    lateinit var emptyPhotoEntityProvider: Provider<PhotoEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        emptyPhotoEntityProvider = EmptyPhotoEntityProvider()
    }

    @Test
    fun `should update liveData when accepting list of photoEntities`() {

        viewModel.photosObservable.observeForever(observer)

        val listOfPhotos = ArrayList<PhotoEntity>()
        viewModel.accept(listOfPhotos)

        verify(observer, times(1)).onChanged(listOfPhotos)

    }

    @Test
    fun `should update liveData with previous + new photoEntities when accepting new data`() {

        viewModel.photosObservable.observeForever(observer)

        val listOfPhotos = arrayListOf(emptyPhotoEntityProvider.provide())
        viewModel.accept(listOfPhotos)

        viewModel.accept(arrayListOf(emptyPhotoEntityProvider.provide()))

        verify(observer, times(2)).onChanged(listOfPhotos)

    }

}
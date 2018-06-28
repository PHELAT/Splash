package com.phelat.splash.presentation.photolist.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.phelat.splash.data.entity.PhotoEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class PhotoListViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var viewModel: PhotoListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldUpdateLiveData() {

        val observer = (mock(Observer::class.java) as Observer<MutableList<PhotoEntity>>)
        viewModel.photosObservable.observeForever(observer)

        val listOfPhotos = ArrayList<PhotoEntity>()
        viewModel.accept(listOfPhotos)

        verify(observer, times(1)).onChanged(listOfPhotos)

    }

}
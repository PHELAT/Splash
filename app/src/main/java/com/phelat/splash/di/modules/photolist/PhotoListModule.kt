package com.phelat.splash.di.modules.photolist

import com.phelat.splash.data.datasource.DataSource
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.mapper.PhotoResponseToPhotoEntity
import com.phelat.splash.data.repository.photolist.PhotoListRepository
import com.phelat.splash.data.repository.photolist.PhotoListRepositoryImpl
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.di.modules.providers.ProvidersModule
import com.phelat.splash.di.scopes.ForActivity
import com.phelat.splash.presentation.executors.qualifiers.BackgroundThreadQ
import com.phelat.splash.presentation.executors.qualifiers.MainThreadQ
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.presenter.PhotoListPresenter
import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.datasource.GetPhotosRemoteDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module(includes = [ProvidersModule::class])
class PhotoListModule {

    @Provides
    @ForActivity
    fun providePhotoResponseToPhotoEntityMapper(): Mapper<PhotosResponse, PhotoEntity> {
        return PhotoResponseToPhotoEntity()
    }

    @Provides
    @ForActivity
    fun provideGetPhotoListRemoteDataSource(photosAPI: PhotosAPI)
            : DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>> {
        return GetPhotosRemoteDataSource(photosAPI)
    }

    @Provides
    @ForActivity
    fun providePhotoListRepository(remoteDataSource: DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>>,
                                   photoResponseToPhotoEntity: Mapper<PhotosResponse, PhotoEntity>)
            : PhotoListRepository {
        return PhotoListRepositoryImpl(remoteDataSource, photoResponseToPhotoEntity)
    }

    @Provides
    @ForActivity
    fun providePhotoListPresenter(repository: PhotoListRepository,
                                  compositeDisposable: CompositeDisposable,
                                  @MainThreadQ mainThread: SplashThread,
                                  @BackgroundThreadQ backgroundThread: SplashThread): PhotoListContract.Presenter {
        return PhotoListPresenter(repository, compositeDisposable, mainThread, backgroundThread)
    }

}
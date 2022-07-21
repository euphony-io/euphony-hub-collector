package co.jbear.euphony_hub.di

import android.app.Application
import android.content.Context
import co.jbear.euphony_hub.data.database.LocalDatabase
import co.jbear.euphony_hub.data.repository.DataRepository
import co.jbear.euphony_hub.data.repository.FakeDataRepository
import co.jbear.euphony_hub.data.repository.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceRepository(@ApplicationContext context: Context): PreferenceRepository {
        return PreferenceRepository(context)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(app: Application): LocalDatabase = LocalDatabase.getDatabase(app)

    @Provides
    @Singleton
    fun provideDataRepository(db: LocalDatabase): DataRepository = FakeDataRepository()
}
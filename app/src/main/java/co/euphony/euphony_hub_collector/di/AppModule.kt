package co.euphony.euphony_hub_collector.di

import android.app.Application
import android.content.Context
import co.euphony.euphony_hub_collector.data.database.LocalDatabase
import co.euphony.euphony_hub_collector.data.repository.DataRepository
import co.euphony.euphony_hub_collector.data.repository.FakeDataRepository
import co.euphony.euphony_hub_collector.data.repository.PreferenceRepository
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
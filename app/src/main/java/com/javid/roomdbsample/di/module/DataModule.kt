package com.javid.roomdbsample.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.javid.roomdbsample.room.MainDao
import com.javid.roomdbsample.room.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, roomCallback: RoomDatabase.Callback): MainDatabase {
        return Room.databaseBuilder(application.applicationContext,
            MainDatabase::class.java,
            "main_database")
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideRoomDatabaseCallback(): RoomDatabase.Callback {
        return object :RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }
    }

    @Provides
    fun provideMainDao(mainDatabase: MainDatabase): MainDao {
        return mainDatabase.mainDao()
    }

}
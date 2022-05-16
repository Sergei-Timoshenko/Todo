package dev.sergeitimoshenko.todo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sergeitimoshenko.todo.db.TaskDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(
        app: Application
    ) = Room.databaseBuilder(
        app,
        TaskDatabase::class.java,
        "todo_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.getTaskDao()
}
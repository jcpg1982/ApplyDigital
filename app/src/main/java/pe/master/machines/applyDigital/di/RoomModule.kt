package pe.master.machines.applyDigital.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.room.RoomDatabase.JournalMode
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.master.machines.applyDigital.data.dataBase.DataBase
import pe.master.machines.applyDigital.data.repository.RepositoryLocal
import pe.master.machines.applyDigital.helpers.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): DataBase =
        Room.databaseBuilder(context, DataBase::class.java, Constants.NAME_DATA_BASE)
            .addCallback(sRoomDatabaseCallback)
            .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
            .build()

    private val sRoomDatabaseCallback: Callback = object : Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }

    @Provides
    @Singleton
    fun providerRepositoryLocal(dataBase: DataBase) = RepositoryLocal(dataBase)
}
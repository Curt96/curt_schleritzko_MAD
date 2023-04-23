package com.example.learningDiary.DataRoom.Database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.learningDiary.DataRoom.DAOs.MovieDAO
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.utils.CustomConverter

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false, //bei true kann man die Datenbank exportieren lassen
)
@TypeConverters(CustomConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO

    //singelton Pattern
    companion object{ //wie statische variable
        @Volatile //damit keine Instanz von zwei verschiedenen Threads überschireben wird
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build() //erzeugt Instanz
                    .also {
                        Instance = it
                    }
            }
        }
    }
}
package com.example.shopping.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopping.entity.ProductEntity

@Database(entities = arrayOf(ProductEntity::class),version = 1,exportSchema = false)
abstract class ItemDatabase:RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{

        private var instance: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {
            if (instance == null) {
                synchronized(ItemDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java, "shopping_database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance as ItemDatabase
        }

    }

}
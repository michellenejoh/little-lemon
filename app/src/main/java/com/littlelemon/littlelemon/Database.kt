package com.littlelemon.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Delete

import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {

    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) = 0")
    suspend fun isEmpty(): Boolean

    @Insert
    suspend fun insertAll(vararg menuItems: MenuItemRoom): List<Long>

    @Delete
    suspend fun delete(menuItem: MenuItemRoom): Int
}



@Database(entities = [MenuItemRoom::class], version = 2,  exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        const val DATABASE_NAME = "little_lemon_db"
    }
}




package com.game.jumper.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.game.jumper.database.dao.HighScoreDao
import com.game.jumper.database.dao.PlayerDao
import com.game.jumper.database.dao.PowerUpDao
import com.game.jumper.database.entity.HighScore
import com.game.jumper.database.entity.Player
import com.game.jumper.database.entity.PowerUp


@Database(entities = [Player::class, HighScore::class, PowerUp::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JumperDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun highScoreDao(): HighScoreDao
    abstract fun powerUpDao(): PowerUpDao

    companion object {
        @Volatile
        private var INSTANCE: JumperDatabase? = null

        fun getDatabaseInstance(context: Context): JumperDatabase {
            synchronized(JumperDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        JumperDatabase::class.java,
                        "jumper_database"
                    ).build()
                }
                return INSTANCE as JumperDatabase
            }
        }
    }
}

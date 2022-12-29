package com.sijanneupane.mvvmnews.db

import android.content.Context
import androidx.room.*
import com.sijanneupane.mvvmnews.models.Article

/*
Database class for Room always need to be abstract
@Database annotation to let room know the class
pass parameter:
    List of entities, here we only have one single table: article

 */
@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)

abstract class ArticleDatabase : RoomDatabase(){

    //fungsi untuk mengembalikan ArticleDao
    abstract fun getArticleDao(): ArticleDao

    // objek pendamping untuk membuat database
    companion object{
        //Volatile sehingga utas lainnya dapat segera melihat saat utas mengubah instance ini
        @Volatile
        private var instance: ArticleDatabase? =null
        //Kunci variabel untuk menyinkronkan instance, untuk memastikan hanya ada satu instance ArticleDatabase sekaligus
        private val LOCK= Any()

        // fungsi operator dipanggil setiap kali kita membuat instance dari database kita
        //jika instance null, kami menyinkronkannya dengan LOCK:
        //LOCK: agar apa pun yang terjadi di dalam fungsi ini tidak dapat diakses oleh utas lainnya secara bersamaan

        //sinkronkan instance jika hanya nol
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            // centang nol "untuk memastikan bahwa tidak ada utas lain yang menyetel instance ke sesuatu saat kami sudah menyetelnya
            instance ?: createDatabase(context).also{ instance = it }

        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }
}
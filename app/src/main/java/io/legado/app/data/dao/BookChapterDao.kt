package io.legado.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.legado.app.data.entities.BookChapter

@Dao
interface BookChapterDao {

    @Query("select * from chapters")
    fun observeAll(): List<BookChapter>

    @Query("select * from chapters where bookUrl = :bookUrl order by `index`")
    fun observeByBook(bookUrl: String): LiveData<List<BookChapter>>

    @Query("SELECT * FROM chapters where bookUrl = :bookUrl and title like '%'||:key||'%' order by `index`")
    fun liveDataSearch(bookUrl: String, key: String): LiveData<List<BookChapter>>

    @Query("select * from chapters where bookUrl = :bookUrl order by `index`")
    fun getChapterList(bookUrl: String): List<BookChapter>

    @Query("select * from chapters where bookUrl = :bookUrl and `index` >= :start and `index` <= :end order by `index`")
    fun getChapterList(bookUrl: String, start: Int, end: Int): List<BookChapter>

    @Query("select * from chapters where bookUrl = :bookUrl and `index` = :index")
    fun getChapter(bookUrl: String, index: Int): BookChapter?

    @Query("select * from chapters where bookUrl = :bookUrl and `title` = :title")
    fun getChapter(bookUrl: String, title: String): BookChapter?

    @Query("select count(url) from chapters where bookUrl = :bookUrl")
    fun getChapterCount(bookUrl: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg bookChapter: BookChapter)

    @Query("delete from chapters where bookUrl = :bookUrl")
    fun delByBook(bookUrl: String)

}
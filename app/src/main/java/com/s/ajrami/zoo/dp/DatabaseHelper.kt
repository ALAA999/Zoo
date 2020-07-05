package com.s.ajrami.zoo.dp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.s.ajrami.zoo.model.Animal
import com.s.ajrami.zoo.model.Bird
import com.s.ajrami.zoo.model.Fish

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    private var db: SQLiteDatabase

    init {
        db = writableDatabase
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Animal.CREATE_TABLE)
        p0.execSQL(Bird.CREATE_TABLE)
        p0.execSQL(Fish.CREATE_TABLE)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Animal.TABLE_NAME}")
        onCreate(p0)
        p0.execSQL("DROP TABLE IF EXISTS ${Bird.TABLE_NAME}")
        onCreate(p0)
        p0.execSQL("DROP TABLE IF EXISTS ${Fish.TABLE_NAME}")
        onCreate(p0)
    }
    //==================================================================

    fun addanimal(name: String, image: String): Boolean {
        val cv = ContentValues()
        cv.put(Animal.COL_NAME, name)
        cv.put(Animal.COL_IMG, image)
        return db.insert(Animal.TABLE_NAME, null, cv) > 0
    }

    fun getAllanimals(): ArrayList<Animal> {
        val animals = ArrayList<Animal>()
        val c =
            db.rawQuery("select * from ${Animal.TABLE_NAME} order by ${Animal.COL_ID} DESC", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val animal = Animal(c.getInt(0), c.getString(1), c.getString(2))

            animals.add(animal)
            c.moveToNext()
        }
        c.close()
        return animals
    }

    fun deleteanimal(id: Int): Boolean {
        return db.delete(Animal.TABLE_NAME, "id = $id", null) > 0

    }
    fun updateAnimal(oldId: Int, name: String, image: String): Boolean {
        val cv = ContentValues()
        cv.put(Animal.COL_NAME, name)
        cv.put(Animal.COL_IMG, image)
        return db.update(Animal.TABLE_NAME, cv, "id = $oldId", null) > 0
    }


    fun addbird(name: String, image: String): Boolean {
        val cv = ContentValues()
        cv.put(Bird.COL_NAME, name)
        cv.put(Bird.COL_IMG, image)
        return db.insert(Bird.TABLE_NAME, null, cv) > 0
    }

    fun getAllbirds(): ArrayList<Bird> {
        val birds = ArrayList<Bird>()
        val c =
            db.rawQuery("select * from ${Bird.TABLE_NAME} order by ${Bird.COL_ID} DESC", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val bird = Bird(c.getInt(0), c.getString(1), c.getString(2))

            birds.add(bird)
            c.moveToNext()
        }
        c.close()
        return birds
    }

    fun deletebird(id: Int): Boolean {
        return db.delete(Bird.TABLE_NAME, "id = $id", null) > 0

    }
    fun updateBird(oldId: Int, name: String, image:String): Boolean {
        val cv = ContentValues()
        cv.put(Bird.COL_NAME, name)
        cv.put(Bird.COL_IMG, image)

        return db.update(Bird.TABLE_NAME, cv, "id = $oldId", null) > 0
    }



    fun addfish(name: String, image: String): Boolean {
        val cv = ContentValues()
        cv.put(Fish.COL_NAME, name)
        cv.put(Fish.COL_IMG, image)
        return db.insert(Fish.TABLE_NAME, null, cv) > 0
    }

    fun getAllfish(): ArrayList<Fish> {
        val fishs = ArrayList<Fish>()
        val c =
            db.rawQuery("select * from ${Fish.TABLE_NAME} order by ${Fish.COL_ID} DESC", null)

        c.moveToFirst()
        while (!c.isAfterLast) {
            val fish= Fish(c.getInt(0), c.getString(1), c.getString(2))

            fishs.add(fish)
            c.moveToNext()
        }
        c.close()
        return fishs
    }

    fun deletefish(id: Int): Boolean {
        return db.delete(Fish.TABLE_NAME, "id = $id", null) > 0

    }
    fun updateFish(oldId: Int, name_fish: String, image_fish: String): Boolean {
        val cv = ContentValues()
        cv.put(Fish.COL_NAME, name_fish)
        cv.put(Fish.COL_IMG, image_fish)

        return db.update(Fish.TABLE_NAME, cv, "id = $oldId", null) > 0
    }


    companion object {
        const val DATABASE_NAME = "Zoo"
        const val DATABASE_VERSION = 1

    }
}

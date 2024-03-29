package com.norbertoledo.pac_desarrollo_m08;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    // Decalraciones
    SQLiteDatabase db;


    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Validar y retornar si existe creada la tabla
    public boolean checkTableExist(){

        boolean isExist = false;
        db = getReadableDatabase();
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='users' COLLATE NOCASE";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;

    }

    // Crear la tabla 'users'
    public void createTable(){

        db = getWritableDatabase ();
        String table = "CREATE TABLE users"+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "surname TEXT,"+
                "phone TEXT,"+
                "gender TEXT"+
                ")";
        db.execSQL(table);
    }

    // Eliminar la tabla 'users'
    public void deleteTable(){

        db = getWritableDatabase ();
        db.execSQL ("drop table users");
        db.close ();

    }

    // Insertar los datos en la tabla
    public boolean setData(String name, String surname, String phone, String gender){

        // INSERT UPDATE DELETE
        SQLiteDatabase writer = getWritableDatabase();

        String insert = "INSERT INTO users (name,surname,phone, gender)"+
                "VALUES(\""+name+"\",\""+surname+"\",\""+phone+"\",\""+gender+"\")";

        writer.execSQL(insert);
        writer.close();
        return true;
    }

    // Obtener y retornar el listado de registro de la tabla
    public Cursor getData(){

        SQLiteDatabase reader = getReadableDatabase();
        Cursor data = reader.rawQuery("SELECT * FROM users ORDER BY ID DESC", null);

        return data;
    }

    // Eliminar todos los registros de la tabla
    public boolean deleteData(){
        SQLiteDatabase writer = getWritableDatabase();
        writer.execSQL("DELETE FROM users");
        writer.close();
        return true;
    }


}

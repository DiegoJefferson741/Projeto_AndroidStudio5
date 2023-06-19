package com.example.taglineher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelperPaciente extends SQLiteOpenHelper{

    public static final String DBNAME = "register.db";

    public DBHelperPaciente(Context context){
        super(context, "register.db",  null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table paciente(codigo TEXT primary key, nome TEXT, aniversario TEXT, cidade TEXT, rua TEXT, numero TEXT, telefone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1 ) {

        MyDB.execSQL("drop Table if exists paciente");

    }

    public Boolean insertData(String codigo, String nome, String aniversario, String cidade, String rua , String numero, String telefone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo",codigo);
        contentValues.put("nome",nome);
        contentValues.put("aniversario",aniversario);
        contentValues.put("cidade",cidade);
        contentValues.put("rua",rua);
        contentValues.put("numero",numero);
        contentValues.put("telefone",telefone);
        long result = MyDB.insert("paciente",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean check(String codigo){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from paciente where codigo = ?",new String[]{codigo});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }


}
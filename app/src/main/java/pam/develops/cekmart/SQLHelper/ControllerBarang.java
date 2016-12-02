package pam.develops.cekmart.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.Market;

/**
 * Created by paulms on 5/15/2016.
 */
public class ControllerBarang {
    DBHelper dbHelper;
    SQLiteDatabase database;

    public static final String TABLE_NAME = "barang";
    public static final String ID_BARANG = "id_barang";
    public static final String JENIS_BARANG = "jenis_barang";
    public static final String URL = "url";

    public static final String CREATE_BARANG = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ID_BARANG+" integer primary key, "+ JENIS_BARANG +" TEXT, "+ URL +" TEXT)";

    private String[] TABLE_COLUMNS = {ID_BARANG, JENIS_BARANG, URL};

    public ControllerBarang(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteData (){
        database.delete(TABLE_NAME, null, null);
    }

    public void insertData(int id, String barang, String url) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_BARANG, id);
        contentValues.put(JENIS_BARANG, barang);
        contentValues.put(URL, url);
        database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Barang> getData() {
        ArrayList<Barang> allData = new ArrayList<Barang>();
        Cursor cursor = null;

        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_BARANG + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    public ArrayList<Barang> getTipeBarang(int jenis) {
        String[] id_jenis = {String.valueOf(jenis)};
        ArrayList<Barang> allData = new ArrayList<Barang>();
        Cursor cursor = null;
        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, ID_BARANG + " like " + jenis, null, null, null, ID_BARANG + " ASC");
        //cursor = database.query(RESEP_TABLE,resepColums,null,null,null, RESEP_JENISID +" = "+jenis, RESEP_ID + " ASC");
        //cursor = database.query(RESEP_TABLE, resepColums, null, null, null, null, RESEP_ID + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    private Barang parseData(Cursor cursor){
        Barang curData = new Barang();

        curData.setId_barang(cursor.getInt(0));
        curData.setJenis_barang(cursor.getString(1));
        curData.setUrl(cursor.getString(2));
        return curData;
    }
}

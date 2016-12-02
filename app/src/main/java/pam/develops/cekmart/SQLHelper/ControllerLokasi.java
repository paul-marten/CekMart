package pam.develops.cekmart.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pam.develops.cekmart.Model.Lokasi;
import pam.develops.cekmart.Model.Market;

/**
 * Created by paulms on 5/15/2016.
 */
public class ControllerLokasi {
    DBHelper dbHelper;
    SQLiteDatabase database;

    public static final String TABLE_NAME = "lokasi";
    public static final String ID_LOKASI = "id_lokasi";
    public static final String NAMA_LOKASI = "nama_lokasi";
    public static final String KOORDINAT_X = "koordinat_X";
    public static final String KOORDINAT_Y = "koordinat_Y";

    public static final String CREATE_MARKET = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ID_LOKASI+" integer primary key, "+ NAMA_LOKASI +" TEXT, " + KOORDINAT_X +" TEXT , " + KOORDINAT_Y +" TEXT)";

    private String[] TABLE_COLUMNS = {ID_LOKASI, NAMA_LOKASI, KOORDINAT_X, KOORDINAT_Y};

    public ControllerLokasi(Context context){
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

    public void insertData(int id, String nama, String x , String y) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_LOKASI, id);
        contentValues.put(NAMA_LOKASI, nama);
        contentValues.put(KOORDINAT_X, x);
        contentValues.put(KOORDINAT_Y, y);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Lokasi> getData() {
        ArrayList<Lokasi> allData = new ArrayList<Lokasi>();
        Cursor cursor = null;

        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_LOKASI + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    private Lokasi parseData(Cursor cursor){
        Lokasi curData = new Lokasi();

        curData.setId_lokasi(cursor.getInt(0));
        curData.setNama_lokasi(cursor.getString(1));
        curData.setKoordinat_X(cursor.getString(2));
        curData.setKoordinat_Y(cursor.getString(3));

        return curData;
    }


}

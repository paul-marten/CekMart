package pam.develops.cekmart.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.Market;

/**
 * Created by paulms on 5/12/2016.
 */
public class ControllerMarket {
    DBHelper dbHelper;
    SQLiteDatabase database;

    public static final String TABLE_NAME = "market";
    public static final String ID_MARKET = "id_market";
    public static final String NAMA_MARKET = "nama_market";
    public static final String URL = "url";

    public static final String CREATE_MARKET = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ID_MARKET+" integer primary key, "+ NAMA_MARKET +" TEXT, " + URL +" TEXT)";

    private String[] TABLE_COLUMNS = {ID_MARKET, NAMA_MARKET, URL};

//    //Constructor
//    public DBController(Context context) {
//        dbHelper = new DBHelper(context);
//    }

    public ControllerMarket(Context context){
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

    public void insertData(int id, String nama, String url_gambar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_MARKET, id);
        contentValues.put(NAMA_MARKET, nama);
        contentValues.put(URL, url_gambar);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Market> getData() {
        ArrayList<Market> allData = new ArrayList<Market>();
        Cursor cursor = null;

        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID_MARKET + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    public ArrayList<Market> getTipeMarket(int jenis) {
        String[] id_jenis = {String.valueOf(jenis)};
        ArrayList<Market> allData = new ArrayList<Market>();
        Cursor cursor = null;
        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, ID_MARKET + " like " + jenis, null, null, null, ID_MARKET + " ASC");
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

//    public ArrayList<Market> getDatabyTipe(String tipeResep) {
//        ArrayList<Market> allData = new ArrayList<Market>();
//        Cursor cursor = null;
//
//        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, TIPE + "=?", new String[] { tipeResep }, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            allData.add(parseData(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return allData;
//    }

    private Market parseData(Cursor cursor){
        Market curData = new Market();

        curData.setId_market(cursor.getInt(0));
        curData.setNama_market(cursor.getString(1));
        curData.setUrl(cursor.getString(2));

        return curData;
    }
}

package pam.develops.cekmart.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.Model.Market;

/**
 * Created by paulms on 5/13/2016.
 */
public class ControllerDaftarBarang {
    DBHelper dbHelper;
    SQLiteDatabase database;

    public static final String TABLE_NAME = "daftarbarang";
    public static final String ID = "id";
    public static final String NAMA_BARANG = "nama_barang";
    public static final String JENIS_BARANG = "jenis_barang";
    public static final String NAMA_MARKET = "nama_market";
    public static final String NAMA_LOKASI = "nama_lokasi";
    public static final String HARGA_BARANG = "harga_barang";
    public static final String DESKRIPSI = "deskripsi";

    public static final String CREATE_DAFTARBARANG = "CREATE TABLE "+TABLE_NAME+" "+
            "("+ ID +" integer primary key, "+ NAMA_BARANG +" TEXT, " + JENIS_BARANG+" INTEGER, " + NAMA_MARKET + " INTEGER, " + NAMA_LOKASI + " INTEGER, " + HARGA_BARANG + " TEXT, "+ DESKRIPSI +" TEXT)";

    private String[] TABLE_COLUMNS = {ID, NAMA_BARANG, JENIS_BARANG, NAMA_MARKET ,NAMA_LOKASI , HARGA_BARANG , DESKRIPSI};

    //Constructor
    public ControllerDaftarBarang(Context context) {
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

    public void insertData(String nama_barang, String jenis_barang , String nama_market , String nama_lokasi , String harga_barang , String deskripsi ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_BARANG, nama_barang);
        contentValues.put(JENIS_BARANG, jenis_barang);
        contentValues.put(NAMA_MARKET, nama_market);
        contentValues.put(NAMA_LOKASI, nama_lokasi);
        contentValues.put(HARGA_BARANG, harga_barang);
        contentValues.put(DESKRIPSI, deskripsi);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<DaftarBarang> getDataDaftarBarang() {
        ArrayList<DaftarBarang> allData = new ArrayList<DaftarBarang>();
        Cursor cursor = null;

        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null, null, null, ID + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }

    public ArrayList<DaftarBarang> getBarangByTipe(String market , String barang) {
        String[] id_jenis = {String.valueOf(market)};
        ArrayList<DaftarBarang> allData = new ArrayList<DaftarBarang>();
        Cursor cursor = null;
//        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, JENIS_BARANG+ " like " + "Beras Merah", null, null, null, ID + " ASC");
        cursor = database.rawQuery("select * from daftarbarang where jenis_barang = '"+ barang +"' and nama_market = '"+market+"' order by harga_barang ASC ",null);
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

    public ArrayList<DaftarBarang> getAllBarang(String barang) {
        String[] id_jenis = {String.valueOf(barang)};
        ArrayList<DaftarBarang> allData = new ArrayList<DaftarBarang>();
        Cursor cursor = null;
//        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, JENIS_BARANG+ " like " + "Beras Merah", null, null, null, ID + " ASC");
        cursor = database.rawQuery("select * from daftarbarang where jenis_barang = '"+ barang +"' order by harga_barang asc",null);
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

    public ArrayList<DaftarBarang> getSearchBarang(String barang) {
        String[] id_jenis = {String.valueOf(barang)};
        ArrayList<DaftarBarang> allData = new ArrayList<DaftarBarang>();
        Cursor cursor = null;
//        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, JENIS_BARANG+ " like " + "Beras Merah", null, null, null, ID + " ASC");
        cursor = database.rawQuery("select * from daftarbarang where jenis_barang like '"+ barang +"'",null);
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

    private DaftarBarang parseData(Cursor cursor){
        DaftarBarang curData = new DaftarBarang();

        curData.setId_daftarbarang(cursor.getInt(0));
        curData.setNama_barang(cursor.getString(1));
        curData.setJenis_barang(cursor.getString(2));
        curData.setNama_market(cursor.getString(3));
        curData.setNama_lokasi(cursor.getString(4));
        curData.setHarga_barang(cursor.getString(5));
        curData.setDeskripsi(cursor.getString(6));

        return curData;
    }
}

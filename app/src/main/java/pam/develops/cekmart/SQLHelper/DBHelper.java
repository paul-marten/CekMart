package pam.develops.cekmart.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NahduKesaba on 12/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "cekmart.db";
    //ganti nilai DBVERSION jika ingin mengupdate database
    private static final int DBVERSION = 1;
    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ControllerMarket.CREATE_MARKET);
//        db.execSQL(ControllerMarket.CREATE_KOMEN);
        db.execSQL(ControllerBarang.CREATE_BARANG);
        db.execSQL(ControllerDaftarBarang.CREATE_DAFTARBARANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DATABASE_ALTER adalah variable yang menampung query untuk alter table
        //seperti ContactController.CREATE_CONTACT
        //Kosongkan onUpgrade jika tidak ingin melakukan perubahan pada struktur table
        if (oldVersion < 2) {
            //db.execSQL(DATABASE_ALTER_1);
        } else if (oldVersion < 3) {
            //db.execSQL(DATABASE_ALTER_2);
        }
    }
}

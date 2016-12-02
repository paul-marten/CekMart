package pam.develops.cekmart.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pam.develops.cekmart.Adapter.DaftarBarangAdapter;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;
import pam.develops.cekmart.SQLHelper.ControllerDaftarBarang;
import pam.develops.cekmart.SQLHelper.ControllerMarket;

public class BarangByMarketActivity extends AppCompatActivity {

    ControllerDaftarBarang controller;
    ControllerMarket mController;
    ControllerBarang bController;

    ListView listView;
    private ArrayList<DaftarBarang> barang;
    ActionBar actionBar;


    public static final String NAMA_BARANG = "nama_barang";
    public static final String JENIS_BARANG = "jenis_barang";
    public static final String NAMA_MARKET = "nama_market";
    public static final String URL_MARKET = "url_market";
    public static final String URL_BARANG = "url_barang";
    public static final String NAMA_LOKASI = "nama_lokasi";
    public static final String HARGA_BARANG = "harga_barang";
    public static final String DESKRIPSI = "deskripsi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_by_market);

        //tombol back
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listBarangByMarket);

        controller = new ControllerDaftarBarang(getApplicationContext());
        //Calling the method that will fetch data

//        getDetailDaftarBarang();
        getDetailSatuBarang();

        Intent intent = getIntent();
        //String inputan
        final String url_market = intent.getStringExtra(DetailMarketActivity.MARKET_URL);
        final String url_barang = intent.getStringExtra(DetailMarketActivity.BARANG_URL);
        final String jenis_barang = intent.getStringExtra(DetailMarketActivity.JENIS_BARANG);
        final String nama_market = intent.getStringExtra(DetailMarketActivity.NAMA_MARKET);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DaftarBarang barang = (DaftarBarang) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), "Londam", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), barang.getDeskripsi(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailBarangActivity.class);

                //Adding book details to intent
                intent.putExtra(NAMA_BARANG , barang.getNama_barang());
                intent.putExtra(JENIS_BARANG ,jenis_barang);
                intent.putExtra(NAMA_MARKET, nama_market);
                intent.putExtra(URL_MARKET, url_market);
                intent.putExtra(URL_BARANG , url_barang);
                intent.putExtra(NAMA_LOKASI , barang.getNama_lokasi());
                intent.putExtra(HARGA_BARANG , barang.getHarga_barang());
                intent.putExtra(DESKRIPSI , barang.getDeskripsi());
                startActivity(intent);
            }
        });
    }

    private void getDetailDaftarBarang() {
        //While the app fetched data we are displaying a progress dialog
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra(DetailMarketActivity.JENIS_BARANG), Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        barang = controller.getDataDaftarBarang();
        loading.dismiss();
        showList();
    }

    private void getDetailSatuBarang() {
        //While the app fetched data we are displaying a progress dialog
        Intent intent = getIntent();
        String jbarang = intent.getStringExtra(DetailMarketActivity.JENIS_BARANG);
        String nmarket = intent.getStringExtra(DetailMarketActivity.NAMA_MARKET);

        Toast.makeText(getApplicationContext(), intent.getStringExtra(DetailMarketActivity.JENIS_BARANG) , Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        barang = controller.getBarangByTipe(nmarket , jbarang);
        loading.dismiss();
        showList();
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[barang.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<barang.size(); i++){
            //Storing names to string array
            items[i] = barang.get(i).getNama_barang();
        }
        DaftarBarangAdapter adapter = new DaftarBarangAdapter(getApplicationContext(), barang);
        //Creating an array adapter for list view
        //ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_list,items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(android.R.id.home == item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

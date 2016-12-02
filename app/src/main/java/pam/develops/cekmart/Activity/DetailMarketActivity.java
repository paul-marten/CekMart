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

import pam.develops.cekmart.Adapter.BarangAdapter;
import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;

public class DetailMarketActivity extends AppCompatActivity {

    ControllerBarang controller;
    ListView listView;
    private ArrayList<Barang> barang;
    ActionBar actionBar;

    //Strings to bind with intent will be used to send data to other activity
    public static final String ID_BARANG = "id_barang";
    public static final String ID_MARKET2 = "market_id";
    public static final String JENIS_BARANG = "jenis_barang";
    public static final String BARANG_URL = "barang_url";
    public static final String NAMA_MARKET = "nama_market";
    public static final String MARKET_URL ="market_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_market);
        //tombol back
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBarang);

        controller = new ControllerBarang(getApplicationContext());
        //Calling the method that will fetch data

        Intent intent = getIntent();
        final String nama = intent.getStringExtra(MainActivity.NAMA_MARKET);
        final String url = intent.getStringExtra(MainActivity.MARKET_URL);
        getDetailMarket();
        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barang barang = (Barang) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), barang.getJenis_barang(), Toast.LENGTH_SHORT).show();
                //Creating an intent
                Intent intent = new Intent(getApplicationContext(), BarangByMarketActivity.class);

                //Adding book details to intent
                intent.putExtra(NAMA_MARKET , nama);
                intent.putExtra(MARKET_URL , url);
                intent.putExtra(ID_BARANG, barang.getId_barang());
                intent.putExtra(JENIS_BARANG, barang.getJenis_barang());
                intent.putExtra(BARANG_URL, barang.getUrl());
                startActivity(intent);

            }
        });
    }

    private void getDetailMarket() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        barang = controller.getData();
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
            items[i] = barang.get(i).getJenis_barang();
        }
        BarangAdapter adapter = new BarangAdapter(getApplicationContext(), barang);
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

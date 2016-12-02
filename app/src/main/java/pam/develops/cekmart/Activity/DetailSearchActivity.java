package pam.develops.cekmart.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pam.develops.cekmart.Adapter.DaftarBarangAdapter;
import pam.develops.cekmart.Adapter.DetailSearchAdapter;
import pam.develops.cekmart.Adapter.SearchAdapter;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;
import pam.develops.cekmart.SQLHelper.ControllerDaftarBarang;

public class DetailSearchActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextView jenisBarang;
    ListView listView;
    ControllerDaftarBarang controller;
    private ArrayList<DaftarBarang> barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_search);

        //tombol back
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // deklarasi barang
        jenisBarang = (TextView) findViewById(R.id.txtJenis);
        jenisBarang.setText(getIntent().getStringExtra(SearchActivity.JENIS_BARANG));

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewSearch);

        controller = new ControllerDaftarBarang(getApplicationContext());
        //Calling the method that will fetch data

        Intent intent = getIntent();
        if(getIntent().getStringExtra(SearchActivity.STATUS).equals("no")) {
            getAllBarang();
        }
        if(getIntent().getStringExtra(SearchActivity.STATUS).equals("yes")){
            getSearchBarang(getIntent().getStringExtra(SearchActivity.JENIS_BARANG));
        }
    }

    private void getAllBarang() {
        //While the app fetched data we are displaying a progress dialog
        Intent intent = getIntent();
        String jbarang = intent.getStringExtra(SearchActivity.JENIS_BARANG);

        Toast.makeText(getApplicationContext(), intent.getStringExtra(DetailMarketActivity.JENIS_BARANG) , Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        barang = controller.getAllBarang(jbarang);
        loading.dismiss();
        showList();
    }

    private void getSearchBarang(String jbarang) {
        //While the app fetched data we are displaying a progress dialog
//        Intent intent = getIntent();
//        String jbarang = intent.getStringExtra(SearchActivity.JENIS_BARANG);
//
//        Toast.makeText(getApplicationContext(), intent.getStringExtra(DetailMarketActivity.JENIS_BARANG) , Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        barang = controller.getAllBarang(jbarang);
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
        DetailSearchAdapter adapter = new DetailSearchAdapter(getApplicationContext(), barang);
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

package pam.develops.cekmart.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pam.develops.cekmart.Adapter.BarangAdapter;
import pam.develops.cekmart.Adapter.SearchAdapter;
import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;

public class SearchActivity extends AppCompatActivity {

    ControllerBarang controller;
    ListView listView;
    private ArrayList<Barang> barang;
    ActionBar actionBar;
    Button button;

    //Strings to bind with intent will be used to send data to other activity
    public static final String ID_BARANG = "id_barang";
    public static final String ID_MARKET2 = "market_id";
    public static final String JENIS_BARANG = "jenis_barang";
    public static final String BARANG_URL = "barang_url";
    public static final String STATUS = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBarangSearch);

        controller = new ControllerBarang(getApplicationContext());

        //Initialing the button
        button = (Button) findViewById(R.id.buttonSearch);

        //initial inputan
        final EditText search = (EditText) findViewById(R.id.editTextSearch);

        //Calling the method that will fetch data
        getDetailMarket();
        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barang barang = (Barang) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), barang.getJenis_barang(), Toast.LENGTH_SHORT).show();
                //Creating an intent
                Intent intent = new Intent(getApplicationContext(), DetailSearchActivity.class);

                //Adding thing details to intent
                intent.putExtra(ID_BARANG, barang.getId_barang());
                intent.putExtra(JENIS_BARANG, barang.getJenis_barang());
                intent.putExtra(BARANG_URL, barang.getUrl());
                intent.putExtra(STATUS , "no");
                startActivity(intent);

            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            String searchbarang = search.getText().toString();
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext() , DetailSearchActivity.class);
//                intent.putExtra(STATUS , "yes");
//                intent.putExtra(JENIS_BARANG , searchbarang);
//                startActivity(intent);
//            }
//        });


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
        SearchAdapter adapter = new SearchAdapter(getApplicationContext(), barang);
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

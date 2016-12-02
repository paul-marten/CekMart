package pam.develops.cekmart.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import pam.develops.cekmart.Adapter.MarketAdapter;
import pam.develops.cekmart.Fragment.ExitDialogFragment;
import pam.develops.cekmart.Model.Market;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerMarket;

public class MainActivity extends AppCompatActivity {
    MarketAdapter madapter;
    ControllerMarket controller;
    ListView listView;
    private ArrayList<Market> market;

    //Strings to bind with intent will be used to send data to other activity
    public static final String ID_MARKET = "id_market";
    public static final String NAMA_MARKET = "nama_market";
    public static final String MARKET_URL = "market_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewMarket);

        controller = new ControllerMarket(getApplicationContext());
        //Calling the method that will fetch data
        getMarket();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Getting the requested book from the list
                Market market = (Market) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), market.getNama_market(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),market.getId_market(), Toast.LENGTH_LONG).show();
                //Creating an intent
                Intent intent = new Intent(getApplicationContext(), DetailMarketActivity.class);
                //Adding book details to intent
                intent.putExtra(ID_MARKET, market.getId_market());
                intent.putExtra(NAMA_MARKET, market.getNama_market());
                intent.putExtra(MARKET_URL, market.getUrl());
                startActivity(intent);

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainActivity.this , SearchActivity.class);
                startActivity(search);
            }
        });
    }


    private void getMarket() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        controller.open();
        market = controller.getData();
        loading.dismiss();
        showList();
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[market.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<market.size(); i++){
            //Storing names to string array
            items[i] = market.get(i).getNama_market();
        }
        MarketAdapter adapter = new MarketAdapter(getApplicationContext(), market);
        //Creating an array adapter for list view
        //ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_list,items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
        switch (id) {
            case R.id.logout:
                Intent login= new Intent(this,LoginActivity.class);
                LoginManager.getInstance().logOut();
                startActivity(login);
                finish();
                //Toast. makeText(getApplicationContext(), "About menu item pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                ExitDialogFragment exit = new ExitDialogFragment();
                exit.show(getFragmentManager(), "exit");
                //Toast. makeText(getApplicationContext(), "Help menu item pressed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected( item);
    }
}

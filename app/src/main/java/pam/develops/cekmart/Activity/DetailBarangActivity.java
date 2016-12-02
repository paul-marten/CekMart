package pam.develops.cekmart.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import pam.develops.cekmart.API.RESTClient;
import pam.develops.cekmart.R;

public class DetailBarangActivity extends AppCompatActivity {
    Context context;
    ImageView gambar ;
    TextView txtNama;
    TextView txtHarga;
    TextView txtDeskripsi;
    TextView txtLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        gambar = (ImageView) findViewById(R.id.imgGambar);
        txtNama = (TextView) findViewById(R.id.txtNamaBarang);
        txtHarga = (TextView) findViewById(R.id.txtHarga);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        txtLokasi = (TextView) findViewById(R.id.txtLokasi);

        //Getting intent
        Intent intent = getIntent();

        Picasso.with(context).load(RESTClient.getURL_ResepMakananAPI() + intent.getStringExtra(BarangByMarketActivity.URL_BARANG)).into(gambar);
        txtNama.setText(intent.getStringExtra(BarangByMarketActivity.NAMA_BARANG));
        txtHarga.setText( "Rp."+intent.getStringExtra(BarangByMarketActivity.HARGA_BARANG));
        txtDeskripsi.setText(intent.getStringExtra(BarangByMarketActivity.DESKRIPSI));
        txtLokasi.setText(intent.getStringExtra(BarangByMarketActivity.NAMA_MARKET)+" - "+intent.getStringExtra(BarangByMarketActivity.NAMA_LOKASI));

    }
}

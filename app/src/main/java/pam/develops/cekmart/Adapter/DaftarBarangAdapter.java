package pam.develops.cekmart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pam.develops.cekmart.API.RESTClient;
import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;
import pam.develops.cekmart.SQLHelper.ControllerMarket;

/**
 * Created by paulms on 5/17/2016.
 */
public class DaftarBarangAdapter extends ArrayAdapter<DaftarBarang> {
    Context context;
    ArrayList<DaftarBarang> daftarbarangs;

    static class ViewHolder {
        public TextView nama_barang_detail;
        public TextView nama_barang;
        public TextView nama_market;
        public TextView nama_lokasi;
        public TextView harga_barang;
        public TextView deskripsi;
    }

    public DaftarBarangAdapter(Context context, ArrayList<DaftarBarang> daftarbarangs) {
        super(context, R.layout.singleitem_list_daftarbarang, daftarbarangs);
        this.context = context;
        //imgLoader = new ImageLoader(context);
//        imgLoader = ImageLoader.getInstance();
        this.daftarbarangs = daftarbarangs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DaftarBarang dbarang = getItem(position);
        Log.d("Paul test" , dbarang.getNama_barang());
        Log.d("Paul test" , dbarang.getHarga_barang());
        ViewHolder viewHolder;
        if (convertView == null) {
            //membuat baru item
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater. from(getContext());
            convertView = inflater.inflate(R.layout.singleitem_list_daftarbarang, parent,false);
//            viewHolder.nama_barang = (TextView)convertView.findViewById(R.id.textNamaBarang);
            viewHolder.nama_barang_detail = (TextView)convertView.findViewById(R.id.textJenisBarang);
//            viewHolder.nama_market = (TextView)convertView.findViewById(R.id.textMarketBarang);
//            viewHolder.nama_lokasi = (TextView)convertView.findViewById(R.id.textMarketBarang);
            viewHolder.harga_barang = (TextView)convertView.findViewById(R.id.textHarga);
            viewHolder.deskripsi = (TextView)convertView.findViewById(R.id.textDeskripsi);
            convertView.setTag(viewHolder);
        } else {
            //menggunakan item yang sudah pernah dibuat
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Set item dengan value dari objek
//        viewHolder. name.setText(produk.getNama());
//        viewHolder. price.setText(produk.getAsal());
//        String url = RESTClient.getURL_ResepMakananAPI() + market.getUrl();
        //imgLoader.DisplayImage(url, viewHolder.gambar);
        //imgLoader.displayImage(url, viewHolder.gambar);
        //viewHolder.gambar.setImageResource(R.drawable.notif);
//        Picasso.with(context).load(url).into(viewHolder.gambar);
        viewHolder.nama_barang_detail.setText(dbarang.getJenis_barang());
//        viewHolder.nama_barang.setText(controllerBarang.getTipeBarang(dbarang.getId_barang()).indexOf(1));
//        viewHolder.nama_market.setText(controllerMarket.getTipeMarket(dbarang.getId_market()).indexOf(1));
//        viewHolder.nama_barang.setText(dbarang.getId_barang());
//        viewHolder.nama_lokasi.setText(dbarang.getId_lokasi());
//        viewHolder.nama_market.setText(dbarang.getId_market());
        viewHolder.harga_barang.setText(dbarang.getHarga_barang());
        viewHolder.deskripsi.setText(dbarang.getDeskripsi());
        return convertView;
    }
}

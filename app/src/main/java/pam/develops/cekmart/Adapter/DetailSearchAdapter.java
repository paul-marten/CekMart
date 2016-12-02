package pam.develops.cekmart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.R;

/**
 * Created by paulms on 5/23/2016.
 */
public class DetailSearchAdapter extends ArrayAdapter<DaftarBarang> {
    Context context;
    ArrayList<DaftarBarang> daftarbarangs;

    static class ViewHolder {
        public TextView nama_barang;
        public TextView nama_lokasi;
        public TextView harga_barang;
        public TextView deskripsi;
    }

    public DetailSearchAdapter(Context context, ArrayList<DaftarBarang> daftarbarangs) {
        super(context, R.layout.single_list_all_barang, daftarbarangs);
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
            convertView = inflater.inflate(R.layout.single_list_all_barang, parent,false);
            viewHolder.nama_lokasi = (TextView)convertView.findViewById(R.id.txtSLokasi);
            viewHolder.nama_barang = (TextView)convertView.findViewById(R.id.txtSBarang);
            viewHolder.harga_barang = (TextView)convertView.findViewById(R.id.txtSHarga);
            viewHolder.deskripsi = (TextView)convertView.findViewById(R.id.txtSDeskripsi);
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
        viewHolder.nama_barang.setText(dbarang.getNama_barang());
        viewHolder.harga_barang.setText(dbarang.getHarga_barang());
        viewHolder.deskripsi.setText(dbarang.getDeskripsi());
        viewHolder.nama_lokasi.setText(dbarang.getNama_lokasi() +" " + dbarang.getNama_market());
        return convertView;
    }
}

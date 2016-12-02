package pam.develops.cekmart.Adapter;

import android.content.Context;
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
import pam.develops.cekmart.R;

/**
 * Created by paulms on 5/23/2016.
 */
public class SearchAdapter extends ArrayAdapter<Barang> {
        Context context;
        ArrayList<Barang> barangs;

    static class ViewHolder {
        public TextView nama_barang;
    }

    public SearchAdapter(Context context, ArrayList<Barang> barangs) {
        super(context, R.layout.singleitem_list_search, barangs);
        this.context = context;
        //imgLoader = new ImageLoader(context);
//        imgLoader = ImageLoader.getInstance();
        this.barangs = barangs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Barang barang = getItem(position);
        Log.d("Paul" , barang.getUrl());
        Log.d("Test" , barang.getJenis_barang());
        ViewHolder viewHolder;
        if (convertView == null) {
            //membuat baru item
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater. from(getContext());
            convertView = inflater.inflate(R.layout.singleitem_list_search, parent,false);
            viewHolder.nama_barang = (TextView)convertView.findViewById(R.id.textNamaBarang);
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
        viewHolder.nama_barang.setText(barang.getJenis_barang());
        return convertView;
    }
}

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
import pam.develops.cekmart.Model.Market;
import pam.develops.cekmart.R;

/**
 * Created by paulms on 5/12/2016.
 */
public class MarketAdapter extends ArrayAdapter<Market> {
    Context context;
    ArrayList<Market> markets;
//    ImageLoader imgLoader;

    static class ViewHolder {
        public ImageView gambar;
    }

    public MarketAdapter(Context context, ArrayList<Market> markets) {
        super(context, R.layout.singleitem_list_market, markets);
        this.context = context;
        //imgLoader = new ImageLoader(context);
//        imgLoader = ImageLoader.getInstance();
        this.markets = markets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Market market = getItem(position);
        Log.d("Paul" , market.getUrl());
        ViewHolder viewHolder;
        if (convertView == null) {
            //membuat baru item
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater. from(getContext());
            convertView = inflater.inflate(R.layout.singleitem_list_market, parent,false);
            viewHolder.gambar = (ImageView)convertView.findViewById(R.id.itemlist_gambar);
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
        Picasso.with(context).load(RESTClient.getURL_ResepMakananAPI() + market.getUrl()).into(viewHolder.gambar);
        return convertView;
    }

}

package pam.develops.cekmart.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import pam.develops.cekmart.API.RESTClient;
import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.Model.Market;
import pam.develops.cekmart.R;
import pam.develops.cekmart.SQLHelper.ControllerBarang;
import pam.develops.cekmart.SQLHelper.ControllerDaftarBarang;
import pam.develops.cekmart.SQLHelper.ControllerMarket;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreenActivity extends AppCompatActivity {
    ControllerMarket controller;
    ControllerBarang controllerBarang;
    ControllerDaftarBarang controllerDaftarBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        image.startAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, 3000); // delay 3 detik
        RESTClient.get().getMarket(new Callback<List<Market>>() {
            @Override
            public void success(List<Market> markets, Response response) {
                //Toast.makeText(getApplicationContext(), "Sukses kayaknya", Toast.LENGTH_LONG).show();
                if(markets.size() > 0){
                    controller = new ControllerMarket(getApplicationContext());
                    try {
                        controller.open();
                        controller.deleteData();
                        for(int i=0;i<markets.size();i++){
                            Market market = markets.get(i);
                            controller.insertData(market.getId_market(), market.getNama_market(),market.getUrl());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        controller.close();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //loading.dismiss();
                Toast.makeText(getApplicationContext(), "ResepData is not retrieved", Toast.LENGTH_LONG).show();
            }
        });

        RESTClient.get().getBarang(new Callback<List<Barang>>() {
            @Override
            public void success(List<Barang> barangs, Response response) {
//                Toast.makeText(getApplicationContext(), "Sukses kayaknya", Toast.LENGTH_LONG).show();
                if(barangs.size() > 0){
                    controllerBarang = new ControllerBarang(getApplicationContext());
                    try {
                        controllerBarang.open();
                        controllerBarang.deleteData();
                        for(int i=0;i<barangs.size();i++){
                            Barang barang = barangs.get(i);
                            controllerBarang.insertData(barang.getId_barang(), barang.getJenis_barang(),barang.getUrl());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        controllerBarang.close();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "ResepData is not retrieved", Toast.LENGTH_LONG).show();
            }
        });

        RESTClient.get().getDaftarBarang(new Callback<List<DaftarBarang>>() {
            @Override
            public void success(List<DaftarBarang> daftarBarangs, Response response) {;
                if(daftarBarangs.size() > 0){
                    controllerDaftarBarang = new ControllerDaftarBarang(getApplicationContext());
                    try {
                        controllerDaftarBarang.open();
                        controllerDaftarBarang.deleteData();
                        for(int i=0;i<daftarBarangs.size();i++){
                            DaftarBarang dbarang = daftarBarangs.get(i);
                            controllerDaftarBarang.insertData(
                                    dbarang.getNama_barang(),dbarang.getJenis_barang(),
                                    dbarang.getNama_market(),dbarang.getNama_lokasi(),dbarang.getHarga_barang(),dbarang.getDeskripsi());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        controllerDaftarBarang.close();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "ResepData is not retrieved", Toast.LENGTH_LONG).show();
            }
        });

    }
}

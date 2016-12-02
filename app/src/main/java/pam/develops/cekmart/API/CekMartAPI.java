package pam.develops.cekmart.API;

import java.util.List;

import pam.develops.cekmart.Model.Barang;
import pam.develops.cekmart.Model.DaftarBarang;
import pam.develops.cekmart.Model.Market;
import pam.develops.cekmart.Model.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by paulms on 5/4/2016.
 */
public interface CekMartAPI {
//    @GET("/userlogin/{username}/{password}")
//    void login(@Path("username") String username_, @Path("password") String password_, Callback<String> callback);
    @FormUrlEncoded
    @POST("/userlogin")
    void login(@Field("username") String username,@Field("password") String password, Callback<User> cb);

//    @GET("/register")
//    void register(@Field("username") String username, @Field("password") String password, Callback<User> cb);

    @FormUrlEncoded
    @POST("/register")
    void register(@Field("username") String username, @Field("password") String password, Callback<User> pm);

    @GET("/searchmarket")
    void getMarket(Callback<List<Market>> response);

    @GET("/searchbarang")
    void getBarang(Callback<List<Barang>> response);

    @GET("/searchdaftarbarang")
    void getDaftarBarang(Callback<List<DaftarBarang>> response);
}

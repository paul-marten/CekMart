package pam.develops.cekmart.API;

/**
 * Created by paulms on 4/29/2016.
 */

import pam.develops.cekmart.API.CekMartAPI;
import retrofit.RestAdapter;

public class RESTClient {
    private static CekMartAPI REST_CLIENT;
    private static String URL_RESEPMAKANAN = "http://192.168.43.214:81/cekmart";

    static { //dieksekusi sebelum constructor, tapi hanya sekaliuntuk semuainstans
    setupRestClient();
    }

    private RESTClient() {}
        public static CekMartAPI get() {
        return REST_CLIENT;
        }

    private static void setupRestClient() {
        RestAdapter builder = new RestAdapter.Builder().setEndpoint( URL_RESEPMAKANAN).build();

        REST_CLIENT = builder.create(CekMartAPI. class);
        }

    public static String getURL_ResepMakananAPI(){
        return URL_RESEPMAKANAN;
    }
}

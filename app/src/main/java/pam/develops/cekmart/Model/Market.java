package pam.develops.cekmart.Model;

/**
 * Created by paulms on 5/4/2016.
 */
public class Market {
    public int id_market;
    public String nama_market;
    public String url;

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public int getId_market() {
        return id_market;
    }

    public void setId_market(int id_market) {
        this.id_market = id_market;
    }

    public String getNama_market() {
        return nama_market;
    }

    public void setNama_market(String nama_market) {
        this.nama_market = nama_market;
    }
}

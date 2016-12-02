package pam.develops.cekmart.Model;

/**
 * Created by paulms on 5/15/2016.
 */
public class Lokasi {
    public int id_lokasi;
    public String nama_lokasi;
    public String koordinat_X;
    public String koordinat_Y;

    public int getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(int id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    public String getKoordinat_X() {
        return koordinat_X;
    }

    public void setKoordinat_X(String koordinat_X) {
        this.koordinat_X = koordinat_X;
    }

    public String getKoordinat_Y() {
        return koordinat_Y;
    }

    public void setKoordinat_Y(String koordinat_Y) {
        this.koordinat_Y = koordinat_Y;
    }
}

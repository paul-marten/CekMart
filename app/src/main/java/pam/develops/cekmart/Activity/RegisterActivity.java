package pam.develops.cekmart.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pam.develops.cekmart.API.RESTClient;
import pam.develops.cekmart.Model.User;
import pam.develops.cekmart.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {
    Context myContext;
    ProgressDialog progress;
    private EditText inputNama = null;
    private EditText inputPassword = null;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //tombol back
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final EditText Nama = (EditText) findViewById(R.id.regUser);
        final EditText Pass = (EditText) findViewById(R.id.regPass);
        Button buttonSave = (Button) findViewById(R.id.regButton);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Nama.getText().toString();
                String pass = Pass.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    Nama.setError("Kolom Nama Tidak Boleh Kosong");
                }
                if(pass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    Pass.setError("Kolom Password Tidak Boleh Kosong");
                }
                else {
                    myContext = getApplicationContext();
//                    progress = ProgressDialog.show(RegisterActivity.this,
//                            "Inisialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi",
//                            true);
                    RESTClient.get().register(name, pass, new Callback<User>() {

                        @Override
                        public void success(User user, Response response) {
                            finish();
                            startActivity(getIntent());
                            if(!user.getStatus().equals("")) {
                                if (user.getStatus().equals("0"))  // register failure
                                {
                                    Toast.makeText(RegisterActivity.this, "Username Telah Terpakai", Toast.LENGTH_SHORT).show();
                                }
                                else {  //register Success

                                    Toast.makeText(RegisterActivity.this, "Pendaftaran Telah Sukses", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);

                                }
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            finish();
                            startActivity(getIntent());
                            String merror = error.getMessage();
                            Toast.makeText(RegisterActivity.this, merror, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       if(android.R.id.home == item.getItemId()){
           finish();
       }
        return super.onOptionsItemSelected(item);
    }
}

package pam.develops.cekmart.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import pam.develops.cekmart.Analytics.AnalyticsApplication;
import pam.develops.cekmart.Model.User;
import pam.develops.cekmart.R;
import pam.develops.cekmart.API.RESTClient;
import pam.develops.cekmart.SQLHelper.ControllerMarket;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google. android.gms.analytics.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Tracker mTracker;

    Context myContext;
    ProgressDialog progress;
    private EditText inputNama = null;
    private EditText inputPassword = null;
    CallbackManager callbackManager;
    LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tracking
        AppEventsLogger.activateApp(this);
        // Facebook Inisiasi
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        loginButton = (LoginButton)findViewById(R.id.login_button) ;

        AnalyticsApplication application = (AnalyticsApplication)getApplication();
        mTracker = application.getDefaultTracker();

        final EditText Nama = (EditText) findViewById(R.id.loginUsername);
        final EditText Pass = (EditText) findViewById(R.id.loginPassword);
        Button buttonSave = (Button) findViewById(R.id.loginButton);
        Button buttonReg = (Button) findViewById(R.id.registerButton);
        Button buttonAbout = (Button) findViewById(R.id.aboutButton);


//        String test = ControllerMarket.NAMA_MARKET;
//        Toast.makeText(LoginActivity.this , test ,Toast.LENGTH_SHORT).show();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Nama.getText().toString();
                String pass = Pass.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(LoginActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    Nama.setError("Kolom Nama Tidak Boleh Kosong");
                }
                if (pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    Pass.setError("Kolom Password Tidak Boleh Kosong");
                }
                else {
                    myContext = getApplicationContext();
                    RESTClient.get().login(name, pass, new Callback<User>() {

                        @Override
                        public void success(User user, Response response) {
                            finish();
                            startActivity(getIntent());

                            if (user.getStatus().equals("1")) {  //login Success

                                Toast.makeText(LoginActivity.this, "Log In Sukses", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);

                            } else if (user.getStatus().equals("0"))  // login failure
                            {
                                Toast.makeText(LoginActivity.this, "Nama atau Password Salah ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            finish();
                            startActivity(getIntent());
                            String merror = error.getMessage();
                            Toast.makeText(LoginActivity.this, merror, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(register);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, new
                        FacebookCallback<LoginResult>() {

                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                GraphRequest request =
                                        GraphRequest. newMeRequest(loginResult.getAccessToken() , new GraphRequest.GraphJSONObjectCallback() {
                                            @Override
                                            public void onCompleted(JSONObject object, GraphResponse
                                                    response) {
                                                Intent main = new Intent(LoginActivity.this , MainActivity.class);
                                                startActivity(main);
                                            }
                                        }) ;
                                Bundle parameters = new Bundle() ;
                                parameters.putString("fields", "id,name,email,gender, birthday") ;
                                request.setParameters(parameters) ;
                                request.executeAsync() ;
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(LoginActivity.this , "Cancel" , Toast.LENGTH_SHORT);
                            }

                            @Override
                            public void onError(FacebookException error) {
                                Toast.makeText(LoginActivity.this , "Fail" , Toast.LENGTH_SHORT);
                            }
                        });
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(LoginActivity.this , AboutActivity.class);
                startActivity(about);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LOGIN", "Setting screen name: ");
        mTracker.setScreenName("Image~");
        mTracker.send(new HitBuilders. ScreenViewBuilder().build());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data) ;
    }

    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int lvl = intent.getIntExtra("level", 0);
            if (lvl == 100) {
                Toast.makeText(context, "Baterai Penuh", Toast.LENGTH_LONG).show();
            }
            else if (lvl <= 5) {
                Toast.makeText(context, "Baterai Lemah", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Baterai anda " + String.valueOf(lvl) + "%", Toast.LENGTH_LONG).show();
            }
        }
    };

}

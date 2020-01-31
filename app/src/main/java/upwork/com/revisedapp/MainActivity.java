package upwork.com.revisedapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import upwork.com.revisedapp.Utils.Global;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setEvents();
        autoLogin();
    }

    @Override
    public void onBackPressed() {
    }
    private void setEvents(){
        Button signin = (Button) findViewById(R.id.login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                finish();

            }
        });
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));

            }
        });
    }
    private void autoLogin(){
        String userName = Global.GetPreference(context, Global.PREFS_NAME);
        String userPass = Global.GetPreference(context, Global.PREFS_PASS);
        if(userName != null && userPass != null){
            LoginProcess(userName, userPass);
        }
    }
    private void LoginProcess(final String email, final String pass){
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        AsyncHttpClient http = new AsyncHttpClient();
        String url = Global.BASE_URL + "user/login?email=" + email + "&password=" + pass;
        http.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ret_val = jsonObject.getString("status");
                    if (ret_val.equals(Global.ERR_SUCCESS)) {
//                        JSONObject jsonObjData = jsonObject.getJSONObject("ret_data");
                        Global.SavePreference(context, Global.PREFS_NAME, email);
                        Global.SavePreference(context, Global.PREFS_PASS, pass);
                        startActivity(new Intent(MainActivity.this, StartViewActivity.class));
                        progress.dismiss();
                        finish();
                    } else {
//                        String errStr = jsonObject.getString("err_code");
//                        Global.ShowErrorDialog(context, getString(R.string.login_error), getString(R.string.login_pw_error));
                        progress.dismiss();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Global.ShowErrorDialog(context, getString(R.string.net_error), "Login Failed!");
 //                   progress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
//                Global.ShowErrorDialog(context, getString(R.string.net_error), getString(R.string.net_alert));
                progress.dismiss();
            }
        });
    }
}

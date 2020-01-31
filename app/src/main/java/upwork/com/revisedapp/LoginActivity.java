package upwork.com.revisedapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.regex.Pattern;

import upwork.com.revisedapp.Utils.Global;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText etEmail, etPassword;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        setContents();
        setEvents();
    }

    private void setContents(){
        etEmail = (EditText) findViewById(R.id.txtemail);
        etPassword = (EditText) findViewById(R.id.txtpassword);

        etEmail.setText(Global.GetPreference(context, Global.PREFS_NAME));
        etPassword.setText(Global.GetPreference(context, Global.PREFS_PASS));
    }

    private void setEvents(){
        Button forgotpassword = (Button) findViewById(R.id.forgetpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetpasswordActivity.class));
//                finish();

            }
        });
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();

                Pattern pattern = Global.EMAIL_ADDRESS;
                if(email.isEmpty()){
                    Toast.makeText(context, "Please insert email!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(! pattern.matcher(email).matches()){
                    Toast.makeText(context, "Please insert correct email!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(pass.isEmpty()){
                    Toast.makeText(context, "Please insert password!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                LoginProcess(email, pass);
            }
        });
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
                        startActivity(new Intent(LoginActivity.this, StartViewActivity.class));
                        progress.dismiss();
                        finish();
                    } else {
//                        String errStr = jsonObject.getString("err_code");
                        Global.ShowErrorDialog(context, getString(R.string.login_error), getString(R.string.login_pw_error));
                        progress.dismiss();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Global.ShowErrorDialog(context, getString(R.string.net_error), "Login Failed!");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Global.ShowErrorDialog(context, getString(R.string.net_error), getString(R.string.net_alert));
                progress.dismiss();
            }
        });
    }
}

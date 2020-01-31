package upwork.com.revisedapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.regex.Pattern;

import upwork.com.revisedapp.Utils.Global;

public class SignupActivity extends AppCompatActivity {

    private LinearLayout signupLayer;
    private TextView txtsuccessregistered;
    private Button signup;
    private boolean issignSuccess;
    private ProgressDialog progress;
    private EditText etCode, etName, etEmail, etPass;
    private CheckBox checkBox;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context = this;
        setContent();
        setEvent();
    }
    private void setContent(){
        issignSuccess = false;
        signupLayer = (LinearLayout)findViewById(R.id.signullayer);
        txtsuccessregistered = (TextView) findViewById(R.id.successregistered);
        signup = (Button)findViewById(R.id.register);
        signup.setEnabled(false);
        txtsuccessregistered.setVisibility(View.GONE);
        signupLayer.setVisibility(View.VISIBLE);

        etCode = (EditText)findViewById(R.id.txtcode);
        etName = (EditText)findViewById(R.id.txtname);
        etEmail = (EditText)findViewById(R.id.txtemail_register);
        etPass = (EditText)findViewById(R.id.txtpassword_register);
        checkBox = (CheckBox)findViewById(R.id.checkBox);

    }
    private void setEvent(){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    signup.setEnabled(true);
                }else{
                    signup.setEnabled(false);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!issignSuccess){
                    String tCode, tName, tMail, tPass;
                    tCode = etCode.getText().toString();
                    tName = etName.getText().toString();
                    tMail = etEmail.getText().toString();
                    tPass = etPass.getText().toString();

                    if(tCode.isEmpty()){
                        Toast.makeText(context, "Please insert code!",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(tName.isEmpty()){
                        Toast.makeText(context, "Please insert Name!",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(tName.length() < 5){
                        Toast.makeText(context, "Name should be 5 characters long",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(tMail.isEmpty()){
                        Toast.makeText(context, "Please insert email!",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    Pattern pattern = Global.EMAIL_ADDRESS;
                    if(! pattern.matcher(tMail).matches()){
                        Toast.makeText(context, " Email format is not correct ",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(tPass.isEmpty()){
                        Toast.makeText(context, "Please insert password!",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    SignUpProcess(tMail, tPass, tName, tCode);

                }else {
                    startActivity(new Intent(SignupActivity.this, StartViewActivity.class));
                    finish();
                }
            }
        });
    }
    private void SignUpProcess(final String email, final String pass, String name, String code){
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        AsyncHttpClient http = new AsyncHttpClient();
        String url = Global.BASE_URL + "user/register?email=" + email + "&password=" + pass + "&name=" + name + "&code=" + code;
        http.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ret_val = jsonObject.getString("status");
                    if (ret_val.equals(Global.ERR_SUCCESS)) {
                        Global.SavePreference(getApplicationContext(), Global.PREFS_NAME, email);
                        Global.SavePreference(getApplicationContext(), Global.PREFS_PASS, pass);
                        signupLayer.setVisibility(View.GONE);
                        txtsuccessregistered.setVisibility(View.VISIBLE);
                        signup.setText(getString(R.string.menu));
                        issignSuccess = true;
                        progress.dismiss();
                    } else {
//                        String errStr = jsonObject.getString("err_code");
                        Global.ShowErrorDialog(context, getString(R.string.signup_error), getString(R.string.signup_duplicate));
                        progress.dismiss();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Global.ShowErrorDialog(context, getString(R.string.net_error), "Regist Failed!");
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Global.ShowErrorDialog(context, getString(R.string.net_error), "Regist Failed!");
                progress.dismiss();
            }
        });
    }
}

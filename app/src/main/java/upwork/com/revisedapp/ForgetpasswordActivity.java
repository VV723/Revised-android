package upwork.com.revisedapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import upwork.com.revisedapp.Utils.Global;

public class ForgetpasswordActivity extends AppCompatActivity {

    private TextView tvemail, tvsuccess;
    private Button btSendMail, btGotoLogin;
    private EditText etEmail;
    private Context context;
    private LinearLayout successView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        context = this;
        setContents();
        setEvents();
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    private void setContents(){
        tvemail = (TextView) findViewById(R.id.inputemail);
        tvsuccess = (TextView) findViewById(R.id.successsentmail);
        btGotoLogin = (Button) findViewById(R.id.gotologin);
        btSendMail = (Button) findViewById(R.id.sendmail);
        etEmail = (EditText) findViewById(R.id.txtemail);
        successView = (LinearLayout) findViewById(R.id.successView);

        successView.setVisibility(View.GONE);
    }
    private void setEvents(){
        btSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sendOK()){
                    tvemail.setVisibility(View.GONE);
                    btSendMail.setVisibility(View.GONE);
                    etEmail.setVisibility(View.GONE);
                    successView.setVisibility(View.VISIBLE);
                }
            }
        });
        btGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean sendOK(){

        Pattern pattern = Global.EMAIL_ADDRESS;
        if(etEmail.getText().toString().isEmpty()){
            Toast.makeText(context, "Please insert email!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(! pattern.matcher(etEmail.getText().toString()).matches()){
            Toast.makeText(context, " Email format is not correct ",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

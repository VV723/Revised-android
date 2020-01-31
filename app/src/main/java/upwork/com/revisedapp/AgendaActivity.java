package upwork.com.revisedapp;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class AgendaActivity extends AppCompatActivity {
    private WebView scrollTextView;


    private boolean is13 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        setContent();
        setEvent();
    }
    @Override
    public void onBackPressed() {
    }
    private void setContent(){
        scrollTextView = (WebView) findViewById(R.id.agendaweb_view);
        scrollTextView.setBackgroundColor(Color.TRANSPARENT);
        scrollTextView.setScrollbarFadingEnabled(true);
        String url = "file:///android_asset/agenda13.html";
        scrollTextView.loadUrl(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEvent(){

        ImageButton back = (ImageButton) findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

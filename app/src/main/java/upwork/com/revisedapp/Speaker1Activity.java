package upwork.com.revisedapp;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Speaker1Activity extends AppCompatActivity {
    private WebView scrollTextView;
    private LinearLayout scrollbar;
    private ImageView scrollbtt;

    private int initPosY, maxPosY, TextScrollHeight, scrollVisibleHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker1);
        setContent();
        setEvent();
        setInitData();
    }
    @Override
    public void onBackPressed() {
    }

    private void setInitData(){

        scrollbar.post(new Runnable(){
            public void run(){
                maxPosY = scrollbar.getHeight();
                initPosY = scrollbar.getTop();
            }
        });

        scrollTextView.post(new Runnable(){
            public void run(){
                TextScrollHeight = scrollTextView.getHeight();
                scrollVisibleHeight = scrollTextView.getContentHeight();
            }
        });
    }
    private void setContent(){
        scrollTextView = (WebView) findViewById(R.id.speakerwebview);
        scrollTextView.setBackgroundColor(Color.TRANSPARENT);
        scrollTextView.setScrollbarFadingEnabled(true);
        scrollbar = (LinearLayout) findViewById(R.id.mapscrollbar);
        scrollbtt = (ImageView) findViewById(R.id.bttscroll);


        String url = "file:///android_asset/speaker.html";
        scrollTextView.loadUrl(url);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEvent(){
        scrollTextView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(scrollVisibleHeight == 0) return;
                //          int posy = (int)( initPosY - 10 + i1 * scrollVisibleHeight/ (TextScrollHeight - scrollVisibleHeight));
                int posy = (int)( initPosY - 10 + 1.2f * i3 * maxPosY * TextScrollHeight/ (scrollVisibleHeight * scrollVisibleHeight));
                if(posy < initPosY) posy = initPosY;
                if(posy > initPosY+ maxPosY) posy = initPosY+ maxPosY;
                scrollbtt.setY(posy);

            }
        });
        scrollTextView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                setInitData();
            }
        });
        ImageButton back = (ImageButton) findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

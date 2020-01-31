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

public class ContactActivity extends AppCompatActivity {
    private WebView contactWebView;
    private LinearLayout scrollbar;
    private ImageView scrollbtt;

    private int initPosY, maxPosY, TextScrollHeight, scrollVisibleHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setContent();
        setEvent();
    }

    @Override
    public void onBackPressed() {
    }

    private void setContent(){
        contactWebView = (WebView) findViewById(R.id.contactview);
        contactWebView.setBackgroundColor(Color.TRANSPARENT);
        contactWebView.setScrollbarFadingEnabled(true);
        scrollbar = (LinearLayout) findViewById(R.id.mapscrollbar);
        scrollbtt = (ImageView) findViewById(R.id.bttscrollcontact);

        String url = "file:///android_asset/contact.html";
        contactWebView.loadUrl(url);

    }
    private void setInitData(){

        scrollbar.post(new Runnable(){
            public void run(){
                maxPosY = scrollbar.getHeight();
                initPosY = scrollbar.getTop();
            }
        });

        contactWebView.post(new Runnable(){
            public void run(){
                TextScrollHeight = contactWebView.getHeight();
                scrollVisibleHeight = contactWebView.getContentHeight();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEvent(){
        ImageButton back = (ImageButton) findViewById(R.id.backbuttonwelcome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        contactWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(scrollVisibleHeight == 0) return;
                int posy = (int)( initPosY + 1.09f * view.getScrollY() * maxPosY * TextScrollHeight/ (scrollVisibleHeight * scrollVisibleHeight));
                scrollbtt.setY(posy);

            }
        });
        contactWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                setInitData();
            }
        });
    }
}

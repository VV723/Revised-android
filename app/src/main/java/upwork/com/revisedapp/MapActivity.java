package upwork.com.revisedapp;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MapActivity extends AppCompatActivity {
    private WebView scrollTextView;
    private LinearLayout scrollbar;
    private ImageView scrollbtt;

    private int initPosY, maxPosY, TextScrollHeight, scrollVisibleHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        scrollTextView = (WebView) findViewById(R.id.mapwebView);
        scrollTextView.setBackgroundColor(Color.TRANSPARENT);
        scrollTextView.setScrollbarFadingEnabled(true);
        scrollbar = (LinearLayout) findViewById(R.id.mapscrollbar);
        scrollbtt = (ImageView) findViewById(R.id.mapscrollbutton);


        String url = "file:///android_asset/logistics.html";
        scrollTextView.loadUrl(url);
    }
    private void setEvent(){
        scrollTextView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(scrollVisibleHeight == 0) return;
                int posy = (int)( initPosY + i3 * maxPosY * TextScrollHeight/ (scrollVisibleHeight * scrollVisibleHeight));
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
        ImageButton back = (ImageButton) findViewById(R.id.backbuttonmap);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

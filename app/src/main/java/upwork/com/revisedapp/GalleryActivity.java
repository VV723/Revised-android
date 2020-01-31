package upwork.com.revisedapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {
    private TextView scrollTextView;
    private LinearLayout scrollbar;
    private ImageView scrollbtt;

    private int initPosY, maxPosY, TextScrollHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        setContent();
        setEvent();
    }
    @Override
    public void onBackPressed() {
    }
    private void setContent(){
        scrollTextView = (TextView) findViewById(R.id.mapwebView);
        scrollTextView.setMovementMethod(new ScrollingMovementMethod());
        scrollbar = (LinearLayout) findViewById(R.id.mapscrollbar);
        scrollbtt = (ImageView) findViewById(R.id.bttscroll);


        scrollbar.post(new Runnable(){
            public void run(){
                maxPosY = scrollbar.getHeight();
                initPosY = scrollbar.getTop();
            }
        });

        scrollTextView.post(new Runnable(){
            public void run(){
                TextScrollHeight = scrollTextView.getLayout().getLineTop(scrollTextView.getLineCount()) - scrollTextView.getHeight();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEvent(){
        scrollTextView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                scrollbtt.setY(initPosY - 20 + view.getScrollY() * maxPosY / TextScrollHeight);

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

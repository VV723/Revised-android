package upwork.com.revisedapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);
        setEvents();
    }
    @Override
    public void onBackPressed() {
    }
    private void setEvents(){
        Button bWelcome = (Button) findViewById(R.id.btt_welcome);
        Button bAgenda = (Button) findViewById(R.id.btt_agenda);
        Button bSpeakers = (Button) findViewById(R.id.btt_speakers);
        Button bMap = (Button) findViewById(R.id.btt_map);
        Button bGallery = (Button) findViewById(R.id.btt_gallery);
        Button bContact = (Button) findViewById(R.id.btt_contact);
        Button bVoting = (Button) findViewById(R.id.btt_votting);
        bWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, WelcomeActivity.class));
            }
        });
        bAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, AgendaActivity.class));
            }
        });
        bSpeakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, Speaker1Activity.class));
            }
        });
        bContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, ContactActivity.class));
            }
        });
        bVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(StartViewActivity.this, GalleryActivity.class));
                String url = "http://Slido.com/#startwithmore";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, MapActivity.class));
            }
        });
        bGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartViewActivity.this, GalleryActivity.class));
            }
        });
    }
}

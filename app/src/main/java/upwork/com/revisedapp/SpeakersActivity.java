package upwork.com.revisedapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import upwork.com.revisedapp.Utils.SpeakerItem;

public class SpeakersActivity extends AppCompatActivity {
    private LinearLayout scrollbar;
    private ImageView scrollbtt;
    private ListView photoListView;

    private int initPosY, maxPosY, TextScrollHeight, scrollVisibleHeight;
    public PhotoArrayAdapter photoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);
        setContent();
        setInitData();
        setEvent();
    }
    @Override
    public void onBackPressed() {
    }
    private void setContent(){
        photoListView = (ListView) findViewById(R.id.photolistview);
        photoListView.setAdapter(photoArrayAdapter = new PhotoArrayAdapter());
        scrollbar = (LinearLayout) findViewById(R.id.mapscrollbar);
        scrollbtt = (ImageView) findViewById(R.id.bttscroll);
    }

    private void setInitposition(){

        scrollbar.post(new Runnable(){
            public void run(){
                maxPosY = scrollbar.getHeight();
                initPosY = scrollbar.getTop();
            }
        });

        photoListView.post(new Runnable(){
            public void run(){
                scrollVisibleHeight = photoListView.getHeight();
                TextScrollHeight = photoListView.getChildAt(0).getHeight() * 12;
            }
        });
    }
    private void setInitData(){
        photoArrayAdapter.clear();
        SpeakerItem model = new SpeakerItem();
//        model.setTitle("Chairmen:");
//        photoArrayAdapter.add(model);

//        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo1);
        model.setSpeakerRoll("Chair:");
        model.setSpeakerName("Salem Beshyah");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("SKMC");
        photoArrayAdapter.add(model);
        //2
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo2);
        model.setSpeakerRoll("Chair:");
        model.setSpeakerName("Iyad Al Ksseiry");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("City Hospital Dubai");
        photoArrayAdapter.add(model);
        //3
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo3);
        model.setSpeakerRoll("Chair:");
        model.setSpeakerName("Ahmed Hassoun");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("Dubai Diabetes Centre");
        photoArrayAdapter.add(model);
        //4
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo4);
        model.setSpeakerRoll("Chair:");
        model.setSpeakerName("Asjad Hameed");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("Imperial College");
        photoArrayAdapter.add(model);


        //add Title
//        model = new SpeakerItem();
//        model.setTitle("International Speakers:");
//        photoArrayAdapter.add(model);
        //5
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo5);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("MS Kumar");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("Prime Hospital Dubai");
        photoArrayAdapter.add(model);
        //6
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo6);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Hubert Pennincks - Cons.");
        model.setSpeakerPosition("Endocrinologist American");
        model.setSpeakerJob("Hospital Dubai");
        photoArrayAdapter.add(model);


        //add Title
//        model = new SpeakerItem();
//        model.setTitle("Regional Speakers:");
//        photoArrayAdapter.add(model);
        //7
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo7);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Salah Abusnana - Medical");
        model.setSpeakerPosition("Director Rashid Centre for");
        model.setSpeakerJob("Diabetes");
        photoArrayAdapter.add(model);
        //8
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo8);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Ossama Barakat - Consultant");
        model.setSpeakerPosition("Endocrinologist Imperial");
        model.setSpeakerJob("College");
        photoArrayAdapter.add(model);
        //9
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo9);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Prof. Serge Jabbour");
        model.setSpeakerPosition("");
        model.setSpeakerJob("");
        photoArrayAdapter.add(model);
        //10
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo10);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Tarek Fiad");
        model.setSpeakerPosition("Consultant Endocrinologist");
        model.setSpeakerJob("SKMC");
        photoArrayAdapter.add(model);
        //11
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo11);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Hani Sabour - Consultant");
        model.setSpeakerPosition("Cardiologist Cleveland Clinic");
        model.setSpeakerJob("Abu Dhabi");
        photoArrayAdapter.add(model);
        //12
        model = new SpeakerItem();
        model.setPhotoURL(R.drawable.photo12);
        model.setSpeakerRoll("Speaker");
        model.setSpeakerName("Prof. Vincent Woo");
        model.setSpeakerPosition("");
        model.setSpeakerJob("");
        photoArrayAdapter.add(model);

        setInitposition();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEvent(){
        photoListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(scrollVisibleHeight == 0) return;
                View c = photoListView.getChildAt(0);
                int scrolly = -c.getTop() + photoListView.getFirstVisiblePosition() * c.getHeight();
                int posy = (int)( initPosY - 10 + scrolly * scrollVisibleHeight/ (TextScrollHeight - scrollVisibleHeight));
                scrollbtt.setY(posy);

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

    public class PhotoArrayAdapter extends BaseAdapter {
        private ArrayList<SpeakerItem> speakerList;

        public PhotoArrayAdapter() {
            speakerList = new ArrayList<SpeakerItem>();
        }

        @Override
        public int getCount() {
            return speakerList.size();
        }

        @Override
        public Object getItem(int i) {
            return speakerList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public void add(SpeakerItem bean){
            speakerList.add(bean);
        }

        public void clear(){
            speakerList.clear();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(SpeakersActivity.this, R.layout.photo_item, null);
            }

            TextView txtRoll = (TextView) convertView.findViewById(R.id.txtRoll);
            TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
            TextView txtPosition = (TextView) convertView.findViewById(R.id.txtPosition);
            TextView txtJob = (TextView) convertView.findViewById(R.id.txtJob);
            ImageView photoView = (ImageView) convertView.findViewById(R.id.speakerphoto);
            SpeakerItem item = speakerList.get(position);
            if(item.getTitle() == null){
                txtRoll.setText(item.getSpeakerRoll());
                txtName.setText(item.getSpeakerName());
                txtPosition.setText(item.getSpeakerPosition());
                txtJob.setText(item.getSpeakerJob());
                photoView.setImageDrawable(getResources().getDrawable(item.getPhotoURL()));
                txtJob.setVisibility(View.VISIBLE);
                txtName.setVisibility(View.VISIBLE);
                txtPosition.setVisibility(View.VISIBLE);
                photoView.setVisibility(View.VISIBLE);
            }else {
                txtRoll.setText(item.getTitle());
                txtJob.setVisibility(View.GONE);
                txtName.setVisibility(View.GONE);
                txtPosition.setVisibility(View.GONE);
                photoView.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
}

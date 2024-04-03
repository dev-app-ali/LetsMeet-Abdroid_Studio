package com.example.letsmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.ZegoUIKit;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment;

public class LiveActivity extends AppCompatActivity {

    String userID, name, liveID;
    boolean isHost;

    TextView liveIdText;
    ImageView sharebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        liveIdText = findViewById(R.id.live_id_textview);
        sharebtn = findViewById(R.id.share_btn);



        userID = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");
        liveID = getIntent().getStringExtra("live_id");
        isHost = getIntent().getBooleanExtra("host",false);

        liveIdText.setText(liveID);

        addFragment();
    }


    void  addFragment(){
        ZegoUIKitPrebuiltLiveStreamingConfig config;
        if (isHost){
            config = ZegoUIKitPrebuiltLiveStreamingConfig.host();

        }else {
            config = ZegoUIKitPrebuiltLiveStreamingConfig.audience();
        }

        ZegoUIKitPrebuiltLiveStreamingFragment fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(AppConstant.appId,
                AppConstant.appSign,userID, name,liveID,config);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commitNow();



    }
}
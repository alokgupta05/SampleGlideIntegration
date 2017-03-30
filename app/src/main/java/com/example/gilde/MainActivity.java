package com.example.gilde;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gilde.utils.ImageUtils;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ImageView imageData;
    Button getImage;
    Button getCircularImage;
    Button getFallBackImage;
    Button fallBackErrorImage;
    String imageNormalURL = "http://i.imgur.com/w8wgAQX.jpg";
    String imageFallBackURL = "http://i.imgur.com/jlFgGpe.jpg";
    String imageCircularURL = "http://i.imgur.com/ZP23iGG.jpg";
    String fallBackImage = "http://i.imgur.com/removed.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageData = (ImageView) findViewById(R.id.imageData);
        getImage = (Button) findViewById(R.id.getNormalImage);
        getCircularImage = (Button) findViewById(R.id.getCircularImage);
        getFallBackImage = (Button) findViewById(R.id.getFallBackImage);
        fallBackErrorImage= (Button) findViewById(R.id.fallBackErrorImage);
        getImage.setOnClickListener(this);
        getCircularImage.setOnClickListener(this);
        getFallBackImage.setOnClickListener(this);
        fallBackErrorImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getFallBackImage:
                ImageUtils.setImageWithGlide(imageFallBackURL,imageData, R.drawable.fall_back);
                break;
            case R.id.getNormalImage:
                ImageUtils.setImageWithGlide(imageNormalURL,imageData);
                break;
            case R.id.getCircularImage:
                ImageUtils.loadImageWithGlide(imageCircularURL,imageData,this);
                break;
            case R.id.fallBackErrorImage:
                ImageUtils.setImageWithGlide(fallBackImage,imageData,R.drawable.fall_back,R.drawable.error_layout);
                break;
            default:
                break;
        }
    }
}

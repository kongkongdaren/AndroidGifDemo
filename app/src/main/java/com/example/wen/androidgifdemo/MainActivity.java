package com.example.wen.androidgifdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wen.adroid.view.GifView;

import java.io.IOException;
import java.io.InputStream;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends Activity {
    private GifView mGvPhoto, mGvLocalPhoto;
    private ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //方法一 使用自定义gifview播放gif图片
        mGvLocalPhoto = (GifView) findViewById(R.id.gv_local_photo);
        mGvPhoto = (GifView) findViewById(R.id.gv_photo);

        try {
            InputStream is = getAssets().open("assetphoto.gif");
            mGvPhoto.setGifStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 方法二  使用Glide播放gif图片
        mIvPhoto = (ImageView) findViewById(R.id.iv_photo);
        Glide.with(this).load(R.drawable.gifphoto).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvPhoto);
        //方法三 使用android-gif-drawable 库
        GifImageView mGifIvPhoto = (GifImageView) findViewById(R.id.giv_photo);
        try {
            //加载asset文件中的gif图片
            GifDrawable gif = new GifDrawable(getAssets(), "assetphoto.gif");
            mGifIvPhoto.setImageDrawable(gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过 GifImageView 对象获取到 GifDrawable 对象
        GifDrawable gifDrawable = (GifDrawable) mGifIvPhoto.getDrawable();
        //下面这是GifImageView的其他属性
//        gifDrawable.start(); //开始播放
//
//        gifDrawable.stop(); //停止播放
//
//        gifDrawable.reset();  //复位，重新开始播放
//
//        gifDrawable.isRunning(); //是否正在播放
//
//        gifDrawable.setLoopCount( 2 ); //设置播放的次数，播放完了就自动停止
//
        int currentLoop = gifDrawable.getCurrentLoop();//获取正在播放的次数
        Log.e("currentLoop", currentLoop + "");
//
        int currentPosition = gifDrawable.getCurrentPosition();//获取现在到从开始播放所经历的时间
        Log.e("currentPosition", currentPosition + "");
//
        int duration = gifDrawable.getDuration();//获取播放一次所需要的时间
        Log.e("duration", duration + "");

    }
}

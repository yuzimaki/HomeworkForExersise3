package com.example.chapter3.homework;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;

public class Ch3Ex1Activity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private CheckBox loopCheckBox;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex1);

        animationView = findViewById(R.id.animation_view);
        loopCheckBox = findViewById(R.id.loop_checkbox);
        seekBar = findViewById(R.id.seekbar);

        loopCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 当选中自动播放的时候，开始播放 lottie 动画，同时禁止手动修改进度

                    animationView.playAnimation();
                    seekBar.setEnabled(false);
                } else {
                    // 当去除自动播放时，停止播放 lottie 动画，同时允许手动修改进度

                    animationView.pauseAnimation();
                    seekBar.setEnabled(true);
                }
            }
        });
        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int ap=(int)(valueAnimator.getAnimatedFraction()*100);
                seekBar.setProgress(ap);
        }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO ex1-2: 这里应该调用哪个函数呢
                if(fromUser){
                    float sp=progress/100.0f;
                    animationView.setProgress(sp);
                }


                // 提示1：可以参考 https://airbnb.io/lottie/#/android?id=custom-animators
                // 提示2：SeekBar 的文档可以把鼠标放在 OnProgressChanged 中间，并点击 F1 查看，
                // 或者到官网查询 https://developer.android.google.cn/reference/android/widget/SeekBar.OnSeekBarChangeListener.html#onProgressChanged(android.widget.SeekBar,%20int,%20boolean
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}

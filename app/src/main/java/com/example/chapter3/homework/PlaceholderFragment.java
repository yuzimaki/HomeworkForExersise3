package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {


    private LottieAnimationView animationView;
    private ListView listview;
    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);

        animationView = view.findViewById(R.id.animation_view1);
        listview=(ListView) view.findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);




       listview.setAlpha(0f);
       listview.setVisibility(View.VISIBLE);
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                listview.animate()
                        .alpha(1f)
                        .setDuration(1500)
                        .setListener(null);

                animationView.animate()
                        .alpha(0f)
                        .setDuration(1500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationView.setVisibility(View.GONE);
                            }
                        });

            }
        }, 2000);
    }
}

package com.dyx.rsp.view.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.dyx.rsp.R;
import com.dyx.rsp.view.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/24 下午4:43
 * alter person：dayongxin
 * alter time：16/7/24 下午4:43
 * alter remark：
 */
public class LambdaAct extends BaseActivity {
    @Bind(R.id.tv_lambda)
    TextView tvLambda;

    private String[] words = {"I", "am", "an", "android", "developer"};
    private List<String> wordList = Arrays.asList(words);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lambda_layout);
        ButterKnife.bind(this);

        /**
         * 添加字符串
         */
        Observable<String> obShow = Observable.just(getString(R.string.show_words));

        /**
         * 映射，设置TextView
         */
        obShow.observeOn(AndroidSchedulers.mainThread()).map(String::toUpperCase).subscribe(tvLambda::setText);

        /**
         * 显示数组中的每个元素
         */
        Observable<String> obArray = Observable.from(words);

        /**
         * 映射之后进行分发
         */
        obArray.observeOn(AndroidSchedulers.mainThread()).map(String::toUpperCase).subscribe(this::showSnackBar);

        /**
         * 直接获取数据、分发、在合并
         */
        Observable.just(wordList).observeOn(AndroidSchedulers.mainThread()).flatMap(Observable::from).reduce(this::uniteString).subscribe(this::showSnackBar);
    }


    public void showSnackBar(String msg) {
        Snackbar.make(tvLambda, msg, Snackbar.LENGTH_SHORT).show();
    }

    private String uniteString(String str1, String str2) {
        return String.format("%s %s", str1, str2);
    }
}

package com.dyx.rsp.view.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.dyx.rsp.R;
import com.dyx.rsp.view.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/22 上午11:25
 * alter person：dayongxin
 * alter time：16/7/22 上午11:25
 * alter remark：
 */
public class ComplexAct extends BaseActivity {
    @Bind(R.id.tv_complex)
    TextView tvComplex;

    private String[] words = {"I", "am", "an", "android", "developer"};
    private List<String> wordsList = Arrays.asList(words);
    private String content = "I am an android deveoper";

    /**
     * 订阅者，设置TextView
     */
    private Action1<String> mTvAction = new Action1<String>() {
        @Override
        public void call(String s) {
            tvComplex.setText(s);
        }
    };

    /**
     * 订阅者，显示SnackBar
     */
    private Action1<String> mSnackBarAction = new Action1<String>() {
        @Override
        public void call(String s) {
            showSnackBar(tvComplex, s);
        }
    };

    /**
     * 设置映射函数
     */
    private Func1<List<String>, Observable<String>> mLetterFun = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
        }
    };

    /**
     * 映射函数，实现转换为大写字母
     */
    private Func1<String, String> mUpLetterFun = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    /**
     * 映射函数，空格连接字符串(第三个参数为返回类型)
     */
    private Func2<String, String, String> mLinkLetterFun = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s, s2);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_complex_layout);
        ButterKnife.bind(this);

        /**
         * 添加字符串
         */
        Observable<String> obStr = Observable.just(content);

        /**
         *先映射 再设置Textview
         */
        obStr.observeOn(AndroidSchedulers.mainThread()).map(mUpLetterFun).subscribe(mTvAction);

        /**
         * 显示数组中的每一个元素
         */
        Observable<String> obArray = Observable.from(words);

        /**
         * 映射之后进行分发
         */
        obArray.observeOn(AndroidSchedulers.mainThread()).map(mUpLetterFun).subscribe(mSnackBarAction);


        /**
         * 显示链表中的每一个元素
         */
        Observable.just(wordsList).observeOn(AndroidSchedulers.mainThread()).flatMap(mLetterFun).reduce(mLinkLetterFun).subscribe(mSnackBarAction);
    }
}

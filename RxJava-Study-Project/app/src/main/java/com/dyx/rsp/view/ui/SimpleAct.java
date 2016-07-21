package com.dyx.rsp.view.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.dyx.rsp.R;
import com.dyx.rsp.view.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/21 下午11:29
 * alter person：dayongxin
 * alter time：16/7/21 下午11:29
 * alter remark：
 */
public class SimpleAct extends BaseActivity {
    @Bind(R.id.tv_simple)
    TextView tvSimple;

    /**
     * 观察事件发生
     */
    private Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            /**
             * 发生事件
             */
            subscriber.onNext("Hello RxAndroid!");
            /**
             * 完成事件
             */
            subscriber.onCompleted();
        }
    };

    /**
     * 订阅者，接受字符串，修改控件内容
     */
    private Subscriber<String> mTvSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            tvSimple.setText(s);
        }
    };

    private Subscriber<String> mSnackBarSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            showSnackBar(tvSimple, s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_simple_layout);
        ButterKnife.bind(this);
        /**
         * 注册观察活动
         */
        Observable<String> observable = Observable.create(mObservableAction);

        /**
         * 分发订阅事件
         */
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(mTvSubscriber);
        observable.subscribe(mSnackBarSubscriber);
    }
}

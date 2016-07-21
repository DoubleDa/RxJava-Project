package com.dyx.rsp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/21 下午11:03
 * alter person：dayongxin
 * alter time：16/7/21 下午11:03
 * alter remark：
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void intentTo(Class<?> cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}

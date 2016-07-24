package com.dyx.rsp.view.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyx.rsp.R;
import com.dyx.rsp.view.BaseActivity;
import com.dyx.rsp.view.adapter.MenuActAdapter;
import com.dyx.rsp.widget.DividerItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * project name：RxJava-Study-Project
 * class describe：
 * create person：dayongxin
 * create time：16/7/21 下午11:04
 * alter person：dayongxin
 * alter time：16/7/21 下午11:04
 * alter remark：
 */
public class MenuAct extends BaseActivity {
    @Bind(R.id.rv_menu)
    RecyclerView rvMenu;
    private MenuActAdapter mMenuActAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.menu));

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setHasFixedSize(true);
        rvMenu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mMenuActAdapter = new MenuActAdapter(this, list);
        rvMenu.setAdapter(mMenuActAdapter);
        mMenuActAdapter.setmOnRvItemClickListener(pos -> {
            switch (pos) {
                case 0:
                    intentTo(SimpleAct.class);
                    break;
                case 1:
                    intentTo(ComplexAct.class);
                    break;
                case 2:
                    intentTo(LambdaAct.class);
                    break;
                default:
                    break;
            }
        });
    }
}

package com.mvp_mvvm_demo.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.mvp_mvvm_demo.constants.AppConstants;
import com.mvp_mvvm_demo.R;
import com.mvp_mvvm_demo.views.adapters.UserDetailListAdapter;
import com.mvp_mvvm_demo.models.UserDetailModel;
import com.mvp_mvvm_demo.presenters.UserPresenter;
import com.mvp_mvvm_demo.views.interfaces.UserDetailInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserDetailInterface {
    private UserPresenter presenter;
    private ListView listViewUserDetail;
    private UserDetailListAdapter userDetailListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initPresenterAndFetchData();
    }

    private void initViews() {
        listViewUserDetail = (ListView) findViewById(R.id.listViewUserDetailList);
    }

    private void initPresenterAndFetchData() {
        presenter = new UserPresenter(this);
        presenter.getData(AppConstants.URL);
    }

    @Override
    public void setUserDetails(final List<UserDetailModel> userDetailList, final List<String> fullAddressList) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapterToUserList(userDetailList, fullAddressList);
            }
        });
    }

    private void setAdapterToUserList(List<UserDetailModel> userDetailList, List<String> fullAddressList) {
        userDetailListAdapter = new UserDetailListAdapter(MainActivity.this, userDetailList, fullAddressList);
        listViewUserDetail.setAdapter(userDetailListAdapter);
    }
}

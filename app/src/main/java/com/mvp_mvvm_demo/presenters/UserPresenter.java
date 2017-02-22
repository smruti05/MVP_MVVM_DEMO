package com.mvp_mvvm_demo.presenters;

import android.util.Log;

import com.mvp_mvvm_demo.constants.AppConstants;
import com.mvp_mvvm_demo.models.UserDetailModel;
import com.mvp_mvvm_demo.viewmodels.UserDetailViewModel;
import com.mvp_mvvm_demo.views.interfaces.UserDetailInterface;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserPresenter implements Callback {
    private UserDetailInterface userInterface;
    private List<UserDetailModel> userDetailList;
    private List<String> userCompleteAddress;
    private OkHttpClient okHttpClient;
    private String TAG = "Exception";

    public UserPresenter(UserDetailInterface userInterface) {
        this.userInterface = userInterface;
        userDetailList = new ArrayList<>();
        userCompleteAddress = new ArrayList<>();
        okHttpClient = new OkHttpClient();
    }

    public Call getData(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(this);
        return call;
    }

    @Override
    public void onFailure(Request request, IOException e) {
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            String responseString = response.body().string();
            Log.d("response", responseString);
            setUserDetails(responseString);
        } else {
            Log.d("response", String.valueOf(response));
        }
    }

    private void setUserDetails(String responseString) {
        userDetailList = getUserDetailList(responseString);
        UserDetailViewModel userDetailViewModel = new UserDetailViewModel(userDetailList);
        for (int i = 0; i < userDetailList.size(); i++) {
            userCompleteAddress.add(userDetailViewModel.getCompleteAddress(i));
        }
        userInterface.setUserDetails(userDetailList, userCompleteAddress);
    }

    private List<UserDetailModel> getUserDetailList(String response) {
        List<UserDetailModel> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                UserDetailModel userDetailModel = new UserDetailModel();
                userDetailModel.setUserName(jsonObject.getString(AppConstants.NAME));
                userDetailModel.setEmailId(jsonObject.getString(AppConstants.EMAIL));
                JSONObject addressJsonObj = jsonObject.getJSONObject(AppConstants.ADDRESS);
                userDetailModel.setAddCity(addressJsonObj.getString(AppConstants.CITY));
                userDetailModel.setAddStreet(addressJsonObj.getString(AppConstants.STREET));
                userDetailModel.setAddSuite(addressJsonObj.getString(AppConstants.SUITE));
                userDetailModel.setAddZipCode(addressJsonObj.getString(AppConstants.ZIPCODE));
                list.add(userDetailModel);
            }
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
        return list;
    }
}

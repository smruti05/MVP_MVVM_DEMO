package com.mvp_mvvm_demo.viewmodels;

import com.mvp_mvvm_demo.models.UserDetailModel;

import java.util.List;

public class UserDetailViewModel {
    private List<UserDetailModel> userDetailList;

    public UserDetailViewModel(List<UserDetailModel> userDetailList) {
        this.userDetailList = userDetailList;
    }

    public String getCompleteAddress(int index) {
        String fullAddress = userDetailList.get(index).getAddStreet() +
                " " + userDetailList.get(index).getAddSuite() +
                " " + userDetailList.get(index).getAddCity() +
                " " + userDetailList.get(index).getAddZipCode();
        return fullAddress;
    }
}

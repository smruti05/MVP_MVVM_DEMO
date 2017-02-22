package com.mvp_mvvm_demo.views.interfaces;

import com.mvp_mvvm_demo.models.UserDetailModel;
import java.util.List;

public interface UserDetailInterface {
   void setUserDetails(List<UserDetailModel> userDetailList, List<String> fullAddressList);
}

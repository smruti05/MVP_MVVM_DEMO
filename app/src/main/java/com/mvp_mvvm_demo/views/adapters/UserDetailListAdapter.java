package com.mvp_mvvm_demo.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mvp_mvvm_demo.R;
import com.mvp_mvvm_demo.models.UserDetailModel;

import java.util.List;

public class UserDetailListAdapter extends BaseAdapter {
    private List<UserDetailModel> userDetailModelList;
    private List<String> userFullAddressList;
    private Context context;

    public UserDetailListAdapter(Context context, List<UserDetailModel> userDetailModelList, List<String> userFullAddressList) {
        this.context = context;
        this.userDetailModelList = userDetailModelList;
        this.userFullAddressList = userFullAddressList;
    }

    @Override
    public int getCount() {
        return userDetailModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_detail_adapter_row, null);
            holder = new ViewHolder();
            initViews(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setValues(position, holder);
        return convertView;
    }

    private void initViews(View convertView, ViewHolder holder) {
        holder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        holder.textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        holder.textViewFullAddress = (TextView) convertView.findViewById(R.id.textViewFullAddress);
    }

    private void setValues(int position, ViewHolder holder) {
        holder.textViewName.setText(context.getString(R.string.name) + userDetailModelList.get(position).getUserName());
        holder.textViewEmail.setText(context.getString(R.string.email) + userDetailModelList.get(position).getEmailId());
        holder.textViewFullAddress.setText(context.getString(R.string.address) + userFullAddressList.get(position));
    }

    static class ViewHolder {
        private TextView textViewName, textViewEmail, textViewFullAddress;
    }
}

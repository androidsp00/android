package com.example.androidsp.appgplx.BtnDethi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsp.appgplx.R;

import java.util.ArrayList;

/**
 * Created by Cheng Lee on 11/1/2016.
 */
public class MainOverAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ObjectMainOver> arr;

    public MainOverAdapter(Context context, ArrayList<ObjectMainOver> arr) {
        this.context = context;
        this.arr = arr;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        private ImageView img_ques;
        private TextView tv_name;
    }

    @Override
    public View getView(int pos, View conterview, ViewGroup viewGroup) {
        View view = conterview;
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_grid_ketqua, viewGroup, false);
            holder.img_ques = (ImageView) view.findViewById(R.id.img_ques);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        holder.tv_name.setText("Câu " + (arr.get(pos).getIndex() + 1));
        switch (arr.get(pos).getCheckQues()){
            case 1:
                holder.img_ques.setImageResource(R.drawable.right_mark_result);
                break;

            case 2:
                holder.img_ques.setImageResource(R.drawable.wrong_mark_result);
                break;

            case 3:
                holder.img_ques.setImageResource(R.drawable.exclamation_mark_result);
                break;
        }

        return view;
    }
}

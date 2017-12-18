package com.demo.misaki.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by misaki on 2017/12/18.
 */

public class CustomSpinner implements SpinnerAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<DataSetObserver> observerList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    public CustomSpinner(Context context, String[] strings) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        stringList = Arrays.asList(strings);

    }
    @Override
    public View getDropDownView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.right_layout, null);//偶数布局
            ViewHolder holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        }
        View view = convertView;
        TextView textView = ((ViewHolder) view.getTag()).textView;
        textView.setText((String) getItem(i));
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        if (observer == null)
            throw new NullPointerException("observer不可为空");
        if (!observerList.contains(observer))
            observerList.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer == null)
            throw new NullPointerException("observer不可为空");
        if (!observerList.contains(observer))
            throw new Resources.NotFoundException("observer未注册");
        observerList.remove(observer);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.left_layout, null);//奇数布局
            ViewHolder holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        }
        View view = convertView;
        TextView textView = ((ViewHolder) view.getTag()).textView;
        textView.setText((String) getItem(i));
        return view;
    }
    /**
     * 不关注此功能
     * @param i
     * @return
     */
    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    private class ViewHolder {
        TextView textView;
    }
}

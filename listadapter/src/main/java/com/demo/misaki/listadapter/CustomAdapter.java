package com.demo.misaki.listadapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.demo.misaki.listadapter.R.id.odd_layout;

/**
 * Created by misaki on 2017/12/17.
 */

public class CustomAdapter implements ListAdapter{
    private Context context;
    private List<DataSetObserver> observers=new ArrayList<>();
    private List<String>strings=new ArrayList<>();
    private final static int ViewTypeCount = 2;//此Adapter可以适配两种视图类型
    private final static int OddType = 0, EvenType = 1;
    private LayoutInflater inflater;
    public CustomAdapter(Context context, String[] s) {
        this.context = context;
        //阅读相关:通过查看Arrays类的源码可以知道,asList返回的List是Array中的实现的
        //内部类,而该类并没有定义add和remove方法.另外,为什么修改其中一个,另一个也自动
          //      * 获得更新了,因为asList获得List实际引用的就是数组
        strings = Arrays.asList(s);
        inflater = LayoutInflater.from(context);
    }
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        if (observer == null)
            throw new NullPointerException("observer不可为空");
        if (!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer == null)
            throw new NullPointerException("observer不可为空");
        if (!observers.contains(observer))
            throw new Resources.NotFoundException("observer未注册");
        observers.remove(observer);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 此方法用于说明每个视图对应的数据项是否是稳定不变的
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //因为position是从0开始算的，在这里为了模拟我们从1开始算
        int viewType=getItemViewType(i+1);
        if(view==null){
            switch (viewType) {
                case OddType:
                    view = inflater.inflate(R.layout.right_layout,null);
                    break;
                case EvenType:
                    view=inflater.inflate(R.layout.left_layout,null);
                    break;
            }
            TextView textView= (TextView) view.findViewById(R.id.tvContent);
            ViewHolder holder=new ViewHolder();
            holder.textView=textView;
            view.setTag(holder);
        }
        View view1=view;
        TextView textView=((ViewHolder)view.getTag()).textView;
        textView.setText(strings.get(i));
        return view1;
    }

    /**
     * 在这里模拟当奇数项时数字显示在左边，偶数项时数字显示在右边
     *
     * @param i
     * @return
     */
    @Override
    public int getItemViewType(int i) {
        if(i%2==1){
            return OddType;
        }else {
            return EvenType;
        }
    }

    @Override
    public int getViewTypeCount() {
        return ViewTypeCount;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    /**
     * 此方法用于当数据及发生改变是通知观察者数据发生了改变
     */
    public void notifyDataSetChange() {
        if (observers != null && observers.size() != 0) {
            for (DataSetObserver item : observers) {
                item.onChanged();
            }
        }
    }

    private class ViewHolder{
        TextView textView;
    }

}


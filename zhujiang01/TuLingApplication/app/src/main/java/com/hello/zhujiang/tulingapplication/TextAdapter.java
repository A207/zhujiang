package com.hello.zhujiang.tulingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Administrator on 2015/7/16.
 */
public class TextAdapter extends BaseAdapter{
    private List<ListData> lists;
    private Context context;
    RelativeLayout layout;
    public TextAdapter(List<ListData> lists,Context context){
        this.lists=lists;
        this.context=context;
    }
    public int getCount(){
        return lists.size();
    }
    public Object getItem(int position){
        return lists.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater=LayoutInflater.from(context);
        if (lists.get(position).getFlag()==ListData.RECEIVER){
            layout =(RelativeLayout) inflater.inflate(R.layout.left_item, null);
        }
        if (lists.get(position).getFlag()==ListData.SEND){
            layout =(RelativeLayout) inflater.inflate(R.layout.right_item, null);
        }

        TextView tView=(TextView) layout.findViewById(R.id.tv);
        tView.setText(lists.get(position).getContent());
        TextView time=(TextView)layout.findViewById(R.id.time);
        time.setText(lists.get(position).getTime());
        return layout;
    }
}

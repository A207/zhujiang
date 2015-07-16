package com.hello.zhujiang.tulingapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity implements HttpGetDataListener,View.OnClickListener{
    private HttpData mHttpData;
    private List<ListData> lists;
    private ListView lv;
    private EditText sendText;
    private Button sendButton;
    private String context_str;
    private TextAdapter adapter;
    private String[] welcome_array;
    private String welcome_tip;
    private double currentTime;
    private double oldTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView(){
        lv=(ListView) findViewById(R.id.lv);
        sendText=(EditText) findViewById(R.id.sendText);
        sendButton=(Button) findViewById(R.id.sendbtn);
        lists=new ArrayList<ListData>();
        sendButton.setOnClickListener(this);
        adapter=new TextAdapter(lists,this);
        lv.setAdapter(adapter);
        ListData listData=new ListData(getRandomWelcomeTips(),ListData.RECEIVER,getTime());
        lists.add(listData);
    }
    private String getRandomWelcomeTips() {
        String welcome_tip = null;
        welcome_array = this.getResources().getStringArray(R.array.welcome_tip);
        int index =(int) (Math.random() * (welcome_array.length - 1));
        welcome_tip=welcome_array[index];

        return welcome_tip;
    }
    public void getDataUrl(String data){
        // System.out.println(data);
        parseText(data);
    }
    public void parseText(String str){
        try {
            JSONObject jb = new JSONObject(str);
            //System.out.println(jb.getString("code"));
            //System.out.println(jb.getString("text"));
            ListData listData=new ListData(jb.getString("text"),ListData.RECEIVER,getTime());
            lists.add(listData);
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();

         //noinspection SimplifiableIfStatement
         if (id == R.id.action_settings) {
             return true;
         }

         return super.onOptionsItemSelected(item);
     }

    public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd   HH:mm:ss");
        Date curDate=new Date();
        String string=sdf.format(curDate);
        currentTime=System.currentTimeMillis();
        if (currentTime-oldTime>=5*60*1000){
            oldTime=currentTime;
            return string;
        }else {
            return "";
        }
    }
    @Override
    public void onClick(View v) {
        getTime();
        context_str=sendText.getText().toString();
        sendText.setText("");
        String a=context_str.replace(" ","");
        String str=a.replace("\n","");
        mHttpData=( HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=db2d301561b2c4f1f2d4735cdd3abd87&info="+str,this).execute();
        ListData listData=new ListData(context_str,ListData.SEND,getTime());
        lists.add(listData);
        adapter.notifyDataSetChanged();
    }




}
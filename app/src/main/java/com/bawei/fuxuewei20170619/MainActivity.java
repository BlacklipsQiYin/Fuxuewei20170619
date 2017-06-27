package com.bawei.fuxuewei20170619;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ViewAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private Button quanXuan;
    private Button fanXuan;
    private List<CheckBean> list;
    private CheckBean checkBean;
    boolean checked ;
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        quanXuan = (Button) findViewById(R.id.quanXuan);
        fanXuan = (Button) findViewById(R.id.fanXuan);


        list = new ArrayList<CheckBean>();
        for (int i = 0; i < 100; i++){
            checkBean = new CheckBean();
            checkBean.setContent("条目"+i);
            list.add(checkBean);
        }


        //设置适配器
        adapter = new ViewAdapter(this, list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);


        //必须设置布局管理器
        //线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);



        //添加分割线
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.GRAY)
                .build()); //分割线颜色


        //全选的按钮
        findViewById(R.id.quanXuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(int i = 0; i< list.size(); i++){
                    if(!checked){
                        list.get(i).setIscheck(true);
                    }else {
                        list.get(i).setIscheck(false);
                    }
                }
                adapter.notifyDataSetChanged();

                if(!checked){
                    checked = true;
                }else {
                    checked = false;
                }

            }
        });


        //反选的按钮
        findViewById(R.id.fanXuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(int i = 0; i< list.size(); i++){

                    if (list.get(i).ischeck()) {

                        list.get(i).setIscheck(false);
                        adapter.notifyDataSetChanged();

                    } else {

                        list.get(i).setIscheck(true);
                        adapter.notifyDataSetChanged();

                    }

                }


            }
        });


    }

    @Override
    public void onItemClickListener(int position, View view) {

        Toast.makeText(this, list.get(position).getContent(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClickListener(int position, View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = builder.create();
        View viewDialog = View.inflate(MainActivity.this, R.layout.dialogview, null);

        TextView dianji = (TextView) viewDialog.findViewById(R.id.dianji);
        dianji.setText(list.get(position).getContent());

        alertDialog.setView(viewDialog);
        alertDialog.show();

    }
}

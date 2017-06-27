package com.bawei.fuxuewei20170619;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>{
private Context rContext;
private List<CheckBean> rList ;
private LayoutInflater inflater ;
public ViewAdapter(Context context, List<CheckBean> list){

this.rContext = context;
this.rList = list ;
inflater = LayoutInflater.from(context);
}

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


View view = inflater.inflate(R.layout.itemview, parent, false);

ViewHolder viewHolder = new ViewHolder(view);

return viewHolder;


}

@Override
public void onBindViewHolder(final ViewHolder holder, final int position) {


holder.title.setText(rList.get(position).getContent());

    holder.title.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClickListener(position,v);
        }
    });


    holder.title.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            listener.onItemLongClickListener(position,v);
            return true;
        }
    });

    holder.checkBox.setChecked(rList.get(position).ischeck());

    //选中的监听   解决乱选问题
    holder.checkBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rList.get(position).ischeck()){
                rList.get(position).setIscheck(false);
                holder.checkBox.setChecked(false);
            } else {
                rList.get(position).setIscheck(true);
                holder.checkBox.setChecked(true);
            }

            notifyDataSetChanged();

        }
    });


}



@Override
public int getItemCount() {
return rList.size();
}

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private CheckBox checkBox;

    public ViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

    }
}


    interface OnItemClickListener {

        void onItemClickListener(int position,View view);
        void onItemLongClickListener(int position,View view);
    }


    public OnItemClickListener listener ;



    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


}
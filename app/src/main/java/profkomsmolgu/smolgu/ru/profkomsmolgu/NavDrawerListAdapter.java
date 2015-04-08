package profkomsmolgu.smolgu.ru.profkomsmolgu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class NavDrawerListAdapter extends RecyclerView.Adapter<NavDrawerListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<NavDrawerItem> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public NavDrawerListAdapter(Context context, List<NavDrawerItem> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
	}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        NavDrawerItem current = data.get(position);
        Log.d("Test", "test " + position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.icon);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);

        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, profkomsmolgu.smolgu.ru.profkomsmolgu.ProfkomActivity.class));
            if (clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}

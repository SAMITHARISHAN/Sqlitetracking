package projectv2.technocoders.com.sqlitetracking;

/**
 * Created by Asus PC on 12/22/2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {
    List<DataModel> dataModelArrayList;
    private ItemClickListener clickListener;

    public RecycleAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }


    public class Myholder extends RecyclerView.ViewHolder {
       public TextView time;

        public Myholder(View itemView) {
            super(itemView);

          //name = (TextView) itemView.findViewById(R.id.name1);
          // city = (TextView) itemView.findViewById(R.id.city1);
            time = (TextView) itemView.findViewById(R.id.DateID);
            itemView.setTag(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(view.getContext(),MapsActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel=dataModelArrayList.get(position);
       // holder..setText(dataModel.getName());
       // holder.city.setText(dataModel.getCity());
        holder.time.setText(dataModel.getTime());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}

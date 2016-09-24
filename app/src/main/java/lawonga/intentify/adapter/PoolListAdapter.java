package lawonga.intentify.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import lawonga.intentify.model.PoolModel;

/**
 * Created by Andy on 9/24/2016.
 */

public class PoolListAdapter extends RecyclerView.Adapter<PoolListAdapter.GambleHolder>{
    private ArrayList<PoolModel> poolModels;

    public PoolListAdapter(ArrayList<PoolModel> poolModels) {
        this.poolModels = poolModels;
    }

    @Override
    public GambleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GambleHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return poolModels.size();
    }

    public class GambleHolder extends RecyclerView.ViewHolder{
        public GambleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

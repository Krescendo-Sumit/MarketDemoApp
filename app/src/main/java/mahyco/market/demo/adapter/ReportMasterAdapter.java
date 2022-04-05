package mahyco.market.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.ReportMasterModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.reportdetails.ReportDetailsActivity;
import mahyco.market.demo.view.reports.ReportAPI;


public class ReportMasterAdapter extends RecyclerView.Adapter<ReportMasterAdapter.DataObjectHolder> {


    Context context;
    SqlightDatabase sqlightDatabase;
    private static final int UNSELECTED = -1;
    ReportAPI pendingActionAPI;
    ArrayList<ReportMasterModel> actionModelArrayList = null;

    public interface EventListener {
        void onDelete(int trid, int position);
    }

    public ReportMasterAdapter(ArrayList<ReportMasterModel> actionModels, Context context) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;
        sqlightDatabase = new SqlightDatabase(context);

    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report_master, parent, false);

        return new DataObjectHolder(view);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        //  if (mSellerProductlist.size() > 0) {
        return actionModelArrayList.size();
        //} else {
        //  return 0;
        // }
    }


    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        try {
            ReportMasterModel actionModel = actionModelArrayList.get(position);
            holder.tvMDHybridName.setText(actionModel.getMDHybridName());
            holder.tv_farmername.setText(actionModel.getFarmerName());
            holder.tvVillageName.setText(actionModel.getVillageName());
            holder.tvMobileNo.setText(actionModel.getMobileNo());
            holder.btnDownloadPA.setText(actionModel.getUniqueSrNo());
            holder.btnDownloadPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Preferences.save(context, Preferences.SELECTED_UNIQSRID,actionModel.getUniqueSrNo());
                    Intent intent=new Intent(context, ReportDetailsActivity.class);
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tv_farmername,
                tvMDHybridName,
                tvVillageName,
                tvMobileNo;


        Button btnDownloadPA;

        public DataObjectHolder(View itemView) {
            super(itemView);


            tv_farmername=(TextView) itemView.findViewById(R.id.tv_farmername);
                  tvVillageName=(TextView) itemView.findViewById(R.id.tv_villagename);
                    tvMobileNo=(TextView) itemView.findViewById(R.id.tv_mobile);

            tvMDHybridName = (TextView) itemView.findViewById(R.id.tvMDHybridName);

            btnDownloadPA = (Button) itemView.findViewById(R.id.btnDownloadPA);
        }
    }


}

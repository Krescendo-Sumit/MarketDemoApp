package mahyco.market.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.pendingaction.PendingActionAPI;


public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.DataObjectHolder> {


    Context context;
    SqlightDatabase sqlightDatabase;
    private static final int UNSELECTED = -1;
    PendingActionAPI pendingActionAPI;
    ArrayList<ActionModel> actionModelArrayList = null;

    public interface EventListener {
        void onDelete(int trid, int position);
    }

    public ActionAdapter(ArrayList<ActionModel> actionModels, Context context, PendingActionAPI pendingActionAPI) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;
        sqlightDatabase=new SqlightDatabase(context);
        this.pendingActionAPI=pendingActionAPI;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pending_actions, parent, false);

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
            ActionModel actionModel = actionModelArrayList.get(position);

            holder.tvPendingFor.setText(actionModel.getPendingFor());
            holder.tvMDDispatchSegmetnId.setText(actionModel.getMDDispatchSegmetnId());
            holder.tvUniqueSrNo.setText(actionModel.getUniqueSrNo());
            holder.tvYear.setText(actionModel.getYear());
            holder.tvSeason.setText(actionModel.getSeason());
            holder.tvCrop.setText(actionModel.getCrop());
            holder.tvSegment.setText(actionModel.getSegment());
            holder.tvMDHybridName.setText(actionModel.getMDHybridName());
            holder.tvZone.setText(actionModel.getZone());
            holder.tvState.setText(actionModel.getState());
            holder.tvTotalMDSeedAvlInGram.setText(actionModel.getTotalMDSeedAvlInGram());
            holder.tvNoOfKits.setText(actionModel.getNoOfKits());
            holder.tvDistrictId.setText(actionModel.getDistrictId());
            holder.tvTalukaId.setText(actionModel.getTalukaId());
            holder.tvAssignedTo.setText(actionModel.getAssignedTo());
            holder.tvIsAllocated.setText(actionModel.getIsAllocated());
            holder.tvProductId.setText(actionModel.getProductId());
            holder.tvVisitStageId.setText(actionModel.getVisitStageId());
            holder.tvVisitStage.setText(actionModel.getVisitStage());
            holder.tvDemoCropSowingId.setText(actionModel.getDemoCropSowingId());


            holder.btnDownloadPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int  pendingfor=Integer.parseInt(actionModel.getPendingFor().trim());
                    if (pendingfor>1) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("ProductId", actionModel.getProductId());
                        jsonObject.addProperty("VisitStageId", actionModel.getPendingFor());
                        pendingActionAPI.getChartristics(jsonObject);

                    }else {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("filterValue", actionModel.getTalukaId());
                        jsonObject.addProperty("FilterOption", "TalukaId");
                        pendingActionAPI.getVillageList(jsonObject);


                    }
                    if (sqlightDatabase.addPendingAction(actionModel)) {
                        Toast.makeText(context, "Data Saved Locally", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvPendingFor,
                tvMDDispatchSegmetnId,
                tvUniqueSrNo,
                tvYear,
                tvSeason,
                tvCrop,
                tvSegment,
                tvMDHybridName,
                tvZone,
                tvState,
                tvTotalMDSeedAvlInGram,
                tvNoOfKits,
                tvDistrictId,
                tvTalukaId,
                tvAssignedTo,
                tvIsAllocated,
                tvProductId,
                tvVisitStageId,
                tvVisitStage,
                tvDemoCropSowingId;


        Button btnDownloadPA;

        public DataObjectHolder(View itemView) {
            super(itemView);

            tvPendingFor = (TextView) itemView.findViewById(R.id.tvPendingFor);
            tvMDDispatchSegmetnId = (TextView) itemView.findViewById(R.id.tvMDDispatchSegmetnId);
            tvUniqueSrNo = (TextView) itemView.findViewById(R.id.tvUniqueSrNo);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvSeason = (TextView) itemView.findViewById(R.id.tvSeason);
            tvCrop = (TextView) itemView.findViewById(R.id.tvCrop);
            tvSegment = (TextView) itemView.findViewById(R.id.tvSegment);
            tvMDHybridName = (TextView) itemView.findViewById(R.id.tvMDHybridName);
            tvZone = (TextView) itemView.findViewById(R.id.tvZone);
            tvState = (TextView) itemView.findViewById(R.id.tvState);
            tvTotalMDSeedAvlInGram = (TextView) itemView.findViewById(R.id.tvTotalMDSeedAvlInGram);
            tvNoOfKits = (TextView) itemView.findViewById(R.id.tvNoOfKits);
            tvDistrictId = (TextView) itemView.findViewById(R.id.tvDistrictId);
            tvTalukaId = (TextView) itemView.findViewById(R.id.tvTalukaId);
            tvAssignedTo = (TextView) itemView.findViewById(R.id.tvAssignedTo);
            tvIsAllocated = (TextView) itemView.findViewById(R.id.tvIsAllocated);
            tvProductId = (TextView) itemView.findViewById(R.id.tvProductId);
            tvVisitStageId = (TextView) itemView.findViewById(R.id.tvVisitStageId);
            tvVisitStage = (TextView) itemView.findViewById(R.id.tvVisitStage);
            tvDemoCropSowingId = (TextView) itemView.findViewById(R.id.tvDemoCropSowingId);


            btnDownloadPA = (Button) itemView.findViewById(R.id.btnDownloadPA);
        }
    }


}

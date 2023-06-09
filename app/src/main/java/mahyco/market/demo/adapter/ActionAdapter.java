package mahyco.market.demo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.pendingaction.PendingActionAPI;
import mahyco.market.demo.view.remarks.AddRemark;


public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.DataObjectHolder> implements Filterable {


    Context context;
    SqlightDatabase sqlightDatabase;
    private static final int UNSELECTED = -1;
    PendingActionAPI pendingActionAPI;
    ArrayList<ActionModel> actionModelArrayList = null;
    ArrayList<ActionModel> actionModelArrayList_filter = null;
    private ArrayList<ActionModel> movieListFiltered;
    Dialog dialog;

    public interface EventListener {
        void onDelete(int trid, int position);
    }

    public ActionAdapter(ArrayList<ActionModel> actionModels, Context context, PendingActionAPI pendingActionAPI) {

        this.actionModelArrayList = actionModels;
        actionModelArrayList_filter = new ArrayList<>(actionModelArrayList);
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;
        sqlightDatabase = new SqlightDatabase(context);
        this.pendingActionAPI = pendingActionAPI;

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
            String  roleid=Preferences.get(context,Preferences.ROLE_ID).trim();
            holder.tvPendingFor.setText(actionModel.getPendingFor());
            holder.tvMDDispatchSegmetnId.setText(actionModel.getMDDispatchSegmetnId());
            holder.tvUniqueSrNo.setText(actionModel.getUniqueSrNo()+"  "+roleid);
            holder.tvYear.setText(actionModel.getYear());
            holder.tvSeason.setText(actionModel.getSeason());
            holder.tvCrop.setText(actionModel.getCrop());
            holder.tvSegment.setText(actionModel.getSegment());
            holder.tvMDHybridName.setText(actionModel.getMDHybridName());
            holder.tvZone.setText(actionModel.getZone());
            holder.tvState.setText(actionModel.getState());
            holder.tvTotalMDSeedAvlInGram.setText(actionModel.getTotalMDSeedAvlInGram());
            holder.tvNoOfKits.setText(actionModel.getNoOfKits());
            holder.tvDistrictId.setText(actionModel.getDistrict());
            holder.tvTalukaId.setText(actionModel.getTaluka());
            holder.tvAssignedTo.setText(actionModel.getAssignedTo());
            holder.tvIsAllocated.setText(actionModel.getIsAllocated());
            holder.tvProductId.setText(actionModel.getProductId());
            holder.tvVisitStageId.setText(actionModel.getVisitStageId());
boolean isCompleted=false;
            if(actionModel.getPendingVisitStage().trim().equals("")) {
                holder.tvVisitStage.setText("Complete");
                holder.tvVisitStage.setTextColor(Color.GREEN);
                holder.tvVisitStage.setTypeface(Typeface.DEFAULT_BOLD);
                isCompleted=true;
                holder.btnDownloadPA.setEnabled(false);
                holder.btnDownloadPA.setVisibility(View.GONE);
            }
            else {
                isCompleted=false;
                holder.tvVisitStage.setText(actionModel.getPendingVisitStage());
            }
            holder.tvDemoCropSowingId.setText(actionModel.getDemoCropSowingId());

            if(Integer.parseInt(actionModel.getPendingFor().trim())>1)
            {

                holder.ll.setVisibility(View.VISIBLE);
                holder.tvFarmerName.setVisibility(View.VISIBLE);
                holder.tvMobileNo.setVisibility(View.VISIBLE);
                holder.tvWhatsAppNo.setVisibility(View.VISIBLE);
                holder.tvlongitude.setVisibility(View.VISIBLE);
                holder.tvResAddr.setVisibility(View.VISIBLE);
                holder.tvNameOfHybrid.setVisibility(View.VISIBLE);
                holder.tvCheckHybrid.setVisibility(View.VISIBLE);
                holder.tvDOS.setVisibility(View.VISIBLE);

                holder.tvFarmerName.setText(actionModel.getFarmerName());
                holder.tvMobileNo.setText(actionModel.getMobileNo());
                holder.tvWhatsAppNo.setText(actionModel.getWhatsAppNo());
                holder.tvlongitude.setText(actionModel.getLatitude()+","+actionModel.getLongitude());
                holder.tvResAddr.setText(actionModel.getResAddr());
                holder.tvNameOfHybrid.setText(actionModel.getNameOfHybrid());
                holder.tvCheckHybrid.setText(actionModel.getCheckHybrid());
                holder.tvDOS.setText(actionModel.getDOS());

            }else
            {
                holder.tvFarmerName.setVisibility(View.GONE);
                holder.ll.setVisibility(View.GONE);
                holder.tvMobileNo.setVisibility(View.GONE);
                holder.tvWhatsAppNo.setVisibility(View.GONE);
                holder.tvlongitude.setVisibility(View.GONE);
                holder.tvResAddr.setVisibility(View.GONE);
                holder.tvNameOfHybrid.setVisibility(View.GONE);
                holder.tvCheckHybrid.setVisibility(View.GONE);
                holder.tvDOS.setVisibility(View.GONE);
            }




            int k = sqlightDatabase.getSowingMasterCount(actionModel.getUniqueSrNo()) + sqlightDatabase.getSowingUpdateMasterCount(actionModel.getUniqueSrNo(),actionModel.getPendingFor());
            if (k > 0) {
                holder.btnDownloadPA.setText("Already Downloaded");
            }

            if(roleid.equals("2")||roleid.equals("3"))
            {
                holder.btnDownloadRemark.setVisibility(View.VISIBLE);
            }else
            {
                holder.btnDownloadRemark.setVisibility(View.GONE);
            }

            holder.btnDownloadRemark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               /*     Intent intent=new Intent(context, AddRemark.class);
                    context.startActivity(intent);*/

                    openDialog(context,actionModel);
                }
            });

            holder.btnDownloadPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  Toast.makeText(context, "" + sqlightDatabase.getSowingMasterCount(actionModel.getUniqueSrNo()), Toast.LENGTH_SHORT).show();
                    int pendingfor = Integer.parseInt(actionModel.getPendingFor().trim());

                    int k =00;// sqlightDatabase.getSowingMasterCount(actionModel.getUniqueSrNo()) + sqlightDatabase.getSowingUpdateMasterCount(actionModel.getUniqueSrNo(),actionModel.getPendingFor());
                  //  int k = sqlightDatabase.getSowingUpdateMasterCount(actionModel.getUniqueSrNo());
                     if(pendingfor>1)
                     {
                         k=sqlightDatabase.getSowingUpdateMasterCount(actionModel.getUniqueSrNo(),actionModel.getPendingFor());
                     }else
                     {
                         k=sqlightDatabase.getSowingMasterCount(actionModel.getUniqueSrNo());
                     }

                    if (k > 0) {
                        holder.btnDownloadPA.setText("Already Downloaded");
                    }

                    if (k > 0) {

                        Toast.makeText(context, "Already Exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        Preferences.save(context, Preferences.SELECTED_UNIQSRID,actionModel.getUniqueSrNo());
                      if (pendingfor > 1) {
                            if (sqlightDatabase.getChracteristics(actionModel.getProductId(), actionModel.getUniqueSrNo()).size() > 0) {
                           } else {
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("ProductId", actionModel.getProductId());
                                jsonObject.addProperty("VisitStageId", actionModel.getPendingFor());
                                pendingActionAPI.getChartristics(jsonObject);
                            }
                        } else {
                            if (sqlightDatabase.getLocalVillage(actionModel.getTaluka()).size() > 0) {
                         //       Toast.makeText(context, "Taluka Exist", Toast.LENGTH_SHORT).show();

                            } else {
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("filterValue", actionModel.getTaluka());
                                jsonObject.addProperty("FilterOption", "TalukaId");
                                pendingActionAPI.getVillageList(jsonObject);
                            }

                        }

                        if (sqlightDatabase.addPendingAction(actionModel)) {
                            Toast.makeText(context, "Data Saved Locally", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });

        } catch (Exception e) {
            Log.i("Error ", e.getMessage());
        }
    }

     void openDialog(Context context,ActionModel actionModel) {
        try{
            if(actionModel!=null) {
                dialog =new Dialog(context);
                dialog.setContentView(R.layout.popup_addremark);
                dialog.setCanceledOnTouchOutside(false);
                EditText et_remark = dialog.findViewById(R.id.et_remark);
                Spinner sp_status = dialog.findViewById(R.id.sp_status);
                TextView txt_title = dialog.findViewById(R.id.txt_title);
                txt_title.setText(actionModel.getFarmerName() + " (" + actionModel.getUniqueSrNo() + ")");
                ImageButton btn_close = dialog.findViewById(R.id.btnclose);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button btn_submit = dialog.findViewById(R.id.btnSubmit);
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String status=sp_status.getSelectedItem().toString().trim();
                        String remark=et_remark.getText().toString().trim();
                        if(remark.equals(""))
                        {
                            et_remark.setError("Please enter remark");
                        }else
                       if(status.equals("")||status.contains("Select"))
                           Toast.makeText(context, "Select crop Status", Toast.LENGTH_SHORT).show();
                       else {

                           Date c = Calendar.getInstance().getTime();
                           System.out.println("Current time => " + c);
                           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                           String formattedDate = df.format(c);

                           JsonObject jsonObject = new JsonObject();

                           //   jsonObject.addProperty("DemoCropRemarkId", 0);
                           jsonObject.addProperty("RoleId", Integer.parseInt(Preferences.get(context, Preferences.ROLE_ID)));
                           jsonObject.addProperty("DemoCropSowingId", Integer.parseInt(actionModel.getDemoCropSowingId()));
                           jsonObject.addProperty("LastVisitDt", formattedDate);
                           jsonObject.addProperty("Remark", et_remark.getText().toString().trim());
                           jsonObject.addProperty("CropStatus", status);
                           jsonObject.addProperty("CreatedBy",Preferences.get(context, Preferences.USER_ID));
                           jsonObject.addProperty("CreatedDt", formattedDate);//: "2023-04-12T19:35:31.775Z",
                           //     jsonObject.addProperty("ModifiedBy", Preferences.get(context, Preferences.USER_ID));//: "string",
                           //    jsonObject.addProperty("ModifiedDt", "2023-04-12T19:35:31.775Z");//: "2023-04-12T19:35:31.775Z",
                           jsonObject.addProperty("Role", Preferences.get(context, Preferences.ROLE_NAME));//: "string",
                           jsonObject.addProperty("FarmerName", actionModel.getFarmerName());//: "string",
                           jsonObject.addProperty("MobileNo", actionModel.getMobileNo());//: "string",
                           jsonObject.addProperty("NameOfHybrid", actionModel.getNameOfHybrid());//: "string",
                           jsonObject.addProperty("VisitStage", actionModel.getVisitStage());//: "string"

                           Log.i("Json is", jsonObject.toString());

                           JsonArray jsonArray=new JsonArray();
                           jsonArray.add(jsonObject);


                           JsonObject jsonObject1=new JsonObject();
                           jsonObject1.add("demoCropRemarkModels",jsonArray);

                      Log.i("Action",jsonObject1.toString());

                        pendingActionAPI.addRemark(jsonObject1,dialog);

                       }
                    }
                });
                dialog.show();
            }else
            {
                Toast.makeText(context, "no data found.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {

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

        TextView tvFarmerName;
        TextView tvMobileNo;
        TextView tvWhatsAppNo;
        TextView tvLatitude;
        TextView tvlongitude;
        TextView tvResAddr;
        TextView tvNameOfHybrid;
        TextView tvCheckHybrid;
        TextView tvDOS;
        LinearLayout ll;

        Button btnDownloadPA,btnDownloadRemark;

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
            tvFarmerName= (TextView) itemView.findViewById(R.id.tv_farmername);
            tvMobileNo= (TextView) itemView.findViewById(R.id.tv_mobile);
            tvWhatsAppNo= (TextView) itemView.findViewById(R.id.tvWhatsappno);
            tvlongitude= (TextView) itemView.findViewById(R.id.tv_coordinates);
            tvResAddr= (TextView) itemView.findViewById(R.id.tvaddress);
            tvNameOfHybrid= (TextView) itemView.findViewById(R.id.tvdemocrop);
            tvCheckHybrid= (TextView) itemView.findViewById(R.id.tvchkcrop);
            tvDOS= (TextView) itemView.findViewById(R.id.tvdos);
            ll= (LinearLayout) itemView.findViewById(R.id.ll);

            btnDownloadPA = (Button) itemView.findViewById(R.id.btnDownloadPA);
            btnDownloadRemark = (Button) itemView.findViewById(R.id.btnDownloadRemark);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ActionModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(actionModelArrayList_filter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ActionModel item : actionModelArrayList_filter) {
                    if (item.getFarmerName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            actionModelArrayList.clear();
            actionModelArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}

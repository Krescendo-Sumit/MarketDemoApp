package mahyco.market.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.model.CharatristicsMenuReportDetailsModel;
import mahyco.market.demo.model.ReportMasterModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.reportdetails.ReportDetailsActivity;
import mahyco.market.demo.view.reports.ReportAPI;


public class ReportDetailsSegmentMasterAdapter extends RecyclerView.Adapter<ReportDetailsSegmentMasterAdapter.DataObjectHolder> {


    Context context;
    SqlightDatabase sqlightDatabase;
    private static final int UNSELECTED = -1;
    ReportAPI pendingActionAPI;
    String data="";
    ArrayList<ReportMasterModel> actionModelArrayList = null;

    public interface EventListener {
        void onDelete(int trid, int position);
    }

    public ReportDetailsSegmentMasterAdapter(ArrayList<ReportMasterModel> actionModels, Context context) {

        this.actionModelArrayList = actionModels;
        Log.i("Action Count:", ">>" + actionModels.size());
        this.context = context;
        sqlightDatabase = new SqlightDatabase(context);

    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report_segment_master, parent, false);

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
            holder.txt_segment.setText(actionModel.getSegment());
            holder.btnDownloadPA.setText(actionModel.getUniqueSrNo());


            data="<table>";
            List<CharatristicsMenuReportDetailsModel> lst=sqlightDatabase.getTempChracteristics(actionModel.getMDDispatchSegmetnId());
            if(lst!=null&&lst.size()>=0) {
                String str="";
                for (CharatristicsMenuReportDetailsModel model : lst) {
                    try {
                        if(str.equals(model.getCharacteristics().trim()))
                        {
                            data += "<tr> " +
                                    "<td style='font-weight:bold;'>" + model.getMenuTitle() + " </td> " +
                                    "<td> &nbsp;:&nbsp; </td> " +
                                    "<td>" + model.getKeyValue() + " </td> " +
                                    "</tr>";
                        }
                        else {
                            data += "<tr> " +
                                    "<td style='font-weight:bold;background:gray;' colspan='3'>" + model.getCharacteristics() + " </td> </tr>" +
                                    "<tr><td style='font-weight:bold;'>" + model.getMenuTitle() + " </td> " +
                                    "<td> &nbsp;:&nbsp; </td> " +
                                    "<td>" + model.getKeyValue() + " </td> " +
                                    "</tr>";
                        }
                        str=model.getCharacteristics().trim();

                    } catch (Exception e) {

                    }
                }
                data += "</table>";
                holder.web.loadData(data, "text/html", null);
            }


            holder.btnDownloadPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


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
                tvMobileNo,txt_segment;


        Button btnDownloadPA;
        WebView web;

        public DataObjectHolder(View itemView) {
            super(itemView);


            tv_farmername=(TextView) itemView.findViewById(R.id.tv_farmername);
                  tvVillageName=(TextView) itemView.findViewById(R.id.tv_villagename);
                    tvMobileNo=(TextView) itemView.findViewById(R.id.tv_mobile);

            tvMDHybridName = (TextView) itemView.findViewById(R.id.tvMDHybridName);
            txt_segment = (TextView) itemView.findViewById(R.id.txt_segment);
            web = (WebView) itemView.findViewById(R.id.web);

            btnDownloadPA = (Button) itemView.findViewById(R.id.btnDownloadPA);
        }
    }


}

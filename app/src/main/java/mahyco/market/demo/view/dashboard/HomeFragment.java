package mahyco.market.demo.view.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.KeyValue;
import mahyco.market.demo.model.MessageModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.SowingMasterModel;
import mahyco.market.demo.model.UpdateSowingModel;
import mahyco.market.demo.util.AndroidDatabaseManager;
import mahyco.market.demo.util.MyApplicationUtil;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.actionlist.PendingActionList;
import mahyco.market.demo.view.pendingaction.PendingActionActivity;
import mahyco.market.demo.view.reports.ReportMaster;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeListener {
    View baseView;
    Context context;
    List<ActionModel> lst_actionModels;
    SqlightDatabase sqlightDatabase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    HomeAPI homeAPI;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_getPendingActions, btn_takeAction, btn_uplaod_pending_sowingdetails, btn_clear_local_data;
    Button btn_uplaod_update_sowingdetails, btn_takeActionUpdate, btn_view_report;
    TextView txt_user;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_home, container, false);
        baseView = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        sqlightDatabase = new SqlightDatabase(context);
        homeAPI = new HomeAPI(context, this);
        btn_getPendingActions = baseView.findViewById(R.id.btn_getpendingaction);
        btn_takeAction = baseView.findViewById(R.id.btn_takeAction);
        btn_uplaod_pending_sowingdetails = baseView.findViewById(R.id.btn_uplaod_pending_sowingdetails);
        btn_clear_local_data = baseView.findViewById(R.id.btn_clear_local_data);
        btn_uplaod_update_sowingdetails = baseView.findViewById(R.id.btn_uplaod_update_sowingdetails);
        btn_takeActionUpdate = baseView.findViewById(R.id.btn_takeActionUpdate);
        btn_view_report = baseView.findViewById(R.id.btn_view_report);
        txt_user = baseView.findViewById(R.id.txt_user);
        txt_user.setText("Welcome : " + Preferences.get(context, Preferences.USER_NAME) + " ( " + Preferences.get(context, Preferences.USER_ID) + " )");
        showUploadCount();
        txt_user.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, AndroidDatabaseManager.class);
                startActivity(intent);
                return false;
            }
        });

        btn_getPendingActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            /*    JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
                jsonObject.addProperty("FilterOption", "UserCode");
                homeAPI.getPendingActions(jsonObject);*/
                Intent intent = new Intent(context, PendingActionActivity.class);
                startActivity(intent);
            }
        });
        btn_view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            /*    JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
                jsonObject.addProperty("FilterOption", "UserCode");
                homeAPI.getPendingActions(jsonObject);*/
                Intent intent = new Intent(context, ReportMaster.class);
                startActivity(intent);
            }
        });
        btn_takeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            /*    JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
                jsonObject.addProperty("FilterOption", "UserCode");
                homeAPI.getPendingActions(jsonObject);*/
                Preferences.save(context, Preferences.PENDINGFOR_LOCALLIST, "1");
                Intent intent = new Intent(context, PendingActionList.class);
                startActivity(intent);
            }
        });

        btn_uplaod_pending_sowingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<SowingMasterModel> sowingMasterModels = sqlightDatabase.getLocalSowingDetails(0);
                    Toast.makeText(context, "" + sowingMasterModels.size(), Toast.LENGTH_SHORT).show();
                    JsonArray jsonArray = new JsonArray();
                    JsonObject jsonObject = new JsonObject();

                    for (SowingMasterModel m : sowingMasterModels) {
                        JsonObject json = new JsonObject();
                        json.addProperty("UniqueSrNo", m.getUniqueSrNo());
                        json.addProperty("FarmerName", m.getFarmerName());
                        json.addProperty("MobileNo", m.getMobileNo());
                        json.addProperty("WhatsAppNo", m.getWhatsAppNo());
                        json.addProperty("NameOfHybrid", m.getNameOfHybrid());
                        json.addProperty("CheckHybrid", m.getCheckHybrid());
                        json.addProperty("CompanyOfCheckHybrid", m.getCompanyOfCheckHybrid());
                        json.addProperty("DOS", m.getDOS());
                        json.addProperty("Latitude", m.getLatitude());
                        json.addProperty("longitude", m.getLongitude());
                        json.addProperty("ResAddr", m.getResAddr());
                        json.addProperty("ProductId", m.getProductId());
                        json.addProperty("PendingFor", m.getPendingFor());
                        json.addProperty("Village", m.getVillageName());

                        json.addProperty("Taluka", m.getTaluka());
                        json.addProperty("District", m.getDistrict());
                        json.addProperty("State", m.getState());

                        json.addProperty("UserCode", m.getUserCode());
                        json.addProperty("ImageName", m.getImageName());//
                        //   json.addProperty("ImageinByte","");// m.getImageinByte().replace("\n",""));
                        json.addProperty("ImageinByte", m.getImageinByte().replace("\n", ""));
                        jsonArray.add(json);
                    }
                    jsonObject.add("cropSowingModel", jsonArray);
                    Log.i("Json Array is ", jsonObject.toString());
                    homeAPI.uploadSowingDetails(jsonObject);

                } catch (Exception e) {

                }


            }
        });
        btn_clear_local_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sqlightDatabase.clearList()) {
                    Toast.makeText(context, "Data Cleared", Toast.LENGTH_SHORT).show();
                }
                showUploadCount();
            }
        });

        btn_uplaod_update_sowingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(context, "" + sqlightDatabase.getUpdateSowingDetails(0).size(), Toast.LENGTH_SHORT).show();
                try {
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String formattedDate = df.format(c);
                    ArrayList<UpdateSowingModel> sowingMasterModels = sqlightDatabase.getUpdateSowingDetails(0);
                    // Toast.makeText(context, "" + sowingMasterModels.size(), Toast.LENGTH_SHORT).show();
                    JsonArray jsonArray = new JsonArray();
                    JsonObject jsonObject = new JsonObject();
                    JsonArray jsonArrayImages = new JsonArray();
                    for (UpdateSowingModel m : sowingMasterModels) {
                        JsonObject json = new JsonObject();
                        json.addProperty("DemoCropSowingId", m.getDemoCropSowingId());//: 0,
                        json.addProperty("UniqueSrNo", m.getUniqueSrNo());//
                        json.addProperty("ProductId", m.getProductId());//
                        json.addProperty("ImageName", m.getImageName());//
                        if (m.getImageinByte() != null) {
                         //   json.addProperty("ImageinByte", m.getImageinByte().replace("\n", ""));//

                            JSONArray asJsonArray = new JSONArray(m.getImageinByte().trim());
                            for (int i = 0; i < asJsonArray.length(); i++) {
                                Log.i("Data" + i, asJsonArray.get(i).toString());
                                JSONObject jo=asJsonArray.getJSONObject(i);
                                JsonObject jsonObject1=new JsonObject();
                        //        jsonObject1.addProperty("ImageName",jo.getString("filename"));
                                jsonObject1.addProperty("ImageinByte", MyApplicationUtil.getImageDatadetail(jo.getString("filepath").trim()));
                         //       jsonObject1.addProperty("ImageinByte", "");
                                jsonObject1.addProperty("CreatedDt",formattedDate);
                                jsonArrayImages.add(jsonObject1);

                            }

                        } else {
                        //    json.addProperty("ImageinByte", "");//

                        }
                        json.addProperty("PendingFor", m.getPendingFor());//

                        json.addProperty("UserCode", m.getUserCode());//
                        JsonArray jsonArray_menu = new JsonArray();
                        JsonObject jsonObject_menu = new JsonObject();

                        //  Toast.makeText(context, ""+sqlightDatabase.getMenuList(m.getPendingFor(),m.getUniqueSrNo(),0).size(), Toast.LENGTH_SHORT).show();
                        for (KeyValue k : sqlightDatabase.getMenuList(m.getPendingFor(), m.getUniqueSrNo(), 0)) {
                            try {
                                jsonObject_menu = new JsonObject();
                                jsonObject_menu.addProperty("MenuId", k.getSb_id());
                                if (k.getValue() == null)
                                    jsonObject_menu.addProperty("KeyValue", "NA");
                                else

                                    jsonObject_menu.addProperty("KeyValue", k.getValue());
                                jsonObject_menu.addProperty("CreatedDt", k.getCreatedDt());
                                jsonArray_menu.add(jsonObject_menu);
                                Log.i("Added", "" + jsonObject_menu);
                            } catch (Exception e) {
                                Log.i("GoT Error ", e.getMessage());
                            }
                        }
                        json.add("cropCharacteristicsMenuModel", jsonArray_menu);

                        json.add("demoCropImagesModels",jsonArrayImages);
                        jsonArray.add(json);
                    }
                    jsonObject.add("cropAndCharSowingModel", jsonArray);
                   // jsonObject.add("demoCropImagesModels", jsonArrayImages);
                    Log.i("Json Array is ", jsonObject.toString());
                    homeAPI.uploadUdatedSowingDetails(jsonObject);
                }
                catch(Exception e)
                {

                }
            }
        });

        btn_takeActionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.save(context, Preferences.PENDINGFOR_LOCALLIST, "2");
                Intent intent = new Intent(context, PendingActionList.class);
                startActivity(intent);
            }
        });


        return baseView;
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onResponce(MessageModel messageModel) {
        if (messageModel.isSuccess()) {
            String unicno = Preferences.get(context, Preferences.SELECTED_UNIQSRID);

            if (sqlightDatabase.updateSowingMasterStatus(unicno.trim())) {
                Toast.makeText(context, "Data Sync Successfully.", Toast.LENGTH_SHORT).show();
            }
            showMessage(context,messageModel.isSuccess()?"Success":"Error" ,"" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.",false);

      //      Toast.makeText(context, "" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.", Toast.LENGTH_SHORT).show();
        } else {
    //        Toast.makeText(context, "" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            showMessage(context,messageModel.isSuccess()?"Success":"Error" ,"" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.",false);

        }
        if (messageModel.getMessage().contains("Data Already Present for Sowing")) {
            String unicno = Preferences.get(context, Preferences.SELECTED_UNIQSRID);

            if (sqlightDatabase.updateSowingMasterStatus(unicno.trim())) {
                Toast.makeText(context, "Data Sync Successfully.", Toast.LENGTH_SHORT).show();
            }
        }
        showUploadCount();
    }


    @Override
    public void onResponceUpdate(MessageModel messageModel) {
        if (messageModel.isSuccess()) {
            String unicno = Preferences.get(context, Preferences.SELECTED_UNIQSRID);
            if (sqlightDatabase.updateSowingUpdateMasterStatus(unicno.trim())) {
                Toast.makeText(context, "Data Sync Successfully.", Toast.LENGTH_SHORT).show();
            }
         //   Toast.makeText(context, "" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            showMessage(context,messageModel.isSuccess()?"Success":"Error" ,"" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.",false);
        } else {
            Toast.makeText(context, "" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            showMessage(context,messageModel.isSuccess()?"Success":"Error" ,"" + messageModel.getMessage() != null ? messageModel.getMessage() : "Something Went Wrong.",false
            );

        }
        showUploadCount();
    }

    void showMessage(Context context,String title,String message,boolean isFinishedActivity)
    {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(isFinishedActivity)
                            ((Activity)context).finish();
                    }
                })
                .show();
    }

    public void showUploadCount() {
        try {
            btn_uplaod_update_sowingdetails.setText("" + sqlightDatabase.getUpdateSowingDetails(0).size());
            btn_uplaod_pending_sowingdetails.setText("" + sqlightDatabase.getLocalSowingDetails(0).size());
        } catch (NullPointerException e) {

        }

    }

    @Override
    public void onListResponce(List result) {
        try {
            Toast.makeText(context, "" + result.size(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce(PendingActionModel result) {
        if (result.isSuccess()) {
            //Toast.makeText(context, "" + result.getAsJsonArray("PendingActions").size(), Toast.LENGTH_SHORT).show();
            lst_actionModels = result.getPendingActions();
            Toast.makeText(context, "" + lst_actionModels.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showUploadCount();
    }
}
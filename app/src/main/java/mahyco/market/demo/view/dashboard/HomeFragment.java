package mahyco.market.demo.view.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.MessageModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.SowingMasterModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.actionlist.PendingActionList;
import mahyco.market.demo.view.pendingaction.PendingActionActivity;

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
    Button btn_getPendingActions,btn_takeAction,btn_uplaod_pending_sowingdetails,btn_clear_local_data;


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
        sqlightDatabase=new SqlightDatabase(context);
        homeAPI = new HomeAPI(context, this);
        btn_getPendingActions = baseView.findViewById(R.id.btn_getpendingaction);
        btn_takeAction = baseView.findViewById(R.id.btn_takeAction);
        btn_uplaod_pending_sowingdetails = baseView.findViewById(R.id.btn_uplaod_pending_sowingdetails);
        btn_clear_local_data = baseView.findViewById(R.id.btn_clear_local_data);
       btn_uplaod_pending_sowingdetails.setText(Html.fromHtml("Pending Sowing Data : <b>"+sqlightDatabase.getLocalSowingDetails(0).size()+"</b>"));


        btn_getPendingActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            /*    JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
                jsonObject.addProperty("FilterOption", "UserCode");
                homeAPI.getPendingActions(jsonObject);*/
                Intent intent=new Intent(context, PendingActionActivity.class);
                startActivity(intent);
            }
        });

        btn_takeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();
            /*    JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
                jsonObject.addProperty("FilterOption", "UserCode");
                homeAPI.getPendingActions(jsonObject);*/
                Intent intent=new Intent(context, PendingActionList.class);
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
                        json.addProperty("FarmerName", m.getUniqueSrNo());
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
                        json.addProperty("UserCode", m.getUserCode());
                        jsonArray.add(json);
                    }
                    jsonObject.add("cropSowingModel", jsonArray);
                    Log.i("Json Array is ",jsonObject.toString());
                   homeAPI.uploadSowingDetails(jsonObject);

                }catch(Exception e)
                {

                }


            }
        });
        btn_clear_local_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sqlightDatabase.clearList())
                {
                    Toast.makeText(context, "Data Cleared", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return baseView;
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onResponce(MessageModel messageModel) {
        if(messageModel.isSuccess())
        {
            Toast.makeText(context, ""+messageModel.getMessage()!=null?messageModel.getMessage():"Something Went Wrong.", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, ""+messageModel.getMessage()!=null?messageModel.getMessage():"Something Went Wrong.", Toast.LENGTH_SHORT).show();
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
            lst_actionModels=result.getPendingActions();
            Toast.makeText(context, ""+lst_actionModels.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        btn_uplaod_pending_sowingdetails.setText(Html.fromHtml("Pending Sowing Data : <b>"+sqlightDatabase.getLocalSowingDetails(0).size()+"</b>"));

    }
}
package mahyco.market.demo.view.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.util.Preferences;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    HomeAPI homeAPI;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_getPendingActions;

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
        homeAPI = new HomeAPI(context, this);
        btn_getPendingActions = baseView.findViewById(R.id.btn_getpendingaction);
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

        return baseView;
    }

    @Override
    public void onResult(String result) {

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
}
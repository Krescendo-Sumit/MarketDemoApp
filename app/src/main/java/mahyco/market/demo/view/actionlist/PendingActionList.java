package mahyco.market.demo.view.actionlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.adapter.ActionAdapter;
import mahyco.market.demo.adapter.Action_Local_Adapter;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.pendingaction.PendingActionAPI;
import mahyco.market.demo.view.pendingaction.PendingActionActivity;

public class PendingActionList extends AppCompatActivity  {
    Context context;

    List<ActionModel> lst_actionModels;
    SqlightDatabase sqlightDatabase;
    RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    Action_Local_Adapter actionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_action_list);
        context= PendingActionList.this;
        sqlightDatabase=new SqlightDatabase(context);
        rc_pendingaction=findViewById(R.id.rc_pendingaction);
        mManager = new LinearLayoutManager(context);
        rc_pendingaction.setLayoutManager(mManager);
        int pendingfor=Integer.parseInt(Preferences.get(context,Preferences.PENDINGFOR_LOCALLIST));
        laodLocalAction(sqlightDatabase.getLocalActionsList(pendingfor));

    }
    public void laodLocalAction(List<ActionModel> lst_actionModels)
    {
        try{

            Toast.makeText(context, ""+lst_actionModels.size(), Toast.LENGTH_SHORT).show();
            actionAdapter = new Action_Local_Adapter((ArrayList) lst_actionModels, context);
            rc_pendingaction.setAdapter(actionAdapter);

        }catch (Exception e)
        {

        }
    }


}
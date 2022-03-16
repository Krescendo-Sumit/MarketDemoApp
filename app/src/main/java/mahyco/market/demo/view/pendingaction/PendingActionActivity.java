package mahyco.market.demo.view.pendingaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.adapter.ActionAdapter;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.util.Preferences;

public class PendingActionActivity extends AppCompatActivity implements PendingActionListener {
Context context;
PendingActionAPI pendingActionAPI;
List<ActionModel> lst_actionModels;
RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    ActionAdapter actionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_action);
        context=PendingActionActivity.this;
        pendingActionAPI=new PendingActionAPI(context,this);
        rc_pendingaction=findViewById(R.id.rc_pendingaction);
        mManager = new LinearLayoutManager(context);
        rc_pendingaction.setLayoutManager(mManager);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
        jsonObject.addProperty("FilterOption", "UserCode");
        pendingActionAPI.getPendingActions(jsonObject);
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {

    }

    @Override
    public void onListResponce(PendingActionModel result) {
        if (result.isSuccess()) {
            //Toast.makeText(context, "" + result.getAsJsonArray("PendingActions").size(), Toast.LENGTH_SHORT).show();
            lst_actionModels=result.getPendingActions();
            Toast.makeText(context, ""+lst_actionModels.size(), Toast.LENGTH_SHORT).show();
            //txt_days.setText(videos.size()+" Founds.");
            actionAdapter = new ActionAdapter((ArrayList) lst_actionModels, context);
            rc_pendingaction.setAdapter(actionAdapter);

        } else {
            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
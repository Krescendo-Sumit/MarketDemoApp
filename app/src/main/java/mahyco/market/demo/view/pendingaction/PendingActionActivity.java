package mahyco.market.demo.view.pendingaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.adapter.ActionAdapter;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.CharactristicsModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.VillageMasterModel;
import mahyco.market.demo.model.VillageModel;
import mahyco.market.demo.model.parametermodels.ParamterModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;

public class PendingActionActivity extends AppCompatActivity implements PendingActionListener {
Context context;
PendingActionAPI pendingActionAPI;
List<ActionModel> lst_actionModels;
List<VillageModel> lst_actionModels_villages;
RecyclerView rc_pendingaction;
    LinearLayoutManager mManager;
    ActionAdapter actionAdapter;
    SqlightDatabase sqlightDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_action);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context=PendingActionActivity.this;
        setTitle("Download Action");
        pendingActionAPI=new PendingActionAPI(context,this);
        rc_pendingaction=findViewById(R.id.rc_pendingaction);
        mManager = new LinearLayoutManager(context);
        rc_pendingaction.setLayoutManager(mManager);
        sqlightDatabase=new SqlightDatabase(context);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
        jsonObject.addProperty("FilterOption", "UserCode");
        pendingActionAPI.getPendingActions(jsonObject);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {
            // app icon in action bar clicked; goto parent activity.
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(CharactristicsModel result) {
       // Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(context, "" + result.isSuccess(), Toast.LENGTH_SHORT).show();

        List<ParamterModel> paramterModels = result.getEntityModel().getParamList();
        for (ParamterModel model : paramterModels) {

            Log.i("Row :", "" + new Gson().toJson(model));
        }
        if (sqlightDatabase.addCharactristics(paramterModels)) {
            Toast.makeText(context, "Characteristics Added Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Charactristics Not Added .", Toast.LENGTH_SHORT).show();
        }
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
            actionAdapter = new ActionAdapter((ArrayList) lst_actionModels, context ,pendingActionAPI);
            rc_pendingaction.setAdapter(actionAdapter);

        } else {
            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListResponceVillage(VillageMasterModel result) {
        if (result.isSuccess()) {
            //Toast.makeText(context, "" + result.getAsJsonArray("PendingActions").size(), Toast.LENGTH_SHORT).show();
            lst_actionModels_villages=result.getVillageList();
            Toast.makeText(context, "Village id "+lst_actionModels_villages.size(), Toast.LENGTH_SHORT).show();
            if(sqlightDatabase.addVillages(lst_actionModels_villages))
            {
                Toast.makeText(context, "Village Added.", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(context, "Village Not Added.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
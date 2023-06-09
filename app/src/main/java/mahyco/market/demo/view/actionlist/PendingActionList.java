package mahyco.market.demo.view.actionlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
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
    EditText et_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_action_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context= PendingActionList.this;
        sqlightDatabase=new SqlightDatabase(context);
        rc_pendingaction=findViewById(R.id.rc_pendingaction);
        mManager = new LinearLayoutManager(context);
        rc_pendingaction.setLayoutManager(mManager);
        int pendingfor=Integer.parseInt(Preferences.get(context,Preferences.PENDINGFOR_LOCALLIST));
        laodLocalAction(sqlightDatabase.getLocalActionsList(pendingfor));
        et_search=findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actionAdapter.getFilter().filter(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

}
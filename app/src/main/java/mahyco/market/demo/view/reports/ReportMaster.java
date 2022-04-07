package mahyco.market.demo.view.reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.adapter.ActionAdapter;
import mahyco.market.demo.adapter.ReportMasterAdapter;
import mahyco.market.demo.model.ReportMasterModel;
import mahyco.market.demo.util.Preferences;

public class ReportMaster extends AppCompatActivity implements ReportAPI.ReportAPIListener {
    ReportAPI reportAPI;
    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager mManager;
    ReportMasterAdapter reportMasterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_master);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = ReportMaster.this;
        setTitle("Reports");
        recyclerView = findViewById(R.id.recyclerView);
        mManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mManager);


        reportAPI = new ReportAPI(ReportMaster.this, this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.USER_ID));
        jsonObject.addProperty("FilterOption", "UserCode");
        reportAPI.getReportMasster(jsonObject);
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
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List<ReportMasterModel> result) {

        try {
            if (result != null) {
                reportMasterAdapter = new ReportMasterAdapter((ArrayList) result, context);
                recyclerView.setAdapter(reportMasterAdapter);
            } else {
                Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }

}
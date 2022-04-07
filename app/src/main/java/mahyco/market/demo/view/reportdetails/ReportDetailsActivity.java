package mahyco.market.demo.view.reportdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import mahyco.market.demo.R;
import mahyco.market.demo.adapter.ReportDetailsSegmentMasterAdapter;
import mahyco.market.demo.adapter.ReportMasterAdapter;
import mahyco.market.demo.model.ReportDetailsModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;

public class ReportDetailsActivity extends AppCompatActivity implements ReportDetailsAPI.Listener {
ReportDetailsAPI reportDetailsAPI;
Context context;
    ReportDetailsSegmentMasterAdapter reportDetailsSegmentMasterAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager mManager;
    SqlightDatabase sqlightDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context=ReportDetailsActivity.this;
        setTitle("Report Details");
        sqlightDatabase=new SqlightDatabase(context);
        recyclerView = findViewById(R.id.rc_segment);
        mManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mManager);


        reportDetailsAPI=new ReportDetailsAPI(context,this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("filterValue", Preferences.get(context, Preferences.SELECTED_UNIQSRID));
        jsonObject.addProperty("FilterOption", "UniqueSrNo");
        reportDetailsAPI.getReportDetailsMaster(jsonObject);
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
    public void onResult(ReportDetailsModel result) {
     //   Toast.makeText(context, "SowingDetails "+result.getDemoSegmentDetailsModel().size(), Toast.LENGTH_SHORT).show();
    //    Toast.makeText(context, "Charactristics Details "+result.getDemoSowingCharacteristicsMenuModel().size(), Toast.LENGTH_SHORT).show();
       try{

           try {
               if (result != null) {
                   reportDetailsSegmentMasterAdapter = new ReportDetailsSegmentMasterAdapter((ArrayList) result.getDemoSegmentDetailsModel(), context);
                   recyclerView.setAdapter(reportDetailsSegmentMasterAdapter);
               } else {
                   Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
               }
           } catch (Exception e) {

           }
           if(sqlightDatabase.deleteTempChractristics())
           {
              // Toast.makeText(context, "Record Removed.", Toast.LENGTH_SHORT).show();
           }
           if(sqlightDatabase.addTempChractristicsDetails(result.getDemoSowingCharacteristicsMenuModel()))
           {
            //   Toast.makeText(context, "Temp Data Added.", Toast.LENGTH_SHORT).show();
           }

       }catch(Exception r)
       {

       }


    }
}
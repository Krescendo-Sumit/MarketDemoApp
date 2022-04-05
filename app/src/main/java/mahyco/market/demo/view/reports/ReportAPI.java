package mahyco.market.demo.view.reports;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.market.demo.model.ReportMasterModel;
import mahyco.market.demo.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportAPI {


    Context context;
    String result = "";
    ProgressDialog progressDialog;
    ReportAPIListener resultOutput;

    public ReportAPI(Context context, ReportAPIListener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
    }
    public interface ReportAPIListener {
        public void onResult(String result);

        public void onListResponce(List<ReportMasterModel> result);
    }
    public void getReportMasster(JsonObject jsonObject) {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<List<ReportMasterModel>> call = RetrofitClient.getInstance().getMyApi().getReportMaster(jsonObject);
            call.enqueue(new Callback<List<ReportMasterModel>>() {
                @Override
                public void onResponse(Call<List<ReportMasterModel>> call, Response<List<ReportMasterModel>> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        List<ReportMasterModel> result = response.body();
                        try {
                            resultOutput.onListResponce(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ReportMasterModel>> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
   
}

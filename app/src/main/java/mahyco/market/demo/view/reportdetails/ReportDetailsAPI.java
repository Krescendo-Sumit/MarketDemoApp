package mahyco.market.demo.view.reportdetails;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.market.demo.model.ReportDetailsModel;
import mahyco.market.demo.model.ReportMasterModel;
import mahyco.market.demo.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDetailsAPI {


    Context context;
    String result = "";
    ProgressDialog progressDialog;
    Listener resultOutput;

    public ReportDetailsAPI(Context context, Listener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
    }
    public interface Listener {
        public void onResult(ReportDetailsModel result);

    }
    public void getReportDetailsMaster(JsonObject jsonObject) {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<ReportDetailsModel> call = RetrofitClient.getInstance().getMyApi().getReportDetailsMaster(jsonObject);
            call.enqueue(new Callback<ReportDetailsModel>() {
                @Override
                public void onResponse(Call<ReportDetailsModel> call, Response<ReportDetailsModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        ReportDetailsModel result = response.body();
                        try {
                            resultOutput.onResult(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ReportDetailsModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
   
}

package mahyco.market.demo.view.pendingaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.VillageMasterModel;
import mahyco.market.demo.model.VillageModel;
import mahyco.market.demo.util.RetrofitClient;
import mahyco.market.demo.view.dashboard.HomeListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingActionAPI {


        Context context;
        String result = "";
        ProgressDialog progressDialog;
        PendingActionListener resultOutput;

        public PendingActionAPI(Context context, PendingActionListener resultOutput) {
            this.context = context;
            this.resultOutput = resultOutput;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait..");
        }
        public void getPendingActions(JsonObject jsonObject)
        {
            try {

                if (!progressDialog.isShowing())
                    progressDialog.show();

                Call<PendingActionModel> call = RetrofitClient.getInstance().getMyApi().getPendingActions(jsonObject);
                call.enqueue(new Callback<PendingActionModel>() {
                    @Override
                    public void onResponse(Call<PendingActionModel> call, Response<PendingActionModel> response) {

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                        if (response.body() != null) {
                            PendingActionModel result = response.body();
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
                    public void onFailure(Call<PendingActionModel> call, Throwable t) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Error is", t.getMessage());
                    }
                });
            } catch (Exception e) {

            }
        }
    public void getVillageList(JsonObject jsonObject)
    {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<VillageMasterModel> call = RetrofitClient.getInstance().getMyApi().getVillagesList(jsonObject);
            call.enqueue(new Callback<VillageMasterModel>() {
                @Override
                public void onResponse(Call<VillageMasterModel> call, Response<VillageMasterModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {

                        VillageMasterModel result = response.body();
                        try {
                            resultOutput.onListResponceVillage(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<VillageMasterModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }




}
package mahyco.market.demo.view.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import mahyco.market.demo.model.MessageModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.util.RetrofitClient;
import mahyco.market.demo.view.login.LoginAPIListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAPI {
    Context context;
    String result = "";
    ProgressDialog progressDialog;
    HomeListener resultOutput;

    public HomeAPI(Context context, HomeListener resultOutput) {
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
    public void uploadSowingDetails(JsonObject jsonObject)
    {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<MessageModel> call = RetrofitClient.getInstance().getMyApi().uploadSowingDetails(jsonObject);
            call.enqueue(new Callback<MessageModel>() {
                @Override
                public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        MessageModel result = response.body();
                        try {
                            resultOutput.onResponce(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
    public void uploadUdatedSowingDetails(JsonObject jsonObject)
    {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<MessageModel> call = RetrofitClient.getInstance().getMyApi().uploadUpdateSowingDetails(jsonObject);
            call.enqueue(new Callback<MessageModel>() {
                @Override
                public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //  Toast.makeText(CourseList.this, "Calling..", Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        MessageModel result = response.body();
                        try {
                            resultOutput.onResponceUpdate(result);
                        } catch (NullPointerException e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
}

package mahyco.market.demo.view.sowingupdate;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import mahyco.market.demo.model.CharactristicsModel;
import mahyco.market.demo.util.RetrofitClient;
import mahyco.market.demo.view.login.LoginAPIListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SowingUpdateAPI {


    Context context;
    String result = "";
    ProgressDialog progressDialog;
    SowingUpdateAPIListener resultOutput;

    public SowingUpdateAPI(Context context, SowingUpdateAPIListener resultOutput) {
        this.context = context;
        this.resultOutput = resultOutput;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
    }
    public void getChartristics(JsonObject jsonObject) {
        try {

            if (!progressDialog.isShowing())
                progressDialog.show();

            Call<CharactristicsModel> call = RetrofitClient.getInstance().getMyApi().getChractristics(jsonObject);
            call.enqueue(new Callback<CharactristicsModel>() {
                @Override
                public void onResponse(Call<CharactristicsModel> call, Response<CharactristicsModel> response) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body() != null) {
                        CharactristicsModel result = response.body();
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
                public void onFailure(Call<CharactristicsModel> call, Throwable t) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Error is", t.getMessage());
                }
            });
        } catch (Exception e) {

        }
    }
   
}

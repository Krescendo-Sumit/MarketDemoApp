package mahyco.market.demo.view.sowingupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.R;
import mahyco.market.demo.model.CharactristicsModel;
import mahyco.market.demo.model.LocalCharactersticsModel;
import mahyco.market.demo.model.parametermodels.DataMDVisitModel;
import mahyco.market.demo.model.parametermodels.ParamterModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;

public class SowingUpdateActivity extends AppCompatActivity implements SowingUpdateAPIListener {
   SowingUpdateAPI sowingUpdateAPI;
   Context context;
   JsonObject jsonObject;
   int pendingfor,productid;
   SqlightDatabase sqlightDatabase;
ArrayList<LocalCharactersticsModel> arrayList;
LinearLayout ll;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sowing_update);
        context=SowingUpdateActivity.this;
        sowingUpdateAPI=new SowingUpdateAPI(context,this);
        sqlightDatabase=new SqlightDatabase(context);
       ll=(LinearLayout)findViewById(R.id.ll);
        try{

            pendingfor= Integer.parseInt(Preferences.get(context,Preferences.SELECTED_PENDINGFOR).toString().trim());
            productid= Integer.parseInt(Preferences.get(context,Preferences.SELECTED_PRODUCTCODE).toString().trim());
            jsonObject=new JsonObject();
            jsonObject.addProperty("ProductId", productid);
            jsonObject.addProperty("VisitStageId", pendingfor);
         //   sowingUpdateAPI.getChartristics(jsonObject);
             arrayList=sqlightDatabase.getChracteristics(""+productid,Preferences.get(context,Preferences.SELECTED_UNIQSRID));
            Toast.makeText(context, "Size "+arrayList.size(), Toast.LENGTH_SHORT).show();

            for(LocalCharactersticsModel model:arrayList)
            {
               // Log.i("Parameters",model.getParamList());
                 JSONObject jsonObject=new JSONObject(model.getParamList());
              //   Log.i("Title :",jsonObject.getString("CharacteristicsTitle"));
                TextView txt_title=new TextView(context);
                txt_title.setText(jsonObject.getString("CharacteristicsTitle").trim());
                ll.addView(txt_title);
                JSONArray jsonArray=new JSONArray(jsonObject.getString("data_mdvisit"));
                 for(int i=0;i<jsonArray.length();i++)
                 {
                     JSONObject jsonObject1=jsonArray.getJSONObject(i);
                     Log.i("InputType : ",jsonObject1.getString("inputType"));
                     if(jsonObject1.getString("inputType").equals("EditText"))
                     {
                         EditText editText=new EditText(context);
                         ll.addView(editText);
                     }else if(jsonObject1.getString("inputType").equals("Dropdown"))
                     {
                         Spinner spinner=new Spinner(context);
                         String strarr[];
                         JSONArray sp_values=new JSONArray(jsonObject1.getString("spinnerValues"));
                          strarr=new String[sp_values.length()];
                          for(int j=0;j<sp_values.length();j++)
                          {
                              JSONObject jsonobj_spinnerval=sp_values.getJSONObject(j);
                              strarr[j]=jsonobj_spinnerval.getString("value");
                          }
                         ArrayAdapter arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_list_item_1,strarr);
                         spinner.setAdapter(arrayAdapter);
                         ll.addView(spinner);
                     }else if(jsonObject1.getString("inputType").equals("CheckBox"))
                     {
                         CheckBox chk=new CheckBox(context);
                         ll.addView(chk);
                     }

                 }


               //  Log.i("Title :",jsonObject.getString("data_mdvisit"));
            }
            Button btn=new Button(context);
            btn.setText("Save Details");
            ll.addView(btn);
        }catch (Exception e)
        {
        }
    }

    @Override
    public void onResult(CharactristicsModel result) {

        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, ""+result.isSuccess(), Toast.LENGTH_SHORT).show();

        List<ParamterModel> paramterModels=result.getEntityModel().getParamList();
        for(ParamterModel model:paramterModels)
        {

            Log.i("Row :",""+new Gson().toJson(model));
        }
       if(sqlightDatabase.addCharactristics(paramterModels))
       {
           Toast.makeText(context, "Characteristics Added Success", Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(context, "Charactristics Not Added .", Toast.LENGTH_SHORT).show();
       }

    }

    @Override
    public void onListResponce(List result) {

    }
}
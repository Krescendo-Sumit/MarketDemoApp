package mahyco.market.demo.view.sowingupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mahyco.market.demo.MainActivity;
import mahyco.market.demo.R;
import mahyco.market.demo.model.CharactristicsModel;
import mahyco.market.demo.model.KeyValue;
import mahyco.market.demo.model.LocalCharactersticsModel;
import mahyco.market.demo.model.parametermodels.ParamterModel;
import mahyco.market.demo.util.MyApplicationUtil;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;
import mahyco.market.demo.view.sowingaction.AddNewSowingDetails;

public class SowingUpdateActivity extends AppCompatActivity  implements IPickResult {

    Context context;
    JsonObject jsonObject;
    int pendingfor, productid,democropsowingid;
    String uniqueid,usercode;
    SqlightDatabase sqlightDatabase;
    ArrayList<LocalCharactersticsModel> arrayList;
    LinearLayout ll;
    ArrayList<KeyValue> arrayList_keyvalues;
    TextView  txt_chooseimage;
    ImageView img_famerimage;
    String file_path,base64_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sowing_update);
        context = SowingUpdateActivity.this;
        txt_chooseimage = findViewById(R.id.txt_choose_image);
        img_famerimage = findViewById(R.id.img_farmer);
        txt_chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        sqlightDatabase = new SqlightDatabase(context);
        arrayList_keyvalues = new ArrayList();
        ll = (LinearLayout) findViewById(R.id.ll);
        try {

            pendingfor = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_PENDINGFOR).toString().trim());
            uniqueid = Preferences.get(context, Preferences.SELECTED_UNIQSRID).toString().trim();
            setTitle(""+uniqueid);
            usercode = Preferences.get(context, Preferences.USER_ID).toString().trim();
            productid = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_PRODUCTCODE).toString().trim());
            democropsowingid = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_DEMOCROPSOWINGID).toString().trim());
            jsonObject = new JsonObject();
            jsonObject.addProperty("ProductId", productid);
            jsonObject.addProperty("VisitStageId", pendingfor);
            //   sowingUpdateAPI.getChartristics(jsonObject);
            arrayList = sqlightDatabase.getChracteristics("" + productid, Preferences.get(context, Preferences.SELECTED_UNIQSRID));
            Toast.makeText(context, "Size " + arrayList.size(), Toast.LENGTH_SHORT).show();

            for (LocalCharactersticsModel model : arrayList) {
                try {
                    // Log.i("Parameters",model.getParamList());
                    JSONObject jsonObject = new JSONObject(model.getParamList());
                    //Log.i("Title :",jsonObject.getString("CharacteristicsTitle"));
                    TextView txt_title = new TextView(context);
                    txt_title.setText(jsonObject.getString("CharacteristicsTitle").trim());
                    txt_title.setTypeface(null, Typeface.BOLD);
                    txt_title.setTextSize(14);
                    ll.addView(txt_title);
                    LinearLayout linearLayout=new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            1.0f
                    );

                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data_mdvisit"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            KeyValue keyValue = new KeyValue();
                            keyValue.setPendingFor(pendingfor);
                            keyValue.setUniqueId(uniqueid);
                            keyValue.setCreatedDt("2022/03/25");
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Log.i("InputType : ", jsonObject1.getString("inputType"));
                            if (jsonObject1.getString("inputType").equals("EditText")) {
                                EditText editText = new EditText(context);

                                editText.setId(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setSb_id(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setInputype(jsonObject1.getString("inputType"));
                                ll.addView(editText);
                            } else if (jsonObject1.getString("inputType").equals("Dropdown")) {
                                TextView txt_titles = new TextView(context);
                                txt_titles.setText(jsonObject1.getString("title").trim());
                                ll.addView(txt_titles);
                                Spinner spinner = new Spinner(context);
                                spinner.setId(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setSb_id(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setInputype(jsonObject1.getString("inputType"));
                                String strarr[];
                                JSONArray sp_values = new JSONArray(jsonObject1.getString("spinnerValues"));
                                strarr = new String[sp_values.length()];
                                for (int j = 0; j < sp_values.length(); j++) {
                                    JSONObject jsonobj_spinnerval = sp_values.getJSONObject(j);
                                    strarr[j] = jsonobj_spinnerval.getString("value");
                                }
                                ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, strarr);
                                spinner.setAdapter(arrayAdapter);
                                ll.addView(spinner);
                            } else if (jsonObject1.getString("inputType").equals("CheckBox")) {
                                CheckBox chk = new CheckBox(context);
                                chk.setId(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setSb_id(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setInputype(jsonObject1.getString("inputType"));
                                ll.addView(chk);
                            }
                            arrayList_keyvalues.add(keyValue);
                        } catch (Exception e) {

                        }
                    }
                } catch (Exception e) {

                }


                //  Log.i("Title :",jsonObject.getString("data_mdvisit"));
            }
            Button btn = new Button(context);
            btn.setText("Save Details");

            ll.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (KeyValue k:arrayList_keyvalues)
                    {
                        if(k.getInputype().equals("EditText"))
                        {
                            String value=((EditText)findViewById(k.getSb_id())).getText().toString().trim();
                            Log.i("Value in Edit Text",value);
                            k.setValue(value);
                        }else if(k.getInputype().equals("Dropdown"))
                        {
                            String value=((Spinner)findViewById(k.getSb_id())).getSelectedItem().toString().trim();
                            Log.i("Value in Spinner",value);
                            k.setValue(value);
                        }
                        else if(k.getInputype().equals("CheckBox"))
                        {
                            String value=""+((CheckBox)findViewById(k.getSb_id())).isChecked();
                            Log.i("Value in Check Box",value);
                            k.setValue(value);
                        }
                        else
                        {

                        }
                    }

                    if(sqlightDatabase.addSowingUpdateMaster(democropsowingid,uniqueid,productid,"",base64_image,pendingfor,usercode))
                    {
                        Toast.makeText(context, "Master Data Added.", Toast.LENGTH_SHORT).show();

                    }
                    if(sqlightDatabase.addMenus(arrayList_keyvalues))
                    {
                        Toast.makeText(context, "Menu Details Added Successfully.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(context, "Record Saved", Toast.LENGTH_SHORT).show();

                    //  Toast.makeText(context, "" + arrayList_keyvalues.size(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Error is "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        try {
            img_famerimage.setImageBitmap(r.getBitmap());
            file_path=r.getPath();
            base64_image = MyApplicationUtil.getImageDatadetail(r.getPath());
        } catch (Exception e) {

        }
    }
}
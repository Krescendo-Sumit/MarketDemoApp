package mahyco.market.demo.view.sowingupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    String uniqueid,usercode,cropstage="";
    SqlightDatabase sqlightDatabase;
    ArrayList<LocalCharactersticsModel> arrayList;
    LinearLayout ll;
    ArrayList<KeyValue> arrayList_keyvalues;
    TextView  txt_chooseimage,txt_chooseimage1,txt_chooseimage2,txt_chooseimage3,txt_chooseimage4;
    ImageView img_famerimage,img_famerimage1,img_famerimage2,img_famerimage3,img_famerimage4;
    String file_path="",base64_image="";
    int imageid=0;

    JsonObject jsonObjectImages;
    JsonArray jsonArrayImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sowing_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = SowingUpdateActivity.this;
        txt_chooseimage = findViewById(R.id.txt_choose_image);
        txt_chooseimage1 = findViewById(R.id.txt_choose_image1);
        txt_chooseimage2 = findViewById(R.id.txt_choose_image2);
        txt_chooseimage3 = findViewById(R.id.txt_choose_image3);
        txt_chooseimage4 = findViewById(R.id.txt_choose_image4);
        img_famerimage = findViewById(R.id.img_farmer);
        img_famerimage1 = findViewById(R.id.img_farmer1);
        img_famerimage2 = findViewById(R.id.img_farmer2);
        img_famerimage3 = findViewById(R.id.img_farmer3);
        img_famerimage4 = findViewById(R.id.img_farmer4);
        jsonArrayImages=new JsonArray();
        txt_chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageid=1;
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        txt_chooseimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageid=2;
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        txt_chooseimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageid=3;
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        txt_chooseimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageid=4;
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        txt_chooseimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageid=5;
                PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(SowingUpdateActivity.this);
            }
        });
        sqlightDatabase = new SqlightDatabase(context);
        arrayList_keyvalues = new ArrayList();
        ll = (LinearLayout) findViewById(R.id.ll);
        try {

            pendingfor = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_PENDINGFOR).toString().trim());
            uniqueid = Preferences.get(context, Preferences.SELECTED_UNIQSRID).toString().trim();
            cropstage = Preferences.get(context, Preferences.SELECTED_STAGE).toString().trim();

            setTitle("  "+cropstage+" - "+uniqueid);
            usercode = Preferences.get(context, Preferences.USER_ID).toString().trim();
            productid = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_PRODUCTCODE).toString().trim());
            democropsowingid = Integer.parseInt(Preferences.get(context, Preferences.SELECTED_DEMOCROPSOWINGID).toString().trim());
            jsonObject = new JsonObject();
            jsonObject.addProperty("ProductId", productid);
            jsonObject.addProperty("VisitStageId", pendingfor);
            //   sowingUpdateAPI.getChartristics(jsonObject);
            arrayList = sqlightDatabase.getChracteristics("" + productid, uniqueid);
          //  Toast.makeText(context, "Size " + arrayList.size(), Toast.LENGTH_SHORT).show();

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
                            LinearLayout linearLayout1=new LinearLayout(context);
                            linearLayout1.setOrientation(LinearLayout.VERTICAL);
                            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT

                            );

                            KeyValue keyValue = new KeyValue();
                            keyValue.setPendingFor(pendingfor);
                            keyValue.setUniqueId(uniqueid);
                            Date c = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                            String formattedDate = df.format(c);
                            keyValue.setCreatedDt(""+formattedDate);
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Log.i("InputType : ", jsonObject1.getString("inputType"));
                            if (jsonObject1.getString("inputType").equals("Edit Text")) {

//                                TextView txt_titles = new TextView(context);
//                                txt_titles.setText(jsonObject1.getString("title").trim());
//                                txt_titles.setLayoutParams(param1);
//                                linearLayout1.addView(txt_titles);


                                EditText editText = new EditText(context);
                                editText.setHint(jsonObject1.getString("title").trim());
                                editText.setLayoutParams(param1);
                                editText.setWidth(300);

                                editText.setId(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setSb_id(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setInputype(jsonObject1.getString("inputType"));
                                linearLayout1.addView(editText);
                            } else if (jsonObject1.getString("inputType").equals("Dropdown")) {
                                TextView txt_titles = new TextView(context);
                                txt_titles.setText(jsonObject1.getString("title").trim());
                                txt_titles.setLayoutParams(param1);
                                linearLayout1.addView(txt_titles);
                                Spinner spinner = new Spinner(context);
                                spinner.setLayoutParams(param1);
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
                                linearLayout1.addView(spinner);
                            } else if (jsonObject1.getString("inputType").equals("CheckBox")) {
                                CheckBox chk = new CheckBox(context);
                                chk.setId(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setSb_id(Integer.parseInt(jsonObject1.getString("sb_id").trim()));
                                keyValue.setInputype(jsonObject1.getString("inputType"));
                                linearLayout1.addView(chk);
                            }
                            arrayList_keyvalues.add(keyValue);
                            linearLayout.addView(linearLayout1);
                        } catch (Exception e) {

                        }
                    }
                    ll.addView(linearLayout);
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
                        if(k.getInputype().equals("Edit Text"))
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

                    base64_image=jsonArrayImages.toString();
                    if(jsonArrayImages.size()==0)
                    {
                        Toast.makeText(context, "Please take a photo.", Toast.LENGTH_SHORT).show();
                    }else {
                        if (sqlightDatabase.addSowingUpdateMaster(democropsowingid, uniqueid, productid, "", base64_image, pendingfor, usercode)) {
                            Toast.makeText(context, "Master Data Added.", Toast.LENGTH_SHORT).show();
                        }
                        if (sqlightDatabase.addMenus(arrayList_keyvalues)) {
                            Toast.makeText(context, "Menu Details Added Successfully.", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(context, "Record Saved", Toast.LENGTH_SHORT).show();

                        //  Toast.makeText(context, "" + arrayList_keyvalues.size(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Error is "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
    @Override
    public void onPickResult(PickResult r) {
        try {

            File file=new File(r.getPath());
            jsonObjectImages=new JsonObject();
            jsonObjectImages.addProperty("filepath",r.getPath());
            jsonObjectImages.addProperty("filename",file.getName());
            jsonObjectImages.addProperty("id",imageid);
            jsonArrayImages.add(jsonObjectImages);

            switch(imageid)
            {
                case 1:
                    img_famerimage.setVisibility(View.VISIBLE);
                    img_famerimage.setImageBitmap(r.getBitmap());
                    break;

                case 2:
                    img_famerimage1.setVisibility(View.VISIBLE);
                    img_famerimage1.setImageBitmap(r.getBitmap());
                    break;

                case 3:
                    img_famerimage2.setVisibility(View.VISIBLE);
                    img_famerimage2.setImageBitmap(r.getBitmap());
                    break;

                case 4:
                    img_famerimage3.setVisibility(View.VISIBLE);
                    img_famerimage3.setImageBitmap(r.getBitmap());
                    break;

                case 5:
                    img_famerimage4.setVisibility(View.VISIBLE);
                    img_famerimage4.setImageBitmap(r.getBitmap());
                    break;
            }



     /*       base64_image="";
            img_famerimage.setImageBitmap(r.getBitmap());
            file_path=r.getPath();
            base64_image = MyApplicationUtil.getImageDatadetail(r.getPath());*/
        } catch (Exception e) {

        }
    }
}
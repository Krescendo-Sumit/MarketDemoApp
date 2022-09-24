package mahyco.market.demo.view.sowingaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mahyco.market.demo.MainActivity;
import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.SowingMasterModel;
import mahyco.market.demo.model.VillageModel;
import mahyco.market.demo.util.MyApplicationUtil;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;


public class AddNewSowingDetails extends AppCompatActivity implements IPickResult {
    String uniqueSrId;
    Context context;
    ActionModel actionModel;
    SqlightDatabase sqlightDatabase;
    EditText edUno,
            edState,
            edDistrict,
            edBlockTaluka,
            edFarmerName,
            edFarmerMoNo,
            edFarmerWANo,
            edMDHybridName,
            edCheckHybridName,
            edCheckHybridCompany,
            edResAddr, edDateOfSowing;

    TextView edGeoTagging, txt_chooseimage;

    ImageView img_famerimage;
    Button btnSowingSubmit;
    SearchableSpinner sp_village_code;
    ArrayList<VillageModel> villageModelsList;
    String UniqueSrNo;//
    String FarmerName;//  TEXT," +
    String MobileNo;// TEXT," +
    String WhatsAppNo;// TEXT," +
    String NameOfHybrid;// TEXT," +
    String CheckHybrid;// TEXT," +
    String CompanyOfCheckHybrid;// TEXT," +
    String DOS;// TEXT," +
    String Latitude;// TEXT," +
    String longitude;// TEXT," +
    String ResAddr;// TEXT," +
    int ProductId;// INTEGER," +
    int PendingFor;// INTEGER," +
    String VillageName;// TEXT," +
    String State;// TEXT," +
    String District;// TEXT," +
    String Taluka;// TEXT," +
    String UserCode;// TEXT," +
    int SyncStatus;// INTERGER," +
    int DownlaodStatus;// INTEGER"
    int mYear, mMonth, mDay;
    SowingMasterModel sowingMasterModel;
    private FusedLocationProviderClient fusedLocationClient;
    String file_path, base64_image;
    SowingMasterModel localSowingMasterModel;
    int action = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sowing_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

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
    public void init() {
        try {

            context = AddNewSowingDetails.this;
            sqlightDatabase = new SqlightDatabase(context);
            sowingMasterModel = new SowingMasterModel();
            uniqueSrId = Preferences.get(context, Preferences.SELECTED_UNIQSRID);
            setTitle("New Sowing -" + uniqueSrId);
            Toast.makeText(context, "" + uniqueSrId, Toast.LENGTH_SHORT).show();

            edUno = findViewById(R.id.edUno);
            edState = findViewById(R.id.edState);
            edDistrict = findViewById(R.id.edDistrict);
            edBlockTaluka = findViewById(R.id.edBlockTaluka);
            sp_village_code = findViewById(R.id.sp_village_code);
            edFarmerName = findViewById(R.id.edFarmerName);
            edFarmerMoNo = findViewById(R.id.edFarmerMoNo);
            edFarmerWANo = findViewById(R.id.edFarmerWANo);
            edMDHybridName = findViewById(R.id.edMDHybridName);
            edCheckHybridName = findViewById(R.id.edCheckHybridName);
            edCheckHybridCompany = findViewById(R.id.edCheckHybridCompany);
            edDateOfSowing = findViewById(R.id.edDateOfSowing);
            edGeoTagging = findViewById(R.id.edGeoTagging);
            edResAddr = findViewById(R.id.edResAddress);
            btnSowingSubmit = findViewById(R.id.btnSowingSubmit);
            txt_chooseimage = findViewById(R.id.txt_choose_image);
            img_famerimage = findViewById(R.id.img_farmer);
            edMDHybridName.setText(""+Preferences.get(context,Preferences.SELECTED_DEMOCROPNAME));

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        // Logic to handle location object

                        Latitude = "" + location.getLatitude();
                        longitude = "" + location.getLongitude();
                        //Toast.makeText(context, "Location Latitude : " + location.getLatitude() + " Longitude :" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        edGeoTagging.setText(location.getLatitude() + "," + location.getLongitude());
                    }
                }
            });




            txt_chooseimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup().setPickTypes(EPickType.CAMERA)).show(AddNewSowingDetails.this);
                }
            });
            edDateOfSowing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //To show current date in the datepicker
                    Calendar mcurrentDate = Calendar.getInstance();
                    mYear = mcurrentDate.get(Calendar.YEAR);
                    mMonth = mcurrentDate.get(Calendar.MONTH);
                    mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                    final DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            // TODO Auto-generated method stub
                            /*      Your code   to get date and time    */

                            String ssm = "", ssd = "";
                            if ((selectedmonth + 1) < 10)
                                ssm = "0" + (selectedmonth + 1);
                            else
                                ssm = "" + (selectedmonth + 1);
                            if ((selectedday) < 10)
                                ssd = "0" + selectedday;
                            else
                                ssd = "" + selectedday;

                            String dd = selectedyear + "/" + (ssm) + "/" + ssd;
                            edDateOfSowing.setText(dd);

                        }
                    }, mYear, mMonth, mDay);
                    //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                    mDatePicker.setTitle("Sowing Date");
                    mDatePicker.show();

                }
            });


            actionModel = sqlightDatabase.getLocalActionModel(uniqueSrId);

            if (actionModel != null) {
                //Toast.makeText(context, "" + actionModel.getDistrictCode(), Toast.LENGTH_SHORT).show();

                edUno.setText(actionModel.getUniqueSrNo());
                edState.setText(actionModel.getState());
                edDistrict.setText(actionModel.getDistrictName());
                edBlockTaluka.setText(actionModel.getTalukaName());
                villageModelsList = sqlightDatabase.getLocalVillage(actionModel.getTalukaName());
                ArrayAdapter adapter = new ArrayAdapter(context,
                        android.R.layout.simple_spinner_item, sqlightDatabase.getVillageTitleFromList(villageModelsList));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_village_code.setAdapter(adapter);
            }

            btnSowingSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validateUIElement()) {
                        if (action == 0) {

                            sowingMasterModel.setUniqueSrNo(UniqueSrNo);//
                            sowingMasterModel.setFarmerName(FarmerName);//  TEXT," +
                            sowingMasterModel.setMobileNo(MobileNo);// TEXT," +
                            sowingMasterModel.setWhatsAppNo(WhatsAppNo);// TEXT," +
                            sowingMasterModel.setNameOfHybrid(NameOfHybrid);// TEXT," +
                            sowingMasterModel.setCheckHybrid(CheckHybrid);// TEXT," +
                            sowingMasterModel.setCompanyOfCheckHybrid(CompanyOfCheckHybrid);// TEXT," +
                            sowingMasterModel.setDOS(DOS);// TEXT," +
                            sowingMasterModel.setLatitude(Latitude);// TEXT," +
                            sowingMasterModel.setLongitude(longitude);// TEXT," +
                            sowingMasterModel.setResAddr(ResAddr);// TEXT," +
                            sowingMasterModel.setProductId(ProductId);// INTEGER," +
                            sowingMasterModel.setPendingFor(PendingFor);// INTEGER," +
                            sowingMasterModel.setVillageName(VillageName);// TEXT," +
                            sowingMasterModel.setState(State);// TEXT," +
                            sowingMasterModel.setDistrict(District);// TEXT," +
                            sowingMasterModel.setTaluka(Taluka);// TEXT," +
                            sowingMasterModel.setUserCode(UserCode);// TEXT," +
                            sowingMasterModel.setSyncStatus(SyncStatus);// INTERGER," +
                            sowingMasterModel.setDownlaodStatus(DownlaodStatus);// INTEGER"
                           sowingMasterModel.setImageName("");// INTEGER"
                              sowingMasterModel.setImageinByte(base64_image);// INTEGER"
                          //  sowingMasterModel.setImageinByte("");// INTEGER"


                            if (sqlightDatabase.addSowingMaster(sowingMasterModel, action)) {
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                Toast.makeText(context, "Record Saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            localSowingMasterModel.setFarmerName(FarmerName);//  TEXT," +
                            localSowingMasterModel.setMobileNo(MobileNo);// TEXT," +
                            localSowingMasterModel.setWhatsAppNo(WhatsAppNo);// TEXT," +
                            localSowingMasterModel.setNameOfHybrid(NameOfHybrid);// TEXT," +
                            localSowingMasterModel.setCheckHybrid(CheckHybrid);// TEXT," +
                            localSowingMasterModel.setCompanyOfCheckHybrid(CompanyOfCheckHybrid);// TEXT," +
                            localSowingMasterModel.setDOS(DOS);
                            localSowingMasterModel.setResAddr(ResAddr);// TEXT," +
                            if (sqlightDatabase.addSowingMaster(localSowingMasterModel, action)) {
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                Toast.makeText(context, "Record Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });



            localSowingMasterModel = sqlightDatabase.getLocalSowingDetailsByUniqueNo(uniqueSrId);
            //  Toast.makeText(context, "" + localSowingMasterModel.getFarmerName(), Toast.LENGTH_SHORT).show();

            if (localSowingMasterModel != null) {
                Toast.makeText(context, "" + localSowingMasterModel.getFarmerName(), Toast.LENGTH_SHORT).show();
                action = 1;
                loadData();
            }


        } catch (Exception e) {
            Toast.makeText(context, "Error in AddNewSowingDetails" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        try {


            edFarmerName.setText(localSowingMasterModel.getFarmerName());// = findViewById(R.id.edFarmerName);
            edFarmerMoNo.setText(localSowingMasterModel.getMobileNo());// = findViewById(R.id.edFarmerMoNo);
            edFarmerWANo.setText(localSowingMasterModel.getWhatsAppNo());// = findViewById(R.id.edFarmerWANo);
            edMDHybridName.setText(localSowingMasterModel.getNameOfHybrid());// = findViewById(R.id.edMDHybridName);
            edCheckHybridName.setText(localSowingMasterModel.getCheckHybrid());// = findViewById(R.id.edCheckHybridName);
            edCheckHybridCompany.setText(localSowingMasterModel.getCompanyOfCheckHybrid());// = findViewById(R.id.edCheckHybridCompany);
            edDateOfSowing.setText(localSowingMasterModel.getDOS());// = findViewById(R.id.edDateOfSowing);
            edResAddr.setText(localSowingMasterModel.getResAddr());// = findViewById(R.id.edResAddress);

        } catch (Exception e) {

        }
    }

    String getAddress(double latitude, double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            return address + "\n" + city + "\n" + state + "\n" + country + "\n" + postalCode + "\n";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean validateUIElement() {
        try {

            UniqueSrNo = edUno.getText().toString().trim();
            FarmerName = edFarmerName.getText().toString().trim();
            MobileNo = edFarmerMoNo.getText().toString().trim();
            WhatsAppNo = edFarmerWANo.getText().toString().trim();
            NameOfHybrid = edMDHybridName.getText().toString().trim();
            CheckHybrid = edCheckHybridName.getText().toString().trim();
            CompanyOfCheckHybrid = edCheckHybridCompany.getText().toString().trim();
            DOS = edDateOfSowing.getText().toString().trim();
            State = edState.getText().toString().trim();
            District = edDistrict.getText().toString().trim();
            Taluka = edBlockTaluka.getText().toString().trim();
            //  Latitude = "24.377828";
            //   longitude = "75.229930";
            ResAddr = edResAddr.getText().toString().trim();
            ProductId = Integer.parseInt(actionModel.getProductId().toString().trim());
            PendingFor = Integer.parseInt(actionModel.getPendingFor().toString().trim());
            VillageName = sp_village_code.getSelectedItem().toString().trim();
            UserCode = Preferences.get(context, Preferences.USER_ID);
            SyncStatus = 0;
            DownlaodStatus = 0;
            int cnt = 0;
            if (uniqueSrId.equals("")) {
                edUno.setError("Enter UniqueSrNo ");
                cnt++;
            }
            if (FarmerName.equals("")) {
                edFarmerName.setError("Enter Farmer Name ");
                cnt++;
            }
            if (MobileNo.equals("") || MobileNo.length() < 10) {
                edFarmerMoNo.setError("Enter Valid Mobile Number ");
                cnt++;
            }
            if (NameOfHybrid.equals("")) {
                edMDHybridName.setError("Enter Name Of Hybrid");
                cnt++;
            }
            if (State.equals("")) {
                edState.setError("Enter Name Of State");
                cnt++;
            }
            if (District.equals("")) {
                edDistrict.setError("Enter Name Of District");
                cnt++;
            }
            if (Taluka.equals("")) {
                edBlockTaluka.setError("Enter Name Of Taluka");
                cnt++;
            }
            if (CheckHybrid.equals("")) {
                edCheckHybridName.setError("Enter Check Hybrid Name ");
                cnt++;
            }
            if (CompanyOfCheckHybrid.equals("")) {
                edCheckHybridCompany.setError("Enter Check Hybrid Company ");
                cnt++;
            }
            if (DOS.equals("")) {
                edDateOfSowing.setError("Choose DOS");
                cnt++;
            }
            if (ResAddr.equals("")) {
                edResAddr.setError("Enter Address ");
                cnt++;
            }
            if (UserCode.equals("")) {
                Toast.makeText(context, "User Code Missing.", Toast.LENGTH_SHORT).show();
                cnt++;
            }

            if (cnt == 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        try {
            img_famerimage.setImageBitmap(r.getBitmap());
            file_path = r.getPath();
            base64_image = MyApplicationUtil.getImageDatadetail(r.getPath());
        } catch (Exception e) {

        }
    }
}
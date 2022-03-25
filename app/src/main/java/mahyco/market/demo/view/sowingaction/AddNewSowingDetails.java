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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mahyco.market.demo.MainActivity;
import mahyco.market.demo.R;
import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.SowingMasterModel;
import mahyco.market.demo.model.VillageModel;
import mahyco.market.demo.util.Preferences;
import mahyco.market.demo.util.SqlightDatabase;

public class AddNewSowingDetails extends AppCompatActivity {
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
            edResAddr,

    edDateOfSowing;
    TextView edGeoTagging;
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
    String UserCode;// TEXT," +
    int SyncStatus;// INTERGER," +
    int DownlaodStatus;// INTEGER"
    int mYear, mMonth, mDay;
    SowingMasterModel sowingMasterModel;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sowing_details);
        init();

    }

    public void init() {
        try {


            context = AddNewSowingDetails.this;
            sqlightDatabase = new SqlightDatabase(context);
            sowingMasterModel = new SowingMasterModel();
            uniqueSrId = Preferences.get(context, Preferences.SELECTED_UNIQSRID);
            // Toast.makeText(context, ""+uniqueSrId, Toast.LENGTH_SHORT).show();

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
                villageModelsList = sqlightDatabase.getLocalVillage(actionModel.getTalukaId());
                ArrayAdapter adapter = new ArrayAdapter(context,
                        android.R.layout.simple_spinner_item, sqlightDatabase.getVillageTitleFromList(villageModelsList));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_village_code.setAdapter(adapter);
            }

            btnSowingSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validateUIElement()) {
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
                        sowingMasterModel.setUserCode(UserCode);// TEXT," +
                        sowingMasterModel.setSyncStatus(SyncStatus);// INTERGER," +
                        sowingMasterModel.setDownlaodStatus(DownlaodStatus);// INTEGER"

                        if (sqlightDatabase.addSowingMaster(sowingMasterModel)) {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Toast.makeText(context, "Record Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            });


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

                         Latitude=""+location.getLatitude();
                         longitude=""+location.getLongitude();
                       // Toast.makeText(context, "Location Latitude : " + location.getLatitude() + " Longitude :" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        edGeoTagging.setText(location.getLatitude() + "," + location.getLongitude());
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(context, "Error in AddNewSowingDetails" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
          //  Latitude = "24.377828";
         //   longitude = "75.229930";
            ResAddr = edResAddr.getText().toString().trim();
            ProductId = Integer.parseInt(actionModel.getProductId().toString().trim());
            PendingFor = Integer.parseInt(actionModel.getPendingFor().toString().trim());
            VillageName = sp_village_code.getSelectedItem().toString().trim();
            UserCode = Preferences.get(context, Preferences.USER_ID);
            SyncStatus = 0;
            DownlaodStatus = 0;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
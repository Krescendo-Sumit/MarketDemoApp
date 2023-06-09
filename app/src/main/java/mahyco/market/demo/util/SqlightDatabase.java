package mahyco.market.demo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import mahyco.market.demo.model.ActionModel;
import mahyco.market.demo.model.CharatristicsMenuReportDetailsModel;
import mahyco.market.demo.model.KeyValue;
import mahyco.market.demo.model.LocalCharactersticsModel;
import mahyco.market.demo.model.SowingMasterModel;
import mahyco.market.demo.model.UpdateSowingModel;
import mahyco.market.demo.model.VillageModel;
import mahyco.market.demo.model.parametermodels.ParamterModel;


public class SqlightDatabase extends SQLiteOpenHelper {

    final static String DBName = "db_marketdemo.db";
    final static int version = 6;
    private static final String TBL_SOWING_MASTER = "TBL_SOWING_MASTER";
    private static final String TBL_CHARACTRISTICS = "TBL_CHARACTRISTICS";
    long count = 0;
    final String TABLE_ACTION_PENDING = "TBL_ACTION";
    final String TBL_VILLAGE_MASTER = "TBL_VILLAGE";
    Context context;

    public SqlightDatabase(Context context) {
        super(context, DBName, null, version);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String qry_create_tbl_action = "CREATE TABLE IF NOT EXISTS " + TABLE_ACTION_PENDING + " ( " +
                "MDDispatchSegmetnId     INTEGER, " +
                "UniqueSrNo    text, " +
                "DemoCropSowingId    INTEGER, " +
                "Year    TEXT, " +
                "Season    TEXT, " +
                "Crop    TEXT, " +
                "Segment    TEXT, " +
                "MDHybridName    TEXT, " +
                "Zone    TEXT, " +
                "State    TEXT, " +
                "TotalMDSeedAvlInGram    TEXT, " +
                "KitSizeInGram    TEXT, " +
                "NoOfKits    TEXT, " +
                "DistrictId    INTEGER, " +
                "TalukaId    INTEGER, " +
                "AssignedTo    TEXT, " +
                "IsAllocated    BOOLEAN, " +
                "ProductId    INTEGER, " +
                "VisitStageId    INTEGER, " +
                "VisitStage    TEXT, " +
                "PendingFor    INTEGER, " +
                "PendingVisitStage    INTEGER, " +
                "DistrictCode    TEXT, " +
                "DistrictName    TEXT, " +
                "TalukaCode    TEXT, " +
                "TalukaName    TEXT, " +
                "VillageCode  TEXT, " +
                "VillageName TEXT ," +
                "FarmerName TEXT ," +
                "MobileNo TEXT ," +
                "WhatsAppNo TEXT, " +
                "Latitude TEXT, " +
                "longitude TEXT, " +
                "ResAddr TEXT ," +
                "NameOfHybrid TEXT, " +
                "CheckHybrid TEXT, " +
                "DOS TEXT" +
                ")";

        db.execSQL(qry_create_tbl_action);
        Log.i("Query ", "TBL_ACTION SUCCESS");

        String qry_create_tbl_village = "CREATE TABLE IF NOT EXISTS " + TBL_VILLAGE_MASTER + " (" +
                "VillageId INTEGER," +
                "TalukaId  INTEGER," +
                "VillageCode TEXT," +
                "VillageName TEXT," +
                "TalukaCode  TEXT," +
                "Flag INTEGER," +
                "TrDate TEXT" +
                ")";
        db.execSQL(qry_create_tbl_village);
        Log.i("Query ", "TBL_VILLAGE SUCCESS");

        String qry_create_tbl_sowing_master = "CREATE TABLE IF NOT EXISTS " + TBL_SOWING_MASTER + " (" +
                "UniqueSrNo TEXT," +
                "FarmerName  TEXT," +
                "MobileNo TEXT," +
                "WhatsAppNo TEXT," +
                "NameOfHybrid TEXT," +
                "CheckHybrid TEXT," +
                "CompanyOfCheckHybrid TEXT," +
                "DOS TEXT," +
                "Latitude TEXT," +
                "longitude TEXT," +
                "ResAddr TEXT," +
                "ProductId INTEGER," +
                "PendingFor INTEGER," +
                "VillageName TEXT," +
                "State TEXT," +
                "District TEXT," +
                "Taluka TEXT," +
                "UserCode TEXT," +
                "SyncStatus INTERGER," +
                "DownlaodStatus INTEGER," +
                "ImageName TEXT," +
                "ImageinByte TEXT" +
                ")";
        db.execSQL(qry_create_tbl_sowing_master);

        String qry_create_tbl_chractristics = "Create TABLE IF NOT EXISTS " + TBL_CHARACTRISTICS + " (" +
                "uniqueno Text," +
                "farmername Text," +
                "visit_id INTEGER," +
                "product_id INTEGER," +
                "selectedvalue Text," +
                "paramList Text," +
                "syncstatus INTEGER," +
                "downloadstatus INTEGER" +
                ")";
        db.execSQL(qry_create_tbl_chractristics);

        String qry_create_tbl_menu = "Create Table  IF NOT EXISTS  TBL_MENU_MASTER(" +
                "MenuId INTEGER," +
                "KeyValue TEXT," +
                "CreatedDt TEXT," +
                "UniqueSrNo TEXT," +
                "Pendingfor INTEGER," +
                "syncstatus INTEGER" +
                ")";
        db.execSQL(qry_create_tbl_menu);
        Log.i("Query ", "qry_create_tbl_menu SUCCESS");
        String qry_update_sowing_master = "CREATE TABLE  IF NOT EXISTS  TBL_UPDATE_SOWING_MASTER" +
                "(" +
                "DemoCropSowingId INTEGER," +
                "UniqueSrNo TEXT," +
                "ProductId INTEGER," +
                "ImageName TEXT," +
                "ImageinByte TEXT," +
                "PendingFor INT," +
                "UserCode TEXT," +
                "syncstatus INTEGER" +
                ")";
        db.execSQL(qry_update_sowing_master);
        Log.i("Query ", "qry_update_sowing_master SUCCESS");
        String qry_charactristics_temp = "Create table  IF NOT EXISTS   tbl_temp_sowingcharactristics" +
                "(DemoCropCharId integer," +
                "MDDispatchSegmetnId integer," +
                "DemoCropSowingId integer," +
                "ProductId integer," +
                "VisitStageId integer," +
                "MenuId integer," +
                "UniqueSrNo text," +
                "MenuTitle text," +
                "KeyValue text," +
                "Characteristics text)";
        db.execSQL(qry_charactristics_temp);
        Log.i("Query ", "tbl_temp_sowingcharactristics SUCCESS");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.i("Databse version", "DB VERSION CHANGE  TO " + arg1);
        onCreate(db);
    }

    public boolean clearList() {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from " + TBL_SOWING_MASTER;
            mydb.execSQL(q);
            q = "delete from " + TBL_VILLAGE_MASTER;
            mydb.execSQL(q);
            q = "delete from " + TABLE_ACTION_PENDING;
            mydb.execSQL(q);

            q = "delete from " + TBL_CHARACTRISTICS;
            mydb.execSQL(q);

            q = "delete from TBL_MENU_MASTER";
            mydb.execSQL(q);

            q = "delete from TBL_UPDATE_SOWING_MASTER";
            mydb.execSQL(q);
            //String q = "delete from tbl_customersatyam";

            Log.i("Query is -------> ", "" + q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean deleteTempChractristics( ) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from tbl_temp_sowingcharactristics";
            mydb.execSQL(q);

            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean addPendingAction(ActionModel actionModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("MDDispatchSegmetnId", actionModel.getMDDispatchSegmetnId());
            values.put("UniqueSrNo", actionModel.getUniqueSrNo());
            values.put("DemoCropSowingId", actionModel.getDemoCropSowingId());
            values.put("Year", actionModel.getYear());
            values.put("Season", actionModel.getSeason());
            values.put("Crop", actionModel.getCrop());
            values.put("Segment", actionModel.getSegment());
            values.put("MDHybridName", actionModel.getMDHybridName());
            values.put("Zone", actionModel.getZone());
            values.put("State", actionModel.getState());
            values.put("TotalMDSeedAvlInGram", actionModel.getTotalMDSeedAvlInGram());
            values.put("KitSizeInGram", actionModel.getKitSizeInGram());
            values.put("NoOfKits", actionModel.getNoOfKits());
            values.put("DistrictId", actionModel.getDistrictId());
            values.put("TalukaId", actionModel.getTalukaId());
            values.put("AssignedTo", actionModel.getAssignedTo());
            values.put("IsAllocated", actionModel.getIsAllocated());
            values.put("ProductId", actionModel.getProductId());
            values.put("VisitStageId", actionModel.getVisitStageId());
            values.put("VisitStage", actionModel.getVisitStage());
            values.put("PendingFor", actionModel.getPendingFor());
            values.put("PendingVisitStage", actionModel.getPendingVisitStage());
            values.put("DistrictCode", actionModel.getDistrictCode());
            values.put("DistrictName", actionModel.getDistrict());
            values.put("TalukaCode", actionModel.getTalukaCode());
            values.put("TalukaName", actionModel.getTaluka());
            values.put("VillageCode", actionModel.getVillageCode());
            values.put("VillageName", actionModel.getVillage());

            values.put("FarmerName", actionModel.getFarmerName());
            values.put("MobileNo", actionModel.getMobileNo());
            values.put("WhatsAppNo", actionModel.getWhatsAppNo());
            values.put("Latitude", actionModel.getLatitude());
            values.put("longitude", actionModel.getLongitude());
            values.put("ResAddr", actionModel.getResAddr());
            values.put("NameOfHybrid", actionModel.getNameOfHybrid());
            values.put("CheckHybrid", actionModel.getCheckHybrid());
            values.put("DOS", actionModel.getDOS());


            // String q = "insert into video(id,title,cid,path,content,duration,eqf,seq,pdf,karoke) values(" + id.trim() + ",'" + title + "'," + cid.trim() + ",'" + path + "','" + content + "','" + duration + "','" + eqf + "'," + seq.trim() + ",'" + pdf.trim() + "','" + karoke + "')";
            mydb.insert(TABLE_ACTION_PENDING, null, values);
            mydb.close();
            Log.i("Query is -------> ", "");

            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean addSowingUpdateMaster(int DemoCropSowingId, String UniqueSrNo, int ProductId, String ImageName, String ImageinByte, int PendingFor, String UserCode) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            ContentValues values = new ContentValues();

            values.put("DemoCropSowingId", DemoCropSowingId);
            values.put("UniqueSrNo", UniqueSrNo);
            values.put("ProductId", ProductId);
            values.put("ImageName", ImageName);// TEXT," +
            values.put("ImageinByte", ImageinByte);// TEXT," +
            values.put("PendingFor", PendingFor);// INT," +
            values.put("UserCode", UserCode);// TEXT," +
            values.put("syncstatus", 0);// INTEGER" +

            mydb.insert("TBL_UPDATE_SOWING_MASTER", null, values);
            mydb.close();
            Log.i("Query is 123-------> ", "");

            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean addVillages(List<VillageModel> actionModel,String TalukaName) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();

            for (VillageModel v : actionModel) {
                ContentValues values = new ContentValues();
                values.put("VillageId", v.getVillageId());
                values.put("TalukaId", v.getTalukaId());
                values.put("VillageCode", v.getVillageCode());
                values.put("VillageName", v.getVillageName());
                values.put("TalukaCode", TalukaName);
                values.put("Flag", v.getFlag());
                values.put("TrDate", v.getTrDate());
                mydb.insert(TBL_VILLAGE_MASTER, null, values);

                Log.i("Query is -------> ", "");
            }
            mydb.close();
            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean addTempChractristicsDetails(List<CharatristicsMenuReportDetailsModel> actionModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();

            for (CharatristicsMenuReportDetailsModel v : actionModel) {
                ContentValues values = new ContentValues();

                values.put("DemoCropCharId", v.getDemoCropCharId());// integer,
                values.put("MDDispatchSegmetnId", v.getMDDispatchSegmetnId());// integer,
                values.put("DemoCropSowingId", v.getDemoCropSowingId());// integer,
                values.put("ProductId", v.getProductId());// integer,
                values.put("VisitStageId", v.getVisitStageId());// integer,
                values.put("MenuId", v.getMenuId());// integer,
                values.put("UniqueSrNo", v.getUniqueSrNo());// text,
                values.put("MenuTitle", v.getMenuTitle());// text,
                values.put("KeyValue", v.getKeyValue());// text,
                values.put("Characteristics", v.getCharacteristics());//

                mydb.insert("tbl_temp_sowingcharactristics", null, values);

                Log.i("Query is -------> ", "");
            }
            mydb.close();
            return true;
        } catch (Exception e) {
            Log.i("Error is temp", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean addMenus(List<KeyValue> actionModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();

            for (KeyValue v : actionModel) {
                ContentValues values = new ContentValues();

                values.put("MenuId", v.getSb_id());//," +
                values.put("KeyValue", v.getValue());// TEXT," +
                values.put("CreatedDt", v.getCreatedDt());// TEXT," +
                values.put("UniqueSrNo", v.getUniqueId());// TEXT," +
                values.put("Pendingfor", v.getPendingFor());// INTEGER," +
                values.put("syncstatus", 0);// INTEGER" +

                mydb.insert("TBL_MENU_MASTER", null, values);

                Log.i("Query is -------> ", "");
            }
            mydb.close();
            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean addCharactristics(List<ParamterModel> actionModel) {

        SQLiteDatabase mydb = null;
        try {
            int cnt = 0;
            mydb = this.getReadableDatabase();

            for (ParamterModel v : actionModel) {
                cnt++;
                ContentValues values = new ContentValues();
                values.put("uniqueno", Preferences.get(context, Preferences.SELECTED_UNIQSRID)); //Text," +
                values.put("farmername", ""); // Text," +
                values.put("visit_id", Preferences.get(context, Preferences.SELECTED_PENDINGFOR)); // INTEGER," +
                values.put("product_id", Preferences.get(context, Preferences.SELECTED_PRODUCTCODE)); // INTEGER," +
                values.put("selectedvalue", ""); // Text," +
                values.put("paramList", new Gson().toJson(v)); // Text," +
                values.put("syncstatus", 0); // INTEGER," +
                values.put("downloadstatus", 0); // INTEGER" +

                mydb.insert(TBL_CHARACTRISTICS, null, values);

                Log.i("Query is -------> ", "111");
            }
            Log.i("RowAdded is -------> ", "" + cnt);
            mydb.close();
            return true;
        } catch (Exception e) {
            Log.i("Error is ", "TBL_CHARACTRISTICS : " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public ArrayList<LocalCharactersticsModel> getChracteristics(String productid, String uniqueno) {
        SQLiteDatabase mydb = null;
        String k = "";
        LocalCharactersticsModel localCharactersticsModel = null;
        ArrayList<LocalCharactersticsModel> arrayLists = new ArrayList<LocalCharactersticsModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TBL_CHARACTRISTICS + " where product_id=" + productid + " and uniqueno='" + uniqueno.trim() + "'";
            Log.i("Query ", q);
            Cursor c = mydb.rawQuery(q, null);

            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                localCharactersticsModel = new LocalCharactersticsModel();
                localCharactersticsModel.setUniqueno(c.getString(0));// INTEGER," +
                localCharactersticsModel.setFarmername(c.getString(1));//  INTEGER," +
                localCharactersticsModel.setVisit_id(c.getInt(2));// TEXT," +
                localCharactersticsModel.setProduct_id(c.getInt(3));// TEXT," +
                localCharactersticsModel.setSelectedvalue(c.getString(4));//  TEXT," +
                localCharactersticsModel.setParamList(c.getString(5));//  TEXT," +
                localCharactersticsModel.setSyncstatus(c.getInt(6));// INTEGER," +
                localCharactersticsModel.setDownloadstatus(c.getInt(7));// TEXT" +
                arrayLists.add(localCharactersticsModel);
            }
            return arrayLists;
        } catch (Exception e) {
            Log.i("Error getdata", e.getMessage());
            return null;
        } finally {
            mydb.close();
        }
    }


    public ArrayList<CharatristicsMenuReportDetailsModel> getTempChracteristics(int segmentid) {
        SQLiteDatabase mydb = null;
        String k = "";
        CharatristicsMenuReportDetailsModel localCharactersticsModel = null;
        ArrayList<CharatristicsMenuReportDetailsModel> arrayLists = new ArrayList<CharatristicsMenuReportDetailsModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "select * from tbl_temp_sowingcharactristics where MDDispatchSegmetnId="+segmentid;
            Log.i("Query ", q);
            Cursor c = mydb.rawQuery(q, null);

            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                localCharactersticsModel = new CharatristicsMenuReportDetailsModel();

                localCharactersticsModel.setDemoCropCharId(c.getInt(0));//", v.getDemoCropCharId());// integer,
                localCharactersticsModel.setMDDispatchSegmetnId(c.getInt(1));//", v.getMDDispatchSegmetnId());// integer,
                localCharactersticsModel.setDemoCropSowingId(c.getInt(2));//", v.getDemoCropSowingId());// integer,
                localCharactersticsModel.setProductId(c.getInt(3));//", v.getProductId());// integer,
                localCharactersticsModel.setVisitStageId(c.getInt(4));//", v.getVisitStageId());// integer,
                localCharactersticsModel.setMenuId(c.getInt(5));//", v.getMenuId());// integer,
                localCharactersticsModel.setUniqueSrNo(c.getString(6));//", v.getUniqueSrNo());// text,
                localCharactersticsModel.setMenuTitle(c.getString(7));//", v.getMenuTitle());// text,
                localCharactersticsModel.setKeyValue(c.getString(8));//", v.getKeyValue());// text,
                localCharactersticsModel.setCharacteristics(c.getString(9));//", v.getCharacteristics());//

                arrayLists.add(localCharactersticsModel);
            }
            return arrayLists;
        } catch (Exception e) {
            Log.i("Error getdata", e.getMessage());
            return null;
        } finally {
            mydb.close();
        }
    }


    public boolean addSowingMaster(SowingMasterModel sowingMasterModel,int action) {


        SQLiteDatabase mydb = null;
        try {


            mydb = this.getReadableDatabase();
            if(action==1) {

                String q = "delete from " + TBL_SOWING_MASTER +" where UniqueSrNo='"+sowingMasterModel.getUniqueSrNo()+"'";
                mydb.execSQL(q);
                Log.i("Record Deleted","Success");
            }
            ContentValues values = new ContentValues();
            values.put("UniqueSrNo", sowingMasterModel.getUniqueSrNo());// TEXT," +
            values.put("FarmerName", sowingMasterModel.getFarmerName());//  TEXT," +
            values.put("MobileNo", sowingMasterModel.getMobileNo());// TEXT," +
            values.put("WhatsAppNo", sowingMasterModel.getWhatsAppNo());// TEXT," +
            values.put("NameOfHybrid", sowingMasterModel.getNameOfHybrid());// TEXT," +
            values.put("CheckHybrid", sowingMasterModel.getCheckHybrid());// TEXT," +
            values.put("CompanyOfCheckHybrid", sowingMasterModel.getCompanyOfCheckHybrid());// TEXT," +
            values.put("DOS", sowingMasterModel.getDOS());// TEXT," +
            values.put("Latitude", sowingMasterModel.getLatitude());// TEXT," +
            values.put("longitude", sowingMasterModel.getLongitude());// TEXT," +
            values.put("ResAddr", sowingMasterModel.getResAddr());// TEXT," +
            values.put("ProductId", sowingMasterModel.getProductId());// INTEGER," +
            values.put("PendingFor", sowingMasterModel.getPendingFor());// INTEGER," +
            values.put("VillageName", sowingMasterModel.getVillageName());// TEXT," +
            values.put("State", sowingMasterModel.getState());// TEXT," +
            values.put("District", sowingMasterModel.getDistrict());// TEXT," +
            values.put("Taluka", sowingMasterModel.getTaluka());// TEXT," +
            values.put("UserCode", sowingMasterModel.getUserCode());// TEXT," +
            values.put("ImageName", sowingMasterModel.getImageName());// TEXT," +
            values.put("ImageinByte", sowingMasterModel.getImageinByte());// TEXT," +
            values.put("SyncStatus", sowingMasterModel.getSyncStatus());// INTERGER," +
            values.put("DownlaodStatus", sowingMasterModel.getDownlaodStatus());// INTEGER" +

            // String q = "insert into video(id,title,cid,path,content,duration,eqf,seq,pdf,karoke) values(" + id.trim() + ",'" + title + "'," + cid.trim() + ",'" + path + "','" + content + "','" + duration + "','" + eqf + "'," + seq.trim() + ",'" + pdf.trim() + "','" + karoke + "')";
            mydb.insert(TBL_SOWING_MASTER, null, values);
            mydb.close();
            Log.i("Query is -------> ", "");

            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean updateSowingMasterStatus(String UniqueNo) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "Delete from TBL_SOWING_MASTER";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            q = "Delete from TBL_SOWING_MASTER";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean updateSowingUpdateMasterStatus(String UniqueNo) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
         //   String q = "update TBL_UPDATE_SOWING_MASTER set SyncStatus=1 where UniqueSrNo='" + UniqueNo + "'";
            String q = "Delete from  TBL_UPDATE_SOWING_MASTER  where UniqueSrNo='" + UniqueNo + "'";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
          //  q = "update TBL_MENU_MASTER set SyncStatus=1 where UniqueSrNo='" + UniqueNo + "'";
            q = "Delete from  TBL_MENU_MASTER  where UniqueSrNo='" + UniqueNo + "'";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Add User", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }

    public ArrayList<ActionModel> getLocalActionsList(int pendingfor) {
        SQLiteDatabase mydb = null;
        String k = "";
        ActionModel actionModel = null;
        ArrayList<ActionModel> arrayLists = new ArrayList<ActionModel>();
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TABLE_ACTION_PENDING + " order by UniqueSrNo";

            if (pendingfor == 1) {
                q = "SELECT  * FROM " + TABLE_ACTION_PENDING + " where PendingFor=1  order by UniqueSrNo";

            } else if (pendingfor > 1) {
                q = "SELECT  * FROM " + TABLE_ACTION_PENDING + " where PendingFor>1  order by UniqueSrNo";

            }

            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                actionModel = new ActionModel();
                actionModel.setMDDispatchSegmetnId(c.getString(0));    // INTEGER, " +
                actionModel.setUniqueSrNo(c.getString(1));  //  text, " +
                actionModel.setDemoCropSowingId(c.getString(2));  //  INTEGER, " +
                actionModel.setYear(c.getString(3));    //TEXT, " +
                actionModel.setSeason(c.getString(4));  //  TEXT, " +
                actionModel.setCrop(c.getString(5));   // TEXT, " +
                actionModel.setSegment(c.getString(6));   // TEXT, " +
                actionModel.setMDHybridName(c.getString(7));    //TEXT, " +
                actionModel.setZone(c.getString(8));    //TEXT, " +
                actionModel.setState(c.getString(9));   // TEXT, " +
                actionModel.setTotalMDSeedAvlInGram(c.getString(10)); //   TEXT, " +
                actionModel.setKitSizeInGram(c.getString(11)); //   TEXT, " +
                actionModel.setNoOfKits(c.getString(12));   // TEXT, " +
                actionModel.setDistrictId(c.getString(13));   // INTEGER, " +
                actionModel.setTalukaId(c.getString(14));   // INTEGER, " +
                actionModel.setAssignedTo(c.getString(15)); //   TEXT, " +
                actionModel.setIsAllocated(c.getString(16));//   BOOLEAN, " +
                actionModel.setProductId(c.getString(17));  //  INTEGER, " +
                actionModel.setVisitStageId(c.getString(18)); //   INTEGER, " +
                actionModel.setVisitStage(c.getString(19));  //  TEXT, " +
                actionModel.setPendingFor(c.getString(20));  //  INTEGER, " +
                actionModel.setPendingVisitStage(c.getString(21));//    INTEGER, " +
                actionModel.setDistrictCode(c.getString(22));   // TEXT, " +
                actionModel.setDistrictName(c.getString(23));  //  TEXT, " +
                actionModel.setTalukaCode(c.getString(24));  //  TEXT, " +
                actionModel.setTalukaName(c.getString(25));  //  TEXT, " +
                actionModel.setVillageCode(c.getString(26));  //TEXT, " +
                actionModel.setVillageName(c.getString(27));// TEXT " +
                actionModel.setFarmerName(c.getString(28));
                actionModel.setMobileNo(c.getString(29));
                actionModel.setWhatsAppNo(c.getString(30));
                actionModel.setLatitude(c.getString(31));
                actionModel.setLongitude(c.getString(32));
                actionModel.setResAddr(c.getString(33));
                actionModel.setNameOfHybrid(c.getString(34));
                actionModel.setCheckHybrid(c.getString(35));
                actionModel.setDOS(c.getString(36));


                arrayLists.add(actionModel);
                i++;
            }
            return arrayLists;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }

    public ActionModel getLocalActionModel(String UniqueSrNo) {
        SQLiteDatabase mydb = null;
        String k = "";
        ActionModel actionModel = null;

        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TABLE_ACTION_PENDING + " where UniqueSrNo ='" + UniqueSrNo + "' limit 1";
            Log.i("qu", q);
            Cursor c = mydb.rawQuery(q, null);
            if (c.moveToNext()) {
                actionModel = new ActionModel();
                actionModel.setMDDispatchSegmetnId(c.getString(0));    // INTEGER, " +
                actionModel.setUniqueSrNo(c.getString(1));  //  text, " +
                actionModel.setDemoCropSowingId(c.getString(2));  //  INTEGER, " +
                actionModel.setYear(c.getString(3));    //TEXT, " +
                actionModel.setSeason(c.getString(4));  //  TEXT, " +
                actionModel.setCrop(c.getString(5));   // TEXT, " +
                actionModel.setSegment(c.getString(6));   // TEXT, " +
                actionModel.setMDHybridName(c.getString(7));    //TEXT, " +
                actionModel.setZone(c.getString(8));    //TEXT, " +
                actionModel.setState(c.getString(9));   // TEXT, " +
                actionModel.setTotalMDSeedAvlInGram(c.getString(10)); //   TEXT, " +
                actionModel.setKitSizeInGram(c.getString(11)); //   TEXT, " +
                actionModel.setNoOfKits(c.getString(12));   // TEXT, " +
                actionModel.setDistrictId(c.getString(13));   // INTEGER, " +
                actionModel.setTalukaId(c.getString(14));   // INTEGER, " +
                actionModel.setAssignedTo(c.getString(15)); //   TEXT, " +
                actionModel.setIsAllocated(c.getString(16));//   BOOLEAN, " +
                actionModel.setProductId(c.getString(17));  //  INTEGER, " +
                actionModel.setVisitStageId(c.getString(18)); //   INTEGER, " +
                actionModel.setVisitStage(c.getString(19));  //  TEXT, " +
                actionModel.setPendingFor(c.getString(20));  //  INTEGER, " +
                actionModel.setPendingVisitStage(c.getString(21));//    INTEGER, " +
                actionModel.setDistrictCode(c.getString(22));   // TEXT, " +
                actionModel.setDistrictName(c.getString(23));  //  TEXT, " +
                actionModel.setTalukaCode(c.getString(24));  //  TEXT, " +
                actionModel.setTalukaName(c.getString(25));  //  TEXT, " +
                actionModel.setVillageCode(c.getString(26));  //TEXT, " +
                actionModel.setVillageName(c.getString(27));// TEXT " +

            }
            return actionModel;
        } catch (Exception e) {
            Log.i("Error in Local ", e.getMessage());
            return null;
        } finally {
            mydb.close();
        }
    }


    public ArrayList<VillageModel> getLocalVillage(String talukaId) {
        SQLiteDatabase mydb = null;
        String k = "";
        VillageModel villageModel = null;
        ArrayList<VillageModel> arrayLists = new ArrayList<VillageModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TBL_VILLAGE_MASTER + " where TalukaCode='" + talukaId + "' order by VillageName";
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                villageModel = new VillageModel();
                villageModel.setVillageId(c.getInt(0));// INTEGER," +
                villageModel.setTalukaId(c.getInt(1));//  INTEGER," +
                villageModel.setVillageCode(c.getString(2));// TEXT," +
                villageModel.setVillageName(c.getString(3));// TEXT," +
                villageModel.setTalukaCode(c.getString(4));//  TEXT," +
                villageModel.setFlag(c.getInt(5));// INTEGER," +
                villageModel.setTrDate(c.getString(6));// TEXT" +
                arrayLists.add(villageModel);
            }
            return arrayLists;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }

    public ArrayList<UpdateSowingModel> getUpdateSowingDetails(int status) {
        SQLiteDatabase mydb = null;
        String k = "";
        UpdateSowingModel updateSowingModel = null;
        ArrayList<UpdateSowingModel> arrayLists = new ArrayList<UpdateSowingModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM TBL_UPDATE_SOWING_MASTER where syncstatus=" + status;
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                updateSowingModel = new UpdateSowingModel();

                updateSowingModel.setDemoCropSowingId(c.getInt(0));
                updateSowingModel.setUniqueSrNo(c.getString(1));
                updateSowingModel.setProductId(c.getInt(2));
                updateSowingModel.setImageName(c.getString(3));
                updateSowingModel.setImageinByte(c.getString(4));
                //updateSowingModel.setImageinByte("");
                updateSowingModel.setPendingFor(c.getInt(5));
                updateSowingModel.setUserCode(c.getString(6));
                updateSowingModel.setSyncstatus(c.getInt(7));

                arrayLists.add(updateSowingModel);
            }
            return arrayLists;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }

    public ArrayList<KeyValue> getMenuList(int pendingfor, String uniqueno, int status) {

        SQLiteDatabase mydb = null;
        String k = "";
        KeyValue keyValue = null;
        ArrayList<KeyValue> arrayLists = new ArrayList<KeyValue>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM TBL_MENU_MASTER where syncstatus=" + status+" and UniqueSrNo='"+uniqueno+"'";
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                keyValue = new KeyValue();

                keyValue.setSb_id(c.getInt(0));//MenuId", v.getSb_id());//," +
                keyValue.setValue(c.getString(1));//"KeyValue", v.getValue());// TEXT," +
                keyValue.setCreatedDt(c.getString(2));//"CreatedDt", "2022/03/02");// TEXT," +
                keyValue.setUniqueId(c.getString(3));//);t("UniqueSrNo", v.getUniqueId());// TEXT," +
                keyValue.setPendingFor(c.getInt(4));//).put("Pendingfor", v.getPendingFor());// INTEGER," +
                keyValue.setSyncstatus(c.getInt(5));//).put("syncstatus", 0);// INTEGER" +


                arrayLists.add(keyValue);
            }
            return arrayLists;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }

    public ArrayList<SowingMasterModel> getLocalSowingDetails(int status) {
        SQLiteDatabase mydb = null;
        String k = "";
        SowingMasterModel sowingMasterModel = null;
        ArrayList<SowingMasterModel> arrayLists = new ArrayList<SowingMasterModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TBL_SOWING_MASTER + " where SyncStatus=" + status;
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                sowingMasterModel = new SowingMasterModel();
                sowingMasterModel.setUniqueSrNo(c.getString(0));//
                sowingMasterModel.setFarmerName(c.getString(1));//", sowingMasterModel.getFarmerName());//  TEXT," +
                sowingMasterModel.setMobileNo(c.getString(2));//", sowingMasterModel.getMobileNo());// TEXT," +
                sowingMasterModel.setWhatsAppNo(c.getString(3));//", sowingMasterModel.getWhatsAppNo());// TEXT," +
                sowingMasterModel.setNameOfHybrid(c.getString(4));//", sowingMasterModel.getNameOfHybrid());// TEXT," +
                sowingMasterModel.setCheckHybrid(c.getString(5));//", sowingMasterModel.getCheckHybrid());// TEXT," +
                sowingMasterModel.setCompanyOfCheckHybrid(c.getString(6));//", sowingMasterModel.getCompanyOfCheckHybrid());// TEXT," +
                sowingMasterModel.setDOS(c.getString(7));//", sowingMasterModel.getDOS());// TEXT," +
                sowingMasterModel.setLatitude(c.getString(8));//", sowingMasterModel.getLatitude());// TEXT," +
                sowingMasterModel.setLongitude(c.getString(9));//", sowingMasterModel.getLongitude());// TEXT," +
                sowingMasterModel.setResAddr(c.getString(10));//", sowingMasterModel.getResAddr());// TEXT," +
                sowingMasterModel.setProductId(c.getInt(11));//", sowingMasterModel.getProductId());// INTEGER," +
                sowingMasterModel.setPendingFor(c.getInt(12));//", sowingMasterModel.getPendingFor());// INTEGER," +
                sowingMasterModel.setVillageName(c.getString(13));//", sowingMasterModel.getUniqueSrNo());// TEXT," +
                sowingMasterModel.setState(c.getString(14));//", sowingMasterModel.getUniqueSrNo());// TEXT," +
                sowingMasterModel.setDistrict(c.getString(15));//", sowingMasterModel.getUniqueSrNo());// TEXT," +
                sowingMasterModel.setTaluka(c.getString(16));//", sowingMasterModel.getUniqueSrNo());// TEXT," +
                sowingMasterModel.setUserCode(c.getString(17));//", sowingMasterModel.getUserCode());// TEXT," +
                sowingMasterModel.setSyncStatus(c.getInt(18));//", sowingMasterModel.getSyncStatus());// INTERGER," +
                sowingMasterModel.setDownlaodStatus(c.getInt(19));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +
                sowingMasterModel.setImageName(c.getString(20));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +
                sowingMasterModel.setImageinByte(c.getString(21));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +

                arrayLists.add(sowingMasterModel);
            }
            return arrayLists;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }
    public SowingMasterModel getLocalSowingDetailsByUniqueNo(String uniqueId) {
        SQLiteDatabase mydb = null;
        String k = "";
        SowingMasterModel sowingMasterModel = null;
        ArrayList<SowingMasterModel> arrayLists = new ArrayList<SowingMasterModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM " + TBL_SOWING_MASTER + " where UniqueSrNo='" + uniqueId+"'";
             Log.i("Qu",q);
            Cursor c = mydb.rawQuery(q, null);
            if (c.moveToNext()) {
                Log.i("Row", c.getString(3));
                sowingMasterModel = new SowingMasterModel();
                sowingMasterModel.setUniqueSrNo(c.getString(0));//
                sowingMasterModel.setFarmerName(c.getString(1));//", sowingMasterModel.getFarmerName());//  TEXT," +
                sowingMasterModel.setMobileNo(c.getString(2));//", sowingMasterModel.getMobileNo());// TEXT," +
                sowingMasterModel.setWhatsAppNo(c.getString(3));//", sowingMasterModel.getWhatsAppNo());// TEXT," +
                sowingMasterModel.setNameOfHybrid(c.getString(4));//", sowingMasterModel.getNameOfHybrid());// TEXT," +
                sowingMasterModel.setCheckHybrid(c.getString(5));//", sowingMasterModel.getCheckHybrid());// TEXT," +
                sowingMasterModel.setCompanyOfCheckHybrid(c.getString(6));//", sowingMasterModel.getCompanyOfCheckHybrid());// TEXT," +
                sowingMasterModel.setDOS(c.getString(7));//", sowingMasterModel.getDOS());// TEXT," +
                sowingMasterModel.setLatitude(c.getString(8));//", sowingMasterModel.getLatitude());// TEXT," +
                sowingMasterModel.setLongitude(c.getString(9));//", sowingMasterModel.getLongitude());// TEXT," +
                sowingMasterModel.setResAddr(c.getString(10));//", sowingMasterModel.getResAddr());// TEXT," +
                sowingMasterModel.setProductId(c.getInt(11));//", sowingMasterModel.getProductId());// INTEGER," +
                sowingMasterModel.setPendingFor(c.getInt(12));//", sowingMasterModel.getPendingFor());// INTEGER," +
                sowingMasterModel.setVillageName(c.getString(13));//", sowingMasterModel.getUniqueSrNo());// TEXT," +
                sowingMasterModel.setUserCode(c.getString(14));//", sowingMasterModel.getUserCode());// TEXT," +
                sowingMasterModel.setSyncStatus(c.getInt(15));//", sowingMasterModel.getSyncStatus());// INTERGER," +
                sowingMasterModel.setDownlaodStatus(c.getInt(16));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +
                sowingMasterModel.setImageName(c.getString(17));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +
                sowingMasterModel.setImageinByte(c.getString(18));//", sowingMasterModel.getDownlaodStatus());// INTEGER" +


            }
            return sowingMasterModel;
        } catch (Exception e) {
            return null;
        } finally {
            mydb.close();
        }
    }


    public String[] getVillageTitleFromList(List<VillageModel> list) {
        String data[] = null;
        try {
            if (list != null && list.size() > 0) {
                data = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    data[i] = list.get(i).getVillageName().toString().trim();
                }
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public int getSowingMasterCount(String uniquenumber) {
        SQLiteDatabase mydb = null;
        String k = "";
        int cnt = 0;
        SowingMasterModel sowingMasterModel = null;
        ArrayList<SowingMasterModel> arrayLists = new ArrayList<SowingMasterModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "select * from TBL_ACTION where UniqueSrNo='" + uniquenumber + "'";
            Log.i("Query ", q);
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                cnt++;
            }

            return cnt;
        } catch (Exception e) {
            return cnt;
        } finally {
            mydb.close();
        }
    }

    public int getSowingUpdateMasterCount(String uniquenumber,String pendingFor) {
        SQLiteDatabase mydb = null;
        String k = "";
        int cnt = 0;
        SowingMasterModel sowingMasterModel = null;
        ArrayList<SowingMasterModel> arrayLists = new ArrayList<SowingMasterModel>();
        try {
            mydb = this.getReadableDatabase();
            String q = "select * from TBL_UPDATE_SOWING_MASTER where UniqueSrNo='" + uniquenumber + "' and PendingFor='"+pendingFor+"'";
            Log.i("Query ", q);
            Cursor c = mydb.rawQuery(q, null);
            while (c.moveToNext()) {
                cnt++;
            }

            return cnt;
        } catch (Exception e) {
            return cnt;
        } finally {
            mydb.close();
        }
    }

}

package mahyco.market.demo.util;

public class Constants {
   //Live API
    public static final String BASE_URL = "http://10.80.50.26/MarketDemoApp/";
 //  Test API
  //  public static final String BASE_URL = "http://10.80.50.153/MarketDemoApp/";
    public static final String GET_PENDINGACTION = "api/mDDispatchSegment/getPendingActions";
 //OLD API
 //   public static final String GET_VILLAGES = "api/village/getVillage";
    public static final String GET_VILLAGES = "api/location/getVillageByTalukaName";
    public static final String UPLOAD_SOWING_DETAILS = "api/cropSowing/demoCropSowingCreate";
    public static final String GET_PRODUCT_CHARACTRISTICS = "api/appCharacteristicMenu/get";
    public static final String UPLOAD_SOWING_UPDATE_DETAILS = "api/cropSowing/demoCropSowingUpdate";
    public static final String GET_REPORTMASTER ="api/mobileAppReport/getSowingReport" ;
    public static final String GET_REPORTDETAILSMASTER = "api/mobileAppReport/getSowingReportDetails" ;
    public static final String ADD_ADMINREMARK = "api/cropRemark/create";
    static final String CHECK_LOGIN="api/customToken/customToken";

}

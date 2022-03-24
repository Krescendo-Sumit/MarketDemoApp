package mahyco.market.demo.model;

public class LocalCharactersticsModel {
    public String getUniqueno() {
        return uniqueno;
    }

    public void setUniqueno(String uniqueno) {
        this.uniqueno = uniqueno;
    }

    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getSelectedvalue() {
        return selectedvalue;
    }

    public void setSelectedvalue(String selectedvalue) {
        this.selectedvalue = selectedvalue;
    }

    public String getParamList() {
        return paramList;
    }

    public void setParamList(String paramList) {
        this.paramList = paramList;
    }

    public int getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(int syncstatus) {
        this.syncstatus = syncstatus;
    }

    public int getDownloadstatus() {
        return downloadstatus;
    }

    public void setDownloadstatus(int downloadstatus) {
        this.downloadstatus = downloadstatus;
    }

    String uniqueno ;
    String farmername ;
    int visit_id ;
    int product_id ;
    String selectedvalue ;
    String paramList;
    int syncstatus ;
    int downloadstatus ;
}

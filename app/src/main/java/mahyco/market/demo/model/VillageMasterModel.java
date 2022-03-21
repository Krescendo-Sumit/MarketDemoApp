package mahyco.market.demo.model;

import java.util.List;

public class VillageMasterModel {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    String message;
    boolean success;


    public List<VillageModel> getVillageList() {
        return VillageList;
    }

    public void setVillageList(List<VillageModel> villageList) {
        VillageList = villageList;
    }

    List<VillageModel> VillageList;
}

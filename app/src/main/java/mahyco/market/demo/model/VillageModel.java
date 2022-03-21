package mahyco.market.demo.model;

public class VillageModel {
    public int getVillageId() {
        return VillageId;
    }

    public void setVillageId(int villageId) {
        VillageId = villageId;
    }

    public int getTalukaId() {
        return TalukaId;
    }

    public void setTalukaId(int talukaId) {
        TalukaId = talukaId;
    }

    public String getVillageCode() {
        return VillageCode;
    }

    public void setVillageCode(String villageCode) {
        VillageCode = villageCode;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
    }

    public String getTalukaCode() {
        return TalukaCode;
    }

    public void setTalukaCode(String talukaCode) {
        TalukaCode = talukaCode;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    public String getTrDate() {
        return TrDate;
    }

    public void setTrDate(String trDate) {
        TrDate = trDate;
    }

    int VillageId;
    int TalukaId;
    String VillageCode;
    String VillageName;
    String TalukaCode;
    int Flag;
    String TrDate;

}

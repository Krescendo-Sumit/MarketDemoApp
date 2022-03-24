package mahyco.market.demo.model;

import java.util.List;

public class CropAndCharSowingModel {
    public int getDemoCropSowingId() {
        return DemoCropSowingId;
    }

    public void setDemoCropSowingId(int demoCropSowingId) {
        DemoCropSowingId = demoCropSowingId;
    }

    public String getUniqueSrNo() {
        return UniqueSrNo;
    }

    public void setUniqueSrNo(String uniqueSrNo) {
        UniqueSrNo = uniqueSrNo;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageinByte() {
        return ImageinByte;
    }

    public void setImageinByte(String imageinByte) {
        ImageinByte = imageinByte;
    }

    public int getPendingFor() {
        return PendingFor;
    }

    public void setPendingFor(int pendingFor) {
        PendingFor = pendingFor;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public List<CropCharacteristicsMenuModel> getCropCharacteristicsMenuModel() {
        return cropCharacteristicsMenuModel;
    }

    public void setCropCharacteristicsMenuModel(List<CropCharacteristicsMenuModel> cropCharacteristicsMenuModel) {
        this.cropCharacteristicsMenuModel = cropCharacteristicsMenuModel;
    }

    int DemoCropSowingId;
    String UniqueSrNo;
           int ProductId;
    String ImageName;
           String  ImageinByte;
    int PendingFor;
           String UserCode;
    List<CropCharacteristicsMenuModel> cropCharacteristicsMenuModel;
}

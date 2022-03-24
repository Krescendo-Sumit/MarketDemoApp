package mahyco.market.demo.model;

public class CropCharacteristicsMenuModel {
    int MenuId;

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }

    public String getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }

    public String getCreatedDt() {
        return CreatedDt;
    }

    public void setCreatedDt(String createdDt) {
        CreatedDt = createdDt;
    }

    String KeyValue;
    String CreatedDt;
}

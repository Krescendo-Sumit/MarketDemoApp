package mahyco.market.demo.model;

public class KeyValue {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String key;
    String id;
    String value;

    public int getSb_id() {
        return sb_id;
    }

    public void setSb_id(int sb_id) {
        this.sb_id = sb_id;
    }

    int sb_id;

    public String getInputype() {
        return inputype;
    }

    public void setInputype(String inputype) {
        this.inputype = inputype;
    }

    String inputype;


    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    String  UniqueId;

    public int getPendingFor() {
        return PendingFor;
    }

    public void setPendingFor(int pendingFor) {
        PendingFor = pendingFor;
    }

    int PendingFor;

    public String getCreatedDt() {
        return CreatedDt;
    }

    public void setCreatedDt(String createdDt) {
        CreatedDt = createdDt;
    }

    String CreatedDt;

    public int getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(int syncstatus) {
        this.syncstatus = syncstatus;
    }

    int syncstatus;

}

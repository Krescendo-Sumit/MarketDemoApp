package mahyco.market.demo.model;

import com.google.gson.JsonArray;

import java.util.List;

public class PendingActionModel {
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

    public List<ActionModel> getPendingActions() {
        return PendingActions;
    }

    public void setPendingActions(List<ActionModel> pendingActions) {
        PendingActions = pendingActions;
    }

    String message;
boolean success;
List<ActionModel> PendingActions;
}

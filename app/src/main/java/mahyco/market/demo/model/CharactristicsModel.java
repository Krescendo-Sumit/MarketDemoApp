package mahyco.market.demo.model;

import mahyco.market.demo.model.parametermodels.EntityModel;

public class CharactristicsModel {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EntityModel getEntityModel() {
        return EntityModel;
    }

    public void setEntityModel(EntityModel entityModel) {
        EntityModel = entityModel;
    }

    boolean success;
    String message;
    EntityModel EntityModel ;
}

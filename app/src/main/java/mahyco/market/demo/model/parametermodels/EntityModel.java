package mahyco.market.demo.model.parametermodels;

import java.util.List;

public class EntityModel {

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



    int visit_id;
    int product_id;

    public List<ParamterModel> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParamterModel> paramList) {
        this.paramList = paramList;
    }

    List<ParamterModel> paramList;
}

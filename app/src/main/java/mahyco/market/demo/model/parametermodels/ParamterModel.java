package mahyco.market.demo.model.parametermodels;

import java.util.List;

public class ParamterModel {
    public String getCharacteristicsTitle() {
        return CharacteristicsTitle;
    }

    public void setCharacteristicsTitle(String characteristicsTitle) {
        CharacteristicsTitle = characteristicsTitle;
    }

    public List<DataMDVisitModel> getData_mdvisit() {
        return data_mdvisit;
    }

    public void setData_mdvisit(List<DataMDVisitModel> data_mdvisit) {
        this.data_mdvisit = data_mdvisit;
    }

    String CharacteristicsTitle;
    List<DataMDVisitModel> data_mdvisit;
}

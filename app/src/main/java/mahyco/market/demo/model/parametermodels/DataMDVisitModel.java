package mahyco.market.demo.model.parametermodels;

import java.util.List;

public class DataMDVisitModel {

    public int getSb_id() {
        return sb_id;
    }

    public void setSb_id(int sb_id) {
        this.sb_id = sb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getHint_one() {
        return hint_one;
    }

    public void setHint_one(String hint_one) {
        this.hint_one = hint_one;
    }

    public String getHint_two() {
        return hint_two;
    }

    public void setHint_two(String hint_two) {
        this.hint_two = hint_two;
    }



    int sb_id;
    String title;
    String inputType;
    String hint_one;
    String hint_two;

    public List<SpinnerValuesModel> getSpinnerValues() {
        return spinnerValues;
    }

    public void setSpinnerValues(List<SpinnerValuesModel> spinnerValues) {
        this.spinnerValues = spinnerValues;
    }

    List<SpinnerValuesModel> spinnerValues;



}

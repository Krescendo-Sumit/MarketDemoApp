package mahyco.market.demo.view.actionlist;

import java.util.List;

import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.VillageMasterModel;

public interface PendingActionListListener {
    public void onResult(String result);

    public void onListResponce(List result);
    public void onListResponce(PendingActionModel result);


}

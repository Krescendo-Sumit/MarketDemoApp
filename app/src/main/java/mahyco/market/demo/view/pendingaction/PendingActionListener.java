package mahyco.market.demo.view.pendingaction;

import java.util.List;

import mahyco.market.demo.model.CharactristicsModel;
import mahyco.market.demo.model.PendingActionModel;
import mahyco.market.demo.model.VillageMasterModel;

public interface PendingActionListener {

    public void onResult(CharactristicsModel result);
    public void onListResponce(List result);
    public void onListResponce(PendingActionModel result);
    public void onListResponceVillage(VillageMasterModel result);
}

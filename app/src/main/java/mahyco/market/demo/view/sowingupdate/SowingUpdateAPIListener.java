package mahyco.market.demo.view.sowingupdate;

import java.util.List;

import mahyco.market.demo.model.CharactristicsModel;

public interface SowingUpdateAPIListener {
    public void onResult(CharactristicsModel result);

    public void onListResponce(List result);
}

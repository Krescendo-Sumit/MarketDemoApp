package mahyco.market.demo.view.pendingaction;

import java.util.List;

import mahyco.market.demo.model.PendingActionModel;

public interface PendingActionListener {
    public void onResult(String result);

    public void onListResponce(List result);
    public void onListResponce(PendingActionModel result);
}

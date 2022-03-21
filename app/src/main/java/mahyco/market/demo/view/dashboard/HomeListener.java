package mahyco.market.demo.view.dashboard;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import mahyco.market.demo.model.MessageModel;
import mahyco.market.demo.model.PendingActionModel;

public interface HomeListener {
    public void onResult(String result);

    public void onListResponce(List result);
    public void onListResponce(PendingActionModel result);
    public void onResponce(MessageModel messageModel);
}

package callback.eg1;

import java.util.ArrayList;
import java.util.List;

public class EventNotifier {

    private List<CallMe> callMes = new ArrayList<>();

    public void regist(CallMe callMe){
        callMes.add(callMe);
    }

    public void doWork(){
        for (CallMe callMe : callMes)
            callMe.interestingEvent("sample event");
    }
}

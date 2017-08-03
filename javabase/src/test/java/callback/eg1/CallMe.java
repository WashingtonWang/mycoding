package callback.eg1;

public class CallMe implements InterestingEvent {

    private String name;

    public CallMe(String name){
        this.name = name;
    }

    @Override
    public void interestingEvent(String event) {
        System.out.println(name + ":[" + event + "] happened");
    }
}

package plantmon.states;

public class StateFactory{
    public static ParentState createState(int IDstate){
        ParentState newState;
        switch (IDstate){
            case ParentState.FARMSTATE:
                newState = new FarmState(10, 10);
                break;
            case ParentState.FRONTSTATE:
                newState = new FrontState();
                break;
            default:
                newState = new ParentState();
        }
        return newState;
    }
}

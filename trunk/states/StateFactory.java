package plantmon.states;

public class StateFactory{
    public static ParentState createState(int IDstate,Object[] args){
        ParentState newState = null;
        switch (IDstate){
            case ParentState.FARMSTATE:
                newState = new FarmState(10, 10);
                break;
            case ParentState.FRONTSTATE:
                newState = new FrontState(args);
                break;
            case ParentState.INVENTORY:
                newState = new InventoryState(args);
                break;
            case ParentState.HOME:
                newState = new HomeState(args);
                break;
        }
        return newState;
    }
}

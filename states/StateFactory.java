package plantmon.states;

public class StateFactory{
    public StateFactory(){}
    public static ParentState createState(int IDstate,Object[] args){
        ParentState newState = null;
        switch (IDstate){
            case ParentState.FARMSTATE:
                newState = new FarmState(18, 11);
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
            case ParentState.BATTLEGURUN:
                newState = new BattleGurun(16,16);
                break;
            case ParentState.STORE:
                newState = new StoreState(args);
                break;
            case ParentState.MAPSTATE:
                newState = new MapState(args);
                break;
            case ParentState.TOMATSTATE:
                newState = new TomatState(18, 11);
                break;
            case ParentState.NANASSTATE:
                newState = new NanasState(18, 11);
                break;
            case ParentState.BAWANGSTATE:
                newState = new BawangState(18, 11);
                break;
            case ParentState.LABUSTATE:
                newState = new LabuState(18,11);
                break;
            case ParentState.GAMEOVER:
                newState = new GameOver(args);
                break;
            case ParentState.PAPRIKASTATE:
                newState = new PaprikaState(18,11);
                break;
            case ParentState.BAYAMSTATE:
                newState = new BayamState(18,11);
                break;
            case ParentState.KENTANGSTATE:
                newState = new KentangState(18,11);
                break;
        }

        return newState;
    }
}

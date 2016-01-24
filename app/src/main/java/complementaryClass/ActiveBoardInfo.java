package complementaryClass;

//Class that contains the informations on the current board. Used in the main Activity
public class ActiveBoardInfo {
    private String activeBoardId="";

    public void setActiveBoardId(String activeBoardId){
        this.activeBoardId = activeBoardId;
    }
    public String getActiveBoardId(){
        return this.activeBoardId;
    }
}

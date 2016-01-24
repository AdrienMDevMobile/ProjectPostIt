package settings;

/*
    Save the id of the notifications.
    Saving the ids means that if two notifications identical are send, Android will update the last one
    instead of creating a new one.
 */
public abstract class NotificationIda {
    private static final  int  ADD_USER_TO_BOARD_ID = 1;

    public static int getAddUserToBoardId(){
        return ADD_USER_TO_BOARD_ID;
    }
}

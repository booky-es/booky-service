package com.booky.api.constants;

public class Messages {

    private Messages() {}

    public static final String BOOKY_EXCEPTION = "Something went wrong with the application!";
    public static final String USER_LOGIN_EXCEPTION = "User login failed due to exception!";
    public static final String CARD_CREATION_EXCEPTION = "Card creation failed due to exception!";
    public static final String CARD_CREATION_EXCEPTION_NO_SUCH_GROUP = "Card creation failed since group cannot be found!";
    public static final String CARD_CREATION_APPROVAL_EXCEPTION_NO_SUCH_GROUP = "Card creation failed since group cannot be found!";
    public static final String CARD_CREATION_EXCEPTION_INVALID_ADMIN = "Card creation failed since user is not an admin!";
    public static final String CARD_RETRIEVAL_EXCEPTION = "Retrieval of a card failed due to exception!";

    public static final String CARD_QUEUE_RETRIEVAL_EXCEPTION_NO_SUCH_GROUP = "Retrieval of all cards in queue failed since group cannot be found!";
    public static final String CARD_QUEUE_CREATION_APPROVAL_EXCEPTION_NO_SUCH_GROUP = "Card creation in queue failed since group cannot be found!";

    public static final String CARD_UPDATE_EXCEPTION = "Card updating failed due to exception!";
    public static final String CARD_UPDATE_EXCEPTION_NO_SUCH_GROUP = "Card updating failed since group cannot be found!";

    public static final String GROUP_RETRIEVAL_EXCEPTION = "Retrieval of a group failed due to exception!";
    public static final String ALL_GROUPS_RETRIEVAL_EXCEPTION = "Retrieval of all groups failed due to exception!";
    public static final String GROUP_CREATION_EXCEPTION = "Group creation failed due to exception!";
    public static final String GROUP_CARDS_RETRIEVAL_EXCEPTION = "Retrieval of all cards failed due to exception!";
    public static final String GROUP_CARDS_RETRIEVAL_NO_SUCH_GROUP = "Retrieval of all cards failed since group cannot be found!";
    public static final String GROUP_CARDS_RETRIEVAL_INVALID_ADMIN = "Retrieval of all cards failed since user is not admin!";

}

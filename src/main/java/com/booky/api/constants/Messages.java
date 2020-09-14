package com.booky.api.constants;

public class Messages {

    private Messages() {}

    public static final String BOOKY_EXCEPTION = "Something went wrong with the application!";
    public static final String USER_LOGIN_EXCEPTION = "User login failed due to exception!";
    public static final String USERS_RETRIEVAL_EXCEPTION = "Retrieval of users for a match failed due to exception!";

    public static final String CARD_CREATION_EXCEPTION = "Card creation failed due to exception!";
    public static final String CARD_CREATION_EXCEPTION_NO_SUCH_GROUP = "Card creation failed since group cannot be found!";
    public static final String CARD_CREATION_APPROVAL_EXCEPTION_NO_SUCH_GROUP = "Card creation failed since group cannot be found!";
    public static final String CARD_CREATION_EXCEPTION_INVALID_ADMIN = "Card creation failed since user is not an admin!";
    public static final String CARD_RETRIEVAL_EXCEPTION = "Retrieval of a card failed due to exception!";

    public static final String CARD_QUEUE_RETRIEVAL_EXCEPTION_NO_SUCH_GROUP = "Retrieval of all cards in queue failed since group cannot be found!";
    public static final String CARD_QUEUE_CREATION_APPROVAL_EXCEPTION_NO_SUCH_GROUP = "Card creation in queue failed since group cannot be found!";

    public static final String CARD_UPDATE_EXCEPTION_NOT_SUCH_CARD = "Card updating failed since no such card is found!";
    public static final String CARD_UPDATE_EXCEPTION = "Card updating failed due to exception!";
    public static final String CARD_UPDATE_EXCEPTION_NO_SUCH_GROUP = "Card updating failed since group cannot be found!";

    public static final String CARD_DELETE_EXCEPTION = "Card deletion failed due to exception!";
    public static final String CARD_DELETE_EXCEPTION_NO_SUCH_CARD = "Card deletion failed since card cannot be found!";
    public static final String CARD_DELETE_EXCEPTION_NO_GROUP_FOUND_FOR_CARD = "Card deletion failed since group cannot be found for card!";
    public static final String CARD_DELETE_EXCEPTION_NOT_ADMIN = "Card deletion failed since user is not an admin!";

    public static final String GET_ALL_QUEUE_CARD_EXCEPTION = "Retrieval of all cards in queue for a group failed due to exception!";
    public static final String GET_ALL_QUEUE_CARDS_EXCEPTION_NO_SUCH_GROUP = "Retrieval of all cards in queue for a group failed since group cannot be found!!";
    public static final String GET_ALL_QUEUE_CARDS_EXCEPTION_NOT_ADMIN = "Retrieval of all cards in queue for a group failed since user is not admin!";
    public static final String DELETE_QUEUE_CARD_EXCEPTION = "Deletion of card from queue failed!";
    public static final String DELETE_QUEUE_CARD_EXCEPTION_NOT_ADMIN = "Deletion of card from queue failed since user is not admin!";


    public static final String GROUP_RETRIEVAL_EXCEPTION = "Retrieval of a group failed due to exception!";
    public static final String ALL_GROUPS_RETRIEVAL_EXCEPTION = "Retrieval of all groups failed due to exception!";
    public static final String GROUP_CREATION_EXCEPTION = "Group creation failed due to exception!";
    public static final String GROUP_CARDS_RETRIEVAL_EXCEPTION = "Retrieval of all cards failed due to exception!";
    public static final String GROUP_CARDS_RETRIEVAL_NO_SUCH_GROUP = "Retrieval of all cards failed since group cannot be found!";
    public static final String GROUP_CARDS_RETRIEVAL_INVALID_ADMIN = "Retrieval of all cards failed since user is not admin!";
    public static final String GROUP_ADMINS_RETRIEVAL_NO_SUCH_GROUP= "Retrieval of admins of a group failed since group cannot be found!";
    public static final String GROUP_ADMINS_RETRIEVAL_NOT_ADMIN= "Retrieval of admins of a group failed since user is not admin!";
    public static final String GROUP_ADMINS_RETRIEVAL_EXCEPTION = "Retrieval of admins of a group failed due to exception!";

    public static final String GROUP_ADD_ADMIN_EXCEPTION = "Addition of admin failed due to exception!";
    public static final String GROUP_ADD_ADMIN_EXCEPTION_NO_SUCH_GROUP = "Addition of admin failed since group could be found!";
    public static final String GROUP_ADD_ADMIN_EXCEPTION_NO_SUCH_USER = "Addition of admin failed since user could be found!";
    public static final String GROUP_ADD_ADMIN_EXCEPTION_ALREADY_ADMIN = "Addition of admin failed since user is already an admin!";
    public static final String GROUP_ADD_ADMIN_EXCEPTION_NOT_ADMIN = "Addition of admin failed since user is not admin!";

    public static final String GROUP_REMOVE_ADMIN_EXCEPTION = "Removal of admin failed due to exception!";
    public static final String GROUP_REMOVE_ADMIN_EXCEPTION_NO_SUCH_GROUP = "Removal of admin failed since group could be found!";
    public static final String GROUP_REMOVE_ADMIN_EXCEPTION_NO_SUCH_USER = "Removal of admin failed since user could be found!";
    public static final String GROUP_REMOVE_ADMIN_EXCEPTION_LAST_ADMIN = "Removal of admin failed since group has only one admin!";
    public static final String GROUP_REMOVE_ADMIN_EXCEPTION_NOT_ADMIN = "Removal of admin failed since user is not admin!";


}

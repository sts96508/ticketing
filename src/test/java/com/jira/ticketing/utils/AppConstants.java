package com.jira.ticketing.utils;

import com.jira.ticketing.entity.enums.PriorityLevel;
import com.jira.ticketing.entity.enums.TicketStatus;

public class AppConstants {

    public static final String PROJECTNAME = "testProject";
    public static final String PROJECTDESCRIPTION = "random prj";

    public static class UsersConstants {
        public static final String USERNAME = "sid";
        public static final String EMAIL = "xx@xx.com";
        public static final String PASSWORD = "password";
        public static final String ROLE = "jjj";

        public static final String USERNAME1 = "sid1";
        public static final String EMAIL1 = "xx@xx.com1";
        public static final String PASSWORD1 = "password1";
        public static final String ROLE1 = "jjj1";

    }

    public static class TicketConstants {

        public static final String TITLE1 = "xx issue";
        public static final String DESCRIPTION1 = "xx desc";
        public static final TicketStatus STATUS1 = TicketStatus.OPEN;
        public static final PriorityLevel PRIORITY1 = PriorityLevel.MEDIUM;

        public static final String TITLE2 = "yxx issue";
        public static final String DESCRIPTION2 = "yxx desc";
        public static final TicketStatus STATUS2 = TicketStatus.CLOSED;
        public static final PriorityLevel PRIORITY2 = PriorityLevel.HIGH;
    }
}

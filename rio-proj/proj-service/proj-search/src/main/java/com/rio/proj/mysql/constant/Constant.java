package com.rio.proj.mysql.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    private static final String DB_NAME = "proj_data";

    public static class PROJECT_TABLE_INFO {
        public static final String TABLE_NAME = "proj_project";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CREATOR_ID = "creator_id";
        public static final String COLUMN_PROJECT_STATUS = "project_status";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
    }

    public static class STAGE_TABLE_INFO {
        public static final String TABLE_NAME = "proj_stage";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PROJECT_ID = "project_id";
        public static final String COLUMN_STAGE_STATUS = "stage_status";
    }

    public static class TASK_TABLE_INFO {
        public static final String TABLE_NAME = "proj_task";

        public static final String COLUMN_ID = "id";
        public static final String STAGE_ID = "stage_id";
        public static final String PROJECT_ID = "project_id";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
    }

    public static class USER_TABLE_INFO {
        public static final String TABLE_NAME = "proj_user";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_TOKEN = "token";
        public static final String COLUMN_POSITION = "position";
    }

    public static class TASK_USER_TABLE_INFO {
        public static final String TABLE_NAME = "proj_task_user";

        public static final String COLUMN_TASK_ID = "task_id";
        public static final String COLUMN_USER_ID = "user_id";
    }

    public static class TASK_TYPE_TABLE_INFO {
        public static final String TABLE_NAME = "proj_task_type";

        public static final String COLUMN_TASK_ID = "task_id";
        public static final String COLUMN_TYPE = "type";
    }

    public static Map<String,String> table2Db;

    static {
        table2Db = new HashMap<>();
        table2Db.put(PROJECT_TABLE_INFO.TABLE_NAME,DB_NAME);
        table2Db.put(STAGE_TABLE_INFO.TABLE_NAME,DB_NAME);
        table2Db.put(TASK_TABLE_INFO.TABLE_NAME,DB_NAME);
        table2Db.put(USER_TABLE_INFO.TABLE_NAME,DB_NAME);
        table2Db.put(TASK_USER_TABLE_INFO.TABLE_NAME,DB_NAME);
        table2Db.put(TASK_TYPE_TABLE_INFO.TABLE_NAME,DB_NAME);
    }
}

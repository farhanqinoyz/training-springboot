package com.farhan.trainingspringboot.constant;

public class Constant {
    public static class TaskConstant{
        public static final int TASK_LIMIT_PER_HOUR = 1;
    }

    public static class TaskStatus{
        public static final String ON_PROGRESS = "1";
        public static final String DONE = "2";
        public static final String ERROR = "3";
        public static final String ERROR_PARTIAL = "4";
    }
}

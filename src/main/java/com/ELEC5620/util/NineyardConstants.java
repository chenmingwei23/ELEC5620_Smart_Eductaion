package com.ELEC5620.util;


import java.util.ArrayList;
import java.util.List;

public interface NineyardConstants {
    /**
     * Authority: Project member
     */
    int TYPE_EMPLOY = 1;

    /**
     * Authority: Project Manager
     */
    int TYPE_PM = 0;

    /**
     * Authority: Client
     */
    int TYPE_Client = 2;

    /**
     * Response code: success
     */
    int SUCCESS = 0;

    /**
     * Response code: fail
     */
    int FAIL = 1;

    /**
     * Response code: NotExist
     */
    int NOTEXIST = 2;

    /**
     * Response code: Exists
     */
    int EXIST = 3;

    /**
     * Email title: Reset password
     */
    String EMAIL_TITLE_RESETPASSWORD = "[Nineyards]Reset your password";

    /**
     * Department
     */
    List<String> ALL_DEPARTMENT = new ArrayList<>(List.of(new String[]{"Manage", "Design", "Develop", "Financial", "Client", "QA"}));

    /**
     * Position
     */
    List<String> ALL_POSITION = new ArrayList<>(List.of(new String[]{"Project manager", "Product manager", "UI designer",
            "Front-end developer", "Back-end developer", "Tester" , "Accountant", "Client contact", "Operator"}));

    /**
     * 默认状态的登陆凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态下的登陆凭证的超时时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}

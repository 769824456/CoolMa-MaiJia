package io.github.froger.instamaterial.ui.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by yulongsun on 2016/1/3.
 */
public class User extends BmobUser {
    public static final String ICON = "http://115.159.105.144/ic_launcher.png";
    public static final String USER_TYPE_GUKE = "顾客";
    public static final String USER_TYPE_SHANGJIA = "商家";
    private String userIcon;
    private Integer userJiFen;
    private Integer userXinYu;
    private String userType;//用户类型

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Integer getUserJiFen() {
        return userJiFen;
    }

    public void setUserJiFen(Integer userJiFen) {
        this.userJiFen = userJiFen;
    }

    public Integer getUserXinYu() {
        return userXinYu;
    }

    public void setUserXinYu(Integer userXinYu) {
        this.userXinYu = userXinYu;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

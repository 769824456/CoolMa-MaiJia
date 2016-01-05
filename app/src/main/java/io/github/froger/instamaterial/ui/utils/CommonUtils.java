package io.github.froger.instamaterial.ui.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;

import java.text.DecimalFormat;

/**
 * 工具类
 *
 * @author Stone
 * @date 2014-5-9
 */
public class CommonUtils {

    /**
     * 校验姓名长度
     *
     * @param context
     * @param username
     * @return
     */
    public static boolean isUserNameValid(Context context, String username) {
        if (username.length() <= 1 || username.length() >= 7) {
            PromptManager.showToast(context, "亲，请输入正确的姓名长度！~(●'◡'●)~!");
            return false;
        }
        return true;
    }


    /**
     * 校验密码长度
     *
     * @param context
     * @param pwd
     * @return
     */
    public static boolean isPwdValid(Context context, String pwd) {
        if (pwd.length() < 6 || pwd.length() > 16) {
            PromptManager.showToast(context, "亲，正确的密码长度在6-16位之间！~(●'◡'●)~!");
            return false;
        }
        return true;
    }

    /**
     * 判断手机号码是否合理
     *
     * @param context
     * @param phoneNums
     * @return
     */
    public static boolean isPhoneNum(Context context, String phoneNums) {
        if (isMatchLength(phoneNums, 11) && CommonUtils.isMobilePattern(phoneNums)) {
            return true;
        }
        PromptManager.showToast(context, "手机号码输入有误");
        return false;
    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobilePattern(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    /**
     * 判断邮箱地址是否有效
     *
     * @param email
     * @return true 有效 / false 无效
     */
    public static boolean isEmailValid(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }


    /**
     * 得到一个格式化的时间
     *
     * @param time 时间 毫秒
     * @return 时：分：秒：毫秒
     */
    public static String getFormatTime(long time) {
        time = time / 1000;
        long second = time % 60;
        long minute = (time % 3600) / 60;
        long hour = time / 3600;

        // 毫秒秒显示两位
        // String strMillisecond = "" + (millisecond / 10);
        // 秒显示两位
        String strSecond = ("00" + second)
                .substring(("00" + second).length() - 2);
        // 分显示两位
        String strMinute = ("00" + minute)
                .substring(("00" + minute).length() - 2);
        // 时显示两位
        String strHour = ("00" + hour).substring(("00" + hour).length() - 2);

        return strHour + ":" + strMinute + ":" + strSecond;
        // + strMillisecond;
    }

    /**
     * 计算并格式化doubles数值，保留两位有效数字
     *
     * @param doubles
     * @return 返回当前路程
     */
    public static String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr.equals("0") ? "0.0"
                : distanceStr;
    }

    /**
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

}

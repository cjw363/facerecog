package com.facerecog.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CommUtil {
    /**
     * 随机生成六位数验证码
     *
     * @return
     */
    public static int getRandomNum() {
        Random r = new Random();
        return r.nextInt(900000) + 100000;
        //(Math.random()*(999999-100000)+100000)
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     *
     * @param date
     * @return
     */
    public static Date str2Date(String date) {
        if (!StringUtils.isEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return null;
        }
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public static String long2DateStr(Object time, String format) {
        if (time == null || "".equals(time)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String d = sdf.format(time);
        return d;
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    //生成当前时间戳
    public static String createStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        Date date;
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts).substring(0, 10);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s + "000");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static boolean mkdir(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return file.mkdirs();
        return true;
    }

    /**
     * 从请求信息中获取token值
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        // 默认从Cookie里获取token值
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (CommConst.ACCESS_CPFR_TOKEN.equals(cookieName)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(CommConst.ACCESS_CPFR_TOKEN);
            if (StringUtils.isEmpty(token)) {
                // 从请求信息中获取token值
                token = request.getParameter(CommConst.ACCESS_CPFR_TOKEN);
            }
        }

        return token;
    }

    /**
     * 获取配置文件的值
     *
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("config/conf.properties");
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getCronExpression(String str, String day) {
        String[] result = day.split(",");
        String time = "";
        for (int i = 0; i < result.length; i++) {
            switch (result[i]) {
                case "1":
                    result[i] = "MON";
                    break;
                case "2":
                    result[i] = "TUE";
                    break;
                case "3":
                    result[i] = "WED";
                    break;
                case "4":
                    result[i] = "THU";
                    break;
                case "5":
                    result[i] = "FRI";
                    break;
                case "6":
                    result[i] = "SAT";
                    break;
                case "7":
                    result[i] = "SUN";
                    break;
            }
            time += i == 0 ? result[i] : "," + result[i];

        }

        int index = str.indexOf(":");
        return "0 " + str.substring(index + 1, str.length()) + " " + str.substring(0, index) + " ? * " + time;
    }
}
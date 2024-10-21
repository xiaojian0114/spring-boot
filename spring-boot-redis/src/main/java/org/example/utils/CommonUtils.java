package org.example.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {
    //验证手机号

    //生成4位随机数
    public static int generateCode() {

        //开闭区间
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }
}

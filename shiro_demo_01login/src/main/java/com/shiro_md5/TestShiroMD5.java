package com.shiro_md5;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestShiroMD5 {
    public static void main(String[] args) {
        //==========下面是演示一个错误的用法======== |
        //1.创建一个md5加密算法                    |
        /*Md5Hash md5Hash = new Md5Hash();      |
        md5Hash.setBytes("123".getBytes());     |
        String s = md5Hash.toHex();             |
        System.out.println(s);                  |
        */
        //=====================================|

        //注意这样是没有加密的,我们需要使用构造方法进行加密;如下

        //使用md5
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash);

        //使用MD5 + salt 处理 默认拼接到source后面处理
        Md5Hash md5Hash1 = new Md5Hash("123456", "A*B*C");
        System.out.println(md5Hash1);

        // 使用 MD5 + salt + hash散列(推荐)
        Md5Hash md5Hash2 = new Md5Hash("123456", "A*B*C", 1024);

        System.out.println(md5Hash2);

        // e10adc3949ba59abbe56e057f20f883e
        // be2394f88276238c497af601990f851d
        // 30d9516e90384e43a35ccd4a35d1e9ae
    }
}

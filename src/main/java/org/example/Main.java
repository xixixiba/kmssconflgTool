package org.example;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static String decrypt(byte[] bytes, String key) {
        DES des = SecureUtil.des(key.getBytes());
        return des.decryptStr(bytes, CharsetUtil.charset("gb2312"));
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java -jar kmssconflgTool.jar <path-to-kmssconfig.properties>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        byte[] bytes = Files.readAllBytes(path);
        String result = decrypt(bytes, "kmssPropertiesKey");
        System.out.println(result);
    }
}

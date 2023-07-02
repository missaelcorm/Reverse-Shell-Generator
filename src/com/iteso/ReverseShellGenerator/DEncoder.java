package com.iteso.ReverseShellGenerator;

import java.math.BigInteger;
import java.util.Base64;
import java.util.HexFormat;

public class DEncoder {
    
    public static String encodeToBase64(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String encodeToHex(String str){
        return String.format("%040x", new BigInteger(1, str.getBytes()));
    }

    public static String decodeFromBase64(String str){
        return new String(Base64.getDecoder().decode(str));
    }

    public static String decodeFromHex(String str){
        return new String(HexFormat.of().parseHex(str));
    }
}

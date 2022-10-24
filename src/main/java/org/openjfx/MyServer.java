package org.openjfx;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7000);
            Socket s = ss.accept();//establishes connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
            System.out.println(str);
            ss.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}


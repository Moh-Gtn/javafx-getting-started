package org.openjfx;
import java.io.*;
import java.net.*;

public class MyClient {
    public static void main (String [] args) {
        try{
            Socket s=new Socket("localhost", 7000);
            DataOutputStream d_out = new DataOutputStream(s.getOutputStream());
            d_out.writeUTF("Hello SERVER");
            d_out.flush();
            d_out.close();
            s.close();
        } catch(Exception e){
            System.err.println(e);}
        }
    }


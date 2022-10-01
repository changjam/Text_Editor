package main;

import Bridge_UI.*;

public class Application
{
    private static WindowInterface impl;
    //設定Implementor

    public Application() {
        impl = null;
    }

    public static void main(String[] args)
    {

        String OS = "Windows";
        if(OS == "Windows"){
            impl = new Win_WindowInterface().getInstance();
            run();
        }else if(OS == "MAC"){
            impl = new Mac_WindowInterface().getInstance();
        }
    }

    private static void run() {
        impl.setOSFont(impl.getOSFont());
        //建立mainWindow並以Implementor當成參數
        WholeWindow mainWindow = new WholeWindow(impl);
        mainWindow.getEnvironment();
        //執行mainWindow的run()
        mainWindow.run();
    }
}
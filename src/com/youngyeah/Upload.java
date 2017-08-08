package com.youngyeah;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Upload {
    DataOutputStream out = null;
    Socket socket = null;

    public boolean upload() {
        PrintSetting a = Factory.getInstance().getPrintSettingInstance();
        File file = a.getFile();
        //PrintSetting a = Factory.getInstance().getPrintSettingInstance();
        //File file = a.getFile();
        boolean all = a.isAll();//全文打印
        int pages = a.getPages();//文件页码
        int start = a.getStart();//开始打印页码
        int end = a.getEnd();//结束打印页码
        int copys = a.getCopys();//打印份数
        boolean doublepages = a.isDoublepages();//是否双面打印

        if (!file.exists()) {
            return false;
        }
        String type = file.getName();
        type = type.substring(type.indexOf("."));
        try {
            socket = new Socket("183.174.60.20", 12345);
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(file.getName());
            out.writeUTF(type);//文件格式
            out.writeBoolean(all);//全文打印
            out.writeInt(pages);//文件页码
            out.writeInt(copys);
            out.writeBoolean(doublepages);//是否双面打印


            //等待服务器响应
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            FileInputStream fin = new FileInputStream(file);
            //将从服务器来的数据写入终端机
            byte b[] = new byte[1024];
            int len = 0;
            //System.out.println("文件接收中");
            while ((len = fin.read(b)) != -1) {
                out.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

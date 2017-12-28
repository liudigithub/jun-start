package com.liu.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 上传测试
 * 
 * @author liudi
 * @date 2017年12月28日
 */
public class UploadFileTest {
    private static Logger logger = Logger.getLogger(UploadFileTest.class);  
    @Test
    public void testFtp1(){
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local=null;
        try {
            //连接ftp服务器
            ftp.connect("118.184.32.71", 21);
            //登录
            ftp.login("administrator", "ZAQXSW159357");
            //设置上传路径
            String basePath="/image/photo/";
            boolean flagBase = ftp.changeWorkingDirectory(basePath);
            if(!flagBase){
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(basePath);
            }
            Date currentDate = new Date();
            String dateStr=new SimpleDateFormat("yyyy/MM/dd").format(currentDate);
            for(String pathStr:dateStr.split("/")){
                basePath+=pathStr+"/";
                boolean flag = ftp.changeWorkingDirectory(basePath);
                if(!flag){
                    //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                    ftp.makeDirectory(basePath);
                }
            }
            //检查上传路径是否存在 如果不存在返回false
            ftp.changeWorkingDirectory(basePath);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //读取本地文件
            File file = new File("D:\\new.png");
            local = new FileInputStream(file);
            //第一个参数是文件名
            ftp.storeFile(file.getName(), local);
         } catch (SocketException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }finally {
             try {
                 //关闭文件流
                 local.close();
                 //退出
                 ftp.logout();
                 //断开连接
                 ftp.disconnect();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }
}

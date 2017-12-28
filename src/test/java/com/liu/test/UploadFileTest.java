package com.liu.test;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import com.liu.codemap.GlobalErrorCode;

/**
 * 上传测试
 * 
 * @author liudi
 * @date 2017年12月28日
 */
public class UploadFileTest {
    private static Logger logger = Logger.getLogger(UploadFileTest.class);

    /**
     * 文件上传
     * 
     * @author liudi
     * @date 2017年12月28日
     */
    @Test
    public void testFtp1() {
        // 创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        try {
            // 连接ftp服务器
            ftp.connect("118.184.32.71", 21);
            // 登录
            ftp.login("administrator", "ZAQXSW159357");
            // 设置上传路径
            String basePath = "/image/photo/";
            boolean flagBase = ftp.changeWorkingDirectory(basePath);
            if (!flagBase) {
                // 创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(basePath);
            }
            Date currentDate = new Date();
            String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(currentDate);
            for (String pathStr : dateStr.split("/")) {
                basePath += pathStr + "/";
                boolean flag = ftp.changeWorkingDirectory(basePath);
                if (!flag) {
                    // 创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                    ftp.makeDirectory(basePath);
                }
            }
            // 检查上传路径是否存在 如果不存在返回false
            ftp.changeWorkingDirectory(basePath);
            // 指定上传文件的类型 二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // 读取本地文件
            File file = new File("D:\\new03.jpg");
            InputStream in = new FileInputStream(file);
            // 文件压缩
            byte[] by = zipPhoto(in);
            local = new ByteArrayInputStream(by);
            // 获取文件后缀
            String suffix = file.getName().substring(file.getName().lastIndexOf("."));
            // 文件名称
            String fileName = currentDate.getTime() + suffix;
            // 第一个参数是文件名
            ftp.storeFile(fileName, local);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭文件流
                local.close();
                // 退出
                ftp.logout();
                // 断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件下载
     * 
     * @author liudi
     * @date 2017年12月28日
     */
    @Test
    public void testFtp2() {
        FTPClient ftp = new FTPClient();
        try {
            // 连接ftp服务器
            ftp.connect("118.184.32.71", 21);
            // 登录
            ftp.login("administrator", "ZAQXSW159357");
            // 文件路径
            String basePath = "/image/photo/2017/12/28/";
            ftp.changeWorkingDirectory(basePath);// 转移到FTP服务器目录
            // 获取所有文件
            // FTPFile[] fs = ftp.listFiles();
            // 获取指定文件
            // FTPFile file = ftp.mlistFile("/image/photo/2017/12/28/new.png");
            // 文件保存路径
            String localPath = "D:\\new01.png";
            File localFile = new File(localPath);
            OutputStream is = new FileOutputStream(localFile);
            // 下载文件
            ftp.retrieveFile("new.png", is);
            is.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
    
    public byte[] zipPhoto(InputStream inputStream) throws IOException{
        // 读取图像原始数据
        byte[] orginData = StreamUtils.copyToByteArray(inputStream);
        
        // 读取图片数据(MemoryCacheImageInputStream,内存解码)
        ImageInputStream imageInput = new MemoryCacheImageInputStream(new ByteArrayInputStream(orginData));
        Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInput);

        if (!iterator.hasNext()) {
            logger.info("无数据");
        }

        // 获取图片读取对象
        ImageReader reader = (ImageReader) iterator.next();
        reader.setInput(imageInput, true);

        BufferedImage image = reader.read(0);

        int w = image.getWidth(null);
        int h = image.getHeight(null);

        int dstWidth;
        int dstHeight;
        byte[] fileData;

        if (w > 1080 && h > 1080) {
            // 需要压缩
            float rate = 1080f / Math.max(w, h);
            dstWidth = (int) (w * rate);
            dstHeight = (int) (h * rate);
            
            // 压缩
            BufferedImage tag = new BufferedImage(dstWidth, dstHeight, image.getType());
            tag.getGraphics().drawImage(image, 0, 0, dstWidth, dstHeight, null);

            // 得到指定Format图片的writer
            ImageWriter writer = ImageIO.getImageWriter(reader);

            // 得到指定writer的输出参数设置(ImageWriteParam )
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            // if (quality < 1) {
            // iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
            // iwp.setCompressionQuality(quality); // 设置压缩质量参数
            // }
            iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
            // 指定压缩时使用的色彩模式
            ColorModel colorModel = ColorModel.getRGBdefault();
            iwp.setDestinationType(new ImageTypeSpecifier(colorModel,
                    colorModel.createCompatibleSampleModel(32, 32)));

            // 开始打包图片
            IIOImage iiamge = new IIOImage(tag, null, null);

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            try {
                // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
                // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
                writer.setOutput(ImageIO.createImageOutputStream(byteStream));
                writer.write(null, iiamge, iwp);

                byteStream.flush();
                fileData = byteStream.toByteArray();
            } finally {
                // IIO关闭
                byteStream.close();
            }
        } else {
            dstWidth = w;
            dstHeight = h;
            fileData = orginData;
        }
        return fileData;
    }
}

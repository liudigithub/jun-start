package com.liu.component;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.util.StreamUtils;

/**
 * 文件上传工具
 * 
 * @author liudi
 * @date 2018年1月3日
 */
public class FileUploadUtils {
    private static Logger logger = Logger.getLogger(FileUploadUtils.class);

    /** 主机 */
    protected String host;
    /** 端口 */
    protected int port;
    /** 用户名 */
    protected String username;
    /** 密码 */
    protected String password;
    /** 路径 */
    protected String url;

    /**
     * 保存文件
     * 
     * @author liudi
     * @date 2018年1月4日
     * @param name 文件名
     * @param inputStream 输入流
     * @param zip 是否压缩图片
     * @return 路径
     */
    public String save(String name, InputStream inputStream, boolean zip) {
        String savePath = null;
        // 创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        try {
            // 连接ftp服务器
            ftp.connect(host, port);
            // 登录
            ftp.login(username, password);
            // 设置上传路径
            String basePath = url;
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

            if (zip) {
                // 文件压缩
                byte[] by = zipPhoto(inputStream);
                local = new ByteArrayInputStream(by);
            } else {
                local = inputStream;
            }
            // 获取文件后缀
            String suffix = name.substring(name.lastIndexOf("."));
            // 文件名称
            String fileName = currentDate.getTime() + suffix;
            // 第一个参数是文件名
            ftp.storeFile(fileName, local);

            // 取出 /image/
            savePath = basePath.substring(17) + fileName;
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
        return savePath;
    }

    /**
     * 压缩图片到1080
     * 
     * @author liudi
     * @date 2018年1月3日
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] zipPhoto(InputStream inputStream) throws IOException {
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
            iwp.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(32, 32)));

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

    /**
     * 下载文件
     * 
     * @author liudi
     * @date 2018年1月4日
     * @param oldUrl 地址
     * @return 输入流
     */
    public InputStream download(String oldUrl) {
        FTPClient ftp = new FTPClient();
        try {
            // 连接ftp服务器
            ftp.connect(host, port);
            // 登录
            ftp.login(username, password);
            // 文件路径
            String basePath = "/home/test/image/" + oldUrl;
            // 下载文件
            InputStream inputStream = ftp.retrieveFileStream(basePath);
            ftp.logout();
            return inputStream;
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
        return null;
    }

    /**
     * 删除文件
     * 
     * @author liudi
     * @date 2018年1月4日
     * @param oldUrl 文件地址
     */
    public void deleteFile(String oldUrl) {
        FTPClient ftp = new FTPClient();
        try {
            // 连接ftp服务器
            ftp.connect(host, port);
            // 登录
            ftp.login(username, password);
            // 文件路径
            String basePath = "/home/test/image/" + oldUrl;
            // 删除
            ftp.deleteFile(basePath);
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

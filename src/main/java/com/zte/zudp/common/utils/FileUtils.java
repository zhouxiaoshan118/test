package com.zte.zudp.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * 文件操作工具类
 *
 * @author Devin
 * @since 2016/07/05
 */
public class FileUtils {

    /**
     * 文件上传路径
     */
    public static String uploadPath = "/upload";

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static String projectPath;

    /**
     * 获取项目绝对路径
     */
    // todo
    // public static String getProjectPath() {
    //     if (StringUtils.isEmpty(projectPath)) {
    //         projectPath = ServletUtils.getHttpRequest().getServletContext().getRealPath("/");
    //     }
    //     return projectPath;
    // }

    /**
     * <p>保存上传的文件</p>
     * <p>文件路径：
     * {@link FileUtils#getProjectPath()}/{@link FileUtils#uploadPath}/modules/year/month/day/milliseconed.xxx</p>
     *
     * @param file SpringMVC 封装的上传文件类对象
     * @param modules 文件所属模块
     * @return /{@link FileUtils#uploadPath}/modules/year/month/day/milliseconed.xxx
     */
    public static String saveUploadFile(MultipartFile file, String modules) throws IOException {
        String fileSuffix = getFileSuffix(file.getOriginalFilename());

        Path path = generatedPath(modules, fileSuffix);
        if (Files.notExists(path)) {
            Files.createDirectories(path.getParent());
        }
        try {
            file.transferTo(path.toFile());
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return getRelativizePath(path);
    }

    /**
     * 返回服务器中的路径
     * @param path 本地绝对路径，必须在项目路径下
     * @return 服务器中的路径
     */
    private static String getRelativizePath(Path path) {
        // return Paths.get(getProjectPath()).relativize(path).normalize().toString().replaceAll("\\\\", "/");
        return null;
    }

    /**
     * 获取文件名后缀，包括 .
     * @param filename 包含后缀的文件名
     * @return 带 . 号的文件后缀
     */
    private static String getFileSuffix(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return filename.substring(index).toLowerCase();
        }
        return null;
    }

    /**
     * 生成本地文件的保存路径
     * @param module 模块名
     * @param suffix 文件名后缀
     * @return 保存文件的本地绝对路径
     */
    private static Path generatedPath(String module, String suffix) {
        // String projectPath = getProjectPath();
        String projectPath = "";
        String date = DateUtils.formatNow(DateUtils.DateFormat.DAY);
        String[] split = date.split("-");

        Path path = Paths.get(projectPath,
                uploadPath,
                module,
                split[0],   // 年
                split[1],   // 月
                split[2],   // 日
                System.currentTimeMillis() + suffix);
        return path.normalize();
    }

    public static String cropImage(InputStream input, String module, int x, int y, int width, int height) throws IOException {
        BufferedImage image = cropImage(ImageIO.read(input), x, y, x + width, y + height);
        Path photo = generatedPath(module, ".jpg");
        if (Files.notExists(photo)) {
            Files.createDirectories(photo.getParent());
            Files.createFile(photo);
        }
        ImageIO.write(image, "JPEG", photo.toFile());
        return getRelativizePath(photo);
    }

    /**
     * 裁剪图片方法
     * @param bufferedImage 图像源
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    private static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX < 0 || startX > width) {
            startX = 0;
        }
        if (startY < 0 || startY > height) {
            startY = 0;
        }
        if (endX == -1 || endX > width) {
            endX = width - 1;
        }
        if (endY == -1 || endY > height) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, TYPE_INT_BGR);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    /**
     *
     */
    public static String getFilePath(String filePath) {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
        DateFormat dateFormat3 = new SimpleDateFormat("MM");
        Date date = new Date();
        String strYear = dateFormat2.format(date);
        String strMonth = dateFormat3.format(date);
        filePath = filePath + strYear + File.separatorChar + strMonth + File.separatorChar;
        filePath = filePath + strYear + File.separator + File.separator + strMonth + File.separator + File.separator;
        return filePath;
    }

    /**
     * 文件转字符串
     *
     * @param modelFile
     * @return
     * @By Devin 2016-07-09
     */
    public static String fileToString(File modelFile) {
        String returnStr = "";
        try {
            FileInputStream fis = new FileInputStream(modelFile);
            Reader reader = new FileReader(modelFile);
            char c[] = new char[fis.available()];
            int len = reader.read(c);
            reader.close();
            returnStr = new String(c, 0, len);
            return returnStr;
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    /**
     * 字符串存为html文件
     *
     * @param filePath
     * @param content
     * @return
     * @By Devin 2016-07-09
     */
    public static String stringToFile(String filePath, String url, String content) {
        String newFileName = "";
        //判断url是否存在
        if (url == null || "".equals(url)) {
            //文件名
            DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddhhmmssSSS");
            Date date = new Date();
            newFileName = "/" + dateFormat1.format(date) + ".html";
            //存储路径
            filePath = getFilePath(filePath);
            //判断上传目录
            File uploadDir = new File(filePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        } else {
            newFileName = url;
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new StringReader(content));
            bufferedWriter = new BufferedWriter(new FileWriter(filePath + newFileName));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
            return filePath + newFileName;
        } catch (Exception ex) {
            logger.error("error:" + ex.toString());
            return "";
        }

    }

}

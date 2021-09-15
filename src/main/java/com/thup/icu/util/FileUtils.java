package com.thup.icu.util;


import com.thup.icu.demo.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.JFileChooser;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;


/**
 *@Author tlibn
 *@Date 2021/9/11 14:05
 **/
@Deprecated
@Slf4j
public class FileUtils {

    /*判断文件是否存在*/
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /*判断是否是文件夹*/
    public static boolean isDir(String path) {
        File file = new File(path);
        if(file.exists()){
            return file.isDirectory();
        }else{
            return false;
        }
    }

    /**
     * 文件或者目录重命名
     * @param oldFilePath 旧文件路径
     * @param newName 新的文件名,可以是单个文件名和绝对路径
     * @return
     */
    public static boolean renameTo(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            //若文件存在
            if(oldFile.exists()){
                //判断是全路径还是文件名
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0){
                    //单文件名，判断是windows还是Linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if(newName.indexOf("/") > 0){
                        //Linux系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1)  + newName;
                    }else{
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1)  + newName;
                    }
                }
                File file = new File(newName);
                //判断重命名后的文件是否存在
                if(file.exists()){
                    log.info("该文件已存在,不能重命名");
                }else{
                    //不存在，重命名
                    return oldFile.renameTo(file);
                }
            }else {
                log.info("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /*文件拷贝操作*/
    public static void copy(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            in = fis.getChannel();//得到对应的文件通道
            out = fos.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null){
                    out.close();
                }
                if (in != null){
                    in.close();
                }
                if (fos != null){
                    fos.close();
                }
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*读取Text文件操作*/
    public static String readText(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 通过上一层目录和目录名得到最后的目录层次
     * @param previousDir 上一层目录
     * @param dirName 当前目录名
     * @return
     */
    public static String getSaveDir(String previousDir, String dirName) {
        if (StringUtils.isNotBlank(previousDir)){
            dirName = previousDir + "/" + dirName + "/";
        }else {
            dirName = dirName + "/";
        }
        return dirName;
    }

    /**
     * 如果目录不存在，就创建文件
     * @param dirPath
     * @return
     */
    public static String mkdirs(String dirPath) {
        try{
            File file = new File(dirPath);
            if(!file.exists()){
                file.mkdirs();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dirPath;
    }


    /**
     * 在Linux系统中读取文件时将文件排序
     * @param filePath
     * @return
     */
    public static File[] fileSort(String filePath){
        File[] files = new File(filePath).listFiles();
        int filesLength = files.length;
        String nextFix = FilenameUtils.getExtension(files[0].getName());
        File[] fileNames = new File[filesLength];
        for (int i = 0; i < filesLength; i++) {
            for (int j = 0; j < filesLength; j++) {
                String absolutePath = files[j].getAbsolutePath();
                if (absolutePath.endsWith("/" + i + "." + nextFix) || absolutePath.endsWith("\\" + i + "." + nextFix)){
                    fileNames[i] = new File(absolutePath);
                    break;
                }
            }
        }
        return fileNames;
    }


    /**
     * 普通文件下载，文件在服务器里面
     * @param request
     * @param response
     */
    public static void download(HttpServletRequest request, HttpServletResponse response) {
        try{
            //设置文件下载时，文件流的格式
            String realPath = request.getServletContext().getRealPath("/");
            realPath = realPath + "index.jsp";
            System.out.println("下载地址="+realPath);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));
            BufferedOutputStream bos =  new BufferedOutputStream(response.getOutputStream());
            //下面这个变量保存的是要下载的文件拼接之后的完整路径
            String downName = realPath.substring(realPath.lastIndexOf("/") + 1);
            System.out.println("下载文件名="+downName);
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(downName,"utf-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            try {
                bis.close();
                bos.close();
            }catch (Exception e){
                e.printStackTrace();;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下载出错");
        }
    }

    /**
     * 普通文件下载，文件路径固定
     * @param targetFile 下载的文件路径
     * @param response
     */
    public static void download(String targetFile, HttpServletResponse response) {
        try{
            System.out.println("下载文件路径="+targetFile);
            //设置文件下载时，文件流的格式
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
            BufferedOutputStream bos =  new BufferedOutputStream(response.getOutputStream());
            //下面这个变量保存的是要下载的文件拼接之后的完整路径
            String downName = targetFile.substring(targetFile.lastIndexOf("/") + 1);
            System.out.println("下载文件名="+downName);
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(downName,"utf-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            try {
                bis.close();
                bos.close();
            }catch (Exception e){
                e.printStackTrace();;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下载出错");
        }
    }

    /**
     * 下载网络文件
     * @param targetFile
     * @param response
     */
    public static void downloadUrl(String targetFile, HttpServletResponse response) {
        try{
            URL website = new URL(targetFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("D:/img/1.zip");//例如：test.txt
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下载出错");
        }
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */

    public static boolean delete (String fileName){
        try{
            File sourceFile = new File(fileName);
            if(sourceFile.isDirectory()){
                for (File listFile : sourceFile.listFiles()) {
                    delete(listFile.getAbsolutePath());
                }
            }
            return sourceFile.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件列表
     * @author zql
     * @createTime 2020-12-01 22:00:30
     *
     * @param fileList 保存File对象的集合
     * @param strPath 路径
     * @param bl 是否获取全部文件，true获取全部，false只获取路径下的文件，不包括路径下子文件夹的文件
     * @return
     */
    public static List<File> getFileList(List<File> fileList, String strPath, boolean bl) {
        File dir = new File(strPath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        // 因为System Volume Information文件夹是无法访问的，所以需要判断不为空
        if (Objects.nonNull(files)) {
            for (int i = 0; i < files.length; i++) {
                // 判断是文件还是文件夹，如果是，是否获取全部文件
                if (files[i].isDirectory() && bl) {
                    // 获取文件绝对路径
                    getFileList(fileList, files[i].getAbsolutePath(), bl);
                    // 判断是文件还是文件夹，如果是文件夹，不添加到文件列表
                } else if (files[i].isDirectory()) {
                    continue;
                } else {
                    fileList.add(files[i]);
                }
            }
        }
        return fileList;
    }

    /**
     * 获取文件夹列表
     * @author zql
     * @createTime 2020-12-01 22:01:57
     *
     * @param folderList 保存File对象的集合
     * @param strPath 路径
     * @return
     */
    public static List<File> getFolderList(List<File> folderList, String strPath) {
        File dir = new File(strPath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        // 因为System Volume Information文件夹是无法访问的，所以需要判断不为空
        if (Objects.nonNull(files)) {
            for (int i = 0, l = files.length; i < l; i++) {
                // 如果是文件夹，就添加入列表
                if (files[i].isDirectory()) {
                    // 递归放在folderList之前，这样是为了输出时能先输出最深层的文件夹
                    getFolderList(folderList, files[i].getPath());
                    folderList.add(files[i]);
                }
            }
        }
        return folderList;
    }

    /**
     * 获取路径下的文件名列表，文件名包括文件名扩展名
     * @author zql
     * @createTime 2020-12-01 22:04:45
     *
     * @param strPath 路径
     * @param bl 是否获取全部文件名列表，true获取全部，包括子文件夹下的文件，false只获取路径下的文件，不包括文件夹
     * @return 返回字符串类型的文件名数组
     */
    public static String[] getFileName(String strPath,boolean bl) {
        List<File> files = new ArrayList<File>();
        List<File> fileList = getFileList(files, strPath, bl);
        String[] fileName = new String[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            fileName[i] = fileList.get(i).getName();
        }
        return fileName;
    }

    /**
     * 获取路径下的文件的全路径，包括文件名扩展名
     * @author zql
     * @createTime 2020-12-01 22:05:07
     *
     * @param strPath 路径
     * @param bl 是否获取全部文件全路径，true获取全部，包括子文件夹下的文件，false只获取路径下的文件，不包括文件夹
     * @return 返回字符串类型的文件名全路径数组
     */
    public static String[] getFileAllPath(String strPath, boolean bl) {
        List<File> files = new ArrayList<File>();
        List<File> fileList = getFileList(files, strPath, bl);
        String[] fileName = new String[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            fileName[i] = fileList.get(i).getAbsolutePath();
        }
        return fileName;
    }

    /**
     * 创建目录
     * @author zql
     * @createTime 2020-12-01 22:05:57
     *
     * @param destDirName 目标目录名
     * @return 目录创建成功返回true，否则返回false
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建单个目录
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除文件
     * @author zql
     * @createTime 2020-12-01 22:06:15
     *
     * @param filePathAndName 文件路径及名称 如c:/fpn.txt
     */
    public static void delFile(String filePathAndName) {
        try {
            Files.deleteIfExists(new File(filePathAndName).toPath());
        } catch (Exception e) {
            System.out.println("There was an error deleting the file!");
            e.printStackTrace();
        }
    }

    /**
     * 文件复制方法
     *
     * @author zql
     * @createTime 2021-03-19 10:48:01
     *
     * @param source 源文件
     * @param dest 新文件
     * @return
     * @throws
     */
    public static void copyFileUsing(File source, File dest) {
        try {
            Files.deleteIfExists(dest.toPath());
            Files.copy(source.toPath(), dest.toPath());
        } catch (Exception e) {
            System.out.println("There was an error deleting the file!");
            e.printStackTrace();
        }
    }

    /**
     * 读取到字节数组0
     * @author zql
     * @createTime 2020-12-01 22:06:30
     *
     * @param filePath 路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static byte[] readToByteArray0(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            ServiceException.ex("文件不存在");
            //file.createNewFile();
        }
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            // file too big 文件太大
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            // Could not completely read file 无法完全读取文件
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * 读取到字节数组1
     * @author zql
     * @createTime 2020-12-01 22:06:50
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readToByteArray1(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * 读取到字节数组2
     * @author zql
     * @createTime 2020-12-01 22:07:03
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readToByteArray2(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取到字节数组3
     * <pre>
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     * </pre>
     * @author zql
     * @createTime 2020-12-01 22:07:15
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readToByteArray3(String filePath) throws IOException {
        FileChannel fc = null;
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filePath, "r");
            fc = rf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
            // System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rf.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件内容
     * @author zql
     * @createTime 2020-12-01 22:07:43
     *
     * @param filePath 文件全路径
     * @param methodType 读取字节的方法
     * <pre>
     * 有四个方法，methodType范围0-3
     * 其中3 可以在处理大文件时，提升性能
     * </pre>
     * @return
     */
    public static String getFileContent(String filePath, int methodType) {
        String str = null;
        boolean past = methodType < 0 || methodType > 3;
        if (past) {
            methodType = 0;
        }
        byte[] b;
        try {
            switch (methodType) {
                case 0:
                    b = readToByteArray0(filePath);
                    break;
                case 1:
                    b = readToByteArray1(filePath);
                    break;
                case 2:
                    b = readToByteArray2(filePath);
                    break;
                case 3:
                    b = readToByteArray3(filePath);
                    break;
                default:
                    b = readToByteArray0(filePath);
            }
            str = new String(b, "utf-8");
        } catch (IOException e) {
            System.out.println("Error reading process!");
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 读取文件内容
     * @author zql
     * @createTime 2020-12-01 22:10:21
     *
     * @param filePath
     * @return
     */
    public static String getFileContent(String filePath) {
        return getFileContent(filePath, 3);
    }

    /**
     * 获取文件的md5
     * @author zql
     * @createTime 2020-12-01 22:10:47
     *
     * @param file
     * @return 文件的md5
     */
    public static String getFileMd5(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return DigestUtils.md5Hex(IOUtils.toByteArray(fileInputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件的md5
     * @author zql
     * @createTime 2020-12-01 22:11:02
     *
     * @param filePath 文件路径，包括文件名及扩展名
     * @return 文件的md5
     */
    public static String getFileMd5(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(filePath + " is not exists!");
            return null;
        }
        return getFileMd5(file);
    }

    /**
     * 字符串写入到文件
     * @author zql
     * @createTime 2020-12-01 22:11:19
     *
     * @param content 字符串内容
     * @param filePath 路径
     * @param append true是往文件后追加，false是覆盖
     */
    public static void writeContentToFile(String content, String filePath, boolean append) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file, append);
            // 注意需要转换对应的字符集
            out.write(content.getBytes("utf-8"));
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("Content writing failed!");
            e.printStackTrace();
        }
    }

    /**
     * 字符串写入到文件，此写入为覆盖的方式
     * @author zql
     * @createTime 2020-12-01 22:11:19
     *
     * @param content 字符串内容
     * @param filePath 路径
     */
    public static void writeContentToFile(String content, String filePath) {
        writeContentToFile(content, filePath, false);
    }

    public static void main(String[] args) {
        writeContentToFile("223", "G:\\learn\\1ceba\\icu\\target\\conf.json");
    }

    /**
     * 获取文件最后修改时间
     * @author zql
     * @createTime 2020-12-01 22:13:00
     *
     * @param filePath
     * @return
     */
    public static String getFileModifiedTime(String filePath) {
        File file = new File(filePath);
        return getFileModifiedTime(file);
    }

    /**
     * 获取文件最后修改时间
     * @author zql
     * @createTime 2020-12-01 22:13:11
     *
     * @param file
     * @return
     */
    public static String getFileModifiedTime(File file) {
        long time = file.lastModified();
        String strTime = DateUtil.getTimeBySetTime(time);
        return strTime;
    }

    /**
     * 获取文件后缀名
     * @author zql
     * @createTime 2020-12-01 22:14:08
     *
     * @param str 文件全名或者文件路径，文件路径请包括文件全名
     * @return
     */
    public static String getFileSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /**
     * 获取文件后缀名
     * @author zql
     * @createTime 2020-12-01 22:14:20
     *
     * @param file
     * @return
     */
    public static String getFileSuffix(File file) {
        String str = file.getAbsolutePath();
        return getFileSuffix(str);
    }

    /**
     * 获取文件的创建时间
     * @author zql
     * @createTime 2020-12-01 22:14:31
     *
     * @param filePath
     * @return
     */
    public static String getFileCreateTime(String filePath) {
        String startTime = null;
        File file = new File(filePath);
        String suffix = getFileSuffix(filePath);
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir " + file.getAbsolutePath() + "/tc");
            InputStream input = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            while (Objects.nonNull(line = br.readLine())) {
                if (line.endsWith(suffix)) {
                    startTime = line.substring(0, 17);
                    startTime = startTime.replace("/", "").replace(":", "").replace(" ", "");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return startTime;
    }

    /**
     * 获取文件的创建时间
     * @author zql
     * @createTime 2020-12-01 22:16:42
     *
     * @param file
     * @return
     */
    public static String getFileCreateTime(File file) {
        return getFileCreateTime(file.getAbsolutePath());
    }

    /**
     * 获取文件的最后访问时间，注：jdk环境需要在1.7或之上
     * @author zql
     * @createTime 2020-12-01 22:18:19
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileAccessTime(String filePath) {
        String time = null;
        Path p = Paths.get(filePath);
        try {
            // 获取文件的属性
            BasicFileAttributes att = Files.readAttributes(p, BasicFileAttributes.class);
            // 创建时间
            /// time = att.creationTime().toMillis() + "";
            // 修改时间
            /// time = att.lastModifiedTime().toMillis() + "";
            // 访问时间
            time = att.lastAccessTime().toMillis() + "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将InputStream写入本地文件
     * @author zql
     * @createTime 2021-01-02 11:53:07
     *
     * @param outPath 输出路径
     * @param input 输入流
     * @return
     * @throws IOException
     */
    public static void writeToLocal(String outPath, InputStream input) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream fos = new FileOutputStream(outPath);
        while ((index = input.read(bytes)) != -1) {
            fos.write(bytes, 0, index);
            fos.flush();
        }
        input.close();
        fos.close();
    }

    /**
     * 将输入流转化为字节数组
     *
     * @author zql
     * @createTime 2021-03-30 09:20:46
     *
     * @param in
     * @return
     * @throws
     */
    public static byte[] getByte(InputStream in) throws Exception {
        // 将输入流转化为字节数组
        byte[] data = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(in)) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new Exception("输入流关闭异常");
                }
            }
        }
        return data;
    }

    /**
     * 弹出jdk自带资源管理窗口，用于选择路径
     * @author zql
     * @createTime 2020-12-01 22:18:36
     *
     * @return 选择的路径
     */
    public static String getSelectPath() {
        String selectPath = null;
        JFileChooser chooser = new JFileChooser();
        // 设置只能选择目录
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectPath = chooser.getSelectedFile().getPath();
        }
        return selectPath;
    }

}

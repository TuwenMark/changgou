package com.changgou.util;

import com.changgou.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.InetAddress;

/**
 * @Program: changgou
 * @Description: FastDFS的客户端类，对文件进行操作
 * @Author: Mr.Ye
 * @Date: 2021-12-05 20:44
 **/
public class FastDFSClient {

    // 加载全局参数配置
    static {
        // 获取FastDFS的配置文件路径
        String filePath = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param file 待上传的文件
     * @return 文件存储信息
     * @throws Exception 异常
     */
    public static String[] upload(FastDFSFile file) throws Exception{
        // 获取文件附加信息并保存，包括作者、地理位置等
        NameValuePair[] meta_list = new NameValuePair[2];
        meta_list[0] = new NameValuePair("name", file.getName());
        meta_list[1] = new NameValuePair("author", file.getAuthor());
        // 获取storage客户端
        StorageClient storageClient = getStorageClient();
        // 上传文件，返回文件信息：1. Storage的组名；2. 文件名，包括虚拟路径和层级目录
        // http://192.168.136.132:8080/group1/M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png
        String[] fileMessage = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        return fileMessage;
    }

    /**
     * 获取文件信息
     *
     * @param group_name 文件在Storage中的组名，如：group1
     * @param remote_filename 文件在Storage中的文件名，如：M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png
     * @return 文件信息对象
     * @throws Exception 异常
     */
    public static FileInfo getFile(String group_name, String remote_filename) throws Exception {
        StorageClient storageClient = getStorageClient();
        return storageClient.get_file_info(group_name, remote_filename);
    }

    /**
     * 下载文件
     *
     * @param group_name 文件在Storage中的组名，如：group1
     * @param remote_filename 文件在Storage中的文件名，如：M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png
     * @return 文件的二进制输出流
     * @throws Exception 异常
     */
    public static InputStream download(String group_name, String remote_filename) throws Exception {
        StorageClient storageClient = getStorageClient();
        byte[] fileBytes = storageClient.download_file(group_name, remote_filename);
       return new ByteArrayInputStream(fileBytes);
    }

    /**
     * 删除存储的文件
     *
     * @param group_name 文件在Storage中的组名，如：group1
     * @param remote_filename 文件在Storage中的文件名，如：M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png
     * @throws Exception 异常
     */
    public static void deleteFile(String group_name, String remote_filename) throws Exception {
        StorageClient storageClient = getStorageClient();
        storageClient.delete_file(group_name, remote_filename);
    }

    /**
     * 获取单个Storage服务器信息
     * http://192.168.136.132:8080/group1/M00/00/00/wKiIhGGuCbOAeZQgAARzdFKvjiU298.jpg
     * http://192.168.136.132:8080/group1/M00/00/00/wKiIhGGuCemAUkFhAAg3CeGEg2A326.png
     * http:///192.168.136.132:8080/group1/M00/00/00/wKiIhGGuFuWACdcyAAJ9rY7MFTA318.png
     *
     * @return Storage服务器
     * @throws IOException IO异常
     */
    public static StorageServer getStorage() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        return storage;
    }

    /**
     * 根据组名和文件名获取Storage服务器组信息，多个服务器信息
     *
     * @param group_name 组名
     * @param remote_filename 远程文件名
     * @return Storage组信息
     * @throws IOException IO异常
     */
    public static ServerInfo[] getServerInfo(String group_name, String remote_filename) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        ServerInfo[] serverInfos = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
        return serverInfos;
    }

    /**
     * 获取Tracker服务器的访问路径
     *
     * @return Tracker服务器的访问路径
     * @throws IOException IO异常
     */
    public static String getTrackerUrl() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        InetAddress ip = trackerServer.getInetSocketAddress().getAddress();
        // TCP端口，传输数据的端口
//        int port = trackerServer.getInetSocketAddress().getPort();
        // http访问端口
        int port = ClientGlobal.getG_tracker_http_port();
        String url = "http://" + ip + ":" + port;
        return url;
    }

    /**
     * 获取Storage的客户端对象
     *
     * @return Storage客户端对象
     * @throws IOException IO异常
     */
    private static StorageClient getStorageClient() throws IOException {
        // 获取tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        // 通过tracker客户端获取服务器端
        TrackerServer trackerServer = trackerClient.getConnection();
        // 通过tracker服务器端获取storage客户端
        return new StorageClient(trackerServer, null);
    }


    /**
     * 演示方法功能
     *
     * @param args 参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws Exception {
        /**
         * 获取文件信息
         */
        // 获取文件存储信息
//        FileInfo fileInfo = getFile("group1", "M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png");
//        System.out.println(fileInfo.getSourceIpAddr());
//        System.out.println(fileInfo.getFileSize());
//        System.out.println(fileInfo.getCreateTimestamp());

        /**
         * 下载文件
         */
//        // 下载文件
//        InputStream is = download("group1", "M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png");
//        // 创建输出流
//        OutputStream os = new FileOutputStream("D:/1.jpg");
//        // 定义缓冲区
//        byte[] bytes = new byte[1024];
//        // 循环读取写入数据
//        while(is.read(bytes) != -1) {
//            os.write(bytes);
//        }
//        is.close();
//        os.close();

        /**
         * 删除文件
         */
//        deleteFile("group1", "M00/00/00/wKiIhGGsyPOAa1zHAAsTB_XvjXg075.png");

        /**
         * 获取Storage服务器信息
         */
//        StorageServer storage = getStorage();
//        System.out.println(storage.getInetSocketAddress().getAddress());
//        System.out.println(storage.getInetSocketAddress().getPort());

        /**
         * 获取Storage组信息
         * http://192.168.136.132:8080/group1/M00/00/00/wKiIhGGuCemAUkFhAAg3CeGEg2A326.png
         */
//        ServerInfo[] serverInfos = getServerInfo("group1", "M00/00/00/wKiIhGGuCemAUkFhAAg3CeGEg2A326.png");
//        for (ServerInfo serverInfo : serverInfos) {
//            System.out.println(serverInfo.getIpAddr());
//            System.out.println(serverInfo.getPort());
//        }

        /**
         * 获取Tracker服务器的访问路径
         */
        System.out.println(getTrackerUrl());
    }
}

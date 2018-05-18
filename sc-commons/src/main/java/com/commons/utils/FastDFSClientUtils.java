package com.commons.utils;

import com.commons.entity.FastDFSFileEntity;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
  * @Description(功能描述): fastDFS工具
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/5/18 16:40
  **/
public class FastDFSClientUtils {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSClientUtils.class);

	static {
		try {
			String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
			ClientGlobal.init(filePath);
		} catch (Exception e) {
			logger.error("FastDFS客户端初始化失败!",e);
		}
	}

	/** 
	  * @Description(功能描述): 文件上传
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/17 15:48
	  **/ 
	public static FastDFSFileEntity saveFile(MultipartFile multipartFile) throws IOException {
		String[] fileAbsolutePath={};
		String fileName=multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		byte[] file_buff = null;
		InputStream inputStream=multipartFile.getInputStream();
		if(inputStream!=null){
			int len1 = inputStream.available();
			file_buff = new byte[len1];
			inputStream.read(file_buff);
		}
		inputStream.close();
		FastDFSFileEntity file = new FastDFSFileEntity(fileName, file_buff, ext);
		try {
			fileAbsolutePath = FastDFSClientUtils.upload(file);
		} catch (Exception e) {
			logger.error("文件上传异常",e);
		}
		if (fileAbsolutePath==null) {
			logger.error("文件上传异常，请重新上传");
		}
		String path=FastDFSClientUtils.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
		file.setName(fileAbsolutePath[1]);
		file.setGroup(fileAbsolutePath[0]);
		file.setImgUrl(path);
		return file;
	}
	/** 
	  * @Description(功能描述): 上传封装
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/17 15:45
	  **/ 
	public static String[] upload(FastDFSFileEntity file) {
		NameValuePair[] meta_list = new NameValuePair[1];
		meta_list[0] = new NameValuePair("author", file.getAuthor());

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		StorageClient storageClient=null;
		try {
			storageClient = getTrackerClient();
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (IOException e) {
			logger.error("当uploadind文件时，IO异常。:" + file.getName(), e);
		} catch (Exception e) {
			logger.error("当uploadind文件时，非IO异常。:" + file.getName(), e);
		}
		if (uploadResults == null && storageClient!=null) {
			logger.error("上传文件失败,错误代码:" + storageClient.getErrorCode());
		}
		return uploadResults;
	}

	/** 
	  * @Description(功能描述): 获取文件信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/17 15:19
	  **/ 
	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			StorageClient storageClient = getTrackerClient();
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			logger.error("IO异常:从快速DFS获取文件失败。", e);
		} catch (Exception e) {
			logger.error("非IO异常:从快速DFS获取文件失败。", e);
		}
		return null;
	}

	/**
	  * @Description(功能描述): 返回文件
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/18 16:37
	  **/
	public static void fileDownload(HttpServletResponse resp, String groupName, String remoteFileName) throws IOException, MyException {
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		byte[] strings = storageClient.download_file(groupName, remoteFileName);
		OutputStream out = resp.getOutputStream();
		IOUtils.write(strings, out);
	}
	/** 
	  * @Description(功能描述): 下载文件流返回
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/17 15:19
	  **/ 
	public static InputStream downFile(String groupName, String remoteFileName) {
		try {
			StorageClient storageClient = getTrackerClient();
			byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
			InputStream ins = new ByteArrayInputStream(fileByte);
			return ins;
		} catch (IOException e) {
			logger.error("IO异常:从快速DFS获取文件失败。", e);
		} catch (Exception e) {
			logger.error("非IO异常:从快速DFS获取文件失败。", e);
		}
		return null;
	}

	/** 
	  * @Description(功能描述): 删除文件
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/17 15:18
	  **/ 
	public static void deleteFile(String groupName, String remoteFileName)
			throws Exception {
		StorageClient storageClient = getTrackerClient();
		int i = storageClient.delete_file(groupName, remoteFileName);
		logger.info("delete file successfully!!!" + i);
	}

	public static StorageServer[] getStoreStorages(String groupName)
			throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName,
												String remoteFileName) throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}

	/**
	  * @Description(功能描述): 获取当前文件服务器地址
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/18 17:19
	  **/
	public static String getTrackerUrl() throws IOException {
		return "http://"+getTrackerServer().getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
	}

	private static StorageClient getTrackerClient() throws IOException {
		TrackerServer trackerServer = getTrackerServer();
		StorageClient storageClient = new StorageClient(trackerServer, null);
		return  storageClient;
	}

	private static TrackerServer getTrackerServer() throws IOException {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		return  trackerServer;
	}
}
package com.admin.controller.img;

import com.commons.entity.FastDFSFileEntity;
import com.commons.utils.FastDFSClientUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    /**
      * @Description(功能描述): 上传
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/5/18 16:58
      **/
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
			FastDFSFileEntity path=FastDFSClientUtils.saveFile(file);
            redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",path.getImgUrl());
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }
	@GetMapping("/downFile")
	public InputStream downFile(String groupName, String remoteFileName) {
		return FastDFSClientUtils.downFile(groupName,remoteFileName);
	}
	@ResponseBody
	@GetMapping("/getFile")
	public FileInfo getFile(String groupName, String remoteFileName) {
		return FastDFSClientUtils.getFile(groupName,remoteFileName);
	}
	@GetMapping("/getFileName")
	public void getFileName(HttpServletResponse response) throws IOException, MyException {
		FastDFSClientUtils.fileDownload(response,"group1","M00/00/00/rBJD-1r-khOAMa6VAAAyKo-NfeY504.jpg");
	}

	@GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/testDelete")
	public void testDelete(){
		try {
			ClientGlobal.init("fdfs_client.conf");
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer,storageServer);
			int i = storageClient.delete_file("group1", "M01/00/00/rBJD-1r9V7SATeHeAAAyKo-NfeY918.jpg");
			System.out.println( i==0 ? "删除成功" : "删除失败:"+i);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
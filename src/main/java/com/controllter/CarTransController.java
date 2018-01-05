package com.controllter;

import com.comm.response.BaseResponse;
import com.model.CarTransaction;
import com.model.ContainerMessage;
import com.model.Image;
import com.model.request.CarTransactionRequest;
import com.service.CarTransactionService;
import com.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 发布二手车信息
 * 
 * @author zhaixt
 * 
 */
@Slf4j
@RestController
@RequestMapping(value = "/car_transaction")
public class CarTransController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public CarTransactionService carTransactionService;

	@RequestMapping(value = "/publish")
	public BaseResponse<String> publicCarTransactionInfo(@RequestBody CarTransaction carTransaction) {
		return carTransactionService.publisCarTransactionInfo(carTransaction);
	}



	/**
	 * 根据标题、详细信息分页查询二手车交易信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<CarTransaction> queryCarTransactions(@RequestBody CarTransactionRequest carTransactionRequest) {
		
		String topic = carTransactionRequest.getTopic();
		String type = carTransactionRequest.getType();
		Integer axisNum = carTransactionRequest.getAxisNum();
		Integer year = carTransactionRequest.getYear();
		String details = carTransactionRequest.getDetails();

		int currentPage = carTransactionRequest.getCurrentPage();
		
		int pageSize = carTransactionRequest.getPageSize();
		return carTransactionService.findByPage(topic, details,axisNum,type,year,currentPage, pageSize);
	}


	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteCarTransactionInfo(@PathVariable Long id) {
		return carTransactionService.deleteCarTransactionInfo(id);
	}


	/**
	 * 图片文件上传
	 */
	@ResponseBody
	@RequestMapping(value = "/photo_upload",method = RequestMethod.POST)
	public BaseResponse<String> photoUpload(@RequestParam("upLoadFile") MultipartFile file,@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IllegalStateException, IOException{
		BaseResponse<String> resultData = new BaseResponse<>(Boolean.TRUE);
		// 判断用户是否登录
        /*User user=(User) session.getAttribute("user");
        if (user==null) {
            resultData.setCode(40029);
            resultData.setMsg("用户未登录");
            return resultData;
        }*/
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
		if (file!=null) {// 判断上传的文件是否为空
			String path=null;// 文件路径
			String type=null;// 文件类型
//			String fileName=file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:"+fileName);
			log.info("上传的文件原名称:"+fileName);
			// 判断文件类型
			type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
			if (type!=null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
					String realPath= "D:\\alidata1\\admin\\shipping\\";
//					String realPath=request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName=fileName;
					// 设置存放图片文件的路径
					path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
					System.out.println("存放图片文件的路径:"+path);
					// 转存文件到指定的路径
					try {
						file.transferTo(new File(path));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("文件成功上传到指定目录下");
					log.info("文件成功上传到指定目录下");
				}else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					log.warn("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			}else {
				System.out.println("文件类型为空");
				return null;
			}
		}else {
			System.out.println("没有找到相对应的文件");
			log.info("没有找到相对应的文件"+fileName);
			return null;
		}
		return resultData;
	}

	/**
	 * 图片文件上传
	 */
	@ResponseBody
	@RequestMapping(value = "/photo_base64_upload",method = RequestMethod.POST)
	public BaseResponse<String> photoBase64Upload(@RequestParam String base64Code,@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IllegalStateException, IOException{
		BaseResponse<String> resultData = new BaseResponse<>(Boolean.TRUE);
		// 判断用户是否登录
        /*User user=(User) session.getAttribute("user");
        if (user==null) {
            resultData.setCode(40029);
            resultData.setMsg("用户未登录");
            return resultData;
        }*/
		String path=null;// 文件路径
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
		if (!StringUtils.isEmpty(base64Code)) {// 判断上传的文件是否为空

			String realPath= "D:\\alidata1\\admin\\shipping\\";
			path = realPath+fileName;
			Base64Util.GenerateImage(base64Code,path);
		}else {
			System.out.println("没有找到相对应的文件");
			log.info("没有找到相对应的文件"+fileName);

			return null;
		}
		return resultData;
	}

	/**
	 * 图片文件上传
	 */
	@ResponseBody
	@RequestMapping(value = "/multi_photo_base64_upload",method = RequestMethod.POST)
	public BaseResponse<String> photoBase64Upload(@RequestBody List<Image> images, HttpSession session) throws IllegalStateException, IOException{
		BaseResponse<String> resultData = new BaseResponse<>(Boolean.TRUE);
		// 判断用户是否登录
        /*User user=(User) session.getAttribute("user");
        if (user==null) {
            resultData.setCode(40029);
            resultData.setMsg("用户未登录");
            return resultData;
        }*/
		String path=null;// 文件路径
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
		if (images != null && images.size() > 0) {// 判断上传的文件是否为空

			String realPath= "D:\\alidata1\\admin\\shipping\\";
			for(Image image:images){
				path = realPath+image.getFileName();
				Base64Util.GenerateImage(image.getBase64Code(),path);
			}
		}else {
			System.out.println("没有找到相对应的文件");
			log.info("没有找到相对应的文件"+images.get(0).getFileName());

			return null;
		}
		return resultData;
	}

	@RequestMapping(value="/photo_download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
										   @RequestParam("fileName") String filename,
										   Model model)throws Exception {
		//下载文件路径
//		String path = request.getServletContext().getRealPath("/images/");
		String path = "D:\\alidata1\\admin\\shipping\\";
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());

		File file = new File(path + File.separator + filename);
		HttpHeaders headers = new HttpHeaders();
		//下载显示的文件名，解决中文名称乱码问题
		String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFielName);
		//application/octet-stream ： 二进制流数据（最常见的文件下载）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/photo_multi_download")
	public BaseResponse<List<Image>> downloadPhoto(HttpServletRequest request,
										   @RequestParam("filePaths") String filePaths)throws Exception {
		BaseResponse<List<Image>> resultData = new BaseResponse<>(Boolean.TRUE);
		//下载文件路径
		String path = "";
		String[] filePathArray = filePaths.split(";");
		String filePath;
		List<Image> imageList = new ArrayList<>();
		if(filePathArray.length < 1){
			resultData.setSuccess(false);
			resultData.setFailMessage("no pictures");
			return resultData;
		}
		for(int i=0;i<filePathArray.length;i++){
			Image image = new Image();
			filePath = path+filePathArray[i];
			image.setFileName(filePathArray[i]);
			image.setBase64Code(Base64Util.getImageStr(filePath));
			imageList.add(image);
		}
		resultData.setResult(imageList);
		return resultData;
	}

}

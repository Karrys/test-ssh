package com.cict.offical.network.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cict.offical.network.base.conf.PropConf;
import com.cict.offical.network.utils.Result;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@Autowired
	PropConf propConf;

	/**
	 * 图片上传
	 * 
	 * @param picture
	 * @param request
	 * @return
	 * @return
	 */
	@PostMapping("/uploadPicture")
	@ResponseBody
	public Result<String> upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {

		// 获取文件在服务器的储存位置
		// String path = "F://upload//static//upload";
		String path = propConf.getUploadPath();

		File filePath = new File(path);
		System.out.println("文件的保存路径：" + path);
		if (!filePath.exists() && !filePath.isDirectory()) {
			System.out.println("目录不存在，创建目录:" + filePath);
			filePath.mkdirs();
		}

		// 获取原始文件名称(包含格式)
		String originalFileName = picture.getOriginalFilename();
		System.out.println("原始文件名称：" + originalFileName);

		// 获取文件类型，以最后一个`.`为标识
		String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		System.out.println("文件类型：" + type);
		// 获取文件名称（不包含格式）
		String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

		// 设置文件新名称: 当前时间+文件名称（不包含格式）
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(d);
		String fileName = date + name + "." + type;
		System.out.println("新文件名称：" + fileName);

		// 在指定路径下创建一个文件
		System.out.println("文件路径" + filePath.getPath());
		File targetFile = new File(filePath.getPath(), fileName);

		// 将文件保存到服务器指定位置
		try {
			picture.transferTo(targetFile);
			System.out.println("上传成功");
			// 将文件在服务器的存储路径返回
			return Result.returnResult("/news_pic/" + fileName);
		} catch (IOException e) {
			System.out.println("上传失败");
			e.printStackTrace();
			return Result.returnErrorResult("上传失败");
		}
	}

	@RequestMapping(value = "/ioReadImage", method = RequestMethod.GET)
	public String IoReadImage(String imgName, HttpServletRequest request, HttpServletResponse response) {

		ServletOutputStream fos = null;
		FileInputStream fis = null;

		try {
			//
			String path = propConf.getUploadPath();
			File targetFile = new File(path, imgName);
			// File targetFile = new File("F://upload//news_pic", "20190108170619bbb.jpg");
			System.out.println(targetFile);
			fis = new FileInputStream(targetFile);
			// response.setContentType("multipart/form-data");
			response.setContentType("image/*");
			fos = response.getOutputStream();
			//
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/base64ReadImage/{imgName:.+}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImageStrFromPath(@PathVariable("imgName") String imgName) {
		FileInputStream fin = null;
		byte[] data = null;
		try {
			//
			String path = propConf.getUploadPath();
			File targetFile = new File(path, imgName);
			System.out.println(targetFile);
			fin = new FileInputStream(targetFile);

			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = fin.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();

			data = baos.toByteArray();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 对字节数组Base64编码
		Encoder encoder = Base64.getEncoder();
		// 返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}

	public static void main(String[] args) {
		/*
		 * String path =
		 * Thread.currentThread().getContextClassLoader().getResource("").getPath()+
		 * "static/upload"; File filePath = new File(path);
		 * System.err.println(filePath);
		 */
	}
}

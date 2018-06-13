package com.lwyykj.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.lwyykj.common.Constants;
import com.lwyykj.core.service.UploadService;

@Controller
public class UploadController {
	@Resource
	private UploadService uploadService;

	// 无敌图片上传，单图和批量都可以
	// 富文本接收器上传图片，可接受不清楚名称的图片
	@RequestMapping("/uploadFck.do")
	@ResponseBody
	public Object uploadFck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<>();
		// 无敌接收图片
		// 强转spring提供MultpartRequest,此处只接收图片
		MultipartRequest mr = (MultipartRequest) request;
			Map<String, MultipartFile> fileMap = mr.getFileMap();
			Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
//			String dir = request.getServletContext().getRealPath("/");
//			System.out.println(dir);
			for (Entry<String, MultipartFile> entry : entrySet) {
				MultipartFile pic = entry.getValue();
				// 控制台输出原文件名字
				System.out.println(pic.getOriginalFilename());
				// 图片服务器返回路径
				String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
				// 拼接全路径
				String url = Constants.IMG_URL + path;
				// System.out.println(path);
				map.put("url", url);
				System.out.println(url);
				// map.put("error", 0);

			}
		

		return map;
	}

	// 图片上传第二种控制器，以下是使用@ResponseBody注解实现返回json
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/uploadPic1.do")
	@ResponseBody
	public Object uploadPic1(@RequestParam(value = "pic", required = false) MultipartFile pic,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 控制台输出原文件名字
		System.out.println(pic.getOriginalFilename());
		// 图片服务器返回路径
		String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
		// 拼接全路径
		// String url = Constants.IMG_URL + path;
		System.out.println(path);
		// 拼接全路径
		String url = Constants.IMG_URL + path;
		// 实例化json对象
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		// 解决跨域调取接口无权限的问题
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// return new JSONPObject(callback, map);
		return map;
	}

	// 图片上传第二种控制器，以下是使用@ResponseBody注解实现返回json
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/uploadPic2.do")
	@ResponseBody
	public Object uploadPic2(@RequestParam(value = "pic1", required = false) MultipartFile pic,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 控制台输出原文件名字
		System.out.println(pic.getOriginalFilename());
		// 图片服务器返回路径
		String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
		// 拼接全路径
		// String url = Constants.IMG_URL + path;
		System.out.println(path);
		// 拼接全路径
		String url = Constants.IMG_URL + path;
		// 实例化json对象
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		// 解决跨域调取接口无权限的问题
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// return new JSONPObject(callback, map);
		return map;
	}

	// 图片上传第二种控制器，以下是使用@ResponseBody注解实现返回json
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/uploadPic3.do")
	@ResponseBody
	public Object uploadPic3(@RequestParam(value = "pic2", required = false) MultipartFile pic,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 控制台输出原文件名字
		System.out.println(pic.getOriginalFilename());
		// 图片服务器返回路径
		String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
		// 拼接全路径
		// String url = Constants.IMG_URL + path;
		System.out.println(path);
		// 实例化json对象
		Map<String, String> map = new HashMap<>();
		// 拼接全路径
		String url = Constants.IMG_URL + path;
		map.put("url", url);
		// 解决跨域调取接口无权限的问题
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// return new JSONPObject(callback, map);
		return map;
	}

	// 图片上传第二种控制器，以下是使用@ResponseBody注解实现返回json
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/uploadPic4.do")
	@ResponseBody
	public Object uploadPic4(@RequestParam(value = "pic3", required = false) MultipartFile pic,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 控制台输出原文件名字
		System.out.println(pic.getOriginalFilename());
		// 图片服务器返回路径
		String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
		// 拼接全路径
		// String url = Constants.IMG_URL + path;
		System.out.println(path);
		// 拼接全路径
		String url = Constants.IMG_URL + path;
		// 实例化json对象
		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		// 解决跨域调取接口无权限的问题
		// response.setHeader("Access-Control-Allow-Origin", "*");
		// return new JSONPObject(callback, map);
		return map;
	}
	
	// 批量异步上传图片
		@RequestMapping("/uploadPics.do")
		@ResponseBody
		public List<String> uploadPics(@RequestParam(required = false) MultipartFile[] pics,HttpServletRequest request,HttpServletResponse response) throws IOException {
			// 声明一个list对象用于组装多个图片的路径
			List<String> urls = new ArrayList<String>();
			if (pics != null) {
				for (MultipartFile pic : pics) {
					// 控制台输出原文件名字
					System.out.println(pic.getOriginalFilename());
					// 图片服务器返回路径
					String path = uploadService.uploadPic(pic, pic.getOriginalFilename(), request, response);
					// 拼接全路径
					String url = Constants.IMG_URL + path;
					System.out.println(url);
					// 组装多张图片的路径
					urls.add(url);
				}
				return urls;
			}
			urls.add("图片上传的值为空");
			return urls;

		}
		@RequestMapping("/up.do")
		@ResponseBody
		private Object fildUpload(@RequestParam(value="file",required=false) MultipartFile[] file,HttpServletRequest request)throws Exception{  
	        //获得物理路径webapp所在路径  
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String time = year + month;
	        String pathRoot = request.getSession().getServletContext().getRealPath("/"+"upload"+"/"+time);  
	        String path="";  
	        List<String> listImagePath=new ArrayList<String>();  
	        Map<String, Object> map = new HashMap<>();
	        for (MultipartFile mf : file) {  
	            if(!mf.isEmpty()){  
	                //生成uuid作为文件名称  
	            	String NewFileName = Constants.getNewFileName();
	                //获得文件类型（可以判断如果不是图片，禁止上传）  
	                String contentType=mf.getContentType();  
	                //获得文件后缀名称  
	                String ext=contentType.substring(contentType.indexOf("/")+1);  
	                path=NewFileName+"."+ext;  
	                mf.transferTo(new File(pathRoot+path));  
	                listImagePath.add(path);  
	            }  
	        }  
	        System.out.println(path);  
	        map.put("url", listImagePath);  
	        return map;  
	    }  
		//富文本编辑器专用
		@RequestMapping(value = "/uploadKE", method = RequestMethod.POST)
		@ResponseBody
		private void fildUpload1( MultipartFile[] file,HttpServletRequest request,HttpServletResponse response) throws Exception {
			// 获得物理路径webapp所在路径
			File dir = null;
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String time = year + month;
			String realpath = request.getSession().getServletContext().getRealPath("/"+"upload"+"/"+time);
//			System.out.println(realpath+"*********************");
			dir = new File(realpath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
//			String pathRoot = request.getSession().getServletContext().getRealPath("");
			String newName = "";
			String path1="";
			String path ="";
			List<String> listImagePath = new ArrayList<String>();
			for (MultipartFile mf : file) {
				if (!mf.isEmpty()) {				
					newName = Constants.getNewFileName() + "." + "jpg";
					path =Constants.IMG_URL + "upload" +"/"+ time + "/" + newName;
					mf.transferTo(new File(realpath +"/"+ newName));
					listImagePath.add(path);
					if(""!=path1){
						path1 = path1+"," + path;
					}else{
						path1 = path;
					}
					System.out.println(path);
				}
			}
			JSONObject jo = new JSONObject();
			jo.put("error", 0);
			jo.put("url", path1);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jo.toString());
		}
		//图集单图都可以
		@RequestMapping(value = "/uploadXX", method = RequestMethod.POST)
		@ResponseBody
		private Object fildUpload1( MultipartFile[] file,HttpServletRequest request) throws Exception {
			//@RequestParam(value = "file", required = false)
//			Map<String, Object> map = new HashMap<>();
			// 获得物理路径webapp所在路径
			File dir = null;
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String time = year + month;
			String realpath = request.getSession().getServletContext().getRealPath("/"+"upload"+"/"+time);
			dir = new File(realpath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String path = "";
//			String path1="";
			String newName ="";
			List<String> listImagePath = new ArrayList<String>();
			for (MultipartFile mf : file) {
				if (!mf.isEmpty()) {
					// 获得文件类型（可以判断如果不是图片，禁止上传）
					String contentType = mf.getContentType();
					// 获得文件后缀名称
					String ext = contentType.substring(contentType.indexOf("/") + 1);
					newName = Constants.getNewFileName() + "." + ext;
					path =Constants.IMG_URL + "upload" +"/"+ time + "/" + newName;
//					path = "/static/images/" + uuid + "." + imageName;
//					path = "/static/images/" + uuid + "." + "jpeg";
					mf.transferTo(new File(realpath+ "/" + newName));
					listImagePath.add(path);
					System.out.println(path);
				}
			}
			return listImagePath;
		}
}

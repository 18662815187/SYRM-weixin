package com.lwyykj.common;

import java.io.File;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {

	public static String uploadPic(MultipartFile pic, String name,HttpServletRequest request,HttpServletResponse response){
		String path = null;
		File dir = null;
		if (!pic.isEmpty()) {
			int opt = name.lastIndexOf(".");
			String ext = name.substring(opt);
			// 新名字
			String NewFileName = Constants.getNewFileName() + ext;
//			System.out.println(NewFileName);
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
			//获取一个文件的保存路径
			String path1 = realpath+"/"+NewFileName;
//			System.out.println(path1);

			// 为文件这服务器中开辟一给新的空间,*没有数据
//			 File newFile = new File(path1); 
			try {
				pic.transferTo(new File(path1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("上传失败");
				e.printStackTrace();
			}
//			System.out.println(newfilepath);
			path ="upload" +"/"+ time + "/" + NewFileName;
			return path;
		}
		return "error";
	}
}

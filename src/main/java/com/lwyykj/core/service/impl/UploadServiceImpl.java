package com.lwyykj.core.service.impl;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwyykj.common.UploadUtils;
import com.lwyykj.core.service.UploadService;


@Service("uploadService")
@Transactional
public class UploadServiceImpl implements UploadService{

	@Override
	public String uploadPic(MultipartFile pic, String name,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return UploadUtils.uploadPic(pic, name, request, response);
	}

}

package com.lwyykj.core.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.product.Notice;
import com.lwyykj.core.service.NoticeService;

/**
 * 通知控制器
 * 
 * @author 52545
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Resource
	private NoticeService noticeService;

	// 通知内容详情
	@RequestMapping("/toNotice")
	public String toDes(Integer id, Model model) {
		Notice notice = noticeService.findById(id);
		if (null != notice) {
			model.addAttribute("notice", notice);
			return "ad";
		}
		return null;
	}

}

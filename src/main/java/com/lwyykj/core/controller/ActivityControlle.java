package com.lwyykj.core.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lwyykj.common.FormatDate;
import com.lwyykj.core.bean.activity.ActivityType;
import com.lwyykj.core.bean.activity.activity;
import com.lwyykj.core.service.AcTypeService;
import com.lwyykj.core.service.AcUserService;
import com.lwyykj.core.service.ActivityService;
import com.lwyykj.core.service.TypeService;
import com.lwyykj.export.service.ExportService;
import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/activity")
public class ActivityControlle {
	@Resource
	private ActivityService activityService;
	@Resource
	private TypeService typeService;
	@Resource
	private AcUserService acUserService;
	@Resource
	private ExportService exportService;
	@Resource
	private AcTypeService acTypeService;

	// 列表页带分页
	@RequestMapping("/list.do")
	public String toList(Integer pageNo, String title, String time, Model model) {
		Pagination pagination = activityService.selectPaginationByQuery(pageNo, title, time);
		if (null != pagination) {
			model.addAttribute("pagination", pagination);
		}
		model.addAttribute("title", title);
		model.addAttribute("time", time);
		return "/product/list";
	}

	// 去添加页
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		List<ActivityType> activityTypes = typeService.findAll();
		if (activityTypes != null && activityTypes.size() > 0) {
			model.addAttribute("types", activityTypes);
		}
		return "/product/edit";
	}

	// 去编辑页
	@RequestMapping("/modify")
	public String toDes(Integer id, Model model) {
		activity activity = activityService.findById(id);
		List<ActivityType> activityTypes = typeService.findAll();
		if (activityTypes != null && activityTypes.size() > 0) {
			model.addAttribute("types", activityTypes);
		}
		if (activity != null) {
			model.addAttribute("activity", activity);
		}
		return "/product/edit";

	}

	// 保存，更新
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(activity activity, String time1, String time2) {
		Map<String, Object> map = new HashMap<>();
		int addtime = (int) (new Date().getTime() / 1000);
		String timeStyle = "yy-MM-dd";
		if (activity.getName() != null) {
			if (null != activity.getTitle()) {
				if (activity.getAcType() != null) {
					if (time1 != null && time2 != null) {
						int a = FormatDate.formatDate(time1, timeStyle);
						int b = FormatDate.formatDate(time2, timeStyle);
						if (a == b) {
							b += 86399;
						}
						if (null != activity.getId() && activity.getId() > 0) {
							int update = activityService.update(activity);
							if (update > 0) {
								map.put("status", 1);
								map.put("msg", "更新成功！");
							} else {
								map.put("status", 2);
								map.put("msg", "更新失败！");
							}
						} else {
							activity.setActivityStart(a);
							activity.setActivityEnd(b);
							activity.setAddtime(addtime);
							int save = activityService.save(activity);
							if (save > 0) {
								map.put("status", 1);
								map.put("msg", "保存成功！");
							} else {
								map.put("staus", 4);
								map.put("msg", "提交失败！");
							}
						}
					} else {
						map.put("status", 3);
						map.put("msg", "开始和结束时间不能为空！");
					}

				} else {
					map.put("status", 6);
					map.put("msg", "活动类型不能为空！");
				}
			} else {
				map.put("status", 7);
				map.put("msg", "标题不能为空");
			}
		} else {
			map.put("status", 8);
			map.put("msg", "描述不能为空！");
		}
		return map;
	}

	// 单删
	@RequestMapping("/delete")
	public String deleteById(Integer id) {
		int a = activityService.delete(id);
		if (a > 0) {
			return "forward:/activity/list.do";
		}
		return null;
	}

	// 批量删除
	@RequestMapping("/deletes.do")
	public String deletes(Integer[] ids) {
		for (Integer id : ids) {
			activityService.delete(id);
		}
		return "forward:/activity/list.do";
	}

	// 跳转活动结果页
	@RequestMapping("/toResult")
	public String toResult(Integer id, String tel, Integer pageNo, Model model) {
		Pagination pagination = acUserService.findById(id, tel, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("tel", tel);
		model.addAttribute("id", id);
		return "/product/ResultList";
	}

	// 活动结果删除
	@RequestMapping("/delResult")
	public String delRes(Integer id, int ac_id, String tel, Integer pageNo, Model model) {
		int a = acUserService.delById(id);
		if (a > 0) {
			model.addAttribute("id", ac_id);
			model.addAttribute("tel", tel);
			model.addAttribute("pageNo", pageNo);
			return "redirect:/activity/toResult";
		}
		return null;
	}

	// 活动结果批量删除
	@RequestMapping("/dels")
	public String dels(Integer[] ids, int ac_id, String tel, Integer pageNo, Model model) {
		for (Integer id : ids) {
			acUserService.delById(id);
		}
		model.addAttribute("id", ac_id);
		model.addAttribute("tel", tel);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/activity/toResult";
	}

	// 导出
	@RequestMapping("/export")
	public @ResponseBody String export(HttpServletResponse response, Integer ac_id) {
		response.setContentType("application/binary;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(("syrm-weixin" + new Date().getTime()).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			String[] titles = { "ID", "昵称", "参与人手机号", "是否中奖", "点赞数", "获奖收货地址", "收货人名字", "收货人手机号", "券号", "是否参加活动" };
			exportService.export(titles, out, ac_id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
	}

	// 活动类型列表页
	@RequestMapping("/toTypeList")
	public String toTypeList(Integer pageNo, String des, Model model) {
		Pagination pagination = acTypeService.findAll(pageNo, des);
		if (null != pagination) {
			model.addAttribute("pagination", pagination);
		}
		model.addAttribute("des", des);
		return "/product/activityType";
	}

	// 删除活动类型
	@RequestMapping("/delType")
	public String delType(Integer id, Integer pageNo, String des, Model model) {
		int a = acTypeService.delById(id);
		if (a > 0) {
			model.addAttribute("des", des);
			model.addAttribute("pageNo", pageNo);
			return "redirect:/activity/toTypeList";
		}
		return null;
	}

	// 批量删除活动类型
	@RequestMapping("/delTypes")
	public String delTypes(Integer[] ids, Integer pageNo, String des, Model model) {
		int a = 0;
		for (Integer id : ids) {
			a = acTypeService.delById(id);
		}
		if (a > 0) {
			model.addAttribute("des", des);
			model.addAttribute("pageNo", pageNo);
			return "redirect:/activity/toTypeList";
		}
		return null;
	}

	// 保存更新活动类型
	@RequestMapping("/saveOrUpdateType")
	@ResponseBody
	public Object saveType(ActivityType activityType) {
		Map<String, Object> map = new HashMap<>();
		if (null != activityType.getDes()) {
			int a = acTypeService.saveOrUpdate(activityType);
			if (a > 0) {
				map.put("status", 1);
			} else {
				map.put("status", 2);
				map.put("msg", "提交失败请重试!");
			}
		} else {
			map.put("status", 3);
			map.put("msg", "类型名称不能为空!");
		}
		return map;
	}

	// 去活动类型编辑页
	@RequestMapping("/toType")
	public String toTypeEdit(Integer id, Model model) {
		if (null != id) {
			ActivityType activityType = acTypeService.findById(id);
			if (null != activityType) {
				model.addAttribute("activityType", activityType);
				return "/product/editType";
			}
		}
		return "/product/editType";
	}

}

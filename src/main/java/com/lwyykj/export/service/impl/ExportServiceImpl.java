package com.lwyykj.export.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import com.lwyykj.core.bean.activity.AcUser;
import com.lwyykj.core.service.AcUserService;
import com.lwyykj.export.service.ExportService;

@Service("exportService")
public class ExportServiceImpl implements ExportService {
	// @Resource
	// private ReportService reportService;
	@Resource
	private AcUserService acUserService;

	@Override
	public void export(String[] titles, ServletOutputStream out, Integer ac_id) {
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}
			// 第五步，写入实体数据
			// System.out.println("这是导出"+time+"--"+proName+"--"+phId);
			List<AcUser> acUsers = acUserService.findByACID(ac_id);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (acUsers != null && !acUsers.isEmpty()) {
				for (int i = 0; i < acUsers.size(); i++) {
					hssfRow = hssfSheet.createRow(i + 1);
					AcUser acUser = acUsers.get(i);

					// 第六步，创建单元格，并设置值
					// 用户编号
					int uid = 0;
					if (acUser.getId() != 0) {
						uid = acUser.getId();
					}
					hssfRow.createCell(0).setCellValue(uid);

					// 品名
					String name = "";
					if (acUser.getNickname() != null) {
						name = acUser.getNickname();
					}
					hssfRow.createCell(1).setCellValue(name);
					// 手机号
					String phone = "";
					if (acUser.getUserPhone() != null) {
						phone = acUser.getUserPhone();
					}
					hssfRow.createCell(2).setCellValue(phone);
					// 是否中奖
					String flag = "";
					if (acUser.getFlag() == 0) {
						flag = "未中奖";
					} else {
						flag = "已中奖";
					}
					hssfRow.createCell(3).setCellValue(flag);
					// 点赞数
					Integer num = 0;
					if (acUser.getNum() != null) {
						num = acUser.getNum();
					}
					hssfRow.createCell(4).setCellValue(num);
					// 获奖收货地址
					String address = "";
					if (acUser.getAddress() != null) {
						address = acUser.getAddress();
					}
					hssfRow.createCell(5).setCellValue(address);
					// 收货人手机号
					String tel = "";
					if (acUser.getPhone() != null) {
						tel = acUser.getPhone();
					}
					hssfRow.createCell(6).setCellValue(tel);
					// 收货人名字
					String g_name = "";
					if (acUser.getName() != null) {
						g_name = acUser.getName();
					}
					hssfRow.createCell(7).setCellValue(g_name);
					// 券号
					String tic = "";
					if (acUser.getTicket() != null) {
						tic = acUser.getTicket();
					}
					hssfRow.createCell(8).setCellValue(tic);
					// 是否参加活动
					String isJoin = "";
					if (acUser.getIsjoin() == 0) {
						isJoin = "未参加";
					} else {
						isJoin = "已参加";
					}
					hssfRow.createCell(9).setCellValue(isJoin);
				}
			}

			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("导出信息失败！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
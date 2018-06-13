package com.lwyykj.export.service;

import javax.servlet.ServletOutputStream;

public interface ExportService {
	//报表导出
	public void export(String[] titles, ServletOutputStream out, Integer ac_id);
}

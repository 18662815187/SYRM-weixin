package com.lwyykj.core.service;

import com.lwyykj.core.bean.utils.Vercode;

/**
 * 验证码接口
 * 
 * @author john
 *
 */
public interface VerCodeService {
	// 保存验证码用于核验
	public int insert(String tel, String code);

	// 查询记录
	public Vercode selectByTel(String tel);

	// 重复请求时删除老记录
	public int delByTel(String tel);
}

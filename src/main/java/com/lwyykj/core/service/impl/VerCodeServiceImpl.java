package com.lwyykj.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.utils.Vercode;
import com.lwyykj.core.bean.utils.VercodeQuery;
import com.lwyykj.core.bean.utils.VercodeQuery.Criteria;
import com.lwyykj.core.dao.utils.VercodeDao;
import com.lwyykj.core.service.VerCodeService;

/**
 * 验证码实现类
 * 
 * @author john
 *
 */
@Service("verCodeService")
@Transactional
public class VerCodeServiceImpl implements VerCodeService {
	@Resource
	private VercodeDao verCodeDao;
	//	插入验证码
	@Override
	public int insert(String tel, String code) {
		Vercode vercode = new Vercode();
		vercode.setAddtime(new Date());
		vercode.setTel(tel);
		vercode.setVercode(code);
		int result = verCodeDao.insertSelective(vercode);
		return result;
	}
	//查询记录
	public Vercode selectByTel(String tel){
		VercodeQuery vercodeQuery = new VercodeQuery();
		Criteria createCriteria = vercodeQuery.createCriteria();
		createCriteria.andTelEqualTo(tel);
		List<Vercode> vercodes = verCodeDao.selectByExample(vercodeQuery);
		if(null!=vercodes&&vercodes.size()>0){
			return vercodes.get(0);
		}
		return null;
	}
	//重复请求时删除老记录
	public int delByTel(String tel){
		VercodeQuery vercodeQuery = new VercodeQuery();
		Criteria createCriteria = vercodeQuery.createCriteria();
		createCriteria.andTelEqualTo(tel);
		int result = verCodeDao.deleteByExample(vercodeQuery);
		return result;
	}

}

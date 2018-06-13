package com.lwyykj.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTag extends TagSupport {

	/**
	 * 时间转换
	 */
	private static final long serialVersionUID = 1L;
	private String value;

	@Override
	public int doStartTag() throws JspException {
		// String vv = "" + value;
		if (null != value && "" != value) {
			try {
				// long time = Long.valueOf(vv.trim());
				long time = Long.valueOf(value.trim());
				Calendar c = Calendar.getInstance();
				// java需要乘以1000
				c.setTimeInMillis(time * 1000);
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = dateformat.format(c.getTime());
				pageContext.getOut().write(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return super.doStartTag();
		}
		return 0;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

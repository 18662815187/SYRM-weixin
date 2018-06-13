package com.lwyykj.core.bean.activity;

import java.io.Serializable;

public class activity implements Serializable {
	private Integer id;

	/**
	 * 活动名称
	 */
	private String name;

	/**
	 * 活动标题
	 */
	private String title;

	/**
	 * 活动封面图，用于朋友圈转发
	 */
	private String pic;

	/**
	 * 活动背景图，活动图，事项说明图
	 */
	private String image;

	/**
	 * 富文本编辑器内容
	 */
	private String content;

	/**
	 * 活动类型
	 */
	private Integer acType;

	/**
	 * 是否需要领券
	 */
	private Boolean ticket;

	/**
	 * 点赞数门槛
	 */
	private Integer num;

	/**
	 * 活动开始时间
	 */
	private Integer activityStart;

	/**
	 * 活动结束时间
	 */
	private Integer activityEnd;

	/**
	 * 添加时间
	 */
	private Integer addtime;

	/**
	 * 是否开启活动
	 */
	private Boolean isShow;

	/**
	 * 是否已删除
	 */
	private Boolean isDel;

	/*
	 * 类型名称
	 */
	private String typeName;
	
	 /**
     * 图片数组
     */
    public String[] getPics() {
    	if (image == null || image == "") {
    		return null;
    	}
    	return image.split(",");
    }
	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAcType() {
		return acType;
	}

	public void setAcType(Integer acType) {
		this.acType = acType;
	}

	public Boolean getTicket() {
		return ticket;
	}

	public void setTicket(Boolean ticket) {
		this.ticket = ticket;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getActivityStart() {
		return activityStart;
	}

	public void setActivityStart(Integer activityStart) {
		this.activityStart = activityStart;
	}

	public Integer getActivityEnd() {
		return activityEnd;
	}

	public void setActivityEnd(Integer activityEnd) {
		this.activityEnd = activityEnd;
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", title=").append(title);
		sb.append(", pic=").append(pic);
		sb.append(", image=").append(image);
		sb.append(", content=").append(content);
		sb.append(", acType=").append(acType);
		sb.append(", ticket=").append(ticket);
		sb.append(", num=").append(num);
		sb.append(", activityStart=").append(activityStart);
		sb.append(", activityEnd=").append(activityEnd);
		sb.append(", addtime=").append(addtime);
		sb.append(", isShow=").append(isShow);
		sb.append(", isDel=").append(isDel);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}
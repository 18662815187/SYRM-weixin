package com.lwyykj.core.bean.activity;

import java.io.Serializable;

public class AcUser implements Serializable {
	private Integer id;

	private String nickname;

	private String headimgurl;

	/**
	 * 是否中奖
	 */
	private Integer flag;

	private String openid;
	//收货人手机号
	private String phone;

	private String address;

	private String name;
	//参加人手机号
	private String userPhone;

	private String userkey;
	/**
	 * 是否领券
	 */
	private String ticket;

	private Byte isjoin;

	/**
	 * 活动ID
	 */
	private Integer acId;

	/**
	 * 点赞数
	 */
	private Integer num;
	
	

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey == null ? null : userkey.trim();
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket == null ? null : ticket.trim();
	}

	public Byte getIsjoin() {
		return isjoin;
	}

	public void setIsjoin(Byte isjoin) {
		this.isjoin = isjoin;
	}

	public Integer getAcId() {
		return acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", nickname=").append(nickname);
		sb.append(", headimgurl=").append(headimgurl);
		sb.append(", flag=").append(flag);
		sb.append(", openid=").append(openid);
		sb.append(", phone=").append(phone);
		sb.append(", address=").append(address);
		sb.append(", name=").append(name);
		sb.append(", userPhone=").append(userPhone);
		sb.append(", userkey=").append(userkey);
		sb.append(", ticket=").append(ticket);
		sb.append(", isjoin=").append(isjoin);
		sb.append(", acId=").append(acId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}
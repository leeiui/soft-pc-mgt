package org.soft.pc.mgt.model;

import java.util.Date;

public class MbgComment {
	
	private Integer id;
	private String pcComment;
	private Byte pcStar;
	private Integer fk;
	private Date commntTimestamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPcComment() {
		return pcComment;
	}
	public void setPcComment(String pcComment) {
		this.pcComment = pcComment;
	}
	public Byte getPcStar() {
		return pcStar;
	}
	public void setPcStar(Byte pcStar) {
		this.pcStar = pcStar;
	}
	public Integer getFk() {
		return fk;
	}
	public void setFk(Integer fk) {
		this.fk = fk;
	}
	public Date getCommntTimestamp() {
		return commntTimestamp;
	}
	public void setCommntTimestamp(Date commntTimestamp) {
		this.commntTimestamp = commntTimestamp;
	}
	

}

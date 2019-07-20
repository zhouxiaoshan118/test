package com.zte.zudp.modules.sys.previewUser.entity;

import java.util.ArrayList;
import java.util.List;

public class XTree {
	private String title;
	private String value;
	private boolean checked;
	private List<XTree> data = new ArrayList<XTree>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<XTree> getData() {
		return data;
	}
	public void setData(List<XTree> data) {
		this.data = data;
	}
	
	
}

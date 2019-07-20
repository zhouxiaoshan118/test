package com.zte.zudp.modules.sys.successProject.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 历史成功项目实体类
 * Created by DW on 2017/12/16 16:39
 */
public class SuccessProject extends DateEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView(View.Default.class)
    @Size(min = 1)
    private String name;

    @JsonView(View.Default.class)
    private String info;

    @JsonView(View.Default.class)
    private String applicationField;

    @JsonView(View.Default.class)
    private String chargeMan;

    @JsonView(View.Default.class)
    private String price;

    @JsonView(View.Default.class)
    private String appSize;

    @JsonView(View.Default.class)
    private Integer devDays;

    @JsonView(View.Default.class)
    private Date startDate;

    @JsonView(View.Default.class)
    private Date endDate;

    @JsonView(View.Default.class)
    private String addr;//后台路径
    
    @JsonView(View.Default.class)
    private String PCReceptionAddr;//前台路径
    
    @JsonView(View.Default.class)
    private String AppCodesViewPath; //手机图片App

    @JsonView(View.Default.class)
    private String WXCodesViewPath; //微信图片二维码
    
    @JsonView(View.Default.class)
    private String techUsed;

    @JsonView(View.Default.class)
    private String imagesPath;

    @JsonView({View.Default.class})
    private String topicImage;

    @JsonView(View.Default.class)
    private String marks;

    @JsonView(View.Default.class)
    private Integer isRecommend;

    private Integer isPCReception=0;//PC前台   0:没有;1:有
    private Integer isPCBackstage=0;//PC后台  0:没有;1:有
    private Integer isPhone=0;//手机端 0:没有;1:有
    private Integer isWeixin=0;//微信端 0:没有;1:有
    
    private String generalUserID;//普通用户ID
    
    private String middleUserID; //中层用户ID
    
    private String rootUserID; //高层用户ID
    
    private String generalUserName;
    
    private String generalUserPwd;
    
    private String middleUserName;
    
    private String middleUserPwd;
    
    private String rootUserName;
    
    private String rootUserPwd;
    
    private List<String> AppCodesViewPathList; //手机App图片路径数组
    
    private List<String> WXCodesViewPathList; //微信端图片路径数组
    
    
    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getApplicationField() {
        return applicationField;
    }

    public void setApplicationField(String applicationField) {
        this.applicationField = applicationField;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public Integer getDevDays() {
        return devDays;
    }

    public void setDevDays(Integer devDays) {
        this.devDays = devDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTechUsed() {
        return techUsed;
    }

    public void setTechUsed(String techUsed) {
        this.techUsed = techUsed;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

	public Integer getIsPCReception() {
		return isPCReception;
	}

	public void setIsPCReception(Integer isPCReception) {
		this.isPCReception = isPCReception;
	}

	public Integer getIsPCBackstage() {
		return isPCBackstage;
	}

	public void setIsPCBackstage(Integer isPCBackstage) {
		this.isPCBackstage = isPCBackstage;
	}

	public Integer getIsPhone() {
		return isPhone;
	}

	public void setIsPhone(Integer isPhone) {
		this.isPhone = isPhone;
	}

	public Integer getIsWeixin() {
		return isWeixin;
	}

	public void setIsWeixin(Integer isWeixin) {
		this.isWeixin = isWeixin;
	}

	public String getPCReceptionAddr() {
		return PCReceptionAddr;
	}

	public void setPCReceptionAddr(String pCReceptionAddr) {
		PCReceptionAddr = pCReceptionAddr;
	}

	public String getAppCodesViewPath() {
		return AppCodesViewPath;
	}

	public void setAppCodesViewPath(String appCodesViewPath) {
		AppCodesViewPath = appCodesViewPath;
	}

	public String getWXCodesViewPath() {
		return WXCodesViewPath;
	}

	public void setWXCodesViewPath(String wXCodesViewPath) {
		WXCodesViewPath = wXCodesViewPath;
	}

	public String getGeneralUserID() {
		return generalUserID;
	}

	public void setGeneralUserID(String generalUserID) {
		this.generalUserID = generalUserID;
	}

	public String getMiddleUserID() {
		return middleUserID;
	}

	public void setMiddleUserID(String middleUserID) {
		this.middleUserID = middleUserID;
	}

	public String getRootUserID() {
		return rootUserID;
	}

	public void setRootUserID(String rootUserID) {
		this.rootUserID = rootUserID;
	}

	public String getGeneralUserName() {
		return generalUserName;
	}

	public void setGeneralUserName(String generalUserName) {
		this.generalUserName = generalUserName;
	}

	public String getGeneralUserPwd() {
		return generalUserPwd;
	}

	public void setGeneralUserPwd(String generalUserPwd) {
		this.generalUserPwd = generalUserPwd;
	}

	public String getMiddleUserName() {
		return middleUserName;
	}

	public void setMiddleUserName(String middleUserName) {
		this.middleUserName = middleUserName;
	}

	public String getMiddleUserPwd() {
		return middleUserPwd;
	}

	public void setMiddleUserPwd(String middleUserPwd) {
		this.middleUserPwd = middleUserPwd;
	}

	public String getRootUserName() {
		return rootUserName;
	}

	public void setRootUserName(String rootUserName) {
		this.rootUserName = rootUserName;
	}

	public String getRootUserPwd() {
		return rootUserPwd;
	}

	public void setRootUserPwd(String rootUserPwd) {
		this.rootUserPwd = rootUserPwd;
	}

	

	public List<String> getAppCodesViewPathList() {
		return AppCodesViewPathList;
	}

	public void setAppCodesViewPathList(List<String> appCodesViewPathList) {
		AppCodesViewPathList = appCodesViewPathList;
	}

	public List<String> getWXCodesViewPathList() {
		return WXCodesViewPathList;
	}

	public void setWXCodesViewPathList(List<String> wXCodesViewPathList) {
		WXCodesViewPathList = wXCodesViewPathList;
	}
    
}

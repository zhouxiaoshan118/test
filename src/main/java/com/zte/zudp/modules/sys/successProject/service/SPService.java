package com.zte.zudp.modules.sys.successProject.service;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.Exceptions;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.modules.sys.successProject.dao.SPDao;
import com.zte.zudp.modules.sys.successProject.entity.GeneralUser;
import com.zte.zudp.modules.sys.successProject.entity.MiddleUser;
import com.zte.zudp.modules.sys.successProject.entity.RootUser;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 成功项目 service 层
 * 对数据进行增删改查操作
 * Created by DW on 2017/12/16 21:56
 */
@Service
@Transactional(readOnly = true)
public class SPService extends BusinessService<SuccessProject> {


    @Value("${imageServer.config_loaction}")
    private String imageServerConfLocation;

    @Value("${imageServe.ip}")
    private String imageServeIp;

    @Value("${localImagePath}")
    private String localImagePath;

    @Value("${sys.url.admin}")
    private String admin;


    private SPDao getDao() {
        return (SPDao) dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(SuccessProject successProject) {
        return super.insert(successProject);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int update(SuccessProject successProject) {
        return super.update(successProject);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SuccessProject get(String id) {
        return super.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SuccessProject> findList() {
        return super.findList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SuccessProject> getSPListByAppField(String appField) {
        return getDao().getSPsByAppFiled(appField);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SuccessProject> getRecommendSP() {
        return getDao().getRecommendSPs();
    }
    
    @Transactional(readOnly=true)
	public List<SuccessProject> findAll() {
		
    	
    	return getDao().findAll();
	}

    @Transactional(readOnly=false)
	public void saveGeneralUser(GeneralUser generalUser) {
		generalUser.setId(IDUtils.defaultUUID());
		getDao().saveGeneralUser(generalUser);
	}
    
    

    @Transactional(readOnly=false)
	public void saveMiddleUser(MiddleUser middleUser) {
		middleUser.setId(IDUtils.defaultUUID());
		getDao().saveMiddleUser(middleUser);
	}

    @Transactional(readOnly=false)
	public void saveRootUser(RootUser rootUser) {
		rootUser.setId(IDUtils.defaultUUID());
		getDao().saveRootUser(rootUser);
	}

	public GeneralUser getGeneralUserById(String generalUserID) {
		return getDao().getGeneralUserById(generalUserID);
	}

	public MiddleUser getMiddleUserById(String middleUserID) {
		return getDao().getMiddleUserById(middleUserID);
	}

	public RootUser getRootUserById(String rootUserID) {
		return getDao().getRootUserById(rootUserID);
	}


	public void checkRoles(SuccessProject successProject){
		if(StringUtils.isBlank(successProject.getGeneralUserName()) || StringUtils.isBlank(successProject.getGeneralUserPwd())){
    		successProject.setGeneralUserID(null);
    	}else{
    		successProject.setGeneralUserID(IDUtils.defaultUUID());
    	}
    	
		if(StringUtils.isBlank(successProject.getMiddleUserName()) || StringUtils.isBlank(successProject.getMiddleUserPwd())){
    		successProject.setMiddleUserID(null);
    	}else{
    		successProject.setMiddleUserID(IDUtils.defaultUUID());
    	}
		
		if(StringUtils.isBlank(successProject.getRootUserName()) || StringUtils.isBlank(successProject.getRootUserPwd())){
			successProject.setRootUserID(null);
		}else{
			successProject.setRootUserID(IDUtils.defaultUUID());
		}
	}

	public SuccessProject getProjectRootUserById(String roleID) {
		return getDao().getProjectRootUserById(roleID);
	}

	public SuccessProject getProjectMiddleUserById(String roleID) {
		return getDao().getProjectMiddleUserById(roleID);
	}

	public SuccessProject getProjectGeneralUserById(String roleID) {
		return getDao().getProjectGeneralUserById(roleID);
	}

	@Override
	public String uploadimgs(MultipartFile multipartFile, String path1, String path2) {
//      上传service 返回图片路径
      String jarhome = System.getProperty("user.dir");
      String filePath = (jarhome+"../../upload"+path1).replaceAll("//","/");

      String fileName = UUID.randomUUID().toString().replaceAll("-","");
      String extName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

      File file = new File(filePath, fileName+extName);
      if(!file.getParentFile().exists()) file.getParentFile().mkdirs();

      try {
//
//          URL resource = SPService.class.getResource("/");
          String userhome = System.getProperty("user.dir");
//          logger.info(userhome);
//          logger.debug("zzq的getResource------>"+resource.getPath());
          multipartFile.transferTo(file);


//          logger.debug(fileName+extName+"写入---->/upload"+path+fileName+extName);
          return localImagePath+"/front"+path2+"/getimg/"+fileName+extName;

      } catch (Exception e) {
          String errorMsg = Exceptions.getStackTraceAsString(e);
          logger.error(errorMsg);
          e.printStackTrace();
      }
      return null;
	}

	
	
	

//    //保存文件到fastdfs图片服务器,需要配置要加入fastdfsUtils和导入fastdfsclient.jar,由于这里使用本地上传就不用了
//    public String upload2imgServer(MultipartFile multipartFile) {
////        try {
////            String originalFilename = multipartFile.getOriginalFilename();
////            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
////            FastDFSClient fastDFSClient = new FastDFSClient(imageServerConfLocation);
////            String filePath = fastDFSClient.uploadFile(multipartFile.getBytes(), extName);
////            return imageServeIp+filePath;
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return null;
//    }
//
//    //回显图片,/为项目部署外围路径
//    public Resource getResource(String filePath, String fileName, ResourceLoader resourceLoader) {
//        String ctx = System.getProperty("user.dir");
//        String resourcePath = String.valueOf(Paths.get(ctx+"../../upload"+filePath,fileName));
//        return resourceLoader.getResource("file:"+resourcePath);
//    }
}

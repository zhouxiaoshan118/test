package com.zte.zudp.modules.sys.previewUser.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.modules.sys.previewUser.dao.PreviewUserDao;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUser;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUserResources;
import com.zte.zudp.modules.sys.previewUser.entity.XTree;
import com.zte.zudp.modules.sys.successProject.dao.SPDao;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;

@Service
@Transactional(readOnly=true)
public class PreviewUserService extends BusinessService<PreviewUser>{
	
	
	
	
	@Autowired
	private PreviewUserDao previewUserDao;
	
	@Autowired
	private SPDao spDao;
	
	@Transactional(readOnly=false)
	public void saveResource(PreviewUserResources previewUserResources) {
		previewUserDao.saveResource(previewUserResources);
	}

	
	
	
	public static Map<String,Boolean> getChecked(String[] resource,String resourceId)
	{
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put(resourceId+"_1", false);
		map.put(resourceId+"_2", false);
		map.put(resourceId+"_3", false);
		map.put(resourceId+"_4", false);
		for(String temp:resource)
		{
			if("1".equals(temp))
			{
				map.put(resourceId+"_1", true);
			}
			if("2".equals(temp))
			{
				map.put(resourceId+"_2", true);
			}
			if("3".equals(temp))
			{
				map.put(resourceId+"_3", true);
			}
			if("4".equals(temp))
			{
				map.put(resourceId+"_4", true);
			}
		}
		return map;
	}
		
	public static List<XTree> createTree(List<SuccessProject> projects)
	{
		List<XTree> xtrees = new ArrayList<XTree>();
		for (SuccessProject successProject : projects) {
			
			XTree xtree = new XTree();
			String projectId = successProject.getId();
			xtree.setTitle(successProject.getName());
			xtree.setValue(projectId);
			
			if(successProject.getIsPCReception()==1){
				XTree subXtree = new XTree();
				subXtree.setTitle("PC前台");
				subXtree.setValue(projectId+"_1");
				xtree.getData().add(subXtree);
			}
			
			if(successProject.getIsPCBackstage()==1){
				XTree subXtree = new XTree();
				subXtree.setTitle("PC后台");
				subXtree.setValue(projectId+"_2");
				xtree.getData().add(subXtree);
			}
			
			if(successProject.getIsPhone()==1){
				XTree subXtree = new XTree();
				subXtree.setTitle("手机端");
				subXtree.setValue(projectId+"_3");
				xtree.getData().add(subXtree);
			}
			
			if(successProject.getIsWeixin()==1){
				XTree subXtree = new XTree();
				subXtree.setTitle("微信端");
				subXtree.setValue(projectId+"_4");
				xtree.getData().add(subXtree);
			}
			
			xtrees.add(xtree);
		}
		return  xtrees;
	}
	
	
	@Override
	@Transactional(readOnly=false)
	public void save(PreviewUser t) {
		String join = "";
		List<String> resources = t.getResources();
		for (String resource : resources) {
			String temp  = resource;
			String resourceId = resource.split("_")[0];
			String name = spDao.get(resourceId).getName();
			temp=temp.replaceAll(resourceId, name);
			System.out.println(temp);
			join += temp +",";
		}
		t.setViewResources(join);
		super.save(t);
		for (String resource : resources) {
			String resourceId = resource.split("_")[0];
			PreviewUserResources previewUserResources = new PreviewUserResources();
			previewUserResources.setId(IDUtils.defaultUUID());
			previewUserResources.setPreviewUserID(t.getId());
			previewUserResources.setResourceID(resourceId);
			previewUserResources.setResourceStr(resource);
			saveResource(previewUserResources);
		}
	}

	public PreviewUser getBypreviewUserNameAndPwd(PreviewUser previewUser) {
		return previewUserDao.getBypreviewUserNameAndPwd(previewUser);
	}

	public PreviewUserResources getPreviewUserResourcesByIds(PreviewUserResources newPreviewUserResources) {
		return previewUserDao.getPreviewUserResourcesByIds(newPreviewUserResources);
	}

	public List<PreviewUserResources> getPuResourcesByPuID(String id) {
		return previewUserDao.getPuResourcesByPuID(id);
	}



	@Transactional(readOnly=false)
	public void deleteInTrue(String previewUserId) {
		previewUserDao.deleteInTrue(previewUserId);
	}
	
	@Transactional(readOnly=false)
	public void updatePUResources(List<String> resources,String previewUserId){
		deleteInTrue(previewUserId);
		for (String resource : resources) {
			PreviewUserResources previewUserResources = new PreviewUserResources();
			String[] split = resource.split("_");
			previewUserResources.setId(IDUtils.defaultUUID());
			previewUserResources.setPreviewUserID(previewUserId);
			previewUserResources.setResourceID(split[0]);
			previewUserResources.setResourceStr(resource);
			saveResource(previewUserResources);
		}
	}



	@Transactional(readOnly=false)
	public void qiyong(String id) {
		previewUserDao.qiyong(id);
	}




	public PreviewUser checkPreviewUserName(String previewUserName) {
		return previewUserDao.checkPreviewUserName(previewUserName);
	}
	
}

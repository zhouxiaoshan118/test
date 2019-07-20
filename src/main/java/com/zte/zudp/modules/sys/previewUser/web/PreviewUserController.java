package com.zte.zudp.modules.sys.previewUser.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUser;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUserResources;
import com.zte.zudp.modules.sys.previewUser.entity.XTree;
import com.zte.zudp.modules.sys.previewUser.service.PreviewUserService;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;
import com.zte.zudp.modules.sys.successProject.service.SPService;


@RestController
@RequestMapping("/front/sys/previewUser")
public class PreviewUserController extends RESTfulController<PreviewUser>{
	
	@Autowired
	private SPService spService;
	
	@Autowired
	private PreviewUserService puService;
	
	@Override
    protected void init() {
        super.init();
        this.setResourceIdentity(Permissions.USER);
        setDefaultViewPath("/modules/sys/previewUser");
    }
	
	
	
	@RequestMapping("/xtree")
	@ResponseBody
	public List<XTree> callBackTree(){
		List<SuccessProject> projects = spService.findAll();
		return  PreviewUserService.createTree(projects);
	}
	
	
	
	
	
	@RequestMapping("/updateXtree/{id}")
	@ResponseBody
	public List<XTree> updateXtree(@PathVariable("id")String id)
	{
		
		//初始化Xtree
		List<SuccessProject> projects = spService.findAll();
		List<XTree> xtrees = PreviewUserService.createTree(projects);
		//将Xtree集合转成Map
		Map<String,XTree> mapXtrees = new HashMap<String,XTree>();
		for (XTree xTree:xtrees) 
		{
			mapXtrees.put(xTree.getValue(), xTree);
		}
		//获取当前用户下的权限
		List<PreviewUserResources> PuSources=puService.getPuResourcesByPuID(id);

		//当前用户下的权限节点与全部节点进行对比
		for (PreviewUserResources previewUserResources : PuSources) 
		{
			String resourceStr = previewUserResources.getResourceStr();
			String[] resource = resourceStr.split("_");
			
			if(mapXtrees.containsKey(previewUserResources.getResourceID())){
				XTree resourceXtree = mapXtrees.get(previewUserResources.getResourceID());
				resourceXtree.setChecked(true);
				List<XTree> xTreeList = resourceXtree.getData();
				for(XTree temp:xTreeList)
				{
					Map<String,Boolean> map = PreviewUserService.getChecked(resource,previewUserResources.getResourceID());
					temp.setChecked(map.get(temp.getValue()));
				}
				resourceXtree.setData(xTreeList);
				mapXtrees.put(previewUserResources.getResourceID(),resourceXtree);
			}
		}
		List<XTree> result = new ArrayList<XTree>();
		result.addAll(mapXtrees.values());
		return result;
	}

	
	@RequestMapping("/qiyong/{id}")
	public void qiyong(@PathVariable("id")String id){
		puService.qiyong(id);
	}
	
	/*	public static void main(String[] args) {
			String[] resource = {"1","2","3"};
			Map<String,Boolean> map = getChecked(resource);
			System.out.println(map.get("1"));
			System.out.println(map.get("2"));
			System.out.println(map.get("3"));
			System.out.println(map.get("4"));
		}*/
	@Override
	@HasPermission(Permission.CREATE)
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PreviewUser> create(@Valid @RequestBody PreviewUser t, BindingResult result) {

	
		ResponseEntity<PreviewUser> create = super.create(t, result);
	
		
		
		return create;
	}



	@Override
	@HasPermission(Permission.UPDATE)
    @RequestMapping(value = "/{previewUserId}", method = RequestMethod.PUT)
	public ResponseEntity<PreviewUser> update(@PathVariable("previewUserId")String previewUserId,@Valid @RequestBody PreviewUser t, BindingResult result) {
		ResponseEntity<PreviewUser> responseEntity = super.update(previewUserId, t, result);
//		puService.deleteInTrue(previewUserId);
		List<String> resources = t.getResources();
		
		puService.updatePUResources(resources,previewUserId);
		/*for (String resource : resources) {
			PreviewUserResources previewUserResources = new PreviewUserResources();
			String[] split = resource.split("_");
			previewUserResources.setId(IDUtils.defaultUUID());
			previewUserResources.setPreviewUserID(previewUserId);
			previewUserResources.setResourceID(split[0]);
			previewUserResources.setResourceStr(resource);
			puService.saveResource(previewUserResources);
			
		}*/
		return responseEntity;
	}
	
	@RequestMapping(value="/checkPreviewUserName",method=RequestMethod.POST)
	@ResponseBody
	public PreviewUser checkPreviewUserName(String previewUserName){
		PreviewUser previewUser = puService.checkPreviewUserName(previewUserName);
		//previewUser不为null，说明该用户名已经存在
		if(previewUser == null){
			PreviewUser user = new PreviewUser();
			user.setIsExist(0);
			return user;
		}else{
			previewUser.setIsExist(1);
			return previewUser;
		}
		
	}
	
	
}

package com.zte.zudp.modules.sys.successProject.web;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.modules.sys.successProject.entity.GeneralUser;
import com.zte.zudp.modules.sys.successProject.entity.MiddleUser;
import com.zte.zudp.modules.sys.successProject.entity.RootUser;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;
import com.zte.zudp.modules.sys.successProject.service.SPService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 成功项目模块 ResetFul Controller
 * Created by DW on 2017/12/16 16:35
 */
@RestController
@RequestMapping("/front/sys/sp")
public class SPRestCotroller extends RESTfulController<SuccessProject> {

    @Value("${imageServer.enabled}")
    private Boolean isUseImageServer;

    private SPService getService() {
        return (SPService)service;
    }

    private ResourceLoader resourceLoader;

    @Autowired
    private SPRestCotroller(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected void init() {
        super.init();
        this.setResourceIdentity(Permissions.DICT);
        setDefaultViewPath("modules/sys/successProject");
        setListAlsoCommonData(true);
    }

    @Override
    protected void setCommonData(SuccessProject successProject) {
        super.setCommonData(successProject);
    }

    @PostMapping(value = "/imgupload")
    public Map<String, Object> uploadImg(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        List<String> imgSrcs = new ArrayList<>();

        Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
        String imagePath = "";
        upload:
        for(String key : fileMap.keySet()) {
            if(isUseImageServer) {
                imagePath = getService().upload2imgServer(fileMap.get(key));
            } else {
                imagePath = getService().uploadimgs(fileMap.get(key), "/successproject/imgs/","/sys/sp");
            }
            if(imagePath == null) {
                result.put("code","500");
                result.put("msg","upload error");
//                break upload;
                return result;
            }
            imgSrcs.add(imagePath);
        }
        result.put("code","200");
        result.put("msg","OK");
        result.put("imgSrcs", imgSrcs);
        return result;
    }

    
    
    
    
    @Override
    @HasPermission(Permission.UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SuccessProject> update(@PathVariable("id") String id,@Valid @RequestBody SuccessProject t, BindingResult result) {
    	
    	getService().checkRoles(t);
    	
		return super.update(id, t, result);
	}

	@HasPermission(Permission.CREATE)
    @RequestMapping(value="/addSuccessProject",method = RequestMethod.POST)
    @Override
	public ResponseEntity<SuccessProject> create(@Valid @RequestBody SuccessProject successProject, BindingResult result) {
    	
		getService().checkRoles(successProject);
    			
    	ResponseEntity<SuccessProject> create = super.create(successProject, result);
    	return create;
	}	
    


    @GetMapping( value = "/getimg/{fileName:.+}")
    public ResponseEntity<?> getFile(@PathVariable String fileName) {
        try {
//            String ctx = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
//            String resourcePath = String.valueOf(Paths.get(ctx+"../../../upload/successproject/imgs/",fileName));
//            Resource loaderResource = resourceLoader.getResource("file:"+resourcePath);
            return ResponseEntity.ok(getService().getResource("/successproject/imgs/",fileName, resourceLoader));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping( value = "/getapk/{fileName:.+}")
    public ResponseEntity<?> getapkFile(@PathVariable String fileName) {
        try {
//            String ctx = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
//            String resourcePath = String.valueOf(Paths.get(ctx+"../../../upload/successproject/imgs/",fileName));
//            Resource loaderResource = resourceLoader.getResource("file:"+resourcePath);
        	HttpHeaders header = new HttpHeaders();
        	header.setContentType(MediaType.MULTIPART_FORM_DATA);
        	header.add("Content-Disposition", "attachment;filename="+fileName);
            return new ResponseEntity(getService().getResource("/successproject/apk/",fileName, resourceLoader), header, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}

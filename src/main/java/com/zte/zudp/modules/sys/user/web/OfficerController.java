package com.zte.zudp.modules.sys.user.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.modules.sys.user.service.OfficerService;

@RestController
@MultipartConfig
@RequestMapping("${sys.url.admin}/sys/officer")
public class OfficerController extends RESTfulController<Officer>{
	
	 	@Override
	    protected void init() {
	        super.init();
	        this.setResourceIdentity(Permissions.USER);
	        setDefaultViewPath("/modules/sys/officer");
	    }
	 	
	 	@Value("${imageServer.enabled}")
	    private Boolean isUseImageServer;
	 	
	 	@Autowired
	 	private OfficerService officerService;
	 	
	 	
	 	private ResourceLoader resourceLoader;

	    @Autowired
	    private OfficerController(ResourceLoader resourceLoader) {
	        this.resourceLoader = resourceLoader;
	    }
	 
	 	
	 	 @PostMapping(value = "/upload")
	     public Map<String, Object> uploadImg(HttpServletRequest request, HttpServletResponse response) {
	         Map<String, Object> result = new HashMap<>();
	         List<String> imgSrcs = new ArrayList<>();

	         Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
	         String imagePath = "";
	         upload:
	         for(String key : fileMap.keySet()) {
	             if(isUseImageServer) {
	                 imagePath = officerService.upload2imgServer(fileMap.get(key));
	             } else {
	                 imagePath = officerService.uploadimgs(fileMap.get(key), "/officer/imgs/","/sys/officer");
	             }
	             if(imagePath == null) {
	                 result.put("code","500");
	                 result.put("msg","upload error");
//	                 break upload;
	                 return result;
	             }
	             imgSrcs.add(imagePath);
	         }
	         result.put("code","200");
	         result.put("msg","OK");
	         result.put("imgSrcs", imgSrcs);
	         return result;
	     }
	 	 
	 	 @GetMapping( value = "/getimg/{fileName:.+}")
	     public ResponseEntity<?> getFile(@PathVariable String fileName) {
	         try {
//	             String ctx = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
//	             String resourcePath = String.valueOf(Paths.get(ctx+"../../../upload/successproject/imgs/",fileName));
//	             Resource loaderResource = resourceLoader.getResource("file:"+resourcePath);
	             return ResponseEntity.ok(officerService.getResource("/officer/imgs/",fileName, resourceLoader));
	         } catch(Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	     }
	 	
	
	
}

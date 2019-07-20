package com.zte.zudp.modules.sys.article.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;
import com.zte.zudp.modules.sys.article.service.DailyArticleService;

@RestController
@RequestMapping("${sys.url.admin}/sys/dailyArticleChecked")
public class DailyArticleController extends RESTfulController<DailyArticle> {

	@Autowired
	private DailyArticleService service;
	
	@Value("${imageServer.enabled}")
	private boolean isUseImageServer;
	
	private ResourceLoader resourceLoader;
	
	@Autowired
	public DailyArticleController(ResourceLoader resourceLoader){
		
		this.resourceLoader=resourceLoader;
	}
	 @Override
	    protected void init() {
	        super.init();
	        this.setResourceIdentity(Permissions.DICT);
	        setDefaultViewPath("/modules/sys/dailyArticleChecked");
	        setListAlsoCommonData(true);
	    }

	    public DailyArticleService getService() {
	        return (DailyArticleService) service;
	    }
	    
	    @Override
	    protected void setCommonData(DailyArticle dArticle) {
	        super.setCommonData(dArticle);
	    }
	  /*  
	    @RequestMapping(value = "/select")
	    public void select(String id) {
	    	service().select(id);
	    }*/
	    
	    /**
	     * 待审核报告
	     * 列表数据
	     */
	    // @JsonView(View.Default.class)
	    @HasPermission(Permission.VIEW)
	    @RequestMapping(value = "/select", method = RequestMethod.GET)
	    public ResponseEntity<Page<DailyArticle>> index(DailyArticle dArticle) {
	        if (isListAlsoCommonData()) {
	            setCommonData(dArticle);
	        }
	        Page<DailyArticle> page = service.findCheckedPage(dArticle);
	        return new ResponseEntity<>(page, HttpStatus.OK);
	    }
	    
	    
	    
	    @RequestMapping("/upload")
	    public Map<String,Object> uploadImg(HttpServletRequest request,HttpServletResponse response){
	    		//图片列表，返回值
	    	 Map<String, Object> result = new HashMap<>();
	         List<String> imgSrcs = new ArrayList<String>();
	    	
	         Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
	         String imagePath = "";
	         upload:
	         for(String key : fileMap.keySet()) {
	             if(isUseImageServer) {
	                 imagePath = getService().upload2imgServer(fileMap.get(key));
	             } else {
	                 imagePath = getService().uploadimgs(fileMap.get(key), "/dailyArticleChecked/imgs/","/sys/dailyArticleChecked");
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
	             return ResponseEntity.ok(getService().getResource("/dailyArticleChecked/imgs/",fileName, resourceLoader));
	         } catch(Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	     }
 }

	    


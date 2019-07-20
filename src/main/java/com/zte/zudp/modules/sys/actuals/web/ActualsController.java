package com.zte.zudp.modules.sys.actuals.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.zte.zudp.modules.sys.actuals.entity.Actuals;
import com.zte.zudp.modules.sys.actuals.entity.ActualsOfficer;
import com.zte.zudp.modules.sys.actuals.service.ActualsOfficerService;
import com.zte.zudp.modules.sys.actuals.service.ActualsService;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;
import com.zte.zudp.modules.sys.article.service.DailyArticleService;
import com.zte.zudp.modules.sys.user.entity.Officer;

@RestController
@RequestMapping("${sys.url.admin}/sys/actuals")
public class ActualsController extends RESTfulController<Actuals> {
	
		@Autowired
		private DailyArticleService aService;
		
		@Value("${imageServer.enabled}")
		private boolean isUseImageServer;
		
		private ResourceLoader resourceLoader;
		
		private  ActualsController(ResourceLoader resourceLoader) {
			this.resourceLoader=resourceLoader;
		}
		
	 	@Override
	    protected void init() {
	        super.init();
	        this.setResourceIdentity(Permissions.DICT);
	        setDefaultViewPath("/modules/sys/actuals");

	        setListAlsoCommonData(true);
	    }
	 	public ActualsService service() {
	        return (ActualsService) service;
	    }
	 	
	 	
	 	@Autowired(required=true)
	 	private ActualsOfficerService actualsOfficerService;
	 
	 @RequestMapping(value = "/getOfficers", method = RequestMethod.GET)
	 public ResponseEntity<List<Officer>> getOfficers(){
		
		 
		 
		 List<Officer> officers = ((ActualsService) service).getOfficers();
		 
		 return new ResponseEntity<>(officers,HttpStatus.OK);
	 }
	 
	 	@HasPermission(Permission.CREATE)
	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Actuals> create(@Valid @RequestBody Actuals t, BindingResult result) {
	        if (hasError(t, result)) {
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
	        }

	        if (isListAlsoCommonData()) {
	            setCommonData(t);
	        }
	        
	        
	        
	        service.save(t);
	        
	        for (String developer : t.getDevelopers()) {
	        	ActualsOfficer  actualsOfficer = new ActualsOfficer();
	        	actualsOfficer.setOfficerId(developer);
	        	actualsOfficer.setActualsId(t.getId());
	        	actualsOfficerService.save(actualsOfficer);
			}
	       
	        
	        return new ResponseEntity<>(t, HttpStatus.CREATED);
	    }
		@Override
		public ResponseEntity<Page<Actuals>> index(Actuals t) {
			
			ResponseEntity<Page<Actuals>> index = super.index(t);
			
			List<Actuals> result = index.getBody().getResult();
			
			for (Actuals actuals : result) {
				
				long endTime = actuals.getEndTime().getTime();
				long currentTime = new Date().getTime();
				long workTime=endTime-currentTime>0?(endTime-currentTime):0;
				actuals.setWorkTimes(workTime);
				String id = actuals.getId();
				List<DailyArticle> dailyArticles = aService.getDailyArticles(id);
				int size = dailyArticles.size();
				actuals.setTrendsNum(size);
			}
			
			
		
			return index;
		}
		@HasPermission(Permission.UPDATE)
		@Override
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Actuals> update(@PathVariable("id")String id, @Valid @RequestBody Actuals t, BindingResult result) {
			
			actualsOfficerService.deleteInTure(id);
			List<String> developers = t.getDevelopers();
			for (String officer_id : developers) {
				ActualsOfficer  actualsOfficer = new ActualsOfficer();
	        	actualsOfficer.setOfficerId(officer_id);
	        	actualsOfficer.setActualsId(id);
	        	actualsOfficerService.save(actualsOfficer);
			}
			
			return super.update(id, t, result);
		}
		
	
	 	@RequestMapping("/uploadFiles")
	 	public Map<String, Object> uploadImg(HttpServletRequest request, HttpServletResponse response) {
	         Map<String, Object> result = new HashMap<>();
	         List<String> filesSrc = new ArrayList<>();

	         Map<String, MultipartFile> fileMap = ((MultipartRequest) request).getFileMap();
	         String filePath = "";
	         upload:
	         for(String key : fileMap.keySet()) {
	             if(isUseImageServer) {
	            	 filePath = service().upload2imgServer(fileMap.get(key));
	             } else {
	            	 filePath = service().uploadimgs(fileMap.get(key), "/actuals/files/","/sys/actuals");
	             }
	             if(filePath == null) {
	                 result.put("code","500");
	                 result.put("msg","upload error");
//	                 break upload;
	                 return result;
	             }
	             filesSrc.add(filePath);
	         }
	         result.put("code","200");
	         result.put("msg","OK");
	         result.put("filesSrc", filesSrc);
	         return result;
	     }
	 	
	 	
	 	@GetMapping( value = "/getimg/{fileName:.+}")
	     public ResponseEntity<?> getFile(@PathVariable String fileName) {
	         try {
//	             String ctx = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
//	             String resourcePath = String.valueOf(Paths.get(ctx+"../../../upload/successproject/imgs/",fileName));
//	             Resource loaderResource = resourceLoader.getResource("file:"+resourcePath);
	             return ResponseEntity.ok(service().getResource("/actuals/files/",fileName, resourceLoader));
	         } catch(Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	     }
}

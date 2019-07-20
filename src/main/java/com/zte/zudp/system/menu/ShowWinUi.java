package com.zte.zudp.system.menu;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zte.zudp.common.utils.HttpClient;
import com.zte.zudp.common.utils.IDUtils;
import com.zte.zudp.modules.sys.actuals.entity.Actuals;
import com.zte.zudp.modules.sys.actuals.entity.Comment;
import com.zte.zudp.modules.sys.actuals.service.ActualsService;
import com.zte.zudp.modules.sys.actuals.service.CommentService;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;
import com.zte.zudp.modules.sys.article.service.DailyArticleService;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUser;
import com.zte.zudp.modules.sys.previewUser.entity.PreviewUserResources;
import com.zte.zudp.modules.sys.previewUser.service.PreviewUserService;
import com.zte.zudp.modules.sys.successProject.entity.SuccessProject;
import com.zte.zudp.modules.sys.successProject.service.SPService;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.modules.sys.user.entity.User;
import com.zte.zudp.modules.sys.user.service.OfficerService;
import com.zte.zudp.modules.sys.user.utils.UserUtils;

import net.iharder.Base64;








/**
 * 展示win10前端页面
 * Created by DW on 2017/12/19 10:24
 */
@Controller
@RequestMapping(value = "${sys.url.index}")
public class ShowWinUi {

    @Autowired
    private SPService spService;
    
    @Autowired
    private DailyArticleService aService;
    

    @Autowired
    private OfficerService officerService;

    @Autowired
    private ActualsService actualsService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PreviewUserService puService;
    
    @RequestMapping(value = "/index")
    public String showWinUI() {
        return "/modules/index/winui/index";
    }

    @RequestMapping(value = "/successproject")
    public String showSuccess() {
        return "/modules/index/successproject/successProject";
    }

    @RequestMapping(value = "/successproject/{applicationField}")
    public String showDetailList(Model model, @PathVariable("applicationField") String appFidld) {
        model.addAttribute("appField",appFidld);
        model.addAttribute("recomendSPs",getRecomendSPs());
        return "/modules/index/successproject/successProjectList";
    }
    
    @RequestMapping(value="/successproject/selectProject")
    public String selectProject(String name,Model model){
    	model.addAttribute("name", name);
    	return "/modules/index/successproject/selectProjectList";
    }
    
    @RequestMapping(value = "/dailyarticle")
    public String articleList(Model model, DailyArticle dArticle) {
    	List<DailyArticle> dailyArticles = aService.getArticleList(dArticle);
    	model.addAttribute("dailyArticles", dailyArticles);
    	return "/modules/index/dailyArticle/frontArticle";
    }
    
    @RequestMapping(value="/officer")
    public String officerList(Model model){
    	List<Officer> officers=officerService.getOfficerList();
    	model.addAttribute("officers", officers);
    	return "modules/index/officer/frontOfficer";
    }
    
   
    
    @RequestMapping(value="/officer/officerDetail/{id}")
    public String getOfficer(Model model,@PathVariable("id") String id){
    	Officer officer=officerService.get(id);
    	model.addAttribute("officer", officer);
    	return "/modules/index/officer/frontOfficerDetail";
    }
    

    @RequestMapping(value = "/successproject/spdetail/{id}")
    public String showSPDetail(Model model, @PathVariable("id") String id) {
        SuccessProject successProject = spService.get(id);
        List<String> images = handleImages(successProject);
        model.addAttribute("recomendSPs",getRecomendSPs());
        model.addAttribute("successProject", successProject);
        model.addAttribute("images", images);
        return "/modules/index/successproject/projectdetail";
    }
    
   
    
    @RequestMapping(value = "/actuals")
    public String showActual(Model model, Actuals actual) {
//    	List<Actuals> actuals = actualsService.getActuals(actual);
//    	List<Actuals> actualsList=new ArrayList<Actuals>();
//    	for (Actuals actuals2 : actuals) {
//    		List<DailyArticle> dailyArticles = aService.getDailyArticles(actuals2.getId());
//    		int size = dailyArticles.size();
//    		Long workTimes=actuals2.getEndTime().getTime()-actuals2.getStartTime().getTime();
//    		int day= workTimes.intValue()/(1000*60*60*24);
//    		actuals2.setWorkTimes(day);
//    		actuals2.setTeamNum(size);
//    		actualsList.add(actuals2);
//		}
//    	
//    	model.addAttribute("actuals", actualsList);
    	return "/modules/index/actuals/actualsList";
    }
    
    @RequestMapping(value = "/searActuals")
    public String searActual(Model model,String name) {
    	List<Actuals> actuals = actualsService.findListByName(name);
    	List<Actuals> actualsList=new ArrayList<Actuals>();
    	for (Actuals actuals2 : actuals) {
    		Long workTimes=actuals2.getEndTime().getTime()-actuals2.getStartTime().getTime();
    		Long day= (long) (workTimes.intValue()/(1000*60*60*24));
    		actuals2.setWorkTimes(day);
    		actualsList.add(actuals2);
		}
    	model.addAttribute("actuals", actualsList);
    	return "/modules/index/actuals/actualsList";
    }
    
    @RequestMapping(value = "/actualsInfo/{id}")
    public String showActualInfo(Model model, @PathVariable("id") String id) {
    	List<Actuals> actualsInfo = actualsService.getActualsInfo(id);
    	List<DailyArticle> dailyArticles = aService.getDailyArticles(id);
    	int size = dailyArticles.size();
    	Actuals actuals = actualsInfo.get(0);
    	long workTimes=actuals.getEndTime().getTime()-new Date().getTime();
    	workTimes=workTimes>0?workTimes:0;
    	long day= workTimes/(1000*60*60*24);
    	actuals.setWorkTimes(day);
    	actuals.setTrendsNum(size);
    	Officer person=actualsService.getPerson(actuals.getPerson());
    	List<Officer> linkOfficers=actualsService.getLinkOfficers(id);
    	for (DailyArticle article : dailyArticles) {
    		long time = article.getPushDate().getTime();
			long time2 = new Date().getTime();
			time=time2-time;
			article.setBeforePushDate(time);
		}
    	/*List<Actuals> actualsList=new ArrayList<Actuals>();
    	for (Actuals actuals2 : actualsInfo) {
    		actualsList.add(actuals2);
		}*/
    	model.addAttribute("actuals", actuals);
    	model.addAttribute("linkOfficers", linkOfficers);
    	model.addAttribute("person", person);
    	model.addAttribute("dailyArticles", dailyArticles);
    	return "/modules/index/actuals/actualsInfo";
    }
    
    @RequestMapping("/actual/sumbitDA/{id}")
    @ResponseBody
    private DailyArticle submitDA(DailyArticle da,@PathVariable("id") String id){
    	List<Actuals> actualsInfo = actualsService.getActualsInfo(id);
    	User user = UserUtils.getCurrentUser();
    	String user_id = user.getId();
    	Officer officer = actualsService.getOfficer(user_id);
    	String officer_id = officer.getId();
    	Actuals actuals = actualsInfo.get(0);
    	String actuals_id = actuals.getId();
    	Officer person=actualsService.getPerson(actuals.getPerson());
    	List<Officer> linkOfficers=actualsService.getLinkOfficers(id);
    	
    	List<String> ids=new ArrayList<String>();
    	ids.add(person.getId());
    	
    	for (Officer o : linkOfficers) {
			ids.add(o.getId());
		}
    	if(ids.contains(officer_id)){
    		da.setOfficer(officer);
        	da.setId(IDUtils.defaultUUID());
        	da.setArticleRoot(0);
        	da.setPushPerson(officer.getName());
        	da.setOfficerId(officer.getId());
        	da.setImage(null);
        	da.setActualsId(actualsInfo.get(0).getId());
        	String title=actualsInfo.get(0).getName()+"日报";
        	Date pushDate = new Date();
        	da.setTitle(title);
        	da.setPushDate(pushDate);
        	long currentDate = new Date().getTime();
        	da.setBeforePushDate(currentDate-pushDate.getTime());
        	da.setCreateUser(user);
        	aService.insert(da);
        	da.setRootMan(true);
        	return da;
    	}else{
    		da.setRootMan(false);
    		return da;
    	}
    	
    	
    }
    
    @RequestMapping("/dailyarticle/publicArticle")
    @ResponseBody
    private DailyArticle publicArticle(DailyArticle dailyArticle){
    	Date currentTime = new Date();
    	dailyArticle.setPushDate(currentTime);
    	User currentUser = UserUtils.getCurrentUser();
    	String user_id = currentUser.getId();
    	Officer officer = actualsService.getOfficer(user_id);
    	dailyArticle.setOfficerId(officer.getId());
    	Date pushDate = dailyArticle.getPushDate();
    	Calendar cal = Calendar.getInstance();  
        cal.setTime(pushDate);
        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
    	dailyArticle.setIsAudit(0);
    	aService.save(dailyArticle);
    	dailyArticle.setOfficer(officer);
    	dailyArticle.setWeek(week);
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(pushDate);
		dailyArticle.setStringPushDate(format);
    	return dailyArticle;
    }
    
    
    @RequestMapping("/actual/repeat")
    @ResponseBody
    private Comment repeat(Comment comment){
    	User user = UserUtils.getCurrentUser();
    	String user_id = user.getId();
    	String commentId=comment.getCommentId();
    	comment.setDelfalg(1);
    	comment.setCommentDate(new Date());
    	Officer repeatUser = commentService.getOfficerByUserId(user_id);
    	Officer commentUser = officerService.get(commentId);
    	String repeatId=repeatUser.getId();
    	comment.setRepeatId(repeatId);
    	commentService.save(comment);
    	long currentTime = new Date().getTime();
    	long time = comment.getCommentDate().getTime();
    	long beforeCommentTime=currentTime-time;
    	comment.setBeforeCommentTime(beforeCommentTime);
    	comment.setRepeatUser(repeatUser);
    	comment.setCommentUser(commentUser);
    	return comment;
    }
    
	@RequestMapping("/previewUserLogin/{projectId}")
    @ResponseBody
    public PreviewUser previewProject(HttpServletRequest request,PreviewUser previewUser,@PathVariable("projectId") String projectId){
    	PreviewUser p =new PreviewUser();
    	PreviewUser user = puService.getBypreviewUserNameAndPwd(previewUser);
    	if(user==null){
    		p.setIsValid(4);
    		return p;
    	}else{
    		HttpSession session = request.getSession(false);
    		session.setAttribute("previewUser", user);
    		String userId = user.getId();
    		PreviewUserResources newPreviewUserResources = new PreviewUserResources();
    		newPreviewUserResources.setPreviewUserID(userId);
    		newPreviewUserResources.setResourceID(projectId);
    		PreviewUserResources previewUserResourcesByIds = puService.getPreviewUserResourcesByIds(newPreviewUserResources);
    		if(previewUserResourcesByIds==null){
    			p.setIsValid(3);
    			return p;
    		}else{
    			long startTime = user.getStartDate().getTime();
    			long endTime = user.getEndDate().getTime();
    			long currentTimeMillis = System.currentTimeMillis();
    			if(currentTimeMillis>startTime&&currentTimeMillis<endTime){
    				p.setIsValid(1);
    				return p;
    			}else{
    				p.setIsValid(2);
        			return p;
    			}
    		}
    	}
    }
    
    
    @RequestMapping("/previewUserIsInSession/{projectId}")
    @ResponseBody
    public PreviewUser checkPreviewUser(HttpServletRequest request,@PathVariable("projectId") String projectId){
    	
    	PreviewUser previewUser = new PreviewUser();
    	HttpSession session = request.getSession(true);
    	//session.invalidate();
    	Object obj = session.getAttribute("previewUser");
    	if(obj == null)
    	{
    		previewUser.setIsValid(0);
    		return previewUser;
    	}	
    	PreviewUser user = (PreviewUser)obj;
    	long startTime = user.getStartDate().getTime();
    	////1516292339000
    	long endTime = user.getEndDate().getTime();
    	////1516317921000
    	long currentTimeMillis = System.currentTimeMillis();
    	////1516267023915
    	if(currentTimeMillis < startTime || currentTimeMillis > endTime){
    		previewUser.setIsValid(2);
    		return previewUser;
    	}
    	PreviewUserResources newPreviewUserResources = new PreviewUserResources();
    	String previewUserId = user.getId();
    	newPreviewUserResources.setPreviewUserID(previewUserId);
    	newPreviewUserResources.setResourceID(projectId);
    	PreviewUserResources previewUserResources = puService.getPreviewUserResourcesByIds(newPreviewUserResources);
    	if(previewUserResources==null){
    		previewUser.setIsValid(3);
    		return previewUser;
    	}
    	previewUser.setIsValid(1);
		return previewUser;
    }
    
    
    @RequestMapping("/successPreview/{projectID}")
    public String successPreview(Model model,HttpServletRequest request,@PathVariable("projectID") String projectId){
    	HttpSession session = request.getSession(false);
    	
    	if(session == null){
    		return "redirect:/winui/successproject/spdetail/"+projectId;
    	}
    	
    	
    	
    	PreviewUser previewUser = (PreviewUser) session.getAttribute("previewUser");
    	PreviewUser previewUserInSession = puService.getBypreviewUserNameAndPwd(previewUser);	
    	if(previewUserInSession==null){
    		return "redirect:/winui/successproject/spdetail/"+projectId;
    	}
    	
    	
    	PreviewUserResources newPreviewUserResources = new PreviewUserResources();
    	String previewUserId = previewUser.getId();
    	newPreviewUserResources.setPreviewUserID(previewUserId);
    	newPreviewUserResources.setResourceID(projectId);
    	SuccessProject successProject = spService.get(projectId);
    	PreviewUserResources previewUserResources = puService.getPreviewUserResourcesByIds(newPreviewUserResources);
    	if(previewUserResources==null){
    		return "redirect:/winui/successproject/spdetail/"+projectId;
    	}
    	
    	long startTime = previewUserInSession.getStartDate().getTime();
    	////1516292339000
    	long endTime = previewUserInSession.getEndDate().getTime();
    	////1516317921000
    	long currentTimeMillis = System.currentTimeMillis();
    	////1516267023915
    	if(currentTimeMillis<startTime||currentTimeMillis>endTime){
    		return "redirect:/winui/successproject/spdetail/"+projectId;
    	}
    	String resourceStr = previewUserResources.getResourceStr();
    	SuccessProject p = new SuccessProject();
    	String[] splits = resourceStr.split("_");
    	for (String split : splits) {
			if("1".equals(split)){
				p.setIsPCReception(1);
			}else if("2".equals(split)){
				p.setIsPCBackstage(1);
			}else if("3".equals(split)){
				p.setIsPhone(1);
			}else if("4".equals(split)){
				p.setIsWeixin(1);
			}
		}
    	
    	String appCodesViewPath = successProject.getAppCodesViewPath();
    	String wxCodesViewPath = successProject.getWXCodesViewPath();
    	if(StringUtils.isNotBlank(appCodesViewPath)){
    		successProject.setAppCodesViewPathList(Arrays.asList(appCodesViewPath.split(",")));
    	}else{
    		successProject.setAppCodesViewPathList(null);
    	}
    	if(StringUtils.isNotBlank(wxCodesViewPath)){
    		successProject.setWXCodesViewPathList(Arrays.asList(wxCodesViewPath.split(",")));
    	}else{
    		successProject.setWXCodesViewPathList(null);
    	}
    	
    	model.addAttribute("project", p);
    	model.addAttribute("successProject", successProject);
    	return "/modules/index/successproject/preview";
    }
    
    
	@RequestMapping("/previewUserLogin")
	@ResponseBody
    public PreviewUser previewUserLogin(PreviewUser pvUser,HttpServletRequest request){
    	PreviewUser previewUser = puService.getBypreviewUserNameAndPwd(pvUser);
    	PreviewUser user = new PreviewUser();
    	if(previewUser == null){
    		user.setIsValid(0);
    		return user;
    	}
    	
    	if(previewUser.getDelState() == 0){
    		user.setIsValid(2);
    		return user;
    	}
    	
    	HttpSession session = request.getSession(true);
    	session.setAttribute("previewUser", previewUser);
    	user.setIsValid(1);
		return user;
    }
    
	@RequestMapping("/previewUserLogout")
	@ResponseBody
	public String previewUserLogout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		session.setAttribute("previewUser", null);
		return "true";
	}
	
	@RequestMapping("/rootUser/{roleID}")
	public String previewsRootUser(@PathVariable("roleID")String roleID,HttpServletRequest request,HttpServletResponse response){
		SuccessProject successProjectRootUser = spService.getProjectRootUserById(roleID);
		HttpSession session = request.getSession(false);
		PreviewUser previewUser = (PreviewUser) session.getAttribute("previewUser");
		PreviewUser user = puService.getBypreviewUserNameAndPwd(previewUser);
		String rootUserName = successProjectRootUser.getRootUserName();
		String rootUserPwd = successProjectRootUser.getRootUserPwd();
		
		/*Map<String,String> map= new HashMap<String,String>();
		map.put("loginName", rootUserName);
		map.put("password", rootUserPwd);
		HttpClient http=new HttpClient (response);
		http.setParameter(map);
		try {
			http.sendByPost(successProjectRootUser.getAddr());
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		
		byte[] rootUserNameBytes = null; 
		byte[] rootUserPwdBytes = null;
		try {
			rootUserNameBytes= rootUserName.getBytes("UTF-8");
			rootUserPwdBytes= rootUserPwd.getBytes("UTF-8");
			rootUserName = Base64.encodeBytes(rootUserNameBytes);
			rootUserPwd = Base64.encodeBytes(rootUserPwdBytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		long time = user.getEndDate().getTime();
		
		if(successProjectRootUser.getAddr().lastIndexOf("?")<0){
			return "redirect:"+ successProjectRootUser.getAddr() +"?username="+rootUserName+"&password="+rootUserPwd+"&endTime=" + time ;
		}else{
			return "redirect:"+ successProjectRootUser.getAddr() +"&username="+rootUserName+"&password="+rootUserPwd+"&endTime=" + time ;
		}
	}
	@RequestMapping("/middleUser/{roleID}")
	public String previewsMiddleUser(@PathVariable("roleID")String roleID,HttpServletRequest request){
		SuccessProject successProjectMiddleUser = spService.getProjectMiddleUserById(roleID);
		HttpSession session = request.getSession(false);
		PreviewUser previewUser = (PreviewUser) session.getAttribute("previewUser");
		PreviewUser user = puService.getBypreviewUserNameAndPwd(previewUser);
		String middleUserName = successProjectMiddleUser.getMiddleUserName();
		String middleUserPwd = successProjectMiddleUser.getMiddleUserPwd();
		byte[] middleUserNameBytes = null; 
		byte[] middleUserPwdBytes = null;
		try {
			middleUserNameBytes= middleUserName.getBytes("UTF-8");
			middleUserPwdBytes= middleUserPwd.getBytes("UTF-8");
			middleUserName = Base64.encodeBytes(middleUserNameBytes);
			middleUserPwd = Base64.encodeBytes(middleUserPwdBytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		long time = user.getEndDate().getTime();
		if(successProjectMiddleUser.getAddr().lastIndexOf("?")<0){
			return "redirect:"+ successProjectMiddleUser.getAddr() +"?username="+middleUserName+"&password="+middleUserPwd+"&endTime=" + time ;
		}else{
			return "redirect:"+ successProjectMiddleUser.getAddr() +"&username="+middleUserName+"&password="+middleUserPwd+"&endTime=" + time ;
		}
		
	}
	@RequestMapping("/generalUser/{roleID}")
	public String previewsGeneralUser(@PathVariable("roleID")String roleID,HttpServletRequest request,HttpServletResponse response){
		SuccessProject successProjectGeneralUser = spService.getProjectGeneralUserById(roleID);
		HttpSession session = request.getSession(false);
		PreviewUser previewUser = (PreviewUser) session.getAttribute("previewUser");
		PreviewUser user = puService.getBypreviewUserNameAndPwd(previewUser);
		String generalUserName = successProjectGeneralUser.getGeneralUserName();
		String generalUserPwd = successProjectGeneralUser.getGeneralUserPwd();
		
		
		/*Map<String,String> map= new HashMap<String,String>();
		map.put("loginName", generalUserName);
		map.put("password", generalUserPwd);
		HttpClient http=new HttpClient (response);
		http.setParameter(map);
		try {
			http.sendByPost(successProjectGeneralUser.getAddr());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		byte[] generalUserNameBytes = null; 
		byte[] generalUserPwdBytes = null;
		try {
			generalUserNameBytes= generalUserName.getBytes("UTF-8");
			generalUserPwdBytes= generalUserPwd.getBytes("UTF-8");
			generalUserName = Base64.encodeBytes(generalUserNameBytes);
			generalUserPwd = Base64.encodeBytes(generalUserPwdBytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		long time = user.getEndDate().getTime();
		if(successProjectGeneralUser.getAddr().lastIndexOf("?")<0){
			return "redirect:"+ successProjectGeneralUser.getAddr() +"?username="+generalUserName+"&password="+generalUserPwd+"&endTime=" + time ;
		}else{
			return "redirect:"+ successProjectGeneralUser.getAddr() +"&username="+generalUserName+"&password="+generalUserPwd+"&endTime=" + time ;
		}
	}
	
    private List<String> handleImages(SuccessProject successProjects) {
        List<String> images = new ArrayList<>();
        String imagesPaths = successProjects.getImagesPath();
        if(imagesPaths != null) {
            String[] imgs = successProjects.getImagesPath().split(",");
            for(int i = 0; i<imgs.length; i++) {
                images.add(imgs[i]);
            }
        }
        return images;
    }

    private List<SuccessProject> getRecomendSPs() {
        return spService.getRecommendSP();
    }
    
    
    
    
}

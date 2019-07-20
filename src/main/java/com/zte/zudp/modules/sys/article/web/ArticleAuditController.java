package com.zte.zudp.modules.sys.article.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zte.zudp.common.annotation.HasPermission;
import com.zte.zudp.common.enums.Permission;
import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.persistence.web.RESTfulController;
import com.zte.zudp.common.persistence.web.permission.Permissions;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;
import com.zte.zudp.modules.sys.article.service.DailyArticleService;

@RestController
@RequestMapping("${sys.url.admin}/sys/dailyArticle")
public class ArticleAuditController extends RESTfulController<DailyArticle> {

	
	/**
	 * 
	 * 全部报告
	 */
	 @Override
	    protected void init() {
	        super.init();
	        this.setResourceIdentity(Permissions.DICT);
	        setDefaultViewPath("/modules/sys/dailyArticle");

	        setListAlsoCommonData(true);
	    }

	    public DailyArticleService service() {
	        return (DailyArticleService) service;
	    }
	    
	    @Override
	    protected void setCommonData(DailyArticle dArticle) {
	        super.setCommonData(dArticle);
	    }
	    
	    @RequestMapping(value = "/checkart/{id}")
	    public void checkart(@PathVariable("id")  String id) {
	    	service().updateAudit(id);
	    }

		@Override
		@HasPermission(Permission.VIEW)
	    @RequestMapping(value = "/list", method = RequestMethod.GET)
		public ResponseEntity<Page<DailyArticle>> index(DailyArticle t) {
			
			ResponseEntity<Page<DailyArticle>> index = super.index(t);
			
			List<DailyArticle> result = index.getBody().getResult();
			
			for (DailyArticle dailyArticle : result) {
				Date pushTime = dailyArticle.getPushDate();
				long pushDate = pushTime.getTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String format = simpleDateFormat.format(dailyArticle.getPushDate());
				dailyArticle.setStringPushDate(format);
				long currentTime = new Date().getTime();
				Calendar cal = Calendar.getInstance();  
		        cal.setTime(pushTime);
		        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
				dailyArticle.setBeforePushDate(currentTime-pushDate);
				dailyArticle.setWeek(week);
			}
			
			
			return index;
		}

}

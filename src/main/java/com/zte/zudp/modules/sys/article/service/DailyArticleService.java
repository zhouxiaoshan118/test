package com.zte.zudp.modules.sys.article.service;

import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.modules.sys.article.dao.DailyArticleDao;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DailyArticleService extends BusinessService<DailyArticle> {

	@Autowired
	private DailyArticleDao dao;
	 @Override
	    @Transactional
	    public void save(DailyArticle dArticle) {
	        super.save(dArticle);

	    }

	    @Override
	    @Transactional
	    public void delete(String id) {
	        super.delete(id);
	    }

	    @Override
	    @Transactional
	    public void delete(String[] ids) {
	        super.delete(ids);
	    }
	    
	    @Transactional(readOnly = false )
	    public void updateAudit(String id) {
	    	dao.updateAudit(id);
	    }
	    
	    @Transactional(readOnly = false )
	    public List<DailyArticle> selectList(DailyArticle dArticle){
	    	return dao.selectList(dArticle);
	    }
	    
	    public Page<DailyArticle> findCheckedPage(DailyArticle dArticle) {
	    	Page.start();
	    	return Page.end(selectList(dArticle));
	    }
	    
	    @Transactional(readOnly = false)
		public List<DailyArticle> getArticleList(DailyArticle dArticle) {
			return dao.getArticleList(dArticle);
		}

		public List<DailyArticle> getDailyArticles(String id) {
			return dao.getDailyArticles(id);
		}
	    
}

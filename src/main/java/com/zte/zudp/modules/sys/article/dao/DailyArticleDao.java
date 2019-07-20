package com.zte.zudp.modules.sys.article.dao;

import com.zte.zudp.common.persistence.dao.AbstractDao;
import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.modules.sys.article.entity.DailyArticle;

import java.util.List;

/**
 * 日常报告
 * @author Ying
 *
 */
public interface DailyArticleDao extends AbstractDao<DailyArticle> {
	
	public void updateAudit(String id);
	
    List<DailyArticle> selectList(DailyArticle dArticle);
    
    List<DailyArticle> getArticleList(DailyArticle dArticle);
    
    public Page<DailyArticle> findByPage(int current);

	public List<DailyArticle> getDailyArticles(String id);
}

package com.zte.zudp.modules.sys.actuals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zte.zudp.common.persistence.entity.page.Page;
import com.zte.zudp.common.persistence.service.BusinessService;
import com.zte.zudp.modules.sys.actuals.dao.ActualsDao;
import com.zte.zudp.modules.sys.actuals.entity.Actuals;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.system.cache.CacheUtils;

@Service
@Transactional(readOnly = true)
public class ActualsService extends BusinessService<Actuals>{
   
	@Autowired
	private ActualsDao actualsDao;
	
	@SuppressWarnings("unused")
	private ActualsDao dao() {
        return (ActualsDao) dao;
    }

    @Override
    @Transactional
    public void save(Actuals Actuals) {
        super.save(Actuals);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }

    
    
    
    @Override
    @Transactional
    public void delete(String id) {
        super.delete(id);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        super.delete(ids);
        CacheUtils.remove(CacheUtils.DICT_MAP);
    }
    
    @Transactional(readOnly = false)
    public List<Actuals> getActuals(Actuals actuals){
    	return actualsDao.getActuals(actuals);
    }
    
    @Transactional(readOnly = false)
    public List<Actuals> getActualsInfo(String id){
    	return actualsDao.getActualsInfo(id);
    }
    
    
    @Transactional(readOnly = false)
	public List<Actuals> findListByName(String name) {
		return actualsDao.findListByName(name);
	}
    @Transactional(readOnly = false)
    public List<Officer> getOfficers() {

    	List<Officer> officers = actualsDao.getOfficers();
    	return officers;
	}

	public List<Officer> getLinkOfficers(String id) {

		List<Officer> linkOfficers = actualsDao.getLinkOfficers(id);
		
		return linkOfficers;
	}

	public Officer getPerson(String name) {
		Officer person= actualsDao.getPerson(name);
		return person;
	}

	public Officer getOfficer(String id) {
		
		Officer officer = actualsDao.getOfficer(id);
		
		return officer;
		
	}

	@Override
	public Page<Actuals> findPage(Actuals t) {
		// TODO Auto-generated method stub
		Page<Actuals> findPage = super.findPage(t);
		return findPage;
	}



	
	
	
    
}

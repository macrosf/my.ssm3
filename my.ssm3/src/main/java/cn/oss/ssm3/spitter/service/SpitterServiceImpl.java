package cn.oss.ssm3.spitter.service;

import static java.lang.Math.*;
import static java.util.Collections.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.oss.ssm3.spitter.domain.Spitter;
import cn.oss.ssm3.spitter.domain.Spittle;
import cn.oss.ssm3.spitter.persistence.SpitterDao;

/**
 * copy from project [spitter-service-annotation-tx]
 * @author Administrator
 *
 */
@Service("spitterService")
@Transactional(propagation=Propagation.REQUIRED)
public class SpitterServiceImpl implements SpitterService {

  public void saveSpittle(Spittle spittle) {
    spittle.setWhen(new Date());
    getSpitterDao().saveSpittle(spittle);
  }

  @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
  public List<Spittle> getRecentSpittles(int count) {
    List<Spittle> recentSpittles = 
        getSpitterDao().getRecentSpittle();
    
    reverse(recentSpittles);
    
    return recentSpittles.subList(0, 
            min(49, recentSpittles.size()));
  }
  
  public void saveSpitter(Spitter spitter) {
    if(spitter.getId() == null) {
      getSpitterDao().addSpitter(spitter);
    } else {
      getSpitterDao().saveSpitter(spitter);
    }
  }
  
  @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
  public Spitter getSpitter(long id) {
    return getSpitterDao().getSpitterById(id);
  }

  public void startFollowing(Spitter follower, Spitter followee) {
    // TODO Auto-generated method stub  
  }
  
  public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
    return getSpitterDao().getSpittlesForSpitter(spitter);
  }

  public List<Spittle> getSpittlesForSpitter(String username) {
    Spitter spitter = getSpitterDao().getSpitterByUsername(username);
    return getSpittlesForSpitter(spitter);
  }

  public Spitter getSpitter(String username) {
    return getSpitterDao().getSpitterByUsername(username);
  }

  public void deleteSpittle(long id) {
    getSpitterDao().deleteSpittle(id);
  }
  
  public List<Spitter> getAllSpitters() {
    return getSpitterDao().findAllSpitters();
  }

  @Autowired
private
  SpitterDao spitterDao;

  public Spittle getSpittleById(long id) {
    return getSpitterDao().getSpittleById(id);
  }

public SpitterDao getSpitterDao() {
	return spitterDao;
}

public void setSpitterDao(SpitterDao spitterDao) {
	this.spitterDao = spitterDao;
}
}

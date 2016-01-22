package my.ssm3.spitter.service;

import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

import cn.oss.ssm3.spitter.domain.Spitter;
import cn.oss.ssm3.spitter.persistence.SpitterDao;
import cn.oss.ssm3.spitter.service.SpitterServiceImpl;

public class SpitterServiceImplTest {
  private SpitterServiceImpl spitterService;
  private SpitterDao spitterDao;
  private Spitter newSpitter;
  private Spitter oldSpitter;
  
  @Before
  public void setup() {
    newSpitter = new Spitter();
    newSpitter.setUsername("testuser");
    newSpitter.setPassword("password");
    newSpitter.setFullName("Michael McTest");

    oldSpitter = new Spitter();
    oldSpitter.setId(12345L);
    oldSpitter.setUsername("olduser");
    oldSpitter.setPassword("letmein");
    oldSpitter.setFullName("Bob O'Test");
    
    spitterDao = createNiceMock(SpitterDao.class);  
    spitterDao.saveSpitter(oldSpitter);
    spitterDao.addSpitter(newSpitter);
    replay(spitterDao);
    
    spitterService = new SpitterServiceImpl();
    spitterService.setSpitterDao(spitterDao);
  }
  
  @Test
  public void shouldAddSpitter() {
    spitterService.saveSpitter(newSpitter);
    spitterService.saveSpitter(oldSpitter);
    verify(spitterDao);
  }
  
  @Test
  public void deleteMe() {
    System.out.println("Albus Percival Wulfric Brian Dumbledore".getBytes());
  }
}

package my.ssm3.spitter.mvc;


import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static cn.oss.ssm3.spitter.mvc.HomeController.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import cn.oss.ssm3.spitter.service.SpitterService;
import cn.oss.ssm3.spitter.domain.Spittle;
import cn.oss.ssm3.spitter.mvc.HomeController;

public class HomeControllerTest {  

@Test
  public void shouldDisplayRecentSpittles() {
    List<Spittle> expectedSpittles = 
      asList(new Spittle(), new Spittle(), new Spittle());
    
    SpitterService spitterService = mock(SpitterService.class);//<co id="co_mockSpitterService"/>

    when(spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE)).
        thenReturn(expectedSpittles);
    
    HomeController controller = 
                   new HomeController(spitterService); //<co id="co_createController"/>
    
    HashMap<String, Object> model = new HashMap<String, Object>();
    String viewName = controller.showHomePage(model); //<co id="co_callShowHomePage"/>
    
    assertEquals("home", viewName);

    assertSame(expectedSpittles, model.get("spittles")); //<co id="co_assertResults"/>
    verify(spitterService).getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE);
  }
}

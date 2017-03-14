package lee.Spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {

	@Autowired
    private TestController controller;
	
    @Test
    public void testReader() {
    	assertEquals("123", controller.queryAll());
    }
    
    @Test
    public void testReader2() {
    	assertEquals("123", controller.queryAll());
    }

}
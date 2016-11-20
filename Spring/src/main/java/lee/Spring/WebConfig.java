package lee.Spring;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lee.HttpClient.TestFilter;
import lee.HttpClient.TestListener;
import lee.HttpClient.TestServlet;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Value("${mysetting.servlet.url}")
	private String servletUrl;
	
	@Value("${mysetting.filter.url}")
	private String filterUrl;
	
	@Autowired
	private TestFilter testFilter;
	
	@Autowired
	private TestServlet testServlet;
	
	@Autowired
	private TestListener testListener;
	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(this.testFilter);
	    registration.addUrlPatterns(this.filterUrl);
	    registration.addInitParameter("paramName", "paramValue");
	    registration.setName("testFilter");
	    registration.setOrder(1);
	    return registration;
	}
	
	@Bean
	public ServletRegistrationBean getDemoServlet(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(this.testServlet);
		List<String> urlMappings=new ArrayList<String>();
		urlMappings.add(this.servletUrl);
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener(){
		ServletListenerRegistrationBean<EventListener> registrationBean
		                           =new ServletListenerRegistrationBean<EventListener>();
		registrationBean.setListener(this.testListener);
//		registrationBean.setOrder(1);
		return registrationBean;
	}
}

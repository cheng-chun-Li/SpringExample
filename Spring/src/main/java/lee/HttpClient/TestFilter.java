package lee.HttpClient;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lee.Database.CustomerRepositoryImp;

@Repository
public class TestFilter implements Filter {
	
	@Autowired
	@Qualifier("customerRepositoryImp")
	CustomerRepositoryImp userRepository;
	
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("TestFilter start");
	}
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, res);
		
		System.out.println("Sql :" + userRepository.getAll().get(0).toString());
	}
}

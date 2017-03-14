package lee2.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .anyRequest().hasRole("USER")
            .and()
        .formLogin()
            .permitAll()
            .and()
        .logout()
            .permitAll()
            .and()
        .anonymous()
            .disable();        
		System.out.println("Configure basic Authurization");
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
        System.out.println("Set user name and password");
    }
}

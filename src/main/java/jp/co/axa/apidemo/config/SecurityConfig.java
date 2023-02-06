package jp.co.axa.apidemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security Configuration Class
 * To Secure end point URL's
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().
		withUser("test").password(passwordEncoder.encode("P@ssw0rd")).roles("admin");
		
	}
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
                .antMatchers("/api/**").authenticated()
				.and()
				.formLogin().and()
				.httpBasic();
			
			http.csrf().disable();
	        http.headers().frameOptions().disable();
	    }
	  
	  // H2 has it's own authentication provider
	  //Ignoring Spring Security for h2 console path
	  
	  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	            .ignoring()
	            .antMatchers("/h2-console/**");
	    }
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

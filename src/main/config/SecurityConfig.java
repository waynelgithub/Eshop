package main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("John").password(passwordEncoder().encode("admin")).roles("ADMIN")
			.and()
			.withUser("Eric").password(passwordEncoder().encode("employee")).roles("EMPLOYEE")
			.and()
			.withUser("Micheael").password(passwordEncoder().encode("client")).roles("CLIENT");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/login")
				.permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/checkUserAccount")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/login"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.permitAll();
	}
	
	
	
}

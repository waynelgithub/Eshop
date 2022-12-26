package main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new TourAgencyAccessDeniedHandler();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select login, password, enabled from user where login=?")
			.authoritiesByUsernameQuery("select login, role from role where login=?");
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		http.addFilterBefore(encodingFilter, CsrfFilter.class);
		http.authorizeRequests()
			.antMatchers("/", "/login")
				.permitAll()
			.antMatchers("/addTour")
				.hasAnyRole("ADMIN", "EMPLOYEE")
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/checkUserAccount")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}
	
}
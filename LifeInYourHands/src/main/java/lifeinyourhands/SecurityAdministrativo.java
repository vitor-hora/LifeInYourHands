package lifeinyourhands;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@Order(1)
public class SecurityAdministrativo extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// neste método que vamos tratar os usuários
		
		auth.inMemoryAuthentication()
        .withUser("admin").roles("ADMIN").password("{noop}password");
		/*	
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from medico where email=?")
				.authoritiesByUsernameQuery(
						"select medico.email as username, papel.nome as authority from permissoes inner join medico on medico.id=permissoes.medico_id inner join papel on permissoes.papel_id=papel.id where medico.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());
				;
	*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2Login()
		.defaultSuccessUrl("/administrativo");
		*/

		
		
		
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/administrativo/cadastrar/**")
				.hasAnyAuthority("USER").antMatchers("/administrativo/**").authenticated().and().formLogin()
				.loginPage("/login").failureUrl("/login").loginProcessingUrl("/admin")
				.defaultSuccessUrl("/administrativo").usernameParameter("username").passwordParameter("password").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/administrativo/logout"))
				.logoutSuccessUrl("/login").deleteCookies("JSESSIONID").and().exceptionHandling()
				.accessDeniedPage("/negado").and().csrf().disable();
		 	
	}

}
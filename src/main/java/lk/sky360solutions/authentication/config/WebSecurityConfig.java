package lk.sky360solutions.authentication.config;

import lk.sky360solutions.authentication.repository.UserRepository;
import lk.sky360solutions.authentication.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserRepository userRepository;

  public WebSecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailServiceImpl(userRepository);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .httpBasic().and()
      .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
      /*
      * when we use this API request not working with cookies
      * */
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs",
      "/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**");
  }
}

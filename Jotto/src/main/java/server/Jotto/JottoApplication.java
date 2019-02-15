package server.Jotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import server.Jotto.Models.UserRepository;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class JottoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JottoApplication.class, args);
	}

	//Probably not needed
    @Configuration
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CorsSupport(), ChannelProcessingFilter.class);
        http
        .authorizeRequests()
        .antMatchers("/","/register")
        .permitAll()
        .and()
.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and().csrf().disable();
    }
		}
    }





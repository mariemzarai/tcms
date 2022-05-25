package com.restapi.tcms;

import com.restapi.tcms.dao.AdminDao;
import com.restapi.tcms.model.Admin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class TcmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcmsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(AdminDao adminDao){
//		return args -> {
//			Admin admin = new Admin("mejri", "yassine", "admin@admin.com", "99999999");
//			adminDao.create(admin);
//		};
//	}
}

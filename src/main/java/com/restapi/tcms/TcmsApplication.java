package com.restapi.tcms;

import com.restapi.tcms.dao.AdminDao;
import com.restapi.tcms.dao.ServiceScolariteDao;
import com.restapi.tcms.dao.ServiceStagiaireDao;
import com.restapi.tcms.model.Admin;
import com.restapi.tcms.model.ServiceScolarite;
import com.restapi.tcms.model.ServiceStagiaire;
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
//	CommandLineRunner run(ServiceStagiaireDao serviceStagiaireDao, ServiceScolariteDao serviceScolariteDao){
//		return args -> {
//			ServiceStagiaire sstag = new ServiceStagiaire("zarai", "mariem", "mariem@stagiaire.com", "12345678");
//			serviceStagiaireDao.create(sstag);
//			ServiceScolarite sscol = new ServiceScolarite("bouraoui", "hbiba", "hbiba@scolarite.com", "12345678");
//			serviceScolariteDao.create(sscol);
//		};
//	}
}

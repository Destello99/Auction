package com.project.customer;

import com.project.customer.config.AppConstants;
import com.project.customer.entity.Roles;
import com.project.customer.repositories.RoleRepository;
//import com.project.customer.service.AuctionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;


@SpringBootApplication
public class CustomerApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuctionService auctionService;

	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	@Bean
	public ModelMapper mapper(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return  mapper;
	}

	@Override
	public void run(String... args){
		try
		{
			Roles admin = new Roles();
			admin.setId(AppConstants.ROLE_ADMIN);
			admin.setName("ROLE_ADMIN");

			Roles normal_user = new Roles();
			normal_user.setId(AppConstants.NORMAL_USER);
			normal_user.setName("ROLE_NORMAL");

			List<Roles> roleList = List.of(admin, normal_user);
			List<Roles> result = this.roleRepository.saveAll(roleList);
			result.forEach(r-> System.out.println(r.getName()));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

//	@Scheduled(cron = "${auction.schedule}")
//	public void executeAuction() {
//		auctionService.startAuction();
//	}

}

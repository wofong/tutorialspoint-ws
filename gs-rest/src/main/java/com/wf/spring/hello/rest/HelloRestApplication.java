package com.wf.spring.hello.rest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HelloRestApplication implements CommandLineRunner{
	private static final Logger logger = LogManager.getLogger(HelloRestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HelloRestApplication.class);
	}
	@Override
	public void run(String... args) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
//		List<Equity> equities = restTemplate.getForObject("http://localhost:8080/equity?symbol=600048", List.class);
//		for(Iterator<Equity> i=equities.iterator();i.hasNext();){
//			System.out.println(i);
//		}
//		Equity eq = null;
//		try{
//		eq = restTemplate.getForObject("http://localhost:8080/equity/5700a879e5bd86410507ac76", Equity.class);
//		}catch(Exception e){
//			logger.error(e.getMessage());
//		}
//		logger.info(eq);
	}
}

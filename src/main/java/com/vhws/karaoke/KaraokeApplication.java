package com.vhws.karaoke;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




import javax.sql.DataSource;

@SpringBootApplication
public class KaraokeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaraokeApplication.class, args);
	}


}

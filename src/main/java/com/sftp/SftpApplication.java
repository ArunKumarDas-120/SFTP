package com.sftp;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SftpApplication implements CommandLineRunner {

	@Autowired
	private SftpService sftpService;

	public static void main(String[] args) {
		SpringApplication.run(SftpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		sftpService.checkFileExist("./app/Outbound/", "5051aea0-1c2994erotika-9f262a93842d.jpg");

		sftpService.uplodaFile(new File("E:/test/5471fddf-ea502247032d5330b29b.jpg"),
				Optional.of("./app/Outbound/"));

		sftpService.checkList("./app/Outbound/");

	}

}

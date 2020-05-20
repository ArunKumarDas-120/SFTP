package com.sftp;

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

		// sftpService.upload(new File("D:/Hot/candace-leilani-2-1920x1080.jpg"),
		// Optional.of("/app/multiple/"));
		// sftpService.checkFileExist("./app/multiple/", "279769.jpg");
		sftpService.download();

	}

}

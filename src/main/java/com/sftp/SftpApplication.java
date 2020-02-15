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

		sftpService.checkFileExist("/app/H2H/", "test.txt");
		sftpService.uplodaFile(new File("E:/test/597a2e56-48f60d1501179234597a2d62924087.55398550.jpg"),
				Optional.of("/app/Outbound/"));

		sftpService.checkList(null);

	}

}

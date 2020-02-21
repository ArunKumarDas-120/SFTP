package com.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

import com.jcraft.jsch.ChannelSftp.LsEntry;

@Configuration
@EnableConfigurationProperties(SftpConfigProperties.class)
public class SftpConfig {
	
	@Autowired
	private SftpConfigProperties sftpConfig;
	
	@Bean
	public RemoteFileTemplate<LsEntry> sftpTemplate() {
		DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(false);
		factory.setHost(sftpConfig.getHost());
		factory.setPort(sftpConfig.getPort());
		factory.setUser(sftpConfig.getUserName());
		factory.setPassword(sftpConfig.getPassword());
		factory.setAllowUnknownKeys(sftpConfig.isAllowUnknownKeys());
		SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(factory);
		template.setAutoCreateDirectory(true);
		template.setUseTemporaryFileName(false);
		ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
		template.setRemoteDirectoryExpression(EXPRESSION_PARSER.parseExpression("headers['/']"));
		return template;
	}
}

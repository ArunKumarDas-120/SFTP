package com.sftp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.springframework.jndi.support.SimpleJndiBeanFactory;

@Configuration
public class SftpConfig {


	@Bean
	public RemoteFileTemplate<LsEntry> sftpTemplate(@Value("${secureFtp.host:localhost}") String host,
			@Value("${secureFtp.port:22}") int port, @Value("${secureFtp.userName:test}") String userName,
			@Value("${secureFtp.password:test}") String password,
			@Value("${secureFtp.allowUnknownKeys:true}") boolean allowUnknownKeys) {
		DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(false);
		factory.setHost(host);
		factory.setPort(port);
		factory.setUser(userName);
		factory.setPassword(password);
		factory.setAllowUnknownKeys(allowUnknownKeys);
		RemoteFileTemplate<LsEntry> template = new SftpRemoteFileTemplate(factory);
		template.setAutoCreateDirectory(true);
		template.setUseTemporaryFileName(false);
		template.setBeanFactory(new SimpleJndiBeanFactory());
		template.setRemoteDirectoryExpression(new LiteralExpression(""));
		template.afterPropertiesSet();
		//template.setRemoteDirectoryExpression(new SpelExpressionParser().parseExpression("headers['./']"));
		return template;
	}
}

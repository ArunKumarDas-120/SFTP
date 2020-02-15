package com.sftp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sftp.gate-way")
public class SftpConfigProperties {

    private String host;
    private int port;
    private String userName;
    private String password;
    private boolean allowUnknownKeys;

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isAllowUnknownKeys() {
	return allowUnknownKeys;
    }

    public void setAllowUnknownKeys(boolean allowUnknownKeys) {
	this.allowUnknownKeys = allowUnknownKeys;
    }

}

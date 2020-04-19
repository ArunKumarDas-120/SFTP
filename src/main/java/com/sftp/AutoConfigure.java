package com.sftp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnProperty(prefix = "sftp.autoconfigure",name = {"enabled"},havingValue = "true")
public class AutoConfigure {
	
	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor(
	        Environment environment) {
	    return new BeanFactoryPostProcessor() {

	        @Override
	        public void postProcessBeanFactory(
	                ConfigurableListableBeanFactory beanFactory) throws BeansException {
	        	
	        	System.out.println(environment);
				/*
				 * BindResult<ExampleProperties> result = Binder.get(environment)
				 * .bind("com.example.prefix", ExampleProperties.class); ExampleProperties
				 * properties = result.get();
				 */
	            // Use the properties to post-process the bean factory as needed
	        }

	    };
	}
	
}

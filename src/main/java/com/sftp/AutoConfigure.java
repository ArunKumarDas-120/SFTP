package com.sftp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnProperty(prefix = "sftp.autoconfigure", name = {"enabled"}, havingValue = "true")
public class AutoConfigure {

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(
            Environment environment) {
        return beanFactory -> {
            GenericBeanDefinition bd = new GenericBeanDefinition();
            //bd.setBeanClass(MyBean.class);
            bd.getPropertyValues().add("strProp", "my string property");
            ((DefaultListableBeanFactory) beanFactory)
                    .registerBeanDefinition("myBeanName", bd);
            /*
             * BindResult<ExampleProperties> result = Binder.get(environment)
             * .bind("com.example.prefix", ExampleProperties.class); ExampleProperties
             * properties = result.get();
             */
            // Use the properties to post-process the bean factory as needed
        };
    }

    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {

        return new BeanDefinitionRegistryPostProcessor() {

            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

                GenericBeanDefinition bd = new GenericBeanDefinition();
                //bd.setBeanClass(MyBean.class);
                bd.getPropertyValues().add("strProp", "my string property");
                registry.registerBeanDefinition("myBeanName", bd);

            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

            }
        };
    }

}

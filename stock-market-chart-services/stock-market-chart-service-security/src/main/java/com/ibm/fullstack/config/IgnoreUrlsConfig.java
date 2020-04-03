package com.ibm.fullstack.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "secure.ignored")
//@PropertySource(value = "classpath:application.yml", encoding = "UTF-8", factory = YamlPropertyLoaderFactory.class)
//@PropertySource(value = "classpath:url.properties", encoding = "UTF-8")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<String>();
}

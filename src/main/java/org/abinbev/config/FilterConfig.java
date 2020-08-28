package org.abinbev.config;

import org.abinbev.filter.OAuth2Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OAuth2Filter());
        filterRegistrationBean.setOrder(Integer.MAX_VALUE);
        filterRegistrationBean.addUrlPatterns("/oauth2");
        return filterRegistrationBean;
    }
}

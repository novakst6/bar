package cz.cvut.bar.configuration;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import cz.cvut.bar.service.json.HibernateEntityObjectMapper;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"cz.cvut.bar"})
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver()
    {
        UrlBasedViewResolver res = new InternalResourceViewResolver();
        res.setViewClass(JstlView.class);
        res.setPrefix("/WEB-INF/views/");
        res.setSuffix(".jsp");

        return res;
    }
    
    @Bean
    CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	MappingJackson2HttpMessageConverter mj2hmc = new MappingJackson2HttpMessageConverter();
    	mj2hmc.setObjectMapper(new HibernateEntityObjectMapper());
    	converters.add(mj2hmc);
    }
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true)
                .parameterName("mediaType").ignoreAcceptHeader(true)
                .useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
    
    
}

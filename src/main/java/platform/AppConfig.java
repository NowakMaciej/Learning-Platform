package platform;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import platform.utils.CategoryConverter;
import platform.utils.ExerciseConverter;
import platform.utils.StudentConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "platform")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="platform.repository")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("LearningPlatformPersistenceUnit");
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		final ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		converter.setObjectMapper(objectMapper);
		converters.add(converter);
		super.configureMessageConverters(converters);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	/*
	 * ponizszy @autowired oraz @PostConstruct pozwala na usunięcie parametrów modelu z url. Sposób ten jest podany w linku
	 * https://stackoverflow.com/questions/26175340/spring-automatic-adds-model-param-to-url
	 * oraz (tego nie użyłem)
	 * https://stackoverflow.com/questions/2163517/how-do-i-prevent-spring-3-0-mvc-modelattribute-variables-from-appearing-in-url
	 */
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@PostConstruct
	public void init() {
	    requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(getCategoryConverter());
		registry.addConverter(getExerciseConverter());
		registry.addConverter(getStudentConverter());
	}
	
	@Bean
	public CategoryConverter getCategoryConverter() {
		return new CategoryConverter();
	}
	
	@Bean
	public ExerciseConverter getExerciseConverter() {
		return new ExerciseConverter();
	}
	
	@Bean
	public StudentConverter getStudentConverter() {
		return new StudentConverter();
	}
}

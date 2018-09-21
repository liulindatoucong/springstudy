package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitailizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 指定业务配置类
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class,MyBatisConfig.class};
	}

	/**
	 * 指定controller、视图解析器以及处理器映射
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	/**
	 * 将DispatcherServlet 映射到"/"
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}

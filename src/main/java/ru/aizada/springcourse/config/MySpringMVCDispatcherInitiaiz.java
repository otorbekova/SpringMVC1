package ru.aizada.springcourse.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MySpringMVCDispatcherInitiaiz extends AbstractAnnotationConfigDispatcherServletInitializer {
    //
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    //класс конфиг
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext aServlet) throws ServletException{
        super.onStartup(aServlet);
        registerHiddenFiledFilter(aServlet);
    }

    private void registerHiddenFiledFilter(ServletContext aContext){
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
    }
}

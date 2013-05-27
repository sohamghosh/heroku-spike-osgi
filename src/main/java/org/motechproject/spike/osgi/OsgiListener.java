package org.motechproject.spike.osgi;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OsgiListener implements ServletContextListener {

    public OsgiListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        OsgiFrameworkService service = applicationContext.getBean(OsgiFrameworkService.class);
        service.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}

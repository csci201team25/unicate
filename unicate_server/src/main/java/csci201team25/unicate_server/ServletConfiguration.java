package csci201team25.unicate_server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// the servlet config files for all the servlets (beans!)

@Configuration
public class ServletConfiguration {

    @Bean // bean here means it returns a bean, not that it is a bean
    public ServletRegistrationBean<CommentServlet> commentServletRegistrationBean() {
        return new ServletRegistrationBean<>(new CommentServlet(), "/comment-servlet");
    }

    @Bean
    public ServletRegistrationBean<CalendarServlet> calendarServletServletRegistrationBean() {
        return new ServletRegistrationBean<>(new CalendarServlet(), "/CalendarServlet");
    }

    @Bean
    public ServletRegistrationBean<LoginVerify> loginVerifyServletRegistrationBean() {
        return new ServletRegistrationBean<>(new LoginVerify(), "/LoginVerify");
    }

    @Bean
    public ServletRegistrationBean<RegisterVerify> registerVerifyServletRegistrationBean() {
        return new ServletRegistrationBean<>(new RegisterVerify(), "/RegisterVerify");
    }

}

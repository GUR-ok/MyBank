package mybankapp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "bankSchema")
    public XsdSchema getpersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bankservice.xsd"));
    }
    @Bean(name = "bank")
    public DefaultWsdl11Definition getpersonDefinition(@Qualifier("bankSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BankPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost:8080");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "newsSchema")
    public XsdSchema addpersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/newsservice.xsd"));
    }
    @Bean(name = "news")
    public DefaultWsdl11Definition addpersonDefinition(@Qualifier("newsSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("NewsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost:8080");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "authenticationSchema")
    public XsdSchema addtransactionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/authservice.xsd"));
    }
    @Bean(name = "authentication")
    public DefaultWsdl11Definition addtransactionDefinition(@Qualifier("authenticationSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AuthenticationPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost:8080");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

}

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

    @Bean(name = "getpersonSchema")
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/getpersonservice.xsd"));
    }
    @Bean(name = "getperson")
    public DefaultWsdl11Definition getpersonDefinition(@Qualifier("getpersonSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetPersonPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "addpersonSchema")
    public XsdSchema addpersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/addpersonservice.xsd"));
    }
    @Bean(name = "addperson")
    public DefaultWsdl11Definition addpersonDefinition(@Qualifier("addpersonSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AddPersonPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "addtransactionSchema")
    public XsdSchema addtransactionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/addtransactionservice.xsd"));
    }
    @Bean(name = "addtransaction")
    public DefaultWsdl11Definition addtransactionDefinition(@Qualifier("addtransactionSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AddTransactionPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "addaccountSchema")
    public XsdSchema addaccountSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/addaccountservice.xsd"));
    }
    @Bean(name = "addaccount")
    public DefaultWsdl11Definition addaccountDefinition(@Qualifier("addaccountSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AddAccountPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "deleteaccountSchema")
    public XsdSchema deleteaccountSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/deleteaccountservice.xsd"));
    }
    @Bean(name = "deleteaccount")
    public DefaultWsdl11Definition deleteaccountDefinition(@Qualifier("deleteaccountSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DeleteAccountPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "deletepersonSchema")
    public XsdSchema deletepersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/deletepersonservice.xsd"));
    }
    @Bean(name = "deleteperson")
    public DefaultWsdl11Definition deletepersonDefinition(@Qualifier("deletepersonSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DeletePersonPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getaccounttransactionsSchema")
    public XsdSchema getaccounttransactionsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/getaccounttransactionsservice.xsd"));
    }
    @Bean(name = "getaccounttransactions")
    public DefaultWsdl11Definition getaccounttransactionsDefinition(@Qualifier("getaccounttransactionsSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetAccountTransactionsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getallpersonsSchema")
    public XsdSchema getallpersonsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/getaccounttransactionsservice.xsd"));
    }
    @Bean(name = "getallpersons")
    public DefaultWsdl11Definition getallpersonsDefinition(@Qualifier("getallpersonsSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetAllPersonsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getpersonaccountsSchema")
    public XsdSchema getpersonaccountsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/getaccounttransactionsservice.xsd"));
    }
    @Bean(name = "getpersonaccounts")
    public DefaultWsdl11Definition getpersonaccountsDefinition(@Qualifier("getpersonaccountsSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetpersonAccountsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    @Bean(name = "updatepersonSchema")
    public XsdSchema updatepersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/services/bank/getaccounttransactionsservice.xsd"));
    }
    @Bean(name = "updateperson")
    public DefaultWsdl11Definition updatepersonDefinition(@Qualifier("updatepersonSchema") XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UpdatePersonPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.mybankapp.org/gen");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }
}

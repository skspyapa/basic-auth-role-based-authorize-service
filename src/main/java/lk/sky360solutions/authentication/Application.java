package lk.sky360solutions.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      /*
      * apis() allows selection of RequestHandler's using a predicate.
      * The example here uses an any predicate (default).
      * Out of the box predicates provided are any,
      * none, withClassAnnotation, withMethodAnnotation and basePackage.
      * */
      .paths(PathSelectors.any())
      /*
      * paths() allows selection of Path's using a predicate.
      * The example here uses an any predicate (default).
      * Out of the box we provide predicates for regex, ant, any, none.
      * */
      .build()
      .securityContexts(Collections.singletonList(securityContext()))
      /*
      * Sets up the security schemes used to protect the apis. Supported schemes are ApiKey, BasicAuth and OAuth
      * */
      .securitySchemes(Collections.singletonList(basicAuthScheme()));
      /*
      * Provides a way to globally set up security contexts for operation.
      * The idea here is that we provide a way to select operations to be
      * protected by one of the specified security schemes.
      * */
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
      .securityReferences(Collections.singletonList(basicAuthReference()))
      .forPaths(PathSelectors.ant("/products/**"))
      .build();
  }

  private SecurityScheme basicAuthScheme() {
    return new BasicAuth("basicAuth");
  }

  private SecurityReference basicAuthReference() {
    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
  }

}

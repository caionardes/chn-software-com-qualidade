package com.br.chn.software.interfaces.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(
//						RequestMethod.GET,
//						Arrays.asList(
//							new ResponseMessageBuilder().code(500).message("Erro critico! Http 500.").responseModel(new ModelRef("Error")).build(),
//							new ResponseMessageBuilder().code(403).message("Página não existe! Http 403.").responseModel(new ModelRef("Error")).build()
//						)
//				)
				.apiInfo(apiInfo());
				
				
//				.securitySchemes(Arrays.asList(securityScheme()))
//				.securityContexts(Arrays.asList(securityContext()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My REST API",
				"Some custom description of API.",
				"API TOS",
				"Terms of service",
				new Contact("CaioHN", "www.example.com", "myeaddress@company.com"), "License of API",
				"API license URL",
				Collections.emptyList());
	}
//
//	@Bean
//	public SecurityConfiguration security() {
//		return SecurityConfigurationBuilder.builder()
//				.clientId("3MVG9PE4xB9wtoY.JeB7N8fliBuxps97IcYUVIvBkYwdIAF04AgmywF_J8wTwlpBqtJoj.2MwBU4HE7WRGkBD")
//				.clientSecret("1895851884120497417")
//				.scopeSeparator(" ")
//				.useBasicAuthenticationWithAccessCodeGrant(true)
//				.build();
//	}

//	private SecurityScheme securityScheme() {
//	    GrantType grantType = new AuthorizationCodeGrantBuilder()
//	        .tokenEndpoint(new TokenEndpoint("https://test.salesforce.com/services/oauth2/token", "oauthtoken"))
//	        .tokenRequestEndpoint(
//	          new TokenRequestEndpoint("https://test.salesforce.com/services/oauth2/authorize", "3MVG9PE4xB9wtoY.JeB7N8fliBuxps97IcYUVIvBkYwdIAF04AgmywF_J8wTwlpBqtJoj.2MwBU4HE7WRGkBD", "1895851884120497417"))
//	        .build();
//	 
//	    SecurityScheme oauth = new OAuthBuilder().name("recurso_de_terceiro_protegido")
//	        .grantTypes(Arrays.asList(grantType))
//	        .scopes(Arrays.asList(scopes()))
//	        .build();
//	    return oauth;
//	}
//
//	private AuthorizationScope[] scopes() {
//		AuthorizationScope[] scopes = {
//				new AuthorizationScope("cadastrar_imoveis", "Para as operações de cadastro de imóveis."),
//				new AuthorizationScope("pesquisa_imoveis", "Para as operações de pesquisa de imóveis."),
//				new AuthorizationScope("inscricao_imoveis", "Para as operações de inscrição nos imóveis.")
//		};
//		return scopes;
//	}
//
//	private SecurityContext securityContext() {
//		return SecurityContext.builder()
//				.securityReferences(Arrays.asList(new SecurityReference("recurso_de_terceiro_protegido", scopes())))
//				.forPaths(PathSelectors.regex("/imovel/*")).build();
//	}
}

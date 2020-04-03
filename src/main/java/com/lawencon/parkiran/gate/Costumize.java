package com.lawencon.parkiran.gate;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

public class Costumize implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
	@Override
	 public void customize(ConfigurableServletWebServerFactory server) {
	  server.setPort(9090);
	 }
}

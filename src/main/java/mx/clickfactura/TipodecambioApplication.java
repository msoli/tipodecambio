package mx.clickfactura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TipodecambioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TipodecambioApplication.class, args);
	}


	/**
	 * Used when run as WAR
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TipodecambioApplication.class);
	}

}

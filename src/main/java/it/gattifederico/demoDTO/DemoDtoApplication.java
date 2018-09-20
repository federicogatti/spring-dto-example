package it.gattifederico.demoDTO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Demo dto application.
 */
@SpringBootApplication
public class DemoDtoApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(DemoDtoApplication.class, args);
	}

	/**
	 * Model mapper model mapper.
	 *
	 * @return the model mapper
	 */
	/*@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}*/
}

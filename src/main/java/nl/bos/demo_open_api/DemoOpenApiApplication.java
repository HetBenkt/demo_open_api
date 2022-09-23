package nl.bos.demo_open_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoOpenApiApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoOpenApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoOpenApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args -> {
			bookRepository.save(new Book("The Naked Don’t Fear the Water", "Matthieu Aikins"));
			bookRepository.save(new Book("In Love", "Amy Bloom"));
			bookRepository.save(new Book("The School for Good Mothers", "Jessamine Chan"));
			bookRepository.save(new Book("The Candy House", "Jennifer Egan"));
			bookRepository.save(new Book("The Candy House2", "Jennifer Egan2"));
			bookRepository.save(new Book("The Candy House3_2", "Jennifer Egan3"));

			for (Book book : bookRepository.findAll()) {
				log.info(String.valueOf(book));
			}
		});
	}

}

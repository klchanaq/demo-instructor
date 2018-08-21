package com.example.demo.config;

import com.example.demo.domain.Instructor;
import com.example.demo.domain.embeddableDomain.Address;
import com.example.demo.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
@EnableTransactionManagement
public class DatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private final Environment env;

    public DatabaseConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public CommandLineRunner commandLineRunner(InstructorService instructorService) {
        // after inject repository / service beans
        return args -> {

            // create initial sample data by command liner runner.
            Instructor instructor = new Instructor();
            instructor.setFirstName("John");
            instructor.setLastName("White");
            instructor.setEmail("johnwhite@gmail.com");
            instructor.setAddress(new Address("UK", "London Street 1", null));
            instructorService.save(instructor);

        };
    }

}

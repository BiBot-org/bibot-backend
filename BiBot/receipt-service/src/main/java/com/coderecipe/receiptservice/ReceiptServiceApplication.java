package com.coderecipe.receiptservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
public class ReceiptServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptServiceApplication.class, args);
    }

}

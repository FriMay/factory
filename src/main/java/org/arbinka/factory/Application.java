/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.arbinka.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Приложение написано Владиславом Бирюковым.
 * Почта: frimaymeizu@gmail.com
 */
@EntityScan("org.arbinka.factory.models")
@EnableJpaRepositories("org.arbinka.factory.repositories")
@SpringBootApplication(scanBasePackages = "org.arbinka.factory")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

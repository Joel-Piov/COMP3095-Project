/*********************************************************************************
 * Project: < project name … >
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: The Main class that starts the application
 *********************************************************************************/

package com.assignment2.COMP3095;

import com.assignment2.COMP3095.models.Client;
import com.assignment2.COMP3095.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Comp3095Application {

	public static void main(String[] args) {

		SpringApplication.run(Comp3095Application.class, args);

	}

}

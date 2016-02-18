package org.ekolandia.dispatch.loaddata

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.annotation.ComponentScan


@ComponentScan
@SpringBootApplication
@RestController
class LoadDataController {

	@RequestMapping(value="/data")
	def getData() {
		[
			"test" : "Data"
		]
    }
	
	public static void main(String[] args) {
		SpringApplication.run LoadDataController, args
	}
}
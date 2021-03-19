package jp.co.suyama.menu.deliver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.suyama.menu.deliver.mapper")
public class MenuDeliverApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuDeliverApiApplication.class, args);
	}

}

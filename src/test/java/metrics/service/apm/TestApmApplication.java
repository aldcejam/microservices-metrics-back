package metrics.service.apm;

import org.springframework.boot.SpringApplication;

public class TestApmApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApmApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

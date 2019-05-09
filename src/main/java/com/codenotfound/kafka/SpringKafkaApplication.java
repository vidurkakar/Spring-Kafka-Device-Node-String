package com.codenotfound.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.codenotfound.kafka.osdkLinker.LLib;

@SpringBootApplication
public class SpringKafkaApplication {

  public static void main(String[] args) {

    String librarypath = "src/main/java/com/codenotfound/kafka/osdkLinker";
    System.setProperty("jna.library.path", librarypath);
//
    LLib.instance.intialize_C();
    LLib.instance.droneTakeOff_C();
    LLib.instance.droneMove_C(5,4,3,2);
    LLib.instance.droneLand_C();

   // SpringApplication.run(SpringKafkaApplication.class, args);
    SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringKafkaApplication.class);


    builder.headless(false);
    ConfigurableApplicationContext context = builder.run(args);


  }
}

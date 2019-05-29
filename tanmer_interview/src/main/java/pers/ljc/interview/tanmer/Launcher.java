package pers.ljc.interview.tanmer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.ljc.interview.tanmer.service.LawyerService;

/**
 * Created by liujicheng on 2019/5/28.
 */
@SpringBootApplication(scanBasePackages = {"pers.ljc.interview.tanmer"})
@MapperScan(basePackages = {"pers.ljc.interview.tanmer.dao"})
public class Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }

    @Autowired
    LawyerService lawyerService;

    @Override
    public void run(String... args) throws Exception {
        lawyerService.readLawyerInfo(args);
    }
}

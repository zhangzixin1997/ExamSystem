package cn.zzx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zzx.mapper")
public class StartServer {
    public static void main(String[] args) {
        SpringApplication.run(StartServer.class,args);
    }
}

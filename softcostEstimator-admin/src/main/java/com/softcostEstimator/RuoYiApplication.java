package com.softcostEstimator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ SoftcostEstimator启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " _   _ _____ _   _    ____ ____  _____    _  _____    ____ ____   _      ____   ___ ____  _  _   \n" +
                "| \\ | | ____| | | |  / ___|  _ \\| ____|  / \\|_   _|  / ___|  _ \\ / \\    |___ \\ / _ \\___ \\| || |  \n" +
                "|  \\| |  _| | | | | | |  _| |_) |  _|   / _ \\ | |   | |  _| |_) / _ \\     __) | | | |__) | || |_ \n" +
                "| |\\  | |___| |_| | | |_| |  _ <| |___ / ___ \\| |   | |_| |  __/ ___ \\   / __/| |_| / __/|__   _|\n" +
                "|_| \\_|_____|\\___/   \\____|_| \\_\\_____/_/   \\_\\_|    \\____|_| /_/   \\_\\ |_____|\\___/_____|  |_|  \n");

    }
}

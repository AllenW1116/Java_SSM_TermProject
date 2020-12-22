package pers.allen.whu_java_terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//项目启动文件。这个一般放在项目根目录下，放在某个包里的话会扫描不到导致项目启动失败
@SpringBootApplication
public class WhuJavaTerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhuJavaTerminalApplication.class, args);
	}

}

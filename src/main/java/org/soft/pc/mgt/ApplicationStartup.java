package org.soft.pc.mgt;

import org.soft.pc.mgt.auth.AuthUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class ApplicationStartup implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println("-------初始化系统权限-------");
		System.out.println(AuthUtil.allAuths);
	}

}

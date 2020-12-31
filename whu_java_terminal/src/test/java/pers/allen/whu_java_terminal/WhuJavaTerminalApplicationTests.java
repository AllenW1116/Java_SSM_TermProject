package pers.allen.whu_java_terminal;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.allen.whu_java_terminal.model.entity.User;
import pers.allen.whu_java_terminal.utils.JWTUtils;

@SpringBootTest
class WhuJavaTerminalApplicationTests {

	@Test
	public void testGeneJwt(){  //测试生成的jwt与解析


		User user = new User();
		user.setId(66);
		user.setName("测试人员");
		user.setHeadImg("png");

		String token = JWTUtils.geneJsonWebToken(user);

		System.out.println("生成的token是： "+token);
		/**
		 * 生成的token是： whu_alleneyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbGxlbiIs
		 * ImhlYWRfaW1nIjoicG5nIiwiaWQiOjY2LCJuYW1lIjoi5LqM5b2T5a625bCPRCIs
		 * ImlhdCI6MTYwODY4ODA5NCwiZXhwIjoxNjA5MjkyODk0fQ.EaHoLk0NwsK00zfH
		 * n84sKLGP43FzfDf9Nptcy-GrHxc
		 * 可以看到前面有我们加上的prefix:whu_allen
		 */

		try {
			Thread.sleep(4000L);  //如果这个时间大于expiretime 就会解析失败。jwt永远滴神！
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Claims claims = JWTUtils.checkJWT(token);

		System.out.println(claims);
		/**
		 * 解析出的claims：{sub=Allen, head_img=png, id=66, name=测试人员, iat=1608688094, exp=1609292894}
		 */


		System.out.println(claims.get("name"));
		/**
		 * 从claims中得到名字：测试人员
		 */

	}

}

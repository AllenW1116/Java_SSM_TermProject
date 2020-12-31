package pers.allen.whu_java_terminal.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pers.allen.whu_java_terminal.model.entity.User;

import java.util.Date;

/**
 * Jwt工具类
 * 注意点:
 * 1、生成的token, 是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3、无法作废已颁布的token，除非改秘钥
 */

public class JWTUtils {
    /**
     * 过期时间，一周
     */
    private  static final long EXPIRE = 60000 * 60 * 24 * 7; //单位毫秒
    //private  static final long EXPIRE = 1;


    /**
     * 加密秘钥
     */
    private  static final String SECRET = "allen.cs.whu"; //密钥


    /**
     * 令牌前缀
     */
    private  static final String TOKEN_PREFIX = "whu_allen";


    /**
     * subject（谁颁布的
     */
    private  static final String SUBJECT = "Allen";


    /**
     * 根据用户信息，生成Token令牌
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){

        String token = Jwts.builder().setSubject(SUBJECT) //链式调用 一直往里加要加密的信息就好
                .claim("head_img",user.getHeadImg())  //claim也就是patload 装入业务信息
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //过期时间
                .signWith(SignatureAlgorithm.HS256,SECRET).compact(); //加密算法（签名算法

        token = TOKEN_PREFIX + token; //加一个前缀（惯用做法，其实是要根据自己的业务需求来的


        return token;
    }


    /**
     * 校验token的方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){  //解密返回claims!从中可以取得各种信息

        try{

            final  Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody(); //replace其实就是把prefix拿掉了

            return claims;

        }catch (Exception e){
            return null;
        }

    }
}

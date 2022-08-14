package edu.scau.misp.sys.service;

import edu.scau.misp.sys.domain.HsLoginUser;
import edu.scau.misp.sys.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HsTokenService {

    private int expireTime = 1440; // 默认7天

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private SignatureAlgorithm alg = SignatureAlgorithm.HS512;

    private String secret = "management_information_system";

    public static final String TOKEN_PREFIX = "Bearer ";


    /**
     * 生成token
     * @param loginUser
     * @return
     */
    public String createToken(HsLoginUser loginUser) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + MILLIS_MINUTE * expireTime);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT") // 设置header参数
                .setSubject(loginUser.getUsername()) //设置payload_jwt面向的用户
                .setIssuedAt(nowDate) // 设置payload_jwt的签发时间
                .setExpiration(expireDate) // 设设置payload_jwt的过期时间
                .signWith(alg, secret) // 设置signature
                .compact();
    }

    /**
         * 解析token
         * @param token
         * @return
         */
        public Claims parseToken(String token) {
            // 判断token是否有前缀Bearer，有则前缀
            if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)){
                token = token.replace(TOKEN_PREFIX, "");
            }
            try {
                return Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                return null;
            }
        }


        /**
         * 判断token是否过期
         * @param claims
         * @return
         */
        public boolean isTokenExpired(Claims claims) {
            return claims.getExpiration().before(new Date());
        }

}


package com.library.librarymanagementsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

//@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class JwtUtils {
    private static String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Claims getClaimByToken(String token) {
        try {
            //return Jwts.parserBuilder()
            //        .setSigningKey(secret)
            //        //.setSigningKey(DatatypeConverter.parseBase64Binary(token))
            //        .build()
            //        .parseClaimsJws(token)
            //        .getBody();
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    /**
     * md5加密
     *
     * @param str 待加密字符串
     * @return 加密后结果
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        // 加盐
        String salt = "salt";
        //MessageDigest md = MessageDigest.getInstance("MD5");
        //md.update((str+salt).getBytes());
        //return new BigInteger(1, md.digest()).toString(16);

        // 加密次数
        int count = 3;
        Md5Hash md5Hash = new Md5Hash(str, salt, count);
        return md5Hash.toHex();
    }
}

package shaik.shaikzaid.Security;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import shaik.shaikzaid.domain.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static shaik.shaikzaid.Security.SecurityConstants.EXPIRATION_TIME;
import static shaik.shaikzaid.Security.SecurityConstants.SECRET;
@Component
public class JwtTokenProvider {
    // generate and validate a Token
    public String generateToken(Authentication authentication){
        User user=(User)authentication.getPrincipal();
        Date now=new Date(System.currentTimeMillis());

        Date expiryDate= new Date(now.getTime()+EXPIRATION_TIME);
        String userId= Long.toString(user.getId());

        Map<String,Object> claims=new HashMap<>();
        claims.put("id",(Long.toString(user.getId())));
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullname());


        return Jwts.builder().setSubject(userId)
                .setClaims(claims).setIssuedAt(now).
                setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, SECRET).
                compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);

            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");

        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch(ExpiredJwtException ex){
            System.out.println("Expired JWT Token");
        }catch (UnsupportedJwtException ex){
            System.out.println("UnSupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }
    public Long getUserIdFromJWT(String token){
        Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id= (String) claims.get("id");

        return Long.parseLong(id);
    }
}

package br.ufrn.imd.pdvlp2.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.expiration}")
    private String expiration;
    @Value("${api.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication){
        EmployeeModel user = (EmployeeModel) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime()+Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("PDV2 LP2")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7, bearerToken.length());
            }
            return null;
        } catch (Exception e) {
                e.printStackTrace();
                return null;
        }
    }

    public String refreshToken(String token) {
        Claims tokenClaims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        
        Date todayDate = new Date();
        Date validDate = new Date(todayDate.getTime() + Long.parseLong(expiration));
        
        Claims newClaims = Jwts.claims().setSubject(tokenClaims.getSubject());
        
        newClaims.put("permissions", tokenClaims.get("permissions"));
        
        return Jwts.builder().setClaims(newClaims).setIssuedAt(todayDate).setExpiration(validDate)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}

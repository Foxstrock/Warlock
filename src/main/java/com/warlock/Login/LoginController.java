package com.warlock.Login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.warlock.JwtTokenUtil;
import com.warlock.warlock.Utility.SingletoneData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserLoginInterface userLoginInterface;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<UserLogin> login(@RequestParam(value = "auth") String auth) throws JsonProcessingException {
        String email = new String(Base64.getDecoder().decode(auth)).split(":")[0];
        String password = new String(Base64.getDecoder().decode(auth)).split(":")[1];

        Optional<UserLogin> checkuser = userLoginInterface.findByEmail(email);

        if(checkuser.isPresent()){
            UserLogin user = checkuser.get();
            if(user.password.equals(password)){
                HttpHeaders headers = new HttpHeaders();
                headers.add(SingletoneData.INSTANCE.getMessage(), "Logged In " + user.role);
                headers.add(SingletoneData.INSTANCE.getTokenRefreshHeader(),jwtTokenUtil.generateRefreshToken(user));
                headers.add(SingletoneData.INSTANCE.getTokenHeader(),jwtTokenUtil.generateToken(user));

                return ResponseEntity.ok().headers(headers).body(user);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<UserLogin> refreshtoken(HttpServletRequest request) throws JsonProcessingException {
        String refreshToken = request.getHeader(SingletoneData.INSTANCE.getTokenRefreshHeader());

        if(jwtTokenUtil.isTokenExpired(refreshToken)){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }

        UserLogin user = userLoginInterface.findById(Long.parseLong(jwtTokenUtil.getUserIdFromToken(refreshToken))).get();
        HttpHeaders headers = new HttpHeaders();
        headers.add(SingletoneData.INSTANCE.getMessage(), "Logged In " + user.role);
        headers.add(SingletoneData.INSTANCE.getTokenRefreshHeader(),jwtTokenUtil.generateRefreshToken(user));
        headers.add(SingletoneData.INSTANCE.getTokenHeader(),jwtTokenUtil.generateToken(user));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
    }
}

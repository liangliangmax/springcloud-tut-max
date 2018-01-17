package com.example.zuul.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zuul.jwt.JwtAuthenticationRequest;
import com.example.zuul.jwt.JwtAuthenticationResponse;
import com.example.zuul.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${auth.token-header}")
    private String tokenHeader;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map=new HashMap<>();
        if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            final String token = authService.login(username, password);

            //Authorization貌似是关键字，设置不了值
            response.setHeader("access-token",token);

            map.put("msg","success");
            map.put("token",token);

            return JSONObject.toJSONString(map);
        }

        map.put("msg","error");
        return JSONObject.toJSONString(map);
    }

    @RequestMapping(value = "/refresh",method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token) throws Exception {
        authService.validate(token);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/invalid", method = RequestMethod.POST)
    public ResponseEntity<?> invalid(@RequestHeader("access-token") String token){
        authService.invalid(token);
        return ResponseEntity.ok(true);
    }
}

package com.x.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @GetMapping("/main")
    public String redirectToTwitterOAuth() {
        return "redirect:https://twitter.com/i/oauth2/authorize?response_type=code&client_id=Z0V3bHZVbkdPSVEyMEM2T1RxeTQ6MTpjaQ&redirect_uri=http://localhost:8080/getData&scope=tweet.read%20users.read%20follows.read%20offline.access&state=state&code_challenge=challenge&code_challenge_method=plain";
    }
}

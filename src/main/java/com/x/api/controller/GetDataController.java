package com.x.api.controller;

import com.x.api.services.GetAccessToken;
import com.x.api.services.GetXData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetDataController {

    private final GetAccessToken getAccessToken;
    private final GetXData getXData;

    @Autowired
    public GetDataController(GetAccessToken getAccessToken, GetXData getXData) {
        this.getAccessToken = getAccessToken;
        this.getXData = getXData;
    }

    @GetMapping("/getData")
    public ResponseEntity<String> getData(@RequestParam("code") String code) {
        String accessToken = getAccessToken.fetchAccessToken(code);
        String jsonData = getXData.getDataWithAccessToken(accessToken);
        return ResponseEntity.ok().body(jsonData);
    }
}


package com.example.wordcup.myApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myAppEndPoint {

    @GetMapping(path="/teste")
    public ResponseEntity teste(){
        return ResponseEntity.ok(HttpStatus.OK) ;
    }

}

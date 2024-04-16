package com.crio.starter.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;
import com.crio.starter.exchange.PostMemeReponse;
import com.crio.starter.service.IMemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeController {

    @Autowired
    private IMemeService memeService;

    @PostMapping("/memes/")
    @ResponseBody
    public ResponseEntity<PostMemeReponse> postMemeToPortal(@RequestBody @Valid GetMemeRequest getMemeRequest) {
        
        ResponseEntity<PostMemeReponse> response = null;
        PostMemeReponse postMemeReponse = new PostMemeReponse();

        String id = memeService.postMeme(getMemeRequest);

        if (id == null) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            return response;
        }

        postMemeReponse.setId(id);
        response = ResponseEntity.status(HttpStatus.OK).body(postMemeReponse);
        return response;

    }

    @GetMapping("/memes")
    public ResponseEntity<List<MemeEntity>> getAllMemes() {

        List<MemeEntity> listOfMeme = memeService.getAllMemes();
        return ResponseEntity.status(HttpStatus.OK).body(listOfMeme);
        
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<MemeEntity> getParticularMeme(@PathVariable String id) {
        ResponseEntity<MemeEntity> response;

        Optional<MemeEntity> memeEntity = memeService.getMemeById(id);

        if(!memeEntity.isPresent()){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return response;
        }
        
        response = ResponseEntity.status(HttpStatus.OK).body(memeEntity.get());
        return response;

    }
}
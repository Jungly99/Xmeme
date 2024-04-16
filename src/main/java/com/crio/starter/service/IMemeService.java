package com.crio.starter.service;

import java.util.List;
import java.util.Optional;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;


public interface IMemeService {

	String postMeme(GetMemeRequest getMemeRequest);

	List<MemeEntity> getAllMemes();

	Optional<MemeEntity> getMemeById(String id);
    
}
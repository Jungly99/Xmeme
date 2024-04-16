
package com.crio.starter.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;
import com.crio.starter.repository.IMemeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class MemeServiceImpl implements IMemeService {

    @Autowired
    private IMemeRepository memeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private String autoIncrement = "0";

    @Override
    public String postMeme(GetMemeRequest getMemeRequest) {
        // TODO Auto-generated method stub

        // If the given Meme object is already present
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(getMemeRequest.getName()).and("url")
                .is(getMemeRequest.getUrl()).and("caption").is(getMemeRequest.getCaption()));
        List<MemeEntity> memeExist = mongoTemplate.find(query, MemeEntity.class);
        if (memeExist.size() != 0)
            return null;

        // Else, increment the id and create the object of MemeEntity and save to
        // database
        Integer id = Integer.parseInt(autoIncrement);
        id = id + 1;
        autoIncrement = Integer.toString(id);
        MemeEntity memeEntity = new MemeEntity(autoIncrement, getMemeRequest.getName(),
                getMemeRequest.getUrl(), getMemeRequest.getCaption(), LocalDateTime.now());
        memeRepository.save(memeEntity);
        return autoIncrement;
    }

    @Override
    public List<MemeEntity> getAllMemes() {
        // TODO Auto-generated method stub
        
        List<MemeEntity> listOfAllMemes = memeRepository.findAll();
        
        Comparator<MemeEntity> compareById = (MemeEntity m1, MemeEntity m2) -> m1.getCreatedAt().compareTo(m2.getCreatedAt());
        Collections.sort(listOfAllMemes, compareById.reversed());

        if(listOfAllMemes.size()<=100)
            return listOfAllMemes;
        
        List<MemeEntity> first100MemeList = listOfAllMemes.stream().limit(100).collect(Collectors.toList());
        return first100MemeList;
             
    }

    @Override
    public Optional<MemeEntity> getMemeById(String id) {
        // TODO Auto-generated method stub
        Optional<MemeEntity> memeEntity = memeRepository.findById(id);
        return memeEntity;
    }

    
    
}

package com.status.service;


import com.status.model.DBseq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public String getSeqNumber(String seqName){
        Query query=new Query(Criteria.where("id").is(seqName));
        Update update=new Update().inc("seq",1);

        DBseq counter=mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),
                DBseq.class);
        Integer an=!Objects.isNull(counter) ? counter.getSeq() :1;
        return an.toString();

    }

}

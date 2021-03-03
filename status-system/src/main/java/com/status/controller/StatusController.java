package com.status.controller;


import com.status.model.Status;
import com.status.model.User;
import com.status.repository.StatusRepository;
import com.status.repository.UserRepo;
import com.status.service.SequenceGenerator;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StatusController {

    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addStatus")
    public Status save(@RequestParam("userid") String username,
                       @RequestParam("file") MultipartFile file,@RequestParam("msg") String msg) throws IOException {
        Status status=new Status(sequenceGenerator.getSeqNumber(Status.SEQUENCE_NAME));
        User user=userRepo.findByName(username);
        status.setUserId(user.getId());
        status.setFile(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        status.setMsg(msg);
        return statusRepository.save(status);
    }

    @GetMapping("/allstatus")
    public List<Status> getStatus(@RequestParam("userid") String userid,@RequestParam("pageNumber") Integer pageNumber,
    @RequestParam("pageSize") Integer pageSize){


        return statusRepository.findAllByuserId().stream().skip((pageNumber-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }

}

package com.status.controller;


import com.status.model.Comments;
import com.status.model.Status;
import com.status.model.User;
import com.status.repository.CommentRepo;
import com.status.repository.UserRepo;
import com.status.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addcomment")
    public String addComment(@RequestParam("status_id") String status_id,@RequestParam("user_name") String user_name,@RequestParam("comment") String comments){
        Comments comment=new Comments(sequenceGenerator.getSeqNumber(Comments.SEQUENCE_NAME));
        comment.setUser_id(userRepo.findByName(user_name).getId());
        comment.setComment(comments);
        comment.setStatus_id(status_id);
        commentRepo.save(comment);

        return "you have commented on "+status_id;

    }
}

package com.club.masterpiece.web.service;

import com.club.masterpiece.web.comment.model.Comment;
import com.club.masterpiece.web.comment.repository.CommentRepository;
import com.club.masterpiece.web.comment.service.CommentFindService;
import com.club.masterpiece.web.comment.service.impl.CommentFindServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by pasudo123 on 2019-10-18
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                CommentFindServiceImpl.class,
                CommentRepository.class
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public class CommentFindServiceTest {

}

package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestApiController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping(value = "/news/{page}")
//    public List<Article> getIndexArticles(@PathVariable Long page){
//        return articleRepository.findArticlesWithLimit(page, 10L);
//    }

    @GetMapping(value = "/rest/article/{id}")
    public Article findArticleById(@PathVariable Long id){
        return articleRepository.findArticleById(id);
    }

    @GetMapping(value = "/user/{id}")
    public User findUserById(@PathVariable Long id){
        return userRepository.findUserById(id);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/users")
    public List<User> getUsers(@RequestParam Integer page){
        page = page == 0 ? 0 : page * 10;
        return userRepository.findUsers(page);
    }

}

package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage(){
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String savePerson(User user){
        if (user.getUsername().length() > 3 && user.getPassword().length() > 0){
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
        }
        return "redirect:/me";
    }

    @GetMapping(value = "/auth")
    public ModelAndView getAuthPage(){
        return new ModelAndView("auth", "user", new User());
    }


    @GetMapping(value = "/")
    public ModelAndView getIndexPage(){
        return new ModelAndView("indexTest", "articles",articleDao.findAllReversed());
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/me")
    public @ResponseBody String getMe(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(name = "searchString")String searchString){
        List<Article> articles = articleDao.findArticlesWhereTitleLikeIgnoreCase(searchString);
        return new ModelAndView("indexTest", "articles", articles);
    }



}

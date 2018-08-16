package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserPostsDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dto.UserDto;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Secured("ROLE_USER")
@Controller
public class UserController {

    @Autowired private UserDao userDao;

    @Autowired private ArticleDao articleDao;

    @Autowired private UserPostsDao userPostsDao;

    @GetMapping(value = "/me")
    public ModelAndView getUserPage(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userDao.findUserByUsername(name);
        UserDto user;
        if (userEntity!=null){
            user = new UserDto(userEntity, userPostsDao.findArticlesByUser(userEntity.getId()));
        }else{
            return new ModelAndView("redirect:/auth");
        }
        return new ModelAndView("userPage", "user", user);
    }


    @GetMapping(value = "/user/addArticleToMe/{id}")
    public String addArticleToMyCollection(@PathVariable("id") String idValue){
        long id = Long.parseLong(idValue);
        User user = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (!userPostsDao.findArticlesByUser(user.getId()).contains(articleDao.findArticleById(id))){
            userPostsDao.save(user.getId(), id);
        }
        return "redirect:/me";
    }

}

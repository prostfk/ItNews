package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.AbstractDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.CommentDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @GetMapping(value = "/article/{id}")
    public ModelAndView checkArticle(@PathVariable Long id){
        Article articleById = articleDao.findArticleById(id);
        return new ModelAndView("singleArticle", "article", articleById);
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "/article/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam("comment-content")String comment){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name!=null){
            Comment com = new Comment(name,comment,new Date().toString(),id);
            commentDao.save(com);
        }
        return "redirect:/article/" + id;
    }

    @GetMapping(value = "/articles/{type}")
    public ModelAndView searchByType(@PathVariable String type){
        List<Article> articlesByType = articleDao.findArticlesByType(type);
        return new ModelAndView("indexTest", "articles",articlesByType);
    }

}

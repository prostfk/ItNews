package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/article/{id}")
    public ModelAndView checkArticle(@PathVariable Long id){
        Article articleById = articleRepository.findArticleById(id);
        List<Comment> commentsByArticleId = commentRepository.findCommentsByArticleId(articleById.getId());
        articleById.setComments(commentsByArticleId);
        return new ModelAndView("singleArticle", "article", articleById);
    }

//    @Secured("ROLE_USER")
//    @PostMapping(value = "/article/{id}/comment")
//    public String addComment(@PathVariable Long id, @RequestParam("comment-content")String comment){
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (name!=null){
//            Comment com = new Comment(name,comment,new Date().toString(),id);
//            commentRepository.save(com);
//        }
//        return "redirect:/article/" + id;
//    }

    @GetMapping(value = "/articles/{type}")
    public ModelAndView searchByType(@PathVariable String type, Pageable pageable){
        List<Article> articlesByType = articleRepository.findArticlesByType(type, pageable);
        return new ModelAndView("indexTest", "articles",articlesByType);
    }

}

package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestApiController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/news/{page}")
    public List<Article> getIndexArticles(@PathVariable Long page){
        return articleDao.findArticlesWithLimit(page, 10L);
    }

    @GetMapping(value = "/article/{id}")
    public Article findArticleById(@PathVariable Long id){
        return articleDao.findArticleById(id);
    }

    @GetMapping(value = "/user/{id}")
    public User findUserById(@PathVariable Long id){
        return userDao.findUserById(id);
    }

    @GetMapping(value = "/checkRole")
    public String checkRole() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", SecurityContextHolder.getContext().getAuthentication().getCredentials());
        jsonObject.put("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        jsonObject.put("details", SecurityContextHolder.getContext().getAuthentication().getDetails());
        return jsonObject.toString();
    }

}

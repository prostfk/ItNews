package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String savePerson(@Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/auth";
    }

    @GetMapping(value = "/auth")
    public ModelAndView getAuthPage() {
        return new ModelAndView("auth", "user", new User());
    }


    @GetMapping(value = "/")
    public ModelAndView getIndexPage(Pageable pageable) {
        return new ModelAndView("indexTest", "articles", articleRepository.findAll());
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(name = "searchString") String searchString) {
        List<Article> articles = articleRepository.findArticlesByTitleLikeIgnoreCase(searchString);
        return new ModelAndView("indexTest", "articles", articles);
    }


    //    AJAX
    @GetMapping(value = "/checkPassword")
    @ResponseBody
    public String checkPassword(String password) {
        if (password.length() < 3) {
            return "Weak password";
        } else if (password.length() > 3 && password.length() < 6) {
            return "Good password";
        } else {
            return "Perfect password";
        }
    }

    @GetMapping(value = "/checkUsername")
    @ResponseBody
    public String checkUsername(String username) {
        if (userRepository.findUserByUsername(username) == null && username.length() > 3) {
            return "Good username";
        } else {
            return "User with such username already exists";
        }
    }

    @GetMapping(value = "/tes")
    public String tes() {
        return "restPaginationPage";
    }

    @GetMapping(value = "/check/{id}")
    public String getTest() {
        return "restSinglePage";
    }

}

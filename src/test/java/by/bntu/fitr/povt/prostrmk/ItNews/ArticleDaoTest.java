package by.bntu.fitr.povt.prostrmk.ItNews;


import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void findArticlesByType() {
        List<Article> programming = articleDao.findArticlesByType("programming");
        assertNotSame(Collections.EMPTY_LIST, programming);
    }

    @Test
    public void findAll() {
        List<Article> all = articleDao.findAll();
        assertNotSame(Collections.EMPTY_LIST, all);
    }

    @Test
    public void findAllReversed() {
        List<Article> all = articleDao.findAll();
        List<Article> allReversed = articleDao.findAllReversed();
        Collections.reverse(allReversed);
        assertEquals(all,allReversed);
    }

    @Test
    public void findByIdTest() {
        Article articleById = articleDao.findArticleById(1L);
        assertNotNull(articleById);
    }
}

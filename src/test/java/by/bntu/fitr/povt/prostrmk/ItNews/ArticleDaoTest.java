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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void findArticlesByTypeCorrect() {
        List<Article> programming = articleDao.findArticlesByType("programming");
        programming.forEach(article -> {
            assertEquals("programming", article.getType());
        });
        assertNotSame(Collections.EMPTY_LIST, programming);
    }

    @Test
    public void findArticlesByTypeIncorrect() {
        List<Article> programming = articleDao.findArticlesByType("famous");
        assertEquals(Collections.EMPTY_LIST, programming);
    }

    @Test
    public void findAll() {
        List<Article> all = articleDao.findAll();
        assertNotSame(Collections.EMPTY_LIST, all);
        all.forEach(Assert::assertNotNull);
    }

    @Test
    public void findArticleByIdCorrect() {
        Article articleById = articleDao.findArticleById(1L);
        long id = 1;
        long articleBaseId = articleById.getId();
        assertEquals(id, articleBaseId);
    }

    @Test
    public void findArticleByIdIncorrect() {
        Article articleById = articleDao.findArticleById(null);
        assertNull(articleById);
    }


}

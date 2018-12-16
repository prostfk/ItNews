package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ArticleDaoMockTest {

    @Mock
    private ArticleDao articleDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByIdShouldBeOk() {
        when(articleDao.findArticleById(1L))
                .thenReturn(new Article(1L,"","","",""));
        assertEquals(new Article(1L,"","","",""),
                articleDao.findArticleById(1L));
    }

    @Test
    public void testFindByTypeShouldBeOk() {
        when(articleDao.findArticlesByType("programming"))
                .thenReturn(Collections.singletonList(new Article(0L,"","","programming","")));
        articleDao.findArticlesByType("programming").forEach(article -> {
            assertEquals("programming", article.getType());
        });
    }

    @Test
    public void testFindAllShouldReturnList() {
        when(articleDao.findAll()).thenReturn(new LinkedList<>());
        assertEquals(new LinkedList<>(),articleDao.findAll());
    }

    @Test
    public void testFindTenLatestNewsShouldReturnList() {
        when(articleDao.findTenLatestNews())
                .thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), articleDao.findTenLatestNews());
    }

    @Test
    public void testFindOfferedByIdShouldReturnArticle() {
        when(articleDao.findOfferedArticleById(1L))
                .thenReturn(new Article(1L,"","","",""));
        assertEquals(new Article(1L,"","","",""),articleDao.findOfferedArticleById(1L));

    }
}

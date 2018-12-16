package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UserDaoMockTest {

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUserByIdShouldBeCorrect() {
        when(userDao.findUserById(1L))
                .thenReturn(new User(1L,"",""));
        assertEquals(new User(1L,"",""),userDao.findUserById(1L));
    }

    @Test
    public void testFindUserByUsernameShouldReturnUser() {
        when(userDao.findUserByUsername("Roman"))
                .thenReturn(new User(1L,"Roman","zxccxz"));
        assertEquals(new User(1L,"Roman","zxccxz"), userDao.findUserByUsername("Roman"));
    }

    @Test
    public void testValidateUserShouldBeTrue() {
        when(userDao.validateUser(new User(1L,"Roman","zxccxz")))
                .thenReturn(true);
        assertTrue(userDao.validateUser(new User(1L,"Roman","zxccxz")));
    }

    @Test
    public void testValidateUserShouldBeFalse() {
        when(userDao.validateUser(new User(1L,"Volodya","zxccxz")))
                .thenReturn(false);
        assertFalse(userDao.validateUser(new User(1L,"Volodya","zxccxz")));
    }

    @Test
    public void testFindAllShouldReturnList() {
        when(userDao.findAll())
                .thenReturn(new LinkedList<>());
        assertEquals(new LinkedList<>(), userDao.findAll());
    }
}

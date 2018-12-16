package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.MessageDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MessageDaoMockTest {

    @Mock
    private MessageDao messageDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindMessagesBySenderIdShouldReturnList() {
        when(messageDao.findMessagesBySenderId(1L))
                .thenReturn(new LinkedList<>());
        assertEquals(new LinkedList<>(), messageDao.findMessagesBySenderId(1L));
    }

    @Test
    public void testFindConversationsShouldReturnList() {
        when(messageDao.findConversations(1L))
                .thenReturn(new LinkedList<>());
        assertEquals(new LinkedList<>(), messageDao.findConversations(1L));
    }

    @Test
    public void testFindMessagesBySenderAndReceiverShouldReturnList() {
        when(messageDao.findMessagesBySenderAndReceiver(1L,2L))
                .thenReturn(new LinkedList<>());
        assertEquals(new LinkedList<>(), messageDao.findMessagesBySenderAndReceiver(1L,2L));
    }
}

package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Sunner on 7/26/16.
 */
public class SMSReceiverTest {

    @Test
    public void testGet12306Parser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("12306", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNotNull(parser);
        assertTrue(parser.isValid());
        assertEquals("D2245次14车13F号", parser.getTitle());
        assertEquals("南京南站", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), parser.getBeginTime());
    }

    @Test
    public void testGet1558132322Parser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("15558132322", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNotNull(parser);
        assertTrue(parser.isValid());
        assertEquals("D2245次14车13F号", parser.getTitle());
        assertEquals("南京南站", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), parser.getBeginTime());
    }

    @Test
    public void testGetNonexistParser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("119", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNull(parser);
    }

}
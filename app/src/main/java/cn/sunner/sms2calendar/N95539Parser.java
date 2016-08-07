package cn.sunner.sms2calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sunner on 7/31/16.
 *
 * Parse SMS from China Southern Airlines
 */
public class N95539Parser extends SMSParser {

    public N95539Parser(String text) {
        super(text);
    }

    @Override
    protected boolean parse() {
        String pattern = ".*。(\\d+)月(\\d+)日 ([A-Z0-9]+)航班，(.+\\))(\\d+):(\\d+)-.+\\)(\\d+):(\\d+).*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return false;
        }

        title = "南方航空" + m.group(3);
        location = m.group(4);
        beginTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(1)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(2)),   // Day
                Integer.parseInt(m.group(5)),   // Hour
                Integer.parseInt(m.group(6))    // Minute
        );
        endTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(1)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(2)),   // Day
                Integer.parseInt(m.group(7)),   // Hour
                Integer.parseInt(m.group(8))    // Minute
        );

        return true;
    }
}

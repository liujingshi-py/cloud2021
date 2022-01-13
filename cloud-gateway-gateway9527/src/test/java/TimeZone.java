import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

/**
 * @author liujs
 * @version 1.0
 * @date 2022/1/5 0005 19:42
 */

@SpringBootTest
public class TimeZone {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}

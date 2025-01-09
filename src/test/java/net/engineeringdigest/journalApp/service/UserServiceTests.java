package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "nazir",
           "vipul",
           "jian",
           "doremon"
    })
//    @CsvSource({
//            "nazir",
//            "vipul",
//            "jian",
//            "doremon"
//    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name));

    }
    //    @Disabled
//    @Test
//    public void testFindByUserName(){
//        assertEquals(4,2+2);
//        assertNotNull(userRepository.findByUserName("nazir"));
//        assertTrue(5>3);
//        User user = userRepository.findByUserName("nazir");
//        assertTrue(!user.getJournalEntries().isEmpty());
//    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}

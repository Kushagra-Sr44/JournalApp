package com.webapplication.journalapp.service;

import com.webapplication.journalapp.repository.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
//    @Autowired
//    private UserEntryRepository userEntryRepository;
//
//
//    @ParameterizedTest
//        @ArgumentsSource(strings = {
//            "ram","shyam","vipul"
//    })
//    public void testfindbyusername(String username) {
////        assertEquals(4, 2 + 2);
//        assertNotNull(userEntryRepository.findByUserName(username),"falied for name"+username);
////        assertTrue(5>3);
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "4,2,2", "4,3,1"
//    })
//    public void test(int expected, int a, int b) {
//        assertEquals(expected, a + b);
//    }
}

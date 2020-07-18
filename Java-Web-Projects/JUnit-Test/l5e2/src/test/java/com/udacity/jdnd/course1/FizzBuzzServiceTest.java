package com.udacity.jdnd.course1;

import com.udacity.jdnd.course1.service.FizzBuzzService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FizzBuzzServiceTest {

	@Test
	void testFizzBuzz(){
		FizzBuzzService fbs = new FizzBuzzService();

		// check non-divisible numbers return themselves
		assertEquals("1", fbs.fizzBuzz(1));
		assertEquals("47", fbs.fizzBuzz(47));
		assertEquals("121", fbs.fizzBuzz(121));

		// check numbers divisible by 3
		assertEquals("Fizz", fbs.fizzBuzz(3));
		assertEquals("Fizz", fbs.fizzBuzz(333));

		//check numbers divisible by 5
		assertEquals("Buzz", fbs.fizzBuzz(5));
		assertEquals("Buzz", fbs.fizzBuzz(85));

		//check numbers divisible by 3 and 5
		assertEquals("FizzBuzz", fbs.fizzBuzz(15));
		assertEquals("FizzBuzz", fbs.fizzBuzz(75));

		//check invalid inputs
		assertThrows(IllegalArgumentException.class, () -> fbs.fizzBuzz(0));
		assertThrows(IllegalArgumentException.class, () -> fbs.fizzBuzz(-1));
	}

	@Test
	void testBuzzFizz(){
		FizzBuzzService fbs = new FizzBuzzService();

		//Check Fizz
		assertEquals(9, fbs.buzzFizz("Fizz", 3));
		//Check Buzz
		assertEquals(25, fbs.buzzFizz("Buzz", 5));
		//Check Both Fizz && Buzz
		assertEquals(75, fbs.buzzFizz("FizzBuzz", 5));

		//Check out of bound value
		assertThrows(IllegalArgumentException.class, ()->fbs.buzzFizz("FizzBuzz1", 5));
		//assertThrows(IllegalArgumentException.class, ()->fbs.buzzFizz("5", 5));



	}
}

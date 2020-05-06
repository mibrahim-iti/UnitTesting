package com.mibrahim.airport.database;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.mibrahim.airport.extention.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DatabaseAccessTest {
	@Mock
	private Database database;

	private Credentials credentials = new Credentials("user", "password");

	@Test
	void testUserSuccessfulLogin() {
		when(database.login(credentials)).thenReturn(true);
		Credentials sameCredentials = new Credentials("user", "password");
		assertTrue(database.login(sameCredentials));
	}

	@Test
	void testUserFailedLogin() {
		when(database.login(credentials)).thenReturn(true);
		Credentials otherCredentials = new Credentials("user", "password");
		otherCredentials.setUsername("otherUser");
		otherCredentials.setPassword("otherPassword");
		assertNotEquals(credentials.getUsername(), otherCredentials.getUsername());
		assertNotEquals(credentials.getPassword(), otherCredentials.getPassword());
		assertNotEquals(credentials.hashCode(), otherCredentials.hashCode());
		assertFalse(database.login(otherCredentials));
	}
}

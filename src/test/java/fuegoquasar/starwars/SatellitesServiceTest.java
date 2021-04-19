package fuegoquasar.starwars;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.services.SatellitesService;

@RunWith(SpringRunner.class)
@SpringBootTest
class StarwarsApplicationTests {

	@InjectMocks
	private SatellitesService service;

	@Test
	public void getMessageTestDefault() throws Exception {
		String[] kenobi_m = new String[] {"", "este", "es", "un", "mensaje"};
		String[] skywalker_m = new String[] {"este", "", "un", "mensaje"};
		String[] sato_m = new String[] {"", "", "es", "", "mensaje"};
		String expectedMessage = "este es un mensaje";
		String actualMessage = service.getMessage(kenobi_m, skywalker_m, sato_m);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void getMessageTestAltered() throws Exception {
		String[] kenobi_m = new String[] {"", "", "", "este", "", "un", ""};
		String[] skywalker_m = new String[] {"", "este", "", "un", "mensaje"};
		String[] sato_m = new String[] {"","","", "", "es", "", "mensaje"};
		String expectedMessage = "este es un mensaje";
		String actualMessage = service.getMessage(kenobi_m, skywalker_m, sato_m);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void getMessageTestFullMessage() throws Exception {
		String[] kenobi_m = new String[] {"este", "es", "un", "mensaje"};
		String[] skywalker_m = new String[] {"este", "es", "un", "mensaje"};
		String[] sato_m = new String[] {"este", "es", "un", "mensaje"};
		String expectedMessage = "este es un mensaje";
		String actualMessage = service.getMessage(kenobi_m, skywalker_m, sato_m);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void getLocationTest() throws Exception {
		float kenobi_d = 721.1102f;
		float skywalker_d = 300.0f;
		float sato_d = 412.31058f;
		Position expectedPosition = new Position(100, 200);
		Position actualPosition = service.getLocation(kenobi_d, skywalker_d, sato_d);
		assertEquals(expectedPosition.getX(), actualPosition.getX());
		assertEquals(expectedPosition.getY(), actualPosition.getY());
	}
}
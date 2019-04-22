import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ClientStorageTest {
    private static ClientStorage clientStorage;
    private Client newClient;

    @Before
    public void setUp() {
        newClient = new Client("Dima", "123");
        clientStorage.addUniqueClient(newClient);
    }

    @Test
    public void addUniqueClient() {
        boolean actual = ClientStorage.addUniqueClient(new Client ("Dima", "123"));
        Assert.assertEquals(false, actual);
        Client uniqueClient = new Client("Egor", "123123");
        actual = ClientStorage.addUniqueClient(uniqueClient);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void checkClient() {
        boolean actual = ClientStorage.checkClient(new Client ("Dima", "223"));
        Assert.assertEquals(false, actual);
        actual = ClientStorage.checkClient(newClient);
        Assert.assertEquals(true, actual);
    }
}
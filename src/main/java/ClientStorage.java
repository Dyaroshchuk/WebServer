import java.util.ArrayList;
import java.util.List;

public class ClientStorage {

    public static final List<Client> CLIENTS = new ArrayList<>();

    // return true if client added and false if client already in list
    public static boolean addUniqueClient(Client newClient) {
        boolean check = CLIENTS.stream().anyMatch(o1 -> o1.getLogin().equals(newClient.getLogin()));
        if (check) {
            return false;
        } else {
            CLIENTS.add(newClient);
            return true;
        }
    }

    public static boolean checkClient(Client newClient) {
        return CLIENTS.stream().anyMatch(o1 -> o1.equals(newClient));
    }
}

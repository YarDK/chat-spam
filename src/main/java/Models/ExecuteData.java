package Models;

import java.util.HashSet;
import java.util.Set;

public class ExecuteData {

    private Set<String> vcards;
    private Set<String> personal;
    private Set<String> confrooms;
    private Set<String> devices;

    public Set<String> getVcards() {
        return vcards;
    }

    public Set<String> getPersonal() {
        return personal;
    }

    public Set<String> getConfrooms() {
        return confrooms;
    }

    public Set<String> getDevices() {
        return devices;
    }


    public ExecuteData withVcards(Set<String> vcards) {
        this.vcards = new HashSet<>(vcards);
        return this;
    }

    public ExecuteData withPersonal(Set<String> personal) {
        this.personal = new HashSet<>(personal);
        return this;
    }

    public ExecuteData withConfrooms(Set<String> confrooms) {
        this.confrooms = new HashSet<>(confrooms);
        return this;
    }

    public ExecuteData withDevices(Set<String> devices) {
        this.devices = new HashSet<>(devices);
        return this;
    }
}

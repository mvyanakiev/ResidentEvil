package residentevil.domain.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VirusAddBindingModel {

    private String name;

    public VirusAddBindingModel() {
    }

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name!")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package residentevil.service;

import residentevil.domain.models.serviceModel.VirusServiceModel;

import java.util.List;

public interface VirusService {

    VirusServiceModel addVirus(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> finAllViruses();

}

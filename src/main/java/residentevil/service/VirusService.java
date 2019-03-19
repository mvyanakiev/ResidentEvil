package residentevil.service;

import residentevil.domain.models.serviceModel.VirusServiceModel;

import java.util.List;

public interface VirusService {

    VirusServiceModel addVirus(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> finAllViruses();

    void deleteVirusById(String id);

    VirusServiceModel findVirusById(String id);

    void saveEditedVirus(VirusServiceModel virusServiceModel);



}

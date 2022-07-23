package com.safetynet.alerts.repository;

import com.safetynet.alerts.models.FireStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepository implements IFireStationRepository {
    private static final Logger logger = LogManager.getLogger("FireStationRepository");
    private List<FireStation> fireStationList = new ArrayList<>();

    @Override
    public List<FireStation> getList() {
        return fireStationList;
    }

    @Override
    public List<String> getAddress(int nbStation) {
        List<String> stringList = new ArrayList<>();
        for (FireStation f : fireStationList) {
            if (f.getStation() == nbStation) {
                logger.debug("This address "+f.getAddress()+" is add to the address list");
                stringList.add(f.getAddress());
            }
        }
        return stringList;
    }

    @Override
    public int getStationNumber(String address) {
        for (FireStation f : fireStationList) {
            if (address.equals(f.getAddress())) {
                return f.getStation();
            }
        }
        logger.debug("this address ("+address+") is not associated with any station");
        return 0;
    }

    @Override
    public boolean saveListOfFireStation(List<FireStation> listOfFireStation) {
        for (FireStation f : listOfFireStation) {
            if (f.getStation()==0 || f.getAddress().isEmpty()) {
                logger.debug("the list contains empty or zero values");
                return false;
            }
        }
        fireStationList.addAll(listOfFireStation);
        return true;
    }

    @Override
    public boolean updateFireStation(FireStation fireStation) {
        for (int i = 0; i < fireStationList.size(); i++) {
            FireStation f = fireStationList.get(i);
            if (f.getAddress().equals(fireStation.getAddress())) {
                fireStationList.set(i, fireStation);
                return true;
            }
        }
        logger.debug("The fire station is unknown");
        return false;
    }


    @Override
    public boolean removeFireStation(Integer station) {
        List<FireStation> fList = new ArrayList<>();
        fList.addAll(fireStationList);
        boolean isRemove = false;
        for (FireStation f : fireStationList) {
            if (f.getStation() == station) {
                fList.remove(f);
                logger.debug("Station is remove");
                isRemove = true;

            }
        }
        fireStationList = fList;
        logger.debug("The fire station list has been updated");
        return isRemove;
    }
}

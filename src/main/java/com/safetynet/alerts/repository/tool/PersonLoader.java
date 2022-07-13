package com.safetynet.alerts.repository.tool;

import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.repository.IPersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonLoader implements IPersonLoader {
    private static final Logger logger = LogManager.getLogger("PersonLoader");

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void load(JSONObject jsonObject) {
        JSONArray personArray = (JSONArray) jsonObject.get("persons");
        for (Object o : personArray) {
            JSONObject personJSON = (JSONObject) o;
            Person person = new Person();
            person.setFirstName((String) personJSON.get("firstName"));
            person.setLastName((String) personJSON.get("lastName"));
            person.setAddress((String) personJSON.get("address"));
            person.setCity((String) personJSON.get("city"));
            person.setZip((String) personJSON.get("zip"));
            person.setPhone((String) personJSON.get("phone"));
            person.setEmail((String) personJSON.get("email"));
            personRepository.getPersonList().add(person);
        }
    }
}

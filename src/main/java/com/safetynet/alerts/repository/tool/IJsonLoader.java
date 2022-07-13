package com.safetynet.alerts.repository.tool;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface IJsonLoader {

    JSONObject load(String filePath) throws ParseException;
}

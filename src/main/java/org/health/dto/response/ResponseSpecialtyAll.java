package org.health.dto.response;

import org.health.entity.Specialty;

import java.util.ArrayList;
import java.util.List;

public class ResponseSpecialtyAll {
    private ArrayList<ResponseSpecialtyAllResponse> responses = new ArrayList<>();

    public ResponseSpecialtyAll(List<Specialty> specialties) {
        if (specialties != null)
            specialties.iterator().forEachRemaining(specialty -> responses.add(new ResponseSpecialtyAllResponse(specialty)));
    }

    public ArrayList<ResponseSpecialtyAllResponse> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<ResponseSpecialtyAllResponse> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "ResponseSpecialtyAll{" +
                "responses=" + responses +
                '}';
    }
}

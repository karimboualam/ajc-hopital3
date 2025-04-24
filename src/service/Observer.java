package service;

import model.Patient;

public interface Observer {
    void update(Patient patient);
}

package fs3.dal;

import fs3.be.*;

import java.util.List;

public interface IDAOFacade {
    void updateCitizen(Citizen citizen) throws Exception;

    User readUser(String username, String password) throws Exception;

    List<CitizenInstance> readAllCitizenInstances() throws Exception;

    List<CitizenTemplate> readAllCitizenTemplates() throws Exception;

    Citizen createCitizen(Citizen citizen) throws Exception;

    void deleteCitizen(Citizen citizen) throws Exception;

    List<Student> readAllStudents() throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

    List<Teacher> readAllTeachers() throws Exception;

    List<Admin> readAllAdmins() throws Exception;

    User createUser(User user) throws Exception;

    List<School> readAllSchools() throws Exception;

    void updateSchool(School school) throws Exception;

    void deleteSchool(School school) throws Exception;

    School createSchool(School school) throws Exception;

    List<CitizenInstance> readAllCitizenInstancesFrom(School school) throws Exception;

    List<CitizenTemplate> readAllCitizenTemplatesFrom(School school) throws Exception;

    List<Student> readAllStudentsFrom(School school) throws Exception;
}

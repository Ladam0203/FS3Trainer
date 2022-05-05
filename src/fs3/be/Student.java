package fs3.be;

public class Student extends  User {
    public Student(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public Student(int id, String username, String password){
        setId(id);
        setUsername(password);
        setPassword(password);
    }

    @Override
    public String toString(){return this.getUsername();}
}

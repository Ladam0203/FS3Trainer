package fs3.be;

public class Student extends  User {
    public Student(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString(){return this.getUsername();}
}

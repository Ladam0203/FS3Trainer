package fs3.enums;

public enum UserRole {
    STUDENT(3), TEACHER(2), ADMIN(1);

    private final int id;

    UserRole(int roleId) {
        this.id = roleId;
    }

    public int getId() {
        return id;
    }
}

package chess;

public class Member {

    private String id;
    private String name;
    private Role role;

    public Member(final String id, final String name) {
        this(id, name, null);
    }

    public Member(final String id, final String name, final Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}

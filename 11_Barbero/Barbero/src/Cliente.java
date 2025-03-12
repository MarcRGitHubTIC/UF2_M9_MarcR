public class Cliente {
    private int id;
    private String name;

    public Cliente(int id) {
        this.id = id;
        this.name = "Cliente - " + id;
    }

    public void cortarPelo() {
        System.out.println(name + " esta cortandose el pelo.");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

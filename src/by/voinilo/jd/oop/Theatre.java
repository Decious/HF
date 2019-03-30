package by.voinilo.jd.oop;

public class Theatre extends MunicipalBuilding{

    @Override
    public void fire() {
        super.fire();
    }

    @Override
    public boolean isOpen() {
        return super.isOpen();
    }
   private String name;
    private String title;

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Opera theatre", "Great Fire");

        theatre.construct();
        theatre.fire();
        theatre.isOpen();
        theatre.clean();
        System.out.println(theatre.getName()+" "+ theatre.getTitle());
    }
private Theatre(String name, String title){
        this.name = name;
        this.title = title;
}

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return title;
    }
}

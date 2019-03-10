package seabattle;

import java.util.ArrayList;

class DotCom {
    private ArrayList<String> locationCells;
    private String name;
    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }
    public void setName(String n) { name = n;}

    public String checkYourself(String userInput) {
        String result = "YOU_MISSED! D:";
       int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "GotONE!";
                System.out.println("вы избавили мир от " + name);
            }

            else {
                result = "GOTaHIT!";
            }

        }

        return result;
    }
}


package main;

public class Score {

    private int amountOfFided;
    private int needToFind = 10;

    public Boolean isAllFinded() {
        return amountOfFided == needToFind;
    }

    public void findMine() {
        amountOfFided++;
    }

}

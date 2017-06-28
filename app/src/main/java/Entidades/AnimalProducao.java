package Entidades;

/**
 * Created by Assis on 27/06/2017.
 */

public class AnimalProducao {
    boolean ShowName;
    private String animalId;
    private String animalNome;
    private String animalNumero;
    private int animalPL;

    public AnimalProducao() {
    }

    public boolean isShowName() {
        return ShowName;
    }

    public void setShowName(boolean showName) {
        ShowName = showName;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAnimalNome() {
        return animalNome;
    }

    public void setAnimalNome(String animalNome) {
        this.animalNome = animalNome;
    }

    public String getAnimalNumero() {
        return animalNumero;
    }

    public void setAnimalNumero(String animalNumero) {
        this.animalNumero = animalNumero;
    }

    public double getAnimalPL() {
        return animalPL;
    }

    public void setAnimalPL(int animalPL) {
        this.animalPL = animalPL;
    }
}

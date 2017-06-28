package Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import DAO.ConfiguracaoFirebase;

/**
 * Created by Assis on 13/06/2017.
 */

public class Animal {
    private String animalId;
    private String animalNome;
    private String animalNumero;
    private String animalDataNascimento;
    private String animalRaca;
    private String animalSexo;

    public Animal() {

    }

    public Animal(String animalId, String animalNome, String animalNumero, String animalDataNascimento, String animalRaca, String animalSexo) {
        this.animalId = animalId;
        this.animalNome = animalNome;
        this.animalNumero = animalNumero;
        this.animalDataNascimento = animalDataNascimento;
        this.animalRaca = animalRaca;
        this.animalSexo = animalSexo;
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

    public String getAnimalDataNascimento() {
        return animalDataNascimento;
    }

    public void setAnimalDataNascimento(String animalDataNascimento) {
        this.animalDataNascimento = animalDataNascimento;
    }

    public String getAnimalRaca() {
        return animalRaca;
    }

    public void setAnimalRaca(String animalRaca) {
        this.animalRaca = animalRaca;
    }

    public String getAnimalSexo() {
        return animalSexo;
    }

    public void setAnimalSexo(String animalSexo) {
        this.animalSexo = animalSexo;
    }
}
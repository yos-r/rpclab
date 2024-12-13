package main.java.impl;

import java.util.ArrayList;
import java.util.List;

public class GestionNotes {
    private List<Double> notes;

    public GestionNotes() {
        this.notes = new ArrayList<>();
    }

    public void ajouterNote(double note) {
        notes.add(note);
    }

    public boolean supprimerNote(double note) {
        return notes.remove(note);
    }

    public boolean modifierNote(double ancienneNote, double nouvelleNote) {
        int index = notes.indexOf(ancienneNote);
        if (index != -1) {
            notes.set(index, nouvelleNote);
            return true;
        }
        return false;
    }

    public List<Double> listerNotes() {
        return new ArrayList<>(notes);
    }
}
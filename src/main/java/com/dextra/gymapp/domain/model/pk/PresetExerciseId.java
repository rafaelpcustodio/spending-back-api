package com.dextra.gymapp.domain.model.pk;

import com.dextra.gymapp.domain.model.Exercise;
import com.dextra.gymapp.domain.model.Preset;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PresetExerciseId {

    @ManyToOne
    private Preset preset;

    @ManyToOne
    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Preset getPreset() {
        return preset;
    }

    public void setPreset(Preset preset) {
        this.preset = preset;
    }
}
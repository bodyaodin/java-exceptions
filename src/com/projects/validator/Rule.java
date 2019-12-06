package com.projects.validator;

import com.projects.exceptions.DataException;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Rule<T> {

    private Predicate<T> predicate;
    private Supplier<DataException> supplier;

    public Rule(Predicate<T> predicate, Supplier<DataException> supplier) {
        this.predicate = predicate;
        this.supplier = supplier;
    }

    public void validate (T value) {
        if (predicate.test(value)) {
           supplier.get();
        }
    }
}

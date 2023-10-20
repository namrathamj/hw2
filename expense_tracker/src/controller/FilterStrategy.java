package controller;
import java.util.ArrayList;
import model.Transaction;
import java.util.List;

public interface FilterStrategy {
    ArrayList<Integer> filter(List<Transaction> transactions);
}


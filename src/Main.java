import services.BankingInteractor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankingInteractor bankingInteractor = new BankingInteractor();
        bankingInteractor.run(scanner);

        scanner.close();
    }
}
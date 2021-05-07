package models.transfer;

import java.util.Comparator;

public class TransferComparator implements Comparator<Transfer> {

    @Override
    public int compare(Transfer o1, Transfer o2) {
        if (o1.getAmount().equals(o2.getAmount())) {
            if (o1.getDate().equals(o2.getDate())) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
            return o1.getDate().compareTo(o2.getDate());
        }
        return o1.getAmount().compareTo(o2.getAmount());
    }
}

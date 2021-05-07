package models.transfer;

import java.util.Comparator;

public class TransferComparator implements Comparator<Transfer> {

    @Override
    public int compare(Transfer o1, Transfer o2) {
        return o1.getAmount().compareTo(o2.getAmount());
    }
}

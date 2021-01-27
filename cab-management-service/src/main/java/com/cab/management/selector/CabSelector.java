package com.cab.management.selector;

import com.cab.management.model.BookingDetail;
import com.cab.management.model.CabSnapshot;
import java.util.List;

public interface CabSelector {
    public List<CabSnapshot> select(BookingDetail bookingDetail);
}

package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter ve toStringi hazırlar
@NoArgsConstructor//parametresiz consructer hazırlar
@AllArgsConstructor//butun argumanları variable içeren parametreli constructorı hazırlar

public class PojoHerokuappBookingDates {
    /*
    {
       "checkin":"2021-06-01",
       "checkout":"2021-06-10"
     }
     */
    private String checkin;
    private String checkout;
}

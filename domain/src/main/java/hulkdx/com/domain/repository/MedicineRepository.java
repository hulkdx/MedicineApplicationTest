package hulkdx.com.domain.repository;

import hulkdx.com.domain.models.MedicineCollection;
import io.reactivex.Single;

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
public interface MedicineRepository {
    Single<MedicineCollection> getMedicine();
}

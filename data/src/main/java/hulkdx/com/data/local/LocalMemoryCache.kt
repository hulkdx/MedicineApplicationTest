package hulkdx.com.data.local

import hulkdx.com.domain.models.MedicineCollection
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMemoryCache @Inject constructor() {

    private val lock = ReentrantLock()

    var medicineCollection: MedicineCollection? = null
        get() {
            try {
                lock.lock()
                return field
            } finally {
                lock.unlock()
            }
        }
        set(value) {
            try {
                lock.lock()
                field = value
            } finally {
                lock.unlock()
            }
        }

}
